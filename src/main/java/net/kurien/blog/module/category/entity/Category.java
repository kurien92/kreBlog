package net.kurien.blog.module.category.entity;

public class Category {
	private Integer categoryNo;
	private Integer categoryParentNo;
	private Integer categoryDepth;
	private Integer categoryOrder;
	private String categoryId;
	private String categoryName;
	
	public Integer getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Integer categoryNo) {
		this.categoryNo = categoryNo;
	}
	public Integer getCategoryParentNo() {
		return categoryParentNo;
	}
	public void setCategoryParentNo(Integer categoryParentNo) {
		this.categoryParentNo = categoryParentNo;
	}
	public Integer getCategoryDepth() {
		return categoryDepth;
	}
	public void setCategoryDepth(Integer categoryDepth) {
		this.categoryDepth = categoryDepth;
	}
	public Integer getCategoryOrder() {
		return categoryOrder;
	}
	public void setCategoryOrder(Integer categoryOrder) {
		this.categoryOrder = categoryOrder;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString() {
		return "Category [categoryNo=" + categoryNo + ", categoryParentNo=" + categoryParentNo + ", categoryDepth="
				+ categoryDepth + ", categoryOrder=" + categoryOrder + ", categoryId=" + categoryId + ", categoryName="
				+ categoryName + "]";
	}
}
