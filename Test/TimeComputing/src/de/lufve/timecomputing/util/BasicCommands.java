package de.lufve.timecomputing.util;

import java.util.EnumSet;

public enum BasicCommands {

	NOTHING('A'), POINT('.'), COLON(','), PLUS('+'), MINUS('-'), DIVIDE(Utils.DIVISHION),
	MULTIPLY('*'), EQUAL('='), DELETE('D'), CLEAR('C'), RESET('R'),
	LEFTPARENTHESIS('('), RIGHTPARENTHESIS(')');

	private Character mC;

	BasicCommands(char c) {
		mC = c;
	}

	public static final EnumSet <BasicCommands> OPERATIONS = EnumSet.range(PLUS, MULTIPLY);
	public static final EnumSet <BasicCommands> ERASING = EnumSet.range(DELETE, CLEAR);
	public static final EnumSet <BasicCommands> DELIMITERS = EnumSet.range(POINT, COLON);
	public static final EnumSet <BasicCommands> PARENTHESIS = EnumSet.range(LEFTPARENTHESIS, RIGHTPARENTHESIS);

	public final boolean isOperations() {
		return OPERATIONS.contains(this);
	}

	public final boolean isErasing() {
		return ERASING.contains(this);
	}

	public final boolean isDelimiter() {
		return DELIMITERS.contains(this);
	}

	public final boolean isEqualOperation() {
		return this == EQUAL;
	}

	public final boolean isNothing() {
		return this == NOTHING;
	}

	public final boolean isPARENTHESIS() {
		return PARENTHESIS.contains(this);
	}

	public Character getValue() {
		return mC;
	}
}