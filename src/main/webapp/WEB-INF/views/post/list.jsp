<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="post_list" class="post">
	<h3 class="section_subject">Post List</h3>

	<div class="kre_grid">
		<c:forEach var="post" items="${posts}">
		<div class="kre_grid_item" tabindex="0">
			<ul>
				<c:if test="${post.cateNo}">
				<li class="cate_no">${post.cateNo}</li>
				</c:if>
				<li class="post_title">
					<a href="${contextPath}/post/view/${post.postNo}" class="postViewLink">${post.postTitle}</a>
				</li>
				<li class="post_write_time"><fmt:formatDate value="${post.postWriteTime}" pattern="yy/MM/dd HH:mm" /></li>
			</ul>
		</div>
		<div class="kre_grid_item" tabindex="0">
			<ul>
				<c:if test="${post.cateNo}">
				<li class="cate_no">${post.cateNo}</li>
				</c:if>
				<li class="post_title">${post.postTitle}</li>
				<li class="post_write_time"><fmt:formatDate value="${post.postWriteTime}" pattern="yyyy.MM.dd HH:mm" /></li>
			</ul>
		</div>
		<div class="kre_grid_item" tabindex="0">
			<ul>
				<c:if test="${post.cateNo}">
				<li class="cate_no">${post.cateNo}</li>
				</c:if>
				<li class="post_title">${post.postTitle}</li>
				<li class="post_write_time"><fmt:formatDate value="${post.postWriteTime}" pattern="yyyy.MM.dd" /></li>
			</ul>
		</div>
		<div class="kre_grid_item" tabindex="0">
			<ul>
				<c:if test="${post.cateNo}">
				<li class="cate_no">${post.cateNo}</li>
				</c:if>
				<li class="post_title">${post.postTitle}</li>
				<li class="post_write_time"><fmt:formatDate value="${post.postWriteTime}" pattern="yyyy/MM/dd" /></li>
			</ul>
		</div>
		</c:forEach>
		<div id="ad2" class="adsense kre_grid_item" tabindex="0">
			<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
			<!-- kurien.net postlist1 -->
			<ins class="adsbygoogle postListAdsense"
			     style="display:block"
			     data-ad-client="ca-pub-4805042826277102"
			     data-ad-slot="2041671186"></ins>
			<script>
			(adsbygoogle = window.adsbygoogle || []).push({});
			</script>
		</div>
		
		<div id="ad3" class="adsense kre_grid_item" tabindex="0">
			<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
			<!-- kurien.net postList2 -->
			<ins class="adsbygoogle postListAdsense"
			     style="display:block"
			     data-ad-client="ca-pub-4805042826277102"
			     data-ad-slot="6640241805"></ins>
			<script>
			(adsbygoogle = window.adsbygoogle || []).push({});
			</script>
		</div>
	</div>
	
	<script src="${contextPath}/js/plugin/masonry.pkgd.min.js"></script>
	<script>
	$(document).ready(function() {
		var kreGridItemLength = $(".kre_grid_item").length;
		var randomizeNumber = Math.floor(Math.random() * kreGridItemLength);
	
		$(".kre_grid_item").eq(randomizeNumber).after($("#ad2"));
				
		randomizeNumber = Math.floor(Math.random() * kreGridItemLength)+1;
	
		$(".kre_grid_item").eq(randomizeNumber).after($("#ad3"));
				
		var kreGrid = $('.kre_grid');
		
		kreGrid.masonry({
		  itemSelector: '.kre_grid_item',
		  columnWidth: 300,
		  gutter: 20
		});
	});
	</script>
	
	<script>
	$(document).ready(function() {
		$(".kre_grid_item").children("ul").each(function() {
			var itemHeight = $(this).closest(".kre_grid_item").height();
			var itemListHeight = $(this).height();
			
			var halfPadding = (itemHeight - itemListHeight) / 2; 
			
			$(this).css({paddingTop: halfPadding, paddingBottom: halfPadding});
		});
	});
	</script>
</section>