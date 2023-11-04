package com.ssafy.enjoytrip.util;

import io.swagger.annotations.ApiParam;

public class PageNavigation {
	
	@ApiParam("현재 페이지가 이전이 눌려지지 않는 범위의 페이지 체크")
	private boolean startRange; // 현재 페이지가 이전이 눌려지지 않는 범위의 페이지 체크
	@ApiParam("현재 페이지가 다음이 눌려지지 않는 범위의 페이지 체크")
	private boolean endRange; // 현재 페이지가 다음이 눌려지지 않는 범위의 페이지 체크
	@ApiParam("총 게시글 갯수")
	private int totalCount; // 총 게시글 갯수
	@ApiParam("새글 갯수")
	private int newCount; // 새글 갯수
	@ApiParam("총 페이지 갯수")
	private int totalPageCount; // 총 페이지 갯수
	@ApiParam("현재 페이지 번호")
	private int currentPage; // 현재 페이지 번호
	@ApiParam("네비게이션 사이즈")
	private int naviSize; // 네비게이션 사이즈
	@ApiParam("페이지당 글 갯수")
	private int countPerPage; // 페이지당 글 갯수
	@ApiParam("페이징 버튼을 만드는 html 조각")
	private String navigator;

	public boolean isStartRange() {
		return startRange;
	}

	public void setStartRange(boolean startRange) {
		this.startRange = startRange;
	}

	public boolean isEndRange() {
		return endRange;
	}

	public void setEndRange(boolean endRange) {
		this.endRange = endRange;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNaviSize() {
		return naviSize;
	}

	public void setNaviSize(int naviSize) {
		this.naviSize = naviSize;
	}

	public String getNavigator() {
		return navigator;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	
	public static PageNavigation makePageNavigation(int totalCount, int pageNo, String url) {
		PageNavigation pageNavigation = new PageNavigation();
		
		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int countPerPage = SizeConstant.LIST_SIZE;
		int currentPage = pageNo;
		
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setCountPerPage(countPerPage);
		pageNavigation.setNaviSize(naviSize);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / countPerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator(url);
		
		return pageNavigation;
	}

	public void makeNavigator(String url) {
		int startPage = (currentPage - 1) / naviSize * naviSize + 1;
		int endPage = startPage + naviSize - 1;
		if(totalPageCount < endPage)
			endPage = totalPageCount;
//		StringBuilder path = new StringBuilder(url);
//		
//		StringBuilder builder = new StringBuilder();
//		builder.append("		<ul class=\"pagination  justify-content-center\"> \n");
//		builder.append("			<li class=\"page-item\" data-pg=\"1\"> \n");
//		builder.append("				<a href=\"" + new StringBuilder(path).append(1).toString() + "\" class=\"page-link\">최신</a> \n");
//		builder.append("			</li> \n");
//		builder.append("			<li class=\"page-item\" data-pg=\"" + (this.startRange ? 1 : (startPage - 1)) + "\"> \n");
//		builder.append("				<a href=\"" + new StringBuilder(path).append(this.startRange ? 1 : (startPage - 1)).toString() + "\" class=\"page-link\">이전</a> \n");
//		builder.append("			</li> \n");
//		for(int i=startPage;i<=endPage;i++) {
//			builder.append("			<li class=\"" + (currentPage == i ? "page-item active" : "page-item") + "\" data-pg=\"" + i + "\"><a href=\"" + new StringBuilder(path).append(i).toString() + "\" class=\"page-link\">" + i + "</a></li> \n");
//		}
//		builder.append("			<li class=\"page-item\" data-pg=\"" + (this.endRange ? endPage : (endPage + 1)) + "\"> \n");
//		builder.append("				<a href=\"" + new StringBuilder(path).append(this.endRange ? endPage : (endPage + 1)).toString() + "\" class=\"page-link\">다음</a> \n");
//		builder.append("			</li> \n");
//		builder.append("			<li class=\"page-item\" data-pg=\"" + totalPageCount + "\"> \n");
//		builder.append("				<a href=\"" + new StringBuilder(path).append(totalPageCount).toString() + "\" class=\"page-link\">마지막</a> \n");
//		builder.append("			</li> \n");
//		builder.append("		</ul> \n");
//		this.navigator = builder.toString();
	}

}
