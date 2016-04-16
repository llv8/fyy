package com.fy.fyy.back.action.imp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fy.fyy.back.action.ActionAnnotation;
import com.fy.fyy.back.action.ActionModel;
import com.fy.fyy.back.action.BaseAction;
import com.fy.fyy.back.action.RedirectAnnotation;
import com.fy.fyy.back.bean.BaseBean;
import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.bean.CustomerRole;
import com.fy.fyy.back.bean.Employee;
import com.fy.fyy.back.bean.Role;
import com.fy.fyy.back.bean.RolePermission;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.ContextUtil;
import com.fy.fyy.back.common.Log;
import com.fy.fyy.back.common.PermissionUtil;
import com.fy.fyy.back.permission.ModelNode;
import com.fy.fyy.back.service.BaseService;
import com.fy.fyy.back.service.CustomerRoleService;
import com.fy.fyy.back.service.CustomerService;
import com.fy.fyy.back.service.EmployeeService;
import com.fy.fyy.back.service.RolePermissionService;
import com.fy.fyy.back.service.RoleService;


@ActionAnnotation(name = ActionModel.Customer)
public class CustomerAction extends BaseAction<Customer> {

  private static Log logger = Log.getInstance( CustomerAction.class );
  private static final String PASSWORD = "fyy888";
  private CustomerService customerService = new CustomerService();
  private EmployeeService employeeService = new EmployeeService();
  private RoleService roleService = new RoleService();
  private RolePermissionService rolePermissionService = new RolePermissionService();
  private CustomerRoleService customerRoleService = new CustomerRoleService();

  public CustomerAction() {
    bean = new Customer();
  }

  @RedirectAnnotation
  public String login() {
    Customer customer = customerService.login( bean );
    if ( customer == null ) {
      error( "用户名或者密码错误" );
      logger.error( "user name or password is invalid -- {},{}", bean.getName(), bean.getPassword() );
      return Constraint.LOGIN_UI;
    }
    else {
      ContextUtil.getSessionAttrs().put( Constraint.LOGIN_USER, customer );
      if ( !bean.getName().equals( Constraint.ADMIN ) ) {
        CustomerRole cr = new CustomerRole();
        cr.setCustomerId( customer.getId() );
        List<CustomerRole> customerRoleList = customerRoleService.getBeanByCustomerId( cr );
        if ( !CollectionUtils.isEmpty( customerRoleList ) ) {
          Integer roleId = customerRoleList.get( 0 ).getRoleId();
          RolePermission rp = new RolePermission();
          rp.setRoleId( roleId );
          List<ModelNode> mnList = rolePermissionService.getRolePermList( rp );
          List<ModelNode> menuList = new ArrayList<>();
          Map<String, ModelNode> mnMap = new HashMap<>();

          for ( ModelNode modelNode : mnList ) {
            mnMap.put( modelNode.getId().toString(), modelNode );
            if ( StringUtils.isEmpty( modelNode.getParent() ) ) {
              menuList.add( modelNode );
            }
          }
          ContextUtil.getSessionAttrs().put( Constraint.LOGIN_PERM, mnMap );
          ContextUtil.getSessionAttrs().put( Constraint.LOGIN_PERM_ROOT, menuList );
        }
      }
      else {
        ContextUtil.getSessionAttrs().put( Constraint.LOGIN_PERM_ROOT, PermissionUtil.getTopModelNodeCopyList() );
      }

      return Constraint.INDEX_UI;
    }
  }

  @RedirectAnnotation
  public String unLogin() {
    ContextUtil.getSessionAttrs().put( Constraint.LOGIN_USER, null );
    return Constraint.LOGIN_UI;
  }

  public String loginUI() {
    return "/login.jsp";
  }

  public String indexUI() {
    return "/index.jsp";
  }

  @ActionAnnotation(name = ActionModel.CustomerQuery)
  public String list() {
    List<Customer> customerList = customerService.list( bean );
    ContextUtil.getReqAttrs().put( "beanList", customerList );
    ContextUtil.getReqAttrs().put( "bean", bean );
    return "/customerlist.jsp";
  }

