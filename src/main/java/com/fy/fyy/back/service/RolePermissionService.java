package com.fy.fyy.back.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fy.fyy.back.bean.RolePermission;
import com.fy.fyy.back.common.PermissionUtil;
import com.fy.fyy.back.permission.ModelNode;


public class RolePermissionService extends BaseService<RolePermission> {

  private static Logger logger = LoggerFactory.getLogger( RolePermission.class );

  public List<RolePermission> getListByRoleId( RolePermission searchBean ) {
    List<RolePermission> list = getList( searchBean, new QuerySqlStr<RolePermission>() {

      @Override
      public String get( RolePermission searchBean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and roleId=?" );
        searchBean.getQueryParams().clear();
        searchBean.getQueryParams().add( searchBean.getRoleId() );
        return sb.toString();
      }

    } );
    return list;
  }

  public int insert2Update( RolePermission bean ) {
    //query
    List<RolePermission> list = getList( bean, new QuerySqlStr<RolePermission>() {

      @Override
      public String get( RolePermission searchBean ) {
        StringBuffer sb = new StringBuffer();
        sb.append( " and roleId=?" );
        sb.append( " and modelId=?" );
        searchBean.getQueryParams().clear();
        searchBean.getQueryParams().add( searchBean.getRoleId() );
        searchBean.getQueryParams().add( searchBean.getModelId() );
        return sb.toString();
      }
    } );

    if ( CollectionUtils.isEmpty( list ) ) {
      insert( bean );
    }
    else {
      RolePermission persistRolePermssion = list.get( 0 );
      persistRolePermssion.setIsPerm( bean.getIsPerm() );
      update( persistRolePermssion );
    }
    return 0;
  }

  public List<ModelNode> getRolePermList( RolePermission bean ) {
    bean.getPageInfo().setPageFlag( false );
    List<RolePermission> rolePermissionList = getListByRoleId( bean );
    Map<String, Boolean> modelPermMap = new HashMap<>();

    for ( RolePermission rolePermission : rolePermissionList ) {
      modelPermMap.put( rolePermission.getModelId().toString(), rolePermission.getIsPerm() );
    }

    List<ModelNode> modelNodeList = PermissionUtil.getModelNodeCopyList();

    if ( modelPermMap != null && modelPermMap.size() > 0 ) {
      for ( ModelNode modelNode : modelNodeList ) {
        Boolean perm = (Boolean)modelPermMap.get( modelNode.getId() );
        if ( perm != null && perm == true ) {
          modelNode.getState().setSelected( true );
        }
        else {
          modelNode.getState().setSelected( false );
        }
      }
    }

    return modelNodeList;
  }

}
