package com.fy.fyy.back.servlet.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.fy.fyy.back.bean.Customer;
import com.fy.fyy.back.common.Constraint;
import com.fy.fyy.back.common.PermissionUtil;
import com.fy.fyy.back.permission.ModelNode;


public class A extends SimpleTagSupport {

  private Integer id;
  private String href;
  private String onclick;
  private String text;
  private String style;

  public void doTag() throws JspException, IOException {
    HttpServletRequest request = (HttpServletRequest) ( (PageContext)this.getJspContext() ).getRequest();
    Customer loginCustomer = (Customer)request.getSession().getAttribute( Constraint.LOGIN_USER );
    Map<String, ModelNode> customerPermission = (Map<String, ModelNode>)request.getSession().getAttribute( Constraint.LOGIN_PERM );
    if ( PermissionUtil.isPermission( getId().toString(), loginCustomer, customerPermission ) ) {
      getJspContext().getOut().write( toDefString() );
    }
  }

  public String toDefString() {
    return "<a href='" + getHref() + "' onclick='" + getOnclick() + "'>" + getText() + "</a>";
  }

  public Integer getId() {
    return id;
  }

  public void setId( Integer id ) {
    this.id = id;
  }

  public String getHref() {
    return href;
  }

  public void setHref( String href ) {
    this.href = href;
  }

  public String getOnclick() {
    return onclick;
  }

  public void setOnclick( String onclick ) {
    this.onclick = onclick;
  }

  public String getText() {
    return text;
  }

  public void setText( String text ) {
    this.text = text;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle( String style ) {
    this.style = style;
  }

}
