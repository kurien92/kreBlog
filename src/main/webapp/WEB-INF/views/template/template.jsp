<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html lang="${template.lang}">
<head>
	<meta charset="${template.charset}">
	${template.meta}
	<title>${template.title}</title>
	<link rel="stylesheet" href="${contextPath}/css/normalize.css">
	<link rel="stylesheet" href="${contextPath}/css/base.css">
	<link rel="stylesheet" href="${contextPath}/css/layout.css">
	<link rel="stylesheet" href="${contextPath}/css/plugin/jquery.mCustomScrollbar.min.css" />
	${template.css}
	
	<!--[if lt IE 9]>
	<script src="${contextPath}/js/plugin/html5shiv.min.js"></script>
	<![endif]-->
	<script src="${contextPath}/js/library/jquery.min.js"></script>
	<script src="${contextPath}/js/plugin/jquery.mCustomScrollbar.concat.min.js"></script>
	${template.headJs}
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
	<script>
		$(window).on("load", function() {
	        $(".scrollbar").mCustomScrollbar({
	        	scrollInertia: 300
	        });
		});
	</script>
	${template.footJs}
</body>
</html>