package net.kurien.blog.exception;

/**
 * 전달된 데이터가 존재하지 않는 경우 발생하는 예외
 * 전달받은 파라미터가 비어있는 경우, null 혹은 List.size() == 0 등
 */

public class EmptyParameterException extends Exception {
	private static final long serialVersionUID = 6578402461846716936L;

	public EmptyParameterException () {
		super("빈 데이터가 전달되었습니다.");
	}
	
	public EmptyParameterException(String msg) {
		super(msg);
	}
}
