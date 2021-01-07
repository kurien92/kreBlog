package net.kurien.blog.module.content.entity;

public enum ContentViewStatus {
	FALSE(0), TRUE(1);

	private final int value;

	ContentViewStatus(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static ContentViewStatus valueOf(int value) {
		switch(value) {
			case 0:
				return ContentViewStatus.FALSE;
			case 1:
				return ContentViewStatus.TRUE;
			default:
				throw new AssertionError("Unknown status: " + value);
		}
	}
}