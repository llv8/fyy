package com.fy.fyy.back.action;

public enum ActionModel {
  /**
   * 00101001解析，前三位001是类模块号，中间两位01是父子关系，后三位001是具体的方法模块
   * 
   */

  Material(101001, "物料管理", null),
  MaterialAdd(102001, ActionModel.ADD, 101001),
  MaterialUpdate(102002, ActionModel.UPDATE, 101001),
  MaterialDel(102003, ActionModel.DEL, 101001),
  MaterialQuery(102004, ActionModel.QUERY, 101001),

  Inventory(201001, "库存管理", null),
  InventoryAdd(202001, ActionModel.ADD, 201001),
  InventoryUpdate(202002, ActionModel.UPDATE, 201001),
  InventoryDel(202003, ActionModel.DEL, 201001),
  InventoryQuery(202004, ActionModel.QUERY, 201001),

  InventoryReport(301001, "库存统计", null),
  InventoryReportQuery(302001, "总统计", 301001),

  Employee(401001, "员工管理", null),
  EmployeeAdd(402001, ActionModel.ADD, 401001),
  EmployeeUpdate(402002, ActionModel.UPDATE, 401001),
  EmployeeDel(402003, ActionModel.DEL, 401001),
  EmployeeQuery(402004, ActionModel.QUERY, 401001),

  Permission(501001, "权限管理", null),
  RoleAdd(502001, ActionModel.ADD, 501001),
  RoleUpdate(502002, ActionModel.UPDATE, 501001),
  RoleDel(502003, ActionModel.DEL, 501001),
  RoleQuery(502004, ActionModel.QUERY, 501001),
  RolePermission(502005, ActionModel.ROLE_PERM, 501001),

  Customer(601001, "用户管理", null),
  CustomerAdd(602001, ActionModel.ADD, 601001),
  CustomerUpdate(602002, ActionModel.UPDATE, 601001),
  CustomerDel(602003, ActionModel.DEL, 601001),
  CustomerQuery(602004, ActionModel.QUERY, 601001),
  CustomerRoleAssign(602005, ActionModel.CUST_PERM, 601001);

  private static final String ADD = "新增";
  private static final String UPDATE = "编辑";
  private static final String DEL = "删除";
  private static final String QUERY = "查询";
  private static final String CUST_PERM = "用户权限";
  private static final String ROLE_PERM = "角色权限";

  private String name;
  private Integer id;
  private Integer parentId;

  private ActionModel( Integer id, String name, Integer parentId ) {
    this.name = name;
    this.id = id;
    this.parentId = parentId;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId( Integer id ) {
    this.id = id;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId( Integer parentId ) {
    this.parentId = parentId;
  }

}
