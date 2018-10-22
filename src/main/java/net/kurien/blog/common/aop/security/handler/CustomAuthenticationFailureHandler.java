package net.kurien.blog.common.aop.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private String acntIdName;
	private String acntPasswordName;
	private String acntRedirectName;
	private String exceptionMsgName;
	private String defaultFailureUrl;
	
	public CustomAuthenticationFailureHandler() {
		this.acntIdName = "acntId";
		this.acntPasswordName = "acntPassword";
		this.acntRedirectName = "acntRedirect";
		this.exceptionMsgName = "securityexceptionmsg";
		this.defaultFailureUrl = "/auth/signin?fail=true";
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String acntId = request.getParameter(acntIdName);
		String acntPassword = request.getParameter(acntPasswordName);
		String acntRedirect = request.getParameter(acntRedirectName);
		
		request.setAttribute(acntIdName, acntId);
		request.setAttribute(acntPasswordName, acntPassword);
		request.setAttribute(acntRedirectName, acntRedirect);
		request.setAttribute(exceptionMsgName, exception.getMessage());
		
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}

	public String getAcntIdName() {
		return acntIdName;
	}

	public void setAcntIdName(String acntIdName) {
		this.acntIdName = acntIdName;
	}

	public String getAcntPasswordName() {
		return acntPasswordName;
	}

	public void setAcntPasswordName(String acntPasswordName) {
		this.acntPasswordName = acntPasswordName;
	}

	public String getAcntRedirectName() {
		return acntRedirectName;
	}

	public void setAcntRedirectName(String acntRedirectName) {
		this.acntRedirectName = acntRedirectName;
	}

	public String getExceptionMsgName() {
		return exceptionMsgName;
	}

	public void setExceptionMsgName(String exceptionMsgName) {
		this.exceptionMsgName = exceptionMsgName;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}	
}
