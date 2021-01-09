<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
		</div>
	</div><!-- #kre_content -->
</main><!-- #kre_main -->

<footer id="kre_footer_mobile">
	<div class="copyright">Copyright&copy; 2020 Kurien All rights reserved.</div>
	<div class="kre_footer_btn">
		<a href="${contextPath}/content/privacyPolicy">Privacy Policy</a>
	</div>
</footer>

<script src="${contextPath}/js/kreMainScroll.js"></script>

<script>
   	$(window).on("resize orientationchange", function() {
		if($("#mobile_menu_btn").hasClass("active") === false) {
    		if(isDesktopDevices()) {
   				$("#kre_mobile_menu").show(0);
    		} else {
				$("#kre_mobile_menu").hide(0);
    		}
		}
   	});
   	
   	$(document).ready(function() {
   		$("#mobile_menu_btn").on("click", function() {
   			if($(this).hasClass("active")) {
   				$(this).removeClass("active");    				
   				$("#kre_mobile_menu").slideUp();
   			} else {
   				$(this).addClass("active");    				
   				$("#kre_mobile_menu").slideDown();
   			}
   		});
   	});

   	$("body").on("keypress", function(e) {
   	    if(e.keyCode !== 113) {
   	    	return;
   	    }
   	    
   		if($(e.target).is("textarea, input, select")) {
   			return;
   		}
   	    
    	location.href = '${contextPath}/admin';
   	});

	$(".kre_unimplemented").on("click", function(e) {
		alert("아직 구현되지 않은 기능입니다.")
		e.preventDefault();
	});
</script>

${template.footJs}

<script type="text/javascript" src="//wcs.naver.net/wcslog.js"></script>
<script type="text/javascript">
	if(!wcs_add) var wcs_add = {};
	wcs_add["wa"] = "56a40a9e644d38";
	wcs_do();
</script>
<script src="${contextPath}/js/api/kreVisitorCollect.js"></script>