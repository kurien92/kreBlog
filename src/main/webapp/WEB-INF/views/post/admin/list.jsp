<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="admin_post_list" class="post admin">
	<h3 class="section_subject">Post List</h3>

	<div class="kre_btn_list">
		<ul class="kre_btn_list_left">
			<li>
				<form action="${contextPath}/admin/post/list" method="get">
					<select name="searchType">
						<option value="subject">제목</option>
						<option value="author">작성자</option>
					</select>
					
					<input type="text" name="keyword">
					
					<button type="submit">검색</button>
				</form>
			</li>
		</ul>
		
		<ul class="kre_btn_list_right">
			<li><a href="${contextPath}/admin/post/write" class="kre_btn">글쓰기</a></li>
		</ul>
	</div>
	
	<div class="kre_list">
		<ul>
			<c:forEach var="post" items="${posts}">
			<li class="post_item">
				<div>
					<a href="${contextPath}/admin/post/modify/${post.postNo}" class="postViewLink">[${post.postNo}] ${post.postSubject}</a>
				</div>
				<div>
					<fmt:formatDate value="${post.postWriteTime}" pattern="yy/MM/dd HH:mm" />
				</div>
				<div>
					<a href="${contextPath}/admin/post/preview/${post.postNo}" class="postPreviewLink kre_btn" target="_blank">미리보기</a>
					<a href="${contextPath}/admin/post/delete/${post.postNo}" class="postDeleteLink kre_btn">삭제</a>
				</div>
			</li>
			</c:forEach>
		</ul>
	</div>
</section>
<script>
	$(function() {
		$(".postDeleteLink").on("click touch", function() {
			if(!confirm("삭제된 게시물은 복구할 수 없습니다.\n삭제하시겠습니까?")) {
				return false;
			}
		});
	});
</script>