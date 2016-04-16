package com.fy.fyy.back.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.Role;


public class RoleService extends BaseService<Role> {

  private static Logger logger = LoggerFactory.getLogger( RoleService.class );

  public List<Role> list( Role searchBean ) {
    List<Role> list = getList( searchBean, new DefaultQuerySqlStr<Role>() );
    return list;
  }

}
