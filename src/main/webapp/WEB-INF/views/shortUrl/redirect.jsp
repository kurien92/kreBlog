<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="short_url_redirect" class="shorturl">
	<h3 class="section_subject">Redirect</h3>

	<div class="content_box">
		<h4 class="content_subject"><span id="redirect_time">3</span>초 후 실제 사이트로 이동됩니다.</h4>
		
		<div class="content_description">
			Site URL: ${redirectUrl}
		</div>
		
		<div class="content_button_wrap">
			<a class="kre_btn" href="${redirectUrl}">바로 이동하기</a>
			<button type="button" id="stopRedirect" class="kre_btn reverse_btn">이동 중지하기</button>
		</div>
	</div>
</section>

<script>
	let redirectTime = 3;
	
	let redirect = setInterval(function() {
		redirectTime--;
		$("#redirect_time").text(redirectTime);
		
		if(redirectTime == 0) {
			clearInterval(redirect);
			location.replace('${redirectUrl}');
		}
	}, 1000);
	
	$("#stopRedirect").on("click", function() {
		$(".content_subject").text("이동이 중지되었습니다.")
		clearInterval(redirect);
	});
</script>