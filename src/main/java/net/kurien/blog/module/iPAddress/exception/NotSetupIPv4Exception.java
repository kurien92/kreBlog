package net.kurien.blog.module.iPAddress.exception;

public class NotSetupIPv4Exception extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NotSetupIPv4Exception() {
		
	}
	
	public NotSetupIPv4Exception(String message) {
		super(message);
	}
}
