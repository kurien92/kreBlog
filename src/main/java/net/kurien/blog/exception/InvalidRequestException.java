package net.kurien.blog.exception;

/**
 * 잘못된 형태의 요청이 들어오는 경우 발생하는 예외
 */
public class InvalidRequestException extends Exception {
	private static final long serialVersionUID = -357843356921318530L;

	public InvalidRequestException () {
		super("잘못된 요청입니다.");
	}
	
	public InvalidRequestException(String msg) {
		super(msg);
	}
}
