package net.kurien.blog.domain;

/**
 * 해당 테이블의 Criteria를 생성한다.
 * 
 * page, rowCount 값을 전달 받아 해당하는 Criteria 생성.
 */

public class Criteria {
	private int page;
	private int rowCount;
	
	public Criteria() {
		page = 1;
		rowCount = 10;
	}
	
	public Criteria(int page) {
		this();
		setPage(page);
	}
	
	public Criteria(int page, int rowCount) {
		this(page);
		setRowCount(rowCount);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page < 1) {
			this.page = 1;
			
			return;
		}
		
		this.page = page;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		if(rowCount <= 0 || rowCount > 100){
			this.rowCount = 10;
			return;
		}
		
		this.rowCount = rowCount;
	}
	
	public int getOffset() {
		return (page-1) * rowCount;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", rowCount=" + rowCount + "]";
	}
}
