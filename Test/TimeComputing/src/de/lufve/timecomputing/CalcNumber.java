package de.lufve.timecomputing;

import de.lufve.timecomputing.util.Utils;

class CalcNumber {
	private double mValue = 0;
	private int mTypeNumber = Utils.DOUBLE;
	// private String _string = null;
	@SuppressWarnings("unused")
	private String mTag = "InputNumber";

	// public CalcNumber(List <Character> input) throws Exception {
	// mValue = convertInputToNumber(input);
	// }

	public CalcNumber(double parm, int typeNumber) {
		this.mTypeNumber = typeNumber;
		this.mValue = parm;
	}

	@SuppressWarnings("unused")
	private CalcNumber() {}

	// public CalcNumber(String input) {
	// mValue = convertInputToNumber(input);
	// }

	public int getTypeNumber() {
		return mTypeNumber;
	}

	// public String toString() {
	// if (mTypeNumber == Utils.TIME && _string.isEmpty()) _string =
	// convertTimeToString();
	// else if (mTypeNumber == Utils.DOUBLE) _string = mValue + "";
	//
	// return _string;
	// }

	// private double convertInputToNumber(final List <Character> list) {
	// if (list == null || list.isEmpty()) return 0;
	//
	// String input = TextUtils.join("", list);
	// return convertInputToNumber(input);
	// }
	//
	// private double convertInputToNumber(String input) {
	// // long longNumber = 0;
	// long miliSecond = 0;
	// if (input == null || input.length() == 0) return 0;
	// if (!Utils.isTimeInput(input)) {
	// mTypeNumber = Utils.DOUBLE;
	// try {
	// miliSecond = Long.parseLong(input.toString());
	// Log.i(mTag, "convertInputToNumber. Milisecond: " + miliSecond);
	// }
	// catch (NumberFormatException e) {}
	// } else {
	// StringBuilder s = new StringBuilder();
	//
	// mTypeNumber = Utils.TIME;
	// int sign = 1;
	//
	// for (int i = 0; i < input.length(); i++) {
	// Character character = input.charAt(i);
	// if (character == Utils.MINUS) sign = -1;
	//
	// if (character == 'd') {
	// miliSecond += Integer.parseInt(s.toString()) * 24 * 3600 * 1000;
	// s = new StringBuilder();
	// } else if (character == 'h') {
	// miliSecond += Integer.parseInt(s.toString()) * 3600 * 1000;
	// s = new StringBuilder();
	// }
	// else if (character == 'm') {
	// miliSecond += Integer.parseInt(s.toString()) * 60 * 1000;
	// s = new StringBuilder();
	//
	// } else if (character == 's') {
	// miliSecond += Integer.parseInt(s.toString()) * 1000;
	// s = new StringBuilder();
	//
	// } else if (character == 'n') {
	// miliSecond += Integer.parseInt(s.toString());
	// s = new StringBuilder();
	//
	// } else if (Character.isDigit(character)) {
	// s.append(character);
	// }
	// }
	// miliSecond = miliSecond * sign;
	// }
	//
	// return miliSecond;
	// }

	// private String convertTimeToString() {
	// long milSecInDay = 24 * 3600 * 1000;
	// long milSecInHour = 3600 * 1000;
	// long milSecInMinute = 60 * 1000;
	// long day = 0;
	// long hour = 0;
	// long minute = 0;
	// long second = 0;
	// long miliSecond = 0;
	// int sign = mValue < 0 ? -1 : 1;
	//
	// long time = (long) mValue * sign;
	// long tmpTime = time;
	//
	// if (milSecInDay < time) {
	// day = tmpTime / milSecInDay;
	// tmpTime = time % milSecInDay;
	// }
	// if (milSecInHour < time) {
	// hour = tmpTime / milSecInHour;
	// tmpTime = time % milSecInHour;
	// }
	// if (tmpTime > 0) {
	// minute = tmpTime / milSecInMinute;
	// tmpTime = tmpTime % milSecInMinute;
	// }
	// if (tmpTime > 0) {
	// second = tmpTime / 1000;
	// tmpTime = tmpTime % 1000;
	// }
	//
	// miliSecond = tmpTime;
	// StringBuilder s = new StringBuilder(20);
	// s.append(sign < 0 ? Utils.MINUS : "");
	// s.append(day > 0 ? day + "d " : "");
	// s.append(hour > 0 ? hour + "h " : "");
	// s.append(minute > 0 ? minute + "m " : "");
	// s.append(second > 0 ? second + "s " : "");
	// s.append(miliSecond > 0 ? miliSecond + "ms" : "");
	// Log.i(mTag, sign + " " + hour + " " + minute + " " + " " + second + " " +
	// miliSecond + " " + mValue);
	//
	// return s.toString().trim();
	// }

	public double getValue() {
		return mValue;
	}

	public void reset() {
		mValue = 0;
		mTypeNumber = Utils.DOUBLE;
	}

	// public boolean addNumber(Character nm) {
	//
	// return false;
	// }
	//
	// public boolean addTimeCharachter(Character t) {
	//
	// return true;
	// }
	//
	// public boolean addPoint() {
	//
	// return true;
	// }
	//
	// public boolean deleteLastCarachter() {
	//
	// return true;
	// }

	// public void setPlusMinus() {
	// _value = _value * -1;
	// }

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