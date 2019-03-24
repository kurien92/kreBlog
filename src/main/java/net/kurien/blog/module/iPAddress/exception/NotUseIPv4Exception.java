package net.kurien.blog.module.iPAddress.exception;

public class NotUseIPv4Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NotUseIPv4Exception() {
		
	}
	
	public NotUseIPv4Exception(String message) {
		super(message);
	}
}