  @ActionAnnotation(name = ActionModel.CustomerAdd)
  public String addUI() {
    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "employeelist", employeeService.list( new Employee() ) );
    return "/addcustomer.jsp";
  }

  public String add() {

    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    bean.setCreateDate( now );
    bean.setUpdateDate( now );
    bean.setPassword( PASSWORD );
    customerService.insert( bean );
    return list();
  }

  @ActionAnnotation(name = ActionModel.CustomerUpdate)
  public String updateUI() {
    bean = BaseService.getBean( bean );
    ContextUtil.getReqAttrs().put( "bean", bean );
    ContextUtil.getReqAttrs().put( "employee", BaseService.getBean( BaseBean.getInstance( Employee.class, bean.getEmployeeId() ) ) );
    return "/addcustomer.jsp";
  }

  public String update() {
    Customer persistCustomer = BaseService.getBean( bean );
    persistCustomer.setName( bean.getName() );
    persistCustomer.setUpdateDate( new Date( Calendar.getInstance().getTimeInMillis() ) );
    customerService.update( persistCustomer );
    return list();
  }

  public String updatePassword() {
    Customer loginUser = (Customer)ContextUtil.getSessionAttr( Constraint.LOGIN_USER );
    if ( loginUser != null ) {
      ContextUtil.getReqAttrs().put( "bean", loginUser );
      return "/updatepassword.jsp";
    }
    else {
      return list();
    }
  }

  public String updatePasswordCommit() {
    Customer loginUser = (Customer)ContextUtil.getSessionAttr( Constraint.LOGIN_USER );
    Date now = new Date( Calendar.getInstance().getTimeInMillis() );
    String oldPassword = (String)ContextUtil.getReqParam( "oldpassword" );
    String newPassword = (String)ContextUtil.getReqParam( "newpassword" );
    String newPassword2 = (String)ContextUtil.getReqParam( "newpassword2" );
    ContextUtil.getReqAttrs().put( "oldpassword", oldPassword );
    ContextUtil.getReqAttrs().put( "newpassword", newPassword );
    ContextUtil.getReqAttrs().put( "newpassword2", newPassword2 );
    if ( !loginUser.getPassword().equals( oldPassword ) ) {
      error( "旧密码不正确" );
      return updatePassword();
    }

    if ( StringUtils.isEmpty( newPassword ) || !newPassword.equals( newPassword2 ) ) {
      error( "新密码为空或两次密码不一致" );
      return updatePassword();
    }

    if ( newPassword.length() < 6 ) {
      error( "密码必须大于等于六位" );
      return updatePassword();
    }

    loginUser.setUpdateDate( now );
    loginUser.setPassword( newPassword );
    customerService.update( loginUser );
    return "/welcome.jsp";
  }

  @ActionAnnotation(name = ActionModel.CustomerDel)
  public String del() {
    customerService.delete( bean );
    return list();
  }

  @ActionAnnotation(name = ActionModel.CustomerRoleAssign)
  public String roleAssign() {
    List<Role> roleList = roleService.list( new Role() );
    ContextUtil.getReqAttrs().put( "rolelist", roleList );
    ContextUtil.getReqAttrs().put( "bean", BaseService.getBean( bean ) );
    return "/roleassign.jsp";
  }

  public String roleAssignCfm() {
    String roleIdStr = ContextUtil.getReqParam( "roleId" ).toString();
    CustomerRole cr = new CustomerRole();
    cr.setCustomerId( bean.getId() );
    cr.setRoleId( Integer.valueOf( roleIdStr ) );
    customerRoleService.insert2Update( cr );
    ContextUtil.getReqAttrs().put( "roleId", roleIdStr );
    return list();
  }

  public String validAdd() {

    return validAdd2Update() ? null : addUI();
  }

  public String validUpdate() {

    if ( !validAdd2Update() ) {
      ContextUtil.getReqAttrs().put( "bean", bean );
      ContextUtil.getReqAttrs().put( "employeelist", employeeService.list( new Employee() ) );
      return "/addcustomer.jsp";
    }
    else {
      return null;
    }
  }

  public boolean validAdd2Update() {
    boolean result = true;
    String name = bean.getName();
    if ( StringUtils.isEmpty( name ) ) {
      error( "用户名不能为空" );
      logger.error( "username can not null" );
      result = false;
    }

    if ( !StringUtils.isEmpty( name ) && name.length() > Constraint.MAX_LEN ) {
      error( "用户名太长" );
      logger.error( "username'length is more than " + Constraint.MAX_LEN );
      result = false;
    }

    if ( !StringUtils.isEmpty( name ) && name.length() <= Constraint.MAX_LEN ) {
      List<Customer> list = null;
      if ( !bean.isId( bean.getId() ) ) {
        list = customerService.getBeanByName( bean );
      }
      else {
        list = customerService.getBeanByNameIgnonreSelf( bean );
      }
      if ( !CollectionUtils.isEmpty( list ) && list.size() > 0 ) {
        error( "该用户名已存在" );
        logger.error( bean.getName() + " username already exists" );
        result = false;
      }
    }

    return result;
  }

}
