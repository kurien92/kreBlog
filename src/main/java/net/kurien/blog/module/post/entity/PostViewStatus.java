package net.kurien.blog.module.post.entity;

public enum PostViewStatus {
	FALSE(0), TRUE(1);

	private final int value;

	PostViewStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static PostViewStatus valueOf(int value) {
		switch(value) {
			case 0:
				return PostViewStatus.FALSE;
			case 1:
				return PostViewStatus.TRUE;
			default:
				throw new AssertionError("Unknown status: " + value);
		}
	}
}