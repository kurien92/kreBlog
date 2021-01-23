package net.kurien.blog.common.type;

public enum TrueFalseType {
	FALSE(0), TRUE(1);

	private final int value;

	TrueFalseType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static TrueFalseType valueOf(int value) {
		switch(value) {
			case 0: return TrueFalseType.FALSE;
			case 1: return TrueFalseType.TRUE;
			default: throw new AssertionError("Unknown value: " + value);
		}
	}
}
