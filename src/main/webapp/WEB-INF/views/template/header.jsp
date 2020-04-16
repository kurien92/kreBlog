<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<aside id="kre_aside" class="scrollbar" data-mcs-theme="minimal-dark">
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
			     style="display:inline-block;width:300px;height:250px"
			     data-ad-client="ca-pub-4805042826277102"
			     data-ad-slot="7162985985"></ins>
			<script>
			     (adsbygoogle = window.adsbygoogle || []).push({});
			</script>
		</div>
		
		<div id="kre_mobile_menu">
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
			Copyright&copy; 2020 Kurien All rights reserved.
		</footer>
	</div>
</aside>

<main id="kre_main" class="scrollbar" data-mcs-theme="minimal-dark">
	<h2 class="hidden">Main</h2>
	
	<div id="kre_content">