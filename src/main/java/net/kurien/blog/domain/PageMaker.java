package net.kurien.blog.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalRowCount;
	private int startPage;
	private int endPage;
	
	private boolean first = false;
	private boolean prev = false;
	private boolean next = false;
	private boolean last = false;
	
	private int pageCount = 5;
	
	private Criteria criteria;

	public PageMaker() {
		
	}
	
	public PageMaker(Criteria criteria, int totalRowCount) {
		setCriteria(criteria);
		setTotalRowCount(totalRowCount);
	}
	
	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
		
		calcPage();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.build();
		
		return uriComponents.toUriString();
	}
	
	public String makeSearchQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("searchType", ((SearchCriteria) criteria).getSearchType())
				.queryParam("keyword", ((SearchCriteria) criteria).getKeyword())
				.build();
		
		return uriComponents.toUriString();
	}

	private void calcPage() {
		endPage = (int) (Math.ceil(criteria.getPage() / (double) pageCount) * pageCount);
		startPage = (endPage - pageCount) + 1;
		
		int totalEndPage = (int) (Math.ceil(totalRowCount / (double) criteria.getRowCount()));
		
		if(endPage > totalEndPage) {
			endPage = totalEndPage;
		}
		
		if(startPage != 1) {
			prev = true;
		}
		
		if(endPage * criteria.getRowCount() < totalRowCount) {
			next = true;
		}
	}
	
	@Override
	public String toString() {
		return "PageMaker [totalRowCount=" + totalRowCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", first=" + first + ", prev=" + prev + ", next=" + next + ", last=" + last + ", pageCount="
				+ pageCount + ", criteria=" + criteria + "]";
	}
}
