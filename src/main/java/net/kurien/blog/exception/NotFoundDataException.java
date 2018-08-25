package net.kurien.blog.exception;

/**
 * 해당 데이터가 존재하지 않는 경우 발생하는 예외
 * 이미 삭제되거나, 처음부터 존재하지 않았던 데이터
 */

public class NotFoundDataException extends Exception {
	private static final long serialVersionUID = -6335211481427236904L;

	public NotFoundDataException () {
		super("존재하지 않는 데이터입니다.");
	}
	
	public NotFoundDataException(String msg) {
		super(msg);
	}
}
