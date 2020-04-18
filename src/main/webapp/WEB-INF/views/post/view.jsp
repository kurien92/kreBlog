<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<section id="post_view" class="post">
	<h3 class="section_subject">Post View</h3>
	
	<article class="kre_article">
		<header class="kre_article_header">
			<h4 class="kre_article_subject">${post.postSubject}</h4>
			<div class="kre_article_info">
				<ul>
					<li><strong>Author</strong> ${post.postAuthor}</li>
					<c:if test="${post.categoryId != ''}">
						<li>
							<strong>Category</strong>
							<a href="${contextPath}/category/${post.categoryId}" class="kre_post_category">${category.categoryName}</a>
						</li>
					</c:if>
					<li><strong>Registration Time</strong> <fmt:formatDate value="${post.postWriteTime}" pattern="yyyy/MM/dd HH:mm" /></li>
				</ul>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="kre_btn_list">
					<ul class="kre_btn_list_left">
						<li><a href="${contextPath}/admin/post/modify/${post.postNo}" class="kre_btn">Modify</a></li>
					</ul>
				</div>
				</sec:authorize>
			</div>
		</header>
		
		<div class="kre_article_body">
			${post.postContent}
		</div>
		
		<footer class="kre_article_footer">
			<div class="kre_btn_list">
				<ul class="kre_btn_list_right">
					<li><a href="${contextPath}/post/list" class="kre_btn">List</a></li>
				</ul>
			</div>
		</footer>
	</article>
</section>