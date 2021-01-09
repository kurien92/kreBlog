<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html lang="${template.lang}">
<head>
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
	<meta name="description" content="Kurien's Blog는 프로그래밍과 개발 전반에 대한 내용을 다루는 개인 블로그입니다.">
	
	<meta property="og:type" content="website"> 
	<meta property="og:title" content="${template.title}">
	<meta property="og:description" content="Kurien's Blog는 프로그래밍과 개발 전반에 대한 내용을 다루는 개인 블로그입니다.">
	<meta property="og:image" content="https://www.kurien.net/img/favicon/android-chrome-384x384.png">
	<meta property="og:url" content="https://www.kurien.net">

	${template.meta}
	<title>${template.title}</title>
	<link rel="canonical" href="https://www.kurien.net">
	<link rel="stylesheet" href="${contextPath}/css/normalize.css">
	<link rel="stylesheet" href="${contextPath}/css/base.css">
	<link rel="stylesheet" href="${contextPath}/css/layout.css">
	<link rel="stylesheet" href="${contextPath}/css/plugin/slimscroll.css" />
	${template.css}

	<script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.26/webfont.js"></script>
	<script>
		WebFont.load({
			google: {
				families: ['Nanum Gothic', 'Material Icons'] // Material Icons 폰트 로드
			}
		});
	</script>

	<!--[if lt IE 9]>
	<script src="${contextPath}/js/plugin/html5shiv.min.js"></script>
	<![endif]-->
	<script src="${contextPath}/js/library/jquery.min.js"></script>
    <script src="${contextPath}/js/plugin/slimscroll.js"></script>
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
</body>
</html>