package net.kurien.blog.module.post.entity;

public enum PostPublishStatus {
	FALSE(0), TRUE(1);

	private final int value;

	PostPublishStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static PostPublishStatus valueOf(int value) {
		switch(value) {
			case 0: return PostPublishStatus.FALSE;
			case 1: return PostPublishStatus.TRUE;
			default: throw new AssertionError("Unknown gender: " + value);
		}
	}
}
