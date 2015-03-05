package de.lufve.timecomputing.util;

import java.util.EnumSet;

public enum BasicCommands {

	NOTHING('A'), POINT('.'), COLON(','), PLUS('+'), MINUS('-'), DIVIDE(Utils.DIVISHION),
	MULTIPLY('*'), RESULT('='), DELETE('C'), CLEAR('R');

	private Character mC;

	BasicCommands(char c) {
		mC = c;
	}

	public static final EnumSet <BasicCommands> OPERATIONS = EnumSet.range(PLUS, MULTIPLY);
	public static final EnumSet <BasicCommands> ERASING = EnumSet.range(DELETE, CLEAR);
	public static final EnumSet <BasicCommands> DELIMITERS = EnumSet.range(POINT, COLON);

	public final boolean isOperations() {
		return OPERATIONS.contains(this);
	}

	public final boolean isErasing() {
		return ERASING.contains(this);
	}

	public final boolean isDelimiter() {
		return DELIMITERS.contains(this);
	}

	public final boolean isResult() {
		return this == RESULT;
	}

	public final boolean isNothing() {
		return this == NOTHING;
	}

	public String getValue() {
		return mC.toString();
	}
}