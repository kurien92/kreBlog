package net.kurien.blog.exception;

/**
 * 같은 값을 가진 데이터가 존재하는 경우 발생하는 예외
 * 기본키, 유니크키로 설정된 데이터가 같은 값을 가지는 경우가 있어선 안된다.
 */
public class DuplicatedKeyException extends Exception {
	private static final long serialVersionUID = -3672026612143572284L;

	public DuplicatedKeyException () {
		super("중복된 값을 가진 키가 존재합니다.");
	}
	
	public DuplicatedKeyException(String msg) {
		super(msg);
	}
}
