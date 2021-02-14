<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="auth_signin" class="auth">
	<h3 class="section_subject">Sign in</h3>

	<div class="kre_form">
		<form action="${contextPath}/auth/signinCheck" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="hidden" name="redirectUrl" value="${redirectUrl}" />
			
			<div id="auth_signin_form">
				<div class="kre_box_left">
					<div class="kre_row">
						<label for="acntId" class="row_column">ID</label><input type="text" id="acntId" name="acntId" class="kre_inp" placeholder="Enter a ID" value="${acntId}">
					</div>
					
					<div class="kre_row">
						<label for="acntPassword" class="row_column">PW</label><input type="password" id="acntPassword" name="acntPassword" class="kre_inp" placeholder="Enter a password" value="">
					</div>
				</div>
				
				<div class="kre_box_right">
					<div class="kre_row">
						<button type="submit" class="kre_btn reverse_btn signin_btn">Sign in</button>
					</div>
				</div>
			</div>
		</form>
		
		${securityexceptionmsg}
		
		<div id="auth_btn" class="kre_btn_list">
			<ul class="kre_btn_list_left">
				<li><a href="${contextPath}/account/signup" class="kre_btn">Sign up</a></li>
				<li><a href="${contextPath}/account/find" class="kre_btn">Find my account</a></li>
			</ul>
		</div>
	</div>
</section>