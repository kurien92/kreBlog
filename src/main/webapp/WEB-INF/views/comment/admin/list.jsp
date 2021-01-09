<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="admin_comment_list" class="comment admin">
	<h3 class="section_subject">Comment List</h3>

	<div class="kre_btn_list">
		<ul class="kre_btn_list_left">
			<li>
				<form action="${contextPath}/admin/comment/list" method="get">
					<select name="searchType">
						<option value="content">내용</option>
						<option value="author">작성자</option>
					</select>
					
					<input type="text" name="keyword">
					
					<button type="submit">검색</button>
				</form>
			</li>
		</ul>
	</div>
	
	<div class="kre_list">
		<ul>
			<c:choose>
				<c:when test="${comments.size() == 0}">
					<li class="comment_item">작성된 댓글이 없습니다.</li>
				</c:when>
				<c:otherwise>
					<c:forEach var="comment" items="${comments}">
					<li class="comment_item">
						<span class="comment_author">${comment.author}</span><span class="comment_ip">[${comment.writeIp}]</span>
						<a href="${contextPath}/post/view/${comment.postNo}#comment_item_${comment.commentNo}" class="postViewLink" target="_blank">${comment.comment}</a>
						<fmt:formatDate value="${comment.writeTime}" pattern="yy/MM/dd HH:mm" />
						
						<a href="${contextPath}/admin/comment/delete/${comment.commentNo}" class="commentDeleteLink kre_btn">삭제</a>
					</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</section>
<script>
	$(function() {
		$(".commentDeleteLink").on("click touch", function() {
			if(!confirm("삭제된 댓글은 복구할 수 없습니다.\n삭제하시겠습니까?")) {
				return false;
			}
		});
	});
</script>