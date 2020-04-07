<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html lang="${template.lang}">
<head>
	<meta charset="${template.charset}">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	${template.meta}
	<title>${template.title}</title>
	<link rel="stylesheet" href="${contextPath}/css/normalize.css">
	<link rel="stylesheet" href="${contextPath}/css/base.css">
	<link rel="stylesheet" href="${contextPath}/css/layout.css">
	<link rel="stylesheet" href="${contextPath}/css/plugin/jquery.mCustomScrollbar.min.css" />
	${template.css}

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=G-E48BJNKSFS"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'G-E48BJNKSFS');
	</script>
	<script data-ad-client="ca-pub-4805042826277102" async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	
	<!--[if lt IE 9]>
	<script src="${contextPath}/js/plugin/html5shiv.min.js"></script>
	<![endif]-->
	<script src="${contextPath}/js/library/jquery.min.js"></script>
	<script src="${contextPath}/js/plugin/jquery.mCustomScrollbar.concat.min.js"></script>
	<script>
		var browserResolution = {
			width: window.innerWidth || document.body.clientWidth,
			height: window.innerHeight || document.body.clientHeight 
		};
		
		function isExtraSmallDevices() {
			if(browserResolution.width >= 0 && browserResolution.width < 576) {
				return true;
			}
				
			return false;
		}
		
		function isSmallDevices() {
			if(browserResolution.width >= 576 && browserResolution.width < 768) {
				return true;
			}
				
			return false;
		}
		
		function isMediumDevices() {
			if(browserResolution.width >= 768 && browserResolution.width < 992) {
				return true;
			}
				
			return false;
		}
		
		function isLargeDevices() {
			if(browserResolution.width >= 992 && browserResolution.width < 1200) {
				return true;
			}
				
			return false;
		}
		
		function isExtraLargeDevices() {
			if(browserResolution.width >= 1200) {
				return true;
			}
				
			return false;
		}
		
		function isMobileDevices() {
			if(isExtraSmallDevices() === true || isSmallDevices() === true || isMediumDevices() === true) {
				return true;
			}
			
			return false;
		}
		
		function isDesktopDevices() {
			if(isLargeDevices() === true || isExtraLargeDevices() === true) {
				return true;
			}
			
			return false;
		}
		
		$(window).on("load resize orientationchange", function () {
			browserResolution = {
				width: window.innerWidth || document.body.clientWidth,
				height: window.innerHeight || document.body.clientHeight 
			};
		});
	</script>
	${template.headJs}
</head>
<body>
	<div id="kre_wrap">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
	
	<script>
    	$(window).on("load resize orientationchange", function() {
    		//반응형에 따른 스크롤바 적용
    		if(isDesktopDevices()) {
    			$(".scrollbar").mCustomScrollbar({
    		    	scrollInertia: 300
    		    });
    		} else {
    	        $(".scrollbar").mCustomScrollbar("destroy");
    		}
    		

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
	</script>
	${template.footJs}
</body>
</html>