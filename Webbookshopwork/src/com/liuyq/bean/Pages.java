package com.liuyq.bean;

public class Pages {

	
	private Integer totalCount;//总条数
	private Integer pageCount=6;//每页显示的条数
	private Integer totalPage;//总页数
	private Integer dPage;//当前显示的页码
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalcount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage() {
		if(totalCount%pageCount==0) {
			this.totalPage = totalCount/pageCount;
		}else {
			this.totalPage = totalCount/pageCount+1;
			
		}
	}
	public Integer getDpage() {
		return dPage;
	}
	public void setDpage(Integer dPage) {
		this.dPage = dPage;
	}

	
}
