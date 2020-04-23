<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
		</div>
	</div><!-- #kre_content -->
</main><!-- #kre_main -->

	
<script>
    if(!navigator.userAgent.match('Macintosh')) {
    	var one = null;
    	var two = null;

        $(window).on("load resize orientationchange", function() {
    		if(isDesktopDevices()) {
    			if(alreadyCreateScroll()) {
	    			// 이벤트가 이미 실행되어있다면 갱신만 한다.
	    			if(checkStoppedScroll()) {
		    			one.startScroll();
		    			two.startScroll(); 
    				}
    			
    	            one.resetValues();
    	            two.resetValues();
	    			return;
    			}
    			
    			// 이벤트가 실행되어있지 않다면 새로 만든다.
    			createScroll();    			
    		} else {
    			//모바일 때 이벤트가 있다면 중지시킨다.
    			if(alreadyCreateScroll()) {
	    			one.stopScroll();
	    			two.stopScroll();    				
    			}
    		}
        });
        
        function alreadyCreateScroll() {
        	if(one === null || two === null) {
        		return false;
        	}
        	
        	return true;
        }
        
        function checkStoppedScroll() {
        	if(one.checkStop() && two.checkStop()) {
        		return true;
        	}
        	
        	return false;
        }
        
        function createScroll() {
            var kreAsideScroll = document.querySelectorAll('#kre_aside');

            // Apply slim scroll plugin
            one = new slimScroll(kreAsideScroll[0], {
                wrapperClass: 'scroll-wrapper1 unselectable mac',
                scrollBarContainerClass: 'scrollBarContainer',
                scrollBarContainerSpecialClass: 'animate',
                scrollBarClass: 'scroll',
                keepFocus: false
            });

            var kreMainScroll = document.querySelectorAll('#kre_main');

            two = new slimScroll(kreMainScroll[0], {
                wrapperClass: 'scroll-wrapper2 unselectable mac',
                scrollBarContainerClass: 'scrollBarContainer',
                scrollBarContainerSpecialClass: 'animate',
                scrollBarClass: 'scroll',
                keepFocus: false
            });
        }
        
        if(typeof kreGrid !== 'undefined') {
	        kreGrid.on('layoutComplete', function() {
	    		if(isDesktopDevices()) {
	    			if(alreadyCreateScroll()) {
			            one.resetValues();
			            two.resetValues();	    				
	    			}
	    		}
	        });
        }
    }

</script>

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