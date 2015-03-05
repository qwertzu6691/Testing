package de.lufve.timecomputing;

import java.util.Iterator;
import java.util.List;

import android.text.TextUtils;
import de.lufve.timecomputing.util.Utils;

class InputNumber {
	private double _value = 0;
	private int _typeNumber = 0;
	private String _string = "";

	public InputNumber(List <Character> parm) {
		if (Utils.isTimeInput(parm)) {
			_value = convertInputToTime(parm);
			_typeNumber = Utils.TIME;
		} else {
			_string = TextUtils.join("", parm);
			_value = Double.parseDouble(_string);
		}
	}

	public InputNumber(double parm, int typeNumber) {
		this._typeNumber = typeNumber;
		this._value = parm;
	}

	public int getTypeNumber() {
		return _typeNumber;
	}

	public String toString() {
		if (_typeNumber == Utils.TIME && _string.isEmpty()) _string = convertTimeToString();
		return _string;
	}

	private long convertInputToTime(final List <Character> list) {
		if (list == null || list.isEmpty()) return 0;

		StringBuilder s = new StringBuilder();
		long miliSecond = 0;
		int mSign = 1;

		for (Iterator <Character> iterator = list.iterator(); iterator.hasNext();) {
			Character character = iterator.next();
			if (character == Utils.MINUS) {
				mSign = -1;
			}
			if (character == 'h') {
				miliSecond += Integer.parseInt(s.toString()) * 3600 * 1000;
				s = new StringBuilder();
			}
			else if (character == 'm') {
				miliSecond += Integer.parseInt(s.toString()) * 60 * 1000;
				s = new StringBuilder();

			} else if (character == 's') {
				miliSecond += Integer.parseInt(s.toString()) * 1000;
				s = new StringBuilder();

			} else if (character == 'n') {
				miliSecond += Integer.parseInt(s.toString());
				s = new StringBuilder();

			} else if (Character.isDigit(character)) {
				s.append(character);
			}
		}
		if (miliSecond == 0) {
			try {
				miliSecond = Integer.parseInt(s.toString());
			}
			catch (NumberFormatException e) {}
		}
		return miliSecond * mSign;
	}

	private String convertTimeToString() {
		long milSecInHour = 3600 * 1000;
		long milSecInMinute = 60 * 1000;
		long hour = 0;
		long minute = 0;
		long second = 0;
		long miliSecond = 0;
		int sign = _value < 0 ? -1 : 1;

		long time = (long) _value * sign;
		long tmpTime = time;

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
		s.append(hour > 0 ? hour + "h " : "");
		s.append(minute > 0 ? minute + "m " : "");
		s.append(second > 0 ? second + "s " : "");
		s.append(miliSecond > 0 ? miliSecond + "ms" : "");

		return s.toString().trim();
	}

	public double getValue() {
		return _value;
	}

	public void clear() {
		_value = 0;
		_string = "";
		_typeNumber = Utils.DOUBLE;
	}

}
// private boolean verivyInput(int type, int time) {
// switch (type) {
// case Calendar.HOUR:
// return time <= 24;
// case Calendar.MINUTE:
// case Calendar.SECOND:
// return time <= 60;
// }
// return false;
// }
//
// private void outputResult(boolean operator) {
// long result = 0;
// String s = null;
// try {
// if (mTime) {
// //
// // result = calcTime(convertStringToTime());
// s = convertNumberToString(result);
// // updateDisplay(s, false, operator);
// }
//
// }
// catch (Exception e) {
// //
// }
//
// }