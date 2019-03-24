<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="kre_wrap">
	<aside id="kre_aside" class="scrollbar" data-mcs-theme="minimal-dark">
		<header id="kre_header" class="aside_item">
			<h1 id="logo"><a href="${contextPath}/">Kurien's Blog</a></h1>
		</header>
		
		<div id="ad1" class="adsense aside_item">
			<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
			<!-- kurien.net aside -->
			<ins class="adsbygoogle categoryAdsense"
			     style="display:block"
			     data-ad-client="ca-pub-4805042826277102"
			     data-ad-slot="7162985985"></ins>
			<script>
			(adsbygoogle = window.adsbygoogle || []).push({});
			</script>
		</div>
		
		<div id="kre_category" class="aside_item">
			<h2 id="kre_category_header">Category</h2>
			
			<ul id="kre_category_list" class="category_depth_1">
				<li><a href="${contextPath}/category/web">Web</a>
					<ul class="category_depth_2">
						<li><a href="${contextPath}/category/php">PHP</a></li>
						<li><a href="${contextPath}/category/jsp">JSP</a></li>
						<li><a href="${contextPath}/category/html">HTML</a></li>
						<li><a href="${contextPath}/category/css">CSS</a></li>
						<li><a href="${contextPath}/category/javascript">JavaScript</a></li>
					</ul>
				</li>
			</ul>
		</div>
		
		<footer id="kre_footer" class="aside_item">
			Copyright&copy; 2018 Kurien All rights reserved.
		</footer>
	</aside>
	
	<main id="kre_main" class="scrollbar" data-mcs-theme="minimal-dark">
		<h2 class="hidden">Main</h2>
		<div id="kre_content">