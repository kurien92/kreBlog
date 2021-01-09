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
			
			<div id="kre_mobile_menu">
				<div id="kre_category" class="aside_item">
					<h2 id="kre_category_header">Category</h2>
					
					<div id="kre_category_list">
						<ul class="category_list category_depth_1">
							<li>
								<a href="/admin"><span class="material-icons">arrow_right</span>Admin Home</a>
							</li>

							<li>
								<a href="/admin/post/list"><span class="material-icons">arrow_right</span>Post</a>
								<ul>
									<li>
										<a href="/admin/post/write"><span class="material-icons">arrow_right</span>Write</a>
									</li>
								</ul>
							</li>

							<li>
								<a href="/admin/content/list"><span class="material-icons">arrow_right</span>Content</a>
								<ul>
									<li>
										<a href="/admin/content/write"><span class="material-icons">arrow_right</span>Write</a>
									</li>
								</ul>
							</li>
							
							<li>
								<a href="/admin/comment/list"><span class="material-icons">arrow_right</span>Comment</a>
							</li>
							
							<li>
								<a href="/admin/category/list"><span class="material-icons">arrow_right</span>Category</a>
							</li>
							
							<li>
								<a href="/admin/category/list"><span class="material-icons">arrow_right</span>File</a>
							</li>
							
							<li>
								<a href="/admin/visitor/list"><span class="material-icons">arrow_right</span>Visitor statistics</a>
							</li>
						</ul>
					</div>
				</div>
				
				<div id="kre_menu" class="aside_item">
					<h2 id="kre_menu_header" class="kre_hidden">Buttons</h2>
					
					<ul>
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
	</div>
</aside>

<main id="kre_main" class="kre_scrollbar">
	<div class="scroll-wrapper2">
		<h2 class="hidden">Main</h2>
		
		<div id="kre_content">