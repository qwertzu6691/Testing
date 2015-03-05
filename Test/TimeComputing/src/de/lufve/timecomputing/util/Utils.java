package de.lufve.timecomputing.util;

import java.util.List;
import java.util.regex.Pattern;

import android.text.TextUtils;

public abstract class Utils {
	public static final char EMPTY = '\u0000';
	public static final char DELETE = '\u2421';
	public static final char DIVISHION = '\u00F7';
	public static final char MINUS = '-';
	public static final char PLUS = '+';
	public static final int INT = 0;
	public static final int TIME = 1;
	public static final int DOUBLE = 2;
	public static final Character ZERO = '\u0030';
	public static final String REGEX_IS_TIME = "hmsn";
	public static final String REGEX_TIME_STRING = "^[\\+\\-]([1-2]?[0-9]h)?([1-5]?[0-9]m)?([1-5]?[0-9]s)?([0-9]{0,3})?$";

	private static Pattern mPattrIsTime = Pattern.compile(REGEX_IS_TIME);
	private static Pattern mPattrTimeString = Pattern.compile(REGEX_TIME_STRING);

	{
		mPattrIsTime = Pattern.compile(REGEX_IS_TIME);
		mPattrTimeString = Pattern.compile(REGEX_TIME_STRING);
	}

	public static boolean isTimeInput(List <Character> list) {
		return mPattrIsTime.matcher(TextUtils.join("", list)).matches();
	}

	public static boolean verifyIsTimeString(String str) {
		return mPattrTimeString.matcher(str).matches();
	}
}
