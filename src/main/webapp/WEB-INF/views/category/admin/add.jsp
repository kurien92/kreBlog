<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="admin_category_write" class="category admin">
	<h3 class="section_subject">Category Write</h3>
	
	<form action="${contextPath}/admin/category/${formAction}" method="post">
		<c:if test="${category != null}">
			<input type="hidden" name="categoryNo" value="${category.categoryNo}">
		</c:if>
		
		<div class="kre_writing">
			<div>
				<select name="categoryParentNo">
					<option value="">None</option>
					
					<c:forEach var="parentCategory" items="${parentCategories}">
						<c:if test="${parentCategory.categoryNo != category.categoryNo}">
							<option value="${parentCategory.categoryNo}" ${parentCategory.categoryNo == category.categoryParentNo ? "selected" : ""}>${parentCategory.categoryName}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			
			<div>
				<input type="text" name="categoryId" placeholder="ID" value="${category.categoryId}" ${category != null ? "readonly" : ""}>
			</div>
			
			<div>
				<input type="text" name="categoryName" placeholder="이름" value="${category.categoryName}">
			</div>
			
			
			<div>
				<input type="text" name="categoryOrder" placeholder="순서" value="${category.categoryOrder}">
			</div>
		</div>
            
		<div class="kre_btn_list">
			<ul class="kre_btn_list_left">
				<li><a href="${contextPath}/admin/category/list" class="kre_btn">목록</a></li>
				<li><button type="submit" class="kre_btn">${formSubmit}</button></li>
			</ul>
		</div>
	</form>
</section>