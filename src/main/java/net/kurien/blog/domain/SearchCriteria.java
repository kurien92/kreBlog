package net.kurien.blog.domain;

/**
 * 해당 테이블의 SearchCriteria를 생성한다.
 * 
 * page, rowCount 값과 Search 값을 전달 받아 해당하는 SearchCriteria 생성.
 */

public class SearchCriteria extends Criteria {
	private String searchType;
	private String keyword;
	
	public SearchCriteria() {
		super();
	}
	
	public SearchCriteria(int page) {
		super(page);
	}
	
	public SearchCriteria(int page, int rowCount) {
		super(page, rowCount);
	}
	
	public SearchCriteria(int page, int rowCount, String searchType, String keyword) {
		super(page, rowCount);
		setSearchType(searchType);
		setKeyword(keyword);
	}
	
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + ", toString()=" + super.toString()
				+ "]";
	}
}
