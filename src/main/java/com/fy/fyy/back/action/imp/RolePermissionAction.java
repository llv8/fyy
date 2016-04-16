package com.fy.fyy.back.action.imp;

import java.util.List;

import com.fy.fyy.back.action.ActionAnnotation;
import com.fy.fyy.back.action.ActionModel;
import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.bean.BaseBean;
import com.fy.fyy.back.bean.Role;
import com.fy.fyy.back.bean.RolePermission;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.common.PermissionUtil;
import com.fy.fyy.back.permission.ModelNode;
import com.fy.fyy.back.service.BaseService;
import com.fy.fyy.back.service.RolePermissionService;


public class RolePermissionAction extends BaseAction<RolePermission> {

  private static Log logger = Log.getInstance( RolePermissionAction.class );
  private RolePermissionService rolePermissionService = new RolePermissionService();

  public RolePermissionAction() {
    bean = new RolePermission();
  }

  @ActionAnnotation(name = ActionModel.RolePermission)
  public String perm() {
    List<ModelNode> modelNodeList = rolePermissionService.getRolePermList( bean );

    ContextUtil.getReqAttrs().put( "permjson", PermissionUtil.getJson( modelNodeList ) );
    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "rolebean", BaseService.getBean( BaseBean.getInstance( Role.class, bean.getRoleId() ) ) );
    return "/addrolepermission.jsp";
  }

  public String updatePerm() {

    String permJson = ContextUtil.getReqParam( "permjson" ).toString();

    List<ModelNode> modelNodeList = PermissionUtil.getObjFromJson( permJson );

    for ( ModelNode modelNode : modelNodeList ) {
      RolePermission rolePermission = new RolePermission();
      rolePermission.setIsPerm( modelNode.getState().isSelected() );
      rolePermission.setModelId( Integer.valueOf( modelNode.getId() ) );
      rolePermission.setRoleId( bean.getRoleId() );
      rolePermissionService.insert2Update( rolePermission );
    }

    ContextUtil.getReqAttrs().put( "permjson", PermissionUtil.getJson( modelNodeList ) );
    ContextUtil.getReqAttrs().put( "bean", bean );
    String js = "$('div.info').html('设置成功');$('div.info').css('display','block');$('div.info').fadeOut(3000);";
    ContextUtil.getReqAttrs().put( "ajax_resp", js );
    return "/ajax_resp_container.jsp";
  }

}
