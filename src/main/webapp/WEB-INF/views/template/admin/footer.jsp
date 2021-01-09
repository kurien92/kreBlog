<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
		</div>
	</div><!-- #kre_content -->
</main><!-- #kre_main -->

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
   	    
 	    location.href = '${contextPath}/';
   	});
</script>

${template.footJs}