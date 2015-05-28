package de.lufve.timecomputing.util;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import de.lufve.timecomputing.CalcNumber;
import android.text.TextUtils;
import android.util.Log;

public abstract class Utils {
	public static final char EMPTY = '\u0000';
	public static final char DELETE = '\u2421';
	public static final char DIVISHION = '\u00F7';
	public static final char MINUS = '\u2212';
	public static final char PLUS = '\u002B';
	public static final int ERROR = 0;
	public static final int TIME = 1;
	public static final int DOUBLE = 2;
	public static final String ZERO = "\u0030";
	public static final String REGEX_IS_TIME = "[hmsn]";
	public static final String REGEX_TIME_STRING = "^[\\\\+\\\\-]([1-2]?[0-9]h)?([1-5]?[0-9]m)?([1-5]?[0-9]s)?([0-9]{0,3}n)?$";

	private static Pattern mPattrIsTime = Pattern.compile(REGEX_IS_TIME);
	private static Pattern mPattrTimeString = Pattern.compile(REGEX_TIME_STRING);

	{
		mPattrIsTime = Pattern.compile(REGEX_IS_TIME);
		mPattrTimeString = Pattern.compile(REGEX_TIME_STRING);
	}

	public static boolean isTimeInput(List <Character> list) {
		String s = TextUtils.join("", list);
		return isTimeInput(s);
	}

	public static boolean isVerifiedTimeString(String str) {
		return mPattrTimeString.matcher(str).matches();
	}

	public static boolean isTimeInput(String number) {
		return mPattrIsTime.matcher(number).find();
	}

	public static String formatTimeString(long number) {

		long milSecInDay = 24 * 3600 * 1000;
		long milSecInHour = 3600 * 1000;
		long milSecInMinute = 60 * 1000;
		long day = 0;
		long hour = 0;
		long minute = 0;
		long second = 0;
		long miliSecond = 0;
		int sign = number < 0 ? -1 : 1;

		long time = (long) number * sign;
		long tmpTime = time;

		if (milSecInDay < time) {
			day = tmpTime / milSecInDay;
			tmpTime = time % milSecInDay;
		}
		if (milSecInHour < time) {
			hour = tmpTime / milSecInHour;
			tmpTime = time % milSecInHour;
		}
		if (tmpTime > 0) {
			minute = tmpTime / milSecInMinute;
			tmpTime = tmpTime % milSecInMinute;
		}
		if (tmpTime > 0) {
			second = tmpTime / 1000;
			tmpTime = tmpTime % 1000;
		}

		miliSecond = tmpTime;
		StringBuilder s = new StringBuilder(20);
		s.append(sign < 0 ? Utils.MINUS : "");
		s.append(day > 0 ? day + "d " : "");
		s.append(hour > 0 ? hour + "h " : "");
		s.append(minute > 0 ? minute + "m " : "");
		s.append(second > 0 ? second + "s " : "");
		s.append(miliSecond > 0 ? miliSecond + "ms" : "");

		return s.toString().trim();
	}

	public static int getPrecedent(BasicCommands op) {
		int r = 15;
		switch (op) {
			case PLUS:
			case MINUS:
				r = 4;
				break;
			case DIVIDE:
			case MULTIPLY:
				r = 3;
				break;
			default:
				break;
		}
		return r;
	}

	public static char getAssociativity(BasicCommands op) {
		char c = 'L';
		switch (op) {
			case PLUS:
			case MINUS:
			case DIVIDE:
			case MULTIPLY:
				c = 'L';
				break;
			default:
				break;
		}
		return c;
	}

	public static String convertTimeStrings(CalcNumber calcNumber, int count) {
		long num = (long) calcNumber.getValue();
		double returnValue = 0;
		if (count == 0) { // common
			return formatTimeString(num);
		} else if (count == 1) { // days
			returnValue = num / (24 * 3600 * 1000);
		} else if (count == 2) { // hours
			returnValue = num / (3600 * 1000);
		} else if (count == 3) { // minutes
			returnValue = num / (60 * 1000);
		} else if (count == 4) { // seconds
			returnValue = num / 1000;
		} else if (count == 5) { // reserved

		}
		return Double.toString(returnValue);
	}

	public static long convertStringToTime(String timeString) {
		StringBuilder s = new StringBuilder();
		long miliSecond = 0;
		String localString = timeString.replace("mc", "n");
		for (char character : localString.toCharArray()) {
			if (character == 'd') {
				miliSecond += Long.decode(s.toString()) * 24 * 3600 * 1000;
				s = new StringBuilder();
			}
			else if (character == 'h') {
				miliSecond += Long.decode(s.toString()) * 3600 * 1000;
				s = new StringBuilder();
			}
			else if (character == 'm') {
				miliSecond += Long.decode(s.toString()) * 60 * 1000;
				s = new StringBuilder();

			} else if (character == 's') {
				miliSecond += Long.decode(s.toString()) * 1000;
				s = new StringBuilder();

			} else if (character == 'n') {
				miliSecond += Long.decode(s.toString());
				s = new StringBuilder();

			} else if (Character.isDigit(character)) {
				s.append(character);
			}
		}
		String tmp = s.toString();
		if (tmp.length() > 0) {
			miliSecond += Long.decode(tmp);
		}
		if (miliSecond == 0) {
			try {
				miliSecond = Integer.parseInt(s.toString());
			}
			catch (NumberFormatException e) {}

		}

		return miliSecond;
	}
}
