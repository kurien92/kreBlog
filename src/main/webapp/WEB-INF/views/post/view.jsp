<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="post_view" class="post">
	<h3 class="section_subject">Post View</h3>
	
	<article class="kre_article">
		<header class="kre_article_header">
			<h4 class="kre_article_title">${post.postTitle}</h4>
			<div class="kre_article_info">
				<ul>
					<li><strong>Author</strong> ${post.postAuthor}</li>
					<li><strong>Registration Time</strong> <fmt:formatDate value="${post.postWriteTime}" pattern="yyyy/MM/dd HH:mm:ss" /></li>
				</ul>
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