<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<c:set var="requestURI" value="${requestScope['javax.servlet.forward.request_uri']}" scope="request"/>
<!DOCTYPE html>
<html lang="ko">
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
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>오류페이지 | Kurien's Blog</title>
	
	<link rel="stylesheet" href="${contextPath}/css/normalize.css">
	<link rel="stylesheet" href="${contextPath}/css/base.css">
	<link rel="stylesheet" href="${contextPath}/css/layout.css">
	<link rel="stylesheet" href="${contextPath}/css/module/error.css">
</head>
<body>
	<div id="kre_wrap">
		<section id="fatal_exception" class="error">
			<h3 class="section_subject hidden">Error page</h3>
		
			<div id="exception_box">
				<h4 id="exception_header">예상하지 못한 오류가 발생했습니다.</h4>
		
				<div id="exception_description">
					예상하지 못한 오류가 발생했습니다.<br>아래에 표시된 버튼을 통해 이동하시기 바랍니다.
				</div>
				
				<div id="exception_button_wrap">
					<c:if test="${referer != null}"><a class="kre_btn reverse_btn signin_btn" href="${referer}"><span class="material-icons">arrow_back</span> Previous Page</a></c:if><a class="kre_btn reverse_btn signin_btn" href="${contextPath}/"><span class="material-icons">home</span> Kurien's Blog</a>
				</div>
			</div>
		</section>
	</div>
</body>
</html>