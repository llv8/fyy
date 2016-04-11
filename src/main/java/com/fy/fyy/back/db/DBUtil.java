package com.fy.fyy.back.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.fy.fyy.back.bean.BaseBean;
import com.fy.fyy.back.common.Log;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {

	private static Log logger = Log.getInstance(DBUtil.class);
	private static final String QUERY = "select * from %s where 1=1";
	private static final String QUERY_COUNT = "select count(0) from (%s) abc";
	private static ComboPooledDataSource dataSource;
	static {
		try {
			dataSource = new ComboPooledDataSource();
			Properties properties = new Properties();
			properties.load(DBUtil.class.getClassLoader().getResourceAsStream("/../conf/conf.properties"));
			dataSource.setUser(properties.getProperty("db_user"));
			dataSource.setPassword(properties.getProperty("db_password"));
			dataSource.setJdbcUrl(properties.getProperty("db_url"));
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setInitialPoolSize(10);
			dataSource.setMinPoolSize(5);
			dataSource.setMaxPoolSize(50);
			dataSource.setMaxStatements(100);
			dataSource.setMaxIdleTime(60);
			dataSource.setBreakAfterAcquireFailure(false);
			dataSource.setTestConnectionOnCheckout(false);
			dataSource.setTestConnectionOnCheckin(false);
			dataSource.setIdleConnectionTestPeriod(60);
			dataSource.setAcquireRetryAttempts(10);
			dataSource.setAcquireRetryDelay(1000);
		} catch (Exception e) {
			logger.error("data source init failure!", e);
		}
	}

	public static Connection getConn() {
		Connection conn = null;
		if (null != dataSource) {
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				logger.error("get connection failure!", e);
			}
		}
		return conn;
	}

	public static String getSQL(Class<?> clazz) {
		return String.format(QUERY, clazz.getSimpleName());
	}

	public static <T extends BaseBean> List<T> queryBeanList(String sql, T bean) {
		Connection conn = getConn();
		try {
			if (bean.getPageInfo().isPageFlag()) {
				Long count = new QueryRunner().query(conn, String.format(QUERY_COUNT, sql), new ScalarHandler<Long>(),
						bean.getQueryParams().toArray());
				bean.getPageInfo().setCountRecord(count.intValue());
			}
			if (bean.getPageInfo().isPageFlag()) {
				sql = sql + " limit  " + bean.getPageInfo().getPageSize() * (bean.getPageInfo().getCurrentPageNum() - 1)
						+ " , " + bean.getPageInfo().getPageSize() + " ";
			}
			logger.info(sql + "--params:" + ArrayUtils.toString(bean.getQueryParams().toArray()));
			return new QueryRunner().query(conn, sql, getBeanListHandler(bean), bean.getQueryParams().toArray());
		} catch (Exception e) {
			logger.error("query failure!", e);
			return null;
		} finally {
			bean.getQueryParams().clear();
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	private static <T> BeanListHandler<T> getBeanListHandler(T bean) {
		return new BeanListHandler<T>((Class<T>) bean.getClass());
	}

	public static int update(String sql, Object... objs) {
		Connection conn = getConn();
		try {
			logger.info(sql + "--params:" + ArrayUtils.toString(objs));
			return new QueryRunner().update(conn, sql, objs);
		} catch (Exception e) {
			logger.error("update failure!", e);
			return 0;
		} finally {

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}

		}

	}

	public static <T extends BaseBean> T getObjById(T bean) {
		String sql = getSQL(bean.getClass()) + " and id=?";
		bean.getQueryParams().add(bean.getId());
		List<T> list = queryBeanList(sql, bean);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}

	}

	public static <T extends BaseBean> int insert(T obj) {
		Pair<String, Object[]> pair = getInsertSql(obj);
		return update(pair.getLeft(), pair.getRight());
	}

	public static <T extends BaseBean> int delete(T obj) {
		String tableName = obj.getClass().getSimpleName();
		return update("delete from " + tableName + " where id = ?", obj.getId());
	}

	public static <T extends BaseBean> int update(T obj) {
		Pair<String, Object[]> pair = getUpdateSql(obj);
		return update(pair.getLeft(), pair.getRight());
	}

	private static <T extends BaseBean> Pair<String, Object[]> getInsertSql(T obj) {
		String tableName = obj.getClass().getSimpleName();
		Field[] fields = obj.getClass().getDeclaredFields();
		List<String> keys = new ArrayList<>();
		List<String> placeHolds = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		for (int i = 0; i < fields.length; i++) {
			if (isConvertField(fields[i].getType())) {
				String fieldName = fields[i].getName();
				String methodName = "get" + StringUtils.capitalize(fieldName);
				keys.add(fieldName);
				placeHolds.add("?");
				try {
					values.add(obj.getClass().getMethod(methodName).invoke(obj));
				} catch (Exception e) {
					logger.error("getInsertSql reflect method invoke error.");
				}

			}
		}
		String sql = String.format("insert into %s (%s) values (%s)", tableName, StringUtils.join(keys.toArray(), ","),
				StringUtils.join(placeHolds.toArray(), ","));
		Object[] objs = values.toArray();
		Pair<String, Object[]> pair = new ImmutablePair<String, Object[]>(sql, objs);
		return pair;
	}

	private static <T extends BaseBean> Pair<String, Object[]> getUpdateSql(T obj) {
		String tableName = obj.getClass().getSimpleName();
		Field[] fields = obj.getClass().getDeclaredFields();
		List<String> keys = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		for (int i = 0; i < fields.length; i++) {
			if (isConvertField(fields[i].getType())) {
				String fieldName = fields[i].getName();
				String methodName = "get" + StringUtils.capitalize(fieldName);
				keys.add(fieldName + "=?");
				try {
					values.add(obj.getClass().getMethod(methodName).invoke(obj));
				} catch (Exception e) {
					logger.error("getUpdateSql reflect method invoke error.");
				}

			}
		}
		values.add(obj.getId());

		String sql = String.format("update %s set %s where id=?", tableName, StringUtils.join(keys.toArray(), ","));
		Object[] objs = values.toArray();
		Pair<String, Object[]> pair = new ImmutablePair<String, Object[]>(sql, objs);
		return pair;
	}

	private static boolean isConvertField(Class<?> clazz) {
		return Long.class.equals(clazz) || Integer.class.equals(clazz) || Short.class.equals(clazz)
				|| Float.class.equals(clazz) || Double.class.equals(clazz) || Boolean.class.equals(clazz)
				|| String.class.equals(clazz) || Date.class.equals(clazz);
	}

}
