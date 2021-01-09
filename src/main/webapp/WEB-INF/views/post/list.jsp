<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="post_list" class="post">
	<h3 class="section_subject">Post List</h3>

	<div class="kre_grid">
		<c:choose>
			<c:when test="${posts.size() == 0}">
				<div class="kre_grid_item kre_grid_item_empty" tabindex="0">
					<ul>
						<li class="post_subject">
							작성된 포스트가 없습니다.
						</li>
						<li class="post_write_time"></li>
					</ul>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach var="post" items="${posts}">
				<div class="kre_grid_item" tabindex="0">
					<ul>
						<li class="post_subject">
							<a href="${contextPath}/post/view/${post.postNo}" class="postViewLink">${post.postSubject}</a>
						</li>
						<li class="post_write_time"><fmt:formatDate value="${post.postWriteTime}" pattern="yy/MM/dd HH:mm" /></li>
					</ul>
				</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		<div id="ad2" class="adsense kre_grid_item" tabindex="0">
			<script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
			<!-- kurien.net post1 -->
			<ins class="adsbygoogle"
				style="display:inline-block;width:300px;height:200px"
				data-ad-client="ca-pub-4805042826277102"
				data-ad-slot="7767796617"></ins>
			<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
			</script>
		</div>
		
		<div id="ad3" class="adsense kre_grid_item" tabindex="0">
			<script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
			<!-- kurien.net post2 -->
			<ins class="adsbygoogle"
				style="display:inline-block;width:300px;height:200px"
				data-ad-client="ca-pub-4805042826277102"
				data-ad-slot="8549275682"></ins>
			<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
			</script>
		</div>
	</div>

	<div class="pagination">
		<c:if test="${pageMaker.prev}">
			<a href="${pageUrl}${pageMaker.makeQuery(pageMaker.startPage - 1)}" class="pagination_item pagination_prev">◀</a></li>
		</c:if>

		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="i">
			<c:choose>
				<c:when test="${pageMaker.criteria.page != i}">
					<a href="${pageUrl}${pageMaker.makeQuery(i)}" class="pagination_item">${i}</a>
				</c:when>
				<c:otherwise>
					<span class="pagination_item pagination_current">${i}</span>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<a href="${pageUrl}/${pageMaker.makeQuery(pageMaker.endPage + 1)}" class="pagination_item pagination_next">▶</a>
		</c:if>
	</div>
	
	<script src="${contextPath}/js/plugin/masonry.pkgd.min.js"></script>
	<script>
	var kreGrid = $('.kre_grid');
	
	$(document).ready(function() {
		var kreGridItemLength = $(".kre_grid_item").length;
		var randomizeNumber = Math.floor(Math.random() * kreGridItemLength);
	
		$(".kre_grid_item").eq(randomizeNumber).after($("#ad2"));
				
		randomizeNumber = Math.floor(Math.random() * kreGridItemLength)+1;
	
		$(".kre_grid_item").eq(randomizeNumber).after($("#ad3"));

		if(isDesktopDevices()) {
			kreGrid.masonry({
				itemSelector: '.kre_grid_item',
				columnWidth: 300,
				gutter: 20
			});
		}
	});
	
	$(window).on("resize orientationchange", function() {
		if(isDesktopDevices()) {
			if(typeof kreGrid.data('masonry') !== "undefined") {
				return;
			}
			
			kreGrid.masonry({
				itemSelector: '.kre_grid_item',
				columnWidth: 300,
				gutter: 20
			});
		} else {
			if(typeof kreGrid.data('masonry') === "undefined") {
				return;
			}
			
			kreGrid.masonry('destroy');
		}
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
	
	<script>
	$(document).ready(function() {
		$(".kre_grid_item").not(".adsense").on("click", function(event) {
			event.preventDefault();
			var href = $(this).find(".postViewLink").attr("href");
			
			if(typeof href !== "undefined") {
				location.href = href;
			}
		});
	});
	</script>
</section>