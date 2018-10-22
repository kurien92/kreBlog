<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1>
	Hello world!
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		관리자!
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		회원!
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
		<form action="${contextPath}/auth/logout" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			
			<button type="submit">로그아웃</button> 
		</form> 
	</sec:authorize>
	
	<sec:authorize access="isAnonymous()">
		<a href="${contextPath}/auth/login">로그인</a>
	</sec:authorize>
</h1>
<br>
${bcrypt}<br>
<P>  The time on the server is ${serverTime}. </P>
