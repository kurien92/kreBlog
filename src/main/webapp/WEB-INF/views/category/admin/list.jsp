<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="admin_category_list" class="category admin">
	<h3 class="section_subject">Category List</h3>

	<div class="kre_btn_list">
		<ul class="kre_btn_list_left">
		</ul>
		
		<ul class="kre_btn_list_right">
			<li><a href="${contextPath}/admin/category/add" class="kre_btn">카테고리 추가</a></li>
		</ul>
	</div>
	
	<div class="kre_list">
		<ul>
			<c:forEach var="category" items="${categories}">
			<li class="category_item">
				${category.categoryName}
				<a href="${contextPath}/admin/category/modify/${category.categoryId}" class="categoryModifyLink kre_btn">수정</a>
				<a href="${contextPath}/admin/category/remove/${category.categoryId}" class="categoryRemoveLink kre_btn">삭제</a>
			</li>
			</c:forEach>
		</ul>
	</div>
</section>
<script>
	$(function() {
		$(".categoryRemoveLink").on("click touch", function() {
			if(!confirm("삭제된 카테고리는 복구할 수 없습니다.\n삭제하시겠습니까?")) {
				return false;
			}
		});
	});
</script>