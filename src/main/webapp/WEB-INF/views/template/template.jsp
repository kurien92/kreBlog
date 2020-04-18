<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<c:set var="requestURI" value="${requestScope['javax.servlet.forward.request_uri']}" scope="request"/>
<!DOCTYPE html>
<html lang="${template.lang}">
<head>
	<!-- favicon -->
	<link rel="apple-touch-icon" sizes="180x180" href="/img/favicon/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="/img/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="/img/favicon/favicon-16x16.png">
	<link rel="manifest" href="/img/favicon/site.webmanifest">
	<link rel="mask-icon" href="/img/favicon/safari-pinned-tab.svg" color="#5bbad5">
	<link rel="shortcut icon" href="/img/favicon/favicon.ico">
	<meta name="apple-mobile-web-app-title" content="Kurien's Blog">
	<meta name="application-name" content="Kurien's Blog">
	<meta name="msapplication-TileColor" content="#2b5797">
	<meta name="msapplication-config" content="/img/favicon/browserconfig.xml">
	<meta name="theme-color" content="#ffffff">
	
	<meta charset="${template.charset}">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="x-ua-compatible" content="ie=edge">

	<meta name="title" content="${template.title}">
	<meta name="description" content="${template.description}">
	
	<!-- opengraph -->
	<meta property="og:type" content="website"> 
	<meta property="og:title" content="${template.title}">
	<meta property="og:description" content="${template.description}">
	<meta property="og:image" content="https://www.kurien.net/img/favicon/android-chrome-384x384.png">
	<meta property="og:url" content="https://www.kurien.net${requestURI}">
	
	<meta name="twitter:card" content="summary">
	<meta name="twitter:title" content="${template.title}">
	<meta name="twitter:description" content="${template.description}">
	<meta name="twitter:image" content="https://www.kurien.net/img/favicon/android-chrome-384x384.png">

	<!-- 애플 기기에서 전체화면 웹 앱 실행 가능하게 수정 -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	
	<!-- 사이트 인증 -->
	<meta name="google-site-verification" content="ATFeKG_4caG8sXM6_QjLeiJEtC0wEkHeeyB9sX79qlg" />
	<meta name="naver-site-verification" content="b713eac25ca0c28012695006168a9478c1f554b2" />
	
	${template.meta}
	
	<title>${template.title}</title>
	
	<link rel="canonical" href="https://www.kurien.net${requestURI}">
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
		/**
		 * 반응형에 따른 스크롤바 적용
		 * 모바일에서 destroy를 하지 않는 경우 카테고리가 출력되지 않음.(overflow: hidden이 강제 됨.)
		 */
 		function responsiveScrollbar() {
            if(isDesktopDevices()) {
                $(".scrollbar").mCustomScrollbar({
                    scrollInertia: 300,
                    documentTouchScroll: true
                });
            } else {
                $(".scrollbar").mCustomScrollbar("destroy");
            }
   		}
        
        $(window).on("load resize orientationchange", function() {
            responsiveScrollbar();    
		});

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
</body>
</html>