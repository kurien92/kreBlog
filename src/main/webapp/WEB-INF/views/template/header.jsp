<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<aside id="kre_aside" class="kre_scrollbar">
	<div class="scroll-wrapper1">
		<div id="kre_aside_wrap">
			<header id="kre_header" class="aside_item">
				<h1 id="logo"><a href="${contextPath}/">Kurien's Blog</a></h1>
				
				<div id="menu_icon">
					<button type="button" id="mobile_menu_btn">
						<span class="material-icons">menu</span>
					</button>
				</div>
			</header>
			
			<div id="ad1" class="adsense aside_item">
				<script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<!-- kurien.net aside -->
				<ins class="adsbygoogle"
				     data-ad-client="ca-pub-4805042826277102"
				     data-ad-slot="7162985985"></ins>
				<script>
				     (adsbygoogle = window.adsbygoogle || []).push({});
				</script>
			</div>
			
			<div id="kre_mobile_menu">
				<div id="kre_search" class="aside_item">
					<h2 id="kre_search_header">Search</h2>

					<div id="kre_search_area">
						<input type="text" id="searchQuery" class="kre_inp" placeholder="Please enter a word." value="${template.searchQuery}"><button type="button" id="searchBtn" class="kre_btn"><span class="material-icons">
search
</span></button>
					</div>
					<script>
						$(function() {
							$("#searchBtn").on("click", function() {
								var keyword = $("#searchQuery").val();

								searchQuery(keyword);
							});

							$("#searchQuery").on("keypress", function(e) {
								if(e.keyCode !== 13) {
									return;
								}
								var keyword = $("#searchQuery").val();

								searchQuery(keyword);
							});

							function searchQuery(searchQuery) {
								if(searchQuery.length < 2) {
									alert("검색어는 2자 이상 입력해주세요!");
									return;
								}

								searchQuery = encodeURIComponent(searchQuery);
								location.href = "${contextPath}/search?q=" + searchQuery;
							}
						});
					</script>
				</div>

				<div id="kre_category" class="aside_item">
					<h2 id="kre_category_header">Category</h2>
					
					<div id="kre_category_list">
						${template.categoryHTML}
					</div>
				</div>
				
				<div id="kre_menu" class="aside_item">
					<h2 id="kre_menu_header" class="kre_hidden">Buttons</h2>
					
					<ul>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<li><a href="${contextPath}/admin">Admin</a></li>
						</sec:authorize>
			
						<sec:authorize access="isAnonymous()">
							<li><a href="${contextPath}/auth/signin">Sign in</a></li>
						</sec:authorize>
						
						<sec:authorize access="isAuthenticated()">
							<li>
								<form action="${contextPath}/auth/signout" method="post">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
									
									<button type="submit" class="kre_btn link_btn" id="menu_signout">Sign out</button>
								</form>
							</li>
						</sec:authorize>
					</ul>
				</div>
			</div>

			<footer id="kre_footer" class="aside_item">
				<div class="copyright">Copyright&copy; 2020 Kurien All rights reserved.</div>
				<div class="kre_footer_btn">
					<a href="${contextPath}/content/privacyPolicy">Privacy Policy</a>
				</div>
			</footer>
		</div>
	</div>
</aside>

<main id="kre_main" class="kre_scrollbar">
	<div class="scroll-wrapper2">
		<h2 class="hidden">Main</h2>
		
		<div id="kre_content">