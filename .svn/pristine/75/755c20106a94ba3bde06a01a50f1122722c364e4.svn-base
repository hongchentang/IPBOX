package com.hcis.ipanther.core.page;

import java.io.Serializable;
import java.util.List;




public class Pagination implements Serializable{

	private static final long serialVersionUID = -6579435031214235862L;

	private int rowCount;// 总记录数
	private int pageSize = 10;// 每页记录数
	private int pageCount;// 总页数
	private int currentPage;// 当前页数
	private boolean next;// 是否能下一页
	private boolean previous;// 是否能上一页
	private boolean available = true;//是否分页
	private int navEnd;
	private int navCount=3;
	private int navBegin;
	private boolean isChange;
	public int getNavBegin() {
		return navBegin;
	}

	public void setNavBegin(int navBegin) {
		this.navBegin = navBegin;
	}

	public int getNavEnd() {
		return navEnd;
	}

	public void setNavEnd(int navEnd) {
		this.navEnd = navEnd;
	}

	public int getNavCount() {
		return navCount;
	}

	public void setNavCount(int navCount) {
		this.navCount = navCount;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean change) {
		isChange = change;
	}

	public Pagination(){

	}

	public Pagination(String currentPage, int rowCount) {
		if (currentPage == null) {
			this.currentPage = 1;
		} else {
			this.currentPage = Integer.parseInt(currentPage);
		}
		this.rowCount = rowCount;
		// 计算总页数
		this.pageCount = (int) Math.ceil(this.rowCount / (double) this.pageSize);
		// 计算是否能上一页和下一页
		this.next = this.currentPage < this.pageCount;
		this.previous = this.currentPage > 1;

	}

	public Pagination pagination() {
		if (currentPage == 0) {
			this.currentPage = 1;
		}
		// 计算总页数
		this.pageCount = (int) Math
				.ceil(this.rowCount / (double) this.pageSize);
		// 计算是否能上一页和下一页
		this.next = this.currentPage < this.pageCount;
		this.previous = this.currentPage > 1;
		this.navBegin = (this.currentPage - (int) Math.ceil((double) navCount / 2));//
		if (navBegin < 1) { //当前页-(总显示的页列表数/2)  
			navBegin = 1;  
		}
		this.navEnd = this.currentPage+navCount/2;
		if(this.navEnd-this.navBegin<2){
			this.navEnd = this.navBegin+2;
		}
		if(navEnd>this.pageCount){
			navEnd = pageCount;
		}
		return this;
	} 

	public int getPageCount() {
		return pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(boolean next) {
		this.next = next;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(boolean previous) {
		this.previous = previous;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentMinRow() {
		return (currentPage-1) * pageSize;
	}

	public int getCurrentMaxRow() {
		return currentPage * pageSize;
	}

	public int getNextPage() {
		if (next) {
			return currentPage + 1;
		}
		return currentPage;
	}

	public int getPreviousPage() {
		if (previous) {
			return currentPage - 1;
		}
		return currentPage;
	}

	public boolean isNext() {
		return next;
	}

	public boolean isPrevious() {
		return previous;
	}

	public int nextPage() {
		if (next) {
			currentPage = currentPage + 1;
		}
		return currentPage;
	}

	public int previousPage() {
		if (previous) {
			currentPage = currentPage - 1;
		}
		return currentPage;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	 /**  
     * 对list集合进行分页处理  
	 * @param <E>
     *   
     * @return  
     */  
    public <E> List<E> ListSplit(List<E> list) { 
    	if(this.currentPage == 0){
    		currentPage = 1;
    	}
    	if(list==null||list.isEmpty()){
    		return null;
    	}
    	if (!this.isAvailable()) {
			return list;
		}
        List<E> newList=null;  
        this.rowCount=list.size();  
        newList=list.subList(pageSize*(currentPage-1), ((pageSize*currentPage)>rowCount?rowCount:(pageSize*currentPage))); 
        this.pagination();
        return newList;  
    } 

}
