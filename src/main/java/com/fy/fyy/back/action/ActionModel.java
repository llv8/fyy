package com.fy.fyy.back.action;

public enum ActionModel {
  /**
   * 00101001解析，前三位001是类模块号，中间两位01是父子关系，后三位001是具体的方法模块
   * 
   */

  Customer(00101001, "用户管理"),
  CustomerAdd(00102002, ActionModel.ADD),
  CustomerUpdate(00102003, ActionModel.UPDATE),
  CustomerDel(00102004, ActionModel.DEL),
  CustomerQuery(00102005, ActionModel.QUERY),

  Employee(00201001, "员工管理"),
  EmployeeAdd(00202002, ActionModel.ADD),
  EmployeeUpdate(00202003, ActionModel.UPDATE),
  EmployeeDel(00202004, ActionModel.DEL),
  EmployeeQuery(00202005, ActionModel.QUERY),

  Material(00301001, "物料管理"),
  MaterialAdd(00302002, ActionModel.ADD),
  MaterialUpdate(00302003, ActionModel.UPDATE),
  MaterialDel(00302004, ActionModel.DEL),
  MaterialQuery(00302005, ActionModel.QUERY),

  Inventory(00401001, "物料管理"),
  InventoryAdd(00402002, ActionModel.ADD),
  InventoryUpdate(00402003, ActionModel.UPDATE),
  InventoryDel(00402004, ActionModel.DEL),
  InventoryQuery(00402005, ActionModel.QUERY),

  InventoryReport(00501001, "库存统计"),
  InventoryReportQuery(00502001, "总统计"),

  Role(00601001, "权限管理"),
  RoleAdd(00602002, ActionModel.ADD),
  RoleUpdate(00602003, ActionModel.UPDATE),
  RoleDel(00602004, ActionModel.DEL),
  RoleQuery(00602005, ActionModel.QUERY),
  RolePerssion(00602006, "分配权限");

  private static final String ADD = "新增";
  private static final String UPDATE = "编辑";
  private static final String DEL = "删除";
  private static final String QUERY = "查询";

  private String name;
  private int id;

  private ActionModel( int id, String name ) {
    this.name = name;
    this.id = id;
  }
}
