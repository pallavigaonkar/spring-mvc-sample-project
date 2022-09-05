package com.util;

public enum PayloadStatusEnum {
	FAIL(-1), SUCCESS(0);

	private int value;

	private PayloadStatusEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
