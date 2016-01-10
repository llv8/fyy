package com.fy.fyy.back.bean;

public class PageInfo {

  private int pageSize = 10;
  private int currentPageNum = 1;
  private int countRecord;
  private int countPage;
  private boolean pageFlag = true;

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize( int pageSize ) {
    this.pageSize = pageSize;
  }

  public int getCurrentPageNum() {
    return currentPageNum;
  }

  public void setCurrentPageNum( int currentPageNum ) {
    this.currentPageNum = currentPageNum;
  }

  public int getCountRecord() {
    return countRecord;
  }

  public void setCountRecord( int countRecord ) {
    this.countRecord = countRecord;
  }

  public int getCountPage() {
    return countPage;
  }

  public void setCountPage( int countPage ) {
    this.countPage = countPage;
  }

  public boolean isPageFlag() {
    return pageFlag;
  }

  public void setPageFlag( boolean pageFlag ) {
    this.pageFlag = pageFlag;
  }

}
