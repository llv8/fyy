package com.fy.fyy.back.action.imp;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fy.fyy.back.action.ActionAnnotation;
import com.fy.fyy.back.action.ActionModel;
import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.bean.Role;
import com.fy.fyy.back.bean.RolePermission;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.service.BaseService;
import com.fy.fyy.back.service.RolePermissionService;
import com.fy.fyy.back.service.RoleService;


@ActionAnnotation(name = ActionModel.Permission)
public class RoleAction extends BaseAction<Role> {

  private static Log logger = Log.getInstance( RoleAction.class );
  private RoleService roleService = new RoleService();

  public RoleAction() {
    bean = new Role();
  }

  @ActionAnnotation(name = ActionModel.RoleQuery)
  public String list() {
    List<Role> roleList = roleService.list( bean );
    ContextUtil.getReqAttrs().put( "beanList", roleList );
    ContextUtil.getReqAttrs().put( "bean", bean );
    return "/rolelist.jsp";
  }

  @ActionAnnotation(name = ActionModel.RoleAdd)
  public String addUI() {
    ContextUtil.getReqAttrs().put( "bean", bean );
    return "/addrole.jsp";
  }

  public String add() {
    roleService.insert( bean );
    return list();
  }

  @ActionAnnotation(name = ActionModel.RoleUpdate)
  public String updateUI() {
    bean = BaseService.getBean( bean );
    ContextUtil.getReqAttrs().put( "bean", bean );
    return "/addrole.jsp";
  }

  public String update() {
    Role persistRole = BaseService.getBean( bean );
    persistRole.setName( bean.getName() );
    roleService.update( persistRole );
    return list();
  }

  @ActionAnnotation(name = ActionModel.RoleDel)
  public String del() {
    roleService.delete( bean );
    return list();
  }

  public String validAdd() {
    return validAdd2Update() ? null : addUI();
  }

  public String validUpdate() {
    if ( !validAdd2Update() ) {
      ContextUtil.getReqAttrs().put( "bean", bean );
      return "/addrole.jsp";
    }
    else {
      return null;
    }
  }

  public boolean validAdd2Update() {
    boolean result = true;
    if ( StringUtils.isEmpty( bean.getName() ) ) {
      error( "角色名不能为空" );
      logger.error( "rolename can not null" );
      result = false;
    }

    if ( !StringUtils.isEmpty( bean.getName() ) && bean.getName().length() > Constraint.MAX_LEN ) {
      error( "角色名太长" );
      logger.error( "rolename'length is more than " + Constraint.MAX_LEN );
      result = false;
    }

    if ( !StringUtils.isEmpty( bean.getName() ) && bean.getName().length() <= Constraint.MAX_LEN ) {
      List<Role> list = null;
      if ( !bean.isId( bean.getId() ) ) {
        list = roleService.getBeanByName( bean );
      }
      else {
        list = roleService.getBeanByNameIgnonreSelf( bean );
      }
      if ( !CollectionUtils.isEmpty( list ) && list.size() > 0 ) {
        error( "该角色名已存在" );
        logger.error( bean.getName() + " rolename already exists" );
        result = false;
      }
    }
    return result;
  }

  public String validDel() {
    //TODO: how to deal with the foriegn key error
    RolePermissionService rolePermissionService = new RolePermissionService();
    RolePermission searchRoleBean = new RolePermission();
    searchRoleBean.setRoleId( bean.getId() );
    List<RolePermission> rolePermissionList = rolePermissionService.getListByRoleId( searchRoleBean );
    if ( !CollectionUtils.isEmpty( rolePermissionList ) ) {
      error( "请先删除与该角色有关的角色权限！" );
      return list();
    }
    return null;
  }

}
