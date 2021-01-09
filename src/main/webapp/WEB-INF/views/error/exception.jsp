<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="exception" class="error">
	<h3 class="section_subject">Error page</h3>

	<div id="exception_box" class="content_box">
		<h4 id="exception_header" class="content_subject">${exceptionMsg}</h4>
		
		<div id="exception_description" class="content_description">
			${exceptionDescription}
		</div>
		
		<div id="exception_button_wrap" class="content_button_wrap">
			<c:if test="${referer != null}"><a class="kre_btn reverse_btn signin_btn" href="${referer}"><span class="material-icons">arrow_back</span> Previous Page</a></c:if><a class="kre_btn reverse_btn signin_btn" href="${contextPath}/"><span class="material-icons">home</span> Kurien's Blog</a>
		</div>
	</div>
</section>