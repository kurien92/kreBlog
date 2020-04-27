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
				
				<div class="kre_btn_list">
					<ul class="kre_btn_list_left">
						<c:if test="${shortUrl != null}">
						<li><button id="copyUrl" type="button" class="kre_btn" data-clipboard-text="https://www.kurien.net/s/${shortUrl.encodedUrl}">Link Copy</button></li>
						</c:if>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li><a href="${contextPath}/admin/post/modify/${post.postNo}" class="kre_btn">Modify</a></li>
						</sec:authorize>
					</ul>
				</div>
			</div>
		</header>

		<div class="kre_article_body">
			<div id="kre_article_ad1" class="adsense">
				<script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<ins class="adsbygoogle"
				     style="display:block; text-align:center;"
				     data-ad-layout="in-article"
				     data-ad-format="fluid"
				     data-ad-client="ca-pub-4805042826277102"
				     data-ad-slot="1010816570"></ins>
				<script>
				     (adsbygoogle = window.adsbygoogle || []).push({});
				</script>
			</div>
						
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

<script src="${contextPath}/js/plugin/clipboard.min.js"></script>
<script>
	if($("#copyUrl").length > 0) {
		var clipboard = new ClipboardJS('#copyUrl');
		
		clipboard.on('success', function(e) {
			alert("주소가 복사되었습니다.");

		    e.clearSelection();
		});
	}
</script>