package net.kurien.blog.exception;

/**
 * 기본키로 사용되는 프로퍼티의 값이 사용될 수 없는 경우 발생하는 예외
 * Integer형의 경우, null과 0은 기본키로 사용될 수 없다. 
 */

public class NotUsePrimaryKeyException extends Exception {
	private static final long serialVersionUID = -8665725939866139883L;
	
	public NotUsePrimaryKeyException() {
		super("기본키로 사용할 수 없는 값입니다.");
	}
	
	public NotUsePrimaryKeyException(String msg) {
		super(msg);
	}
}
