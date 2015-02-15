package de.lufve.timecomputing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.widget.TextView;

public class CalcDisplay {

	private static CalcDisplay mInstance = null;
	private Activity mParent;
	private List <Character> mList = new ArrayList <Character>();
	private TextView mViewDisplay;
	private TextView mShortHistory;
	private long mParm1;
	private long mParm2;
	private long mResult;
	private BasicCommands mComm = BasicCommands.NOTHING;
	private boolean mTime;
	private EventListener mListener = null;

	private CalcDisplay() {
		mViewDisplay = (TextView) mParent.findViewById(R.id.txtDisplay);
		mShortHistory = (TextView) mParent.findViewById(R.id.txtMemorryField);
	}

	public void setEventListener(EventListener listener) {
		mListener = listener;
	}

	public static CalcDisplay getInstance(Activity parent) {
		if (parent == null) throw new NullPointerException();
		if (mInstance == null) {
			mInstance = new CalcDisplay();
			mInstance.mParent = parent;
		}
		return mInstance;

	}

	public void addChar(Character ch) {
		mList.add(ch);
	}

	public void addBasicOperation(final BasicCommands op) {

		BasicCommands opTMP = BasicCommands.NOTHING;

		// if (mComm == BasicCommands.NIX) {
		// mComm = op;
		// moveNumberToCache();
		// }
		// else {
		// if (op.isOperations() && mComm.isOperations()) {
		//
		// } else if (mComm.isOperations() && op == BasicCommands.RESULT) {
		//
		// }
		//
		// }

		String res = null;
		String frm = null;
		if (op.isErasing()) {
			deleteInput(op);
		} else if (mComm.isOperations()) {
			verivyInput();
			calc();
			res = convertNumberToString(mResult);

			if (op.isOperations()) {
				// res = res + op.getValue();
				frm = formatString(res, mComm);
				updateDisplay(frm, "");
			}
			else if (op == BasicCommands.RESULT) {
				frm = op.getValue() + res;
				frm = formatString(mComm, mParm1, mParm2);
				updateDisplay(frm, format(res));
			}
			mComm = op;
			mParm1 = mResult;
			mParm2 = 0;
			mResult = 0;
			// mListener.onNewLine(mShortHistory.getText().toString());
			updateDisplay(frm, "");

		} else {
			
			moveNumberToCache();
		}

	}

	private String format(String res) {
		// TODO
		String str = null;
		return str;
	}

	private String formatString(BasicCommands comm, double... parm) {
		// TODO
		String str = null;
		return str;
	}
	private String formatString(BasicCommands comm, long... parm) {
		// TODO
		String str = null;
		return str;
	}

	private String formatString(String string, BasicCommands comm) {
		String str = null;
		return str;

		// TODO

	}

	private void calc() {
		if (mTime) {
			mParm2 = convertStringToTime();
			calcTime(mParm1, mParm2, mComm);
		}

	}

	private void verivyInput() {
		mTime = true;
		if (!mTime) { throw new UnsupportedOperationException(); }

		// TODO

	}

	private void clearCache() {
		String lastLine = null;

		mListener.onNewLine(lastLine);

	}

	private void moveNumberToCache() {
		String d;
		d="d";
	}

	private void deleteInput(BasicCommands op) {
		if (op == BasicCommands.CLEAR) {
			mViewDisplay.setText("");
		} else {
			CharSequence text = mViewDisplay.getText();
			mViewDisplay.setText(text.subSequence(0, text.length() - 2));
		}

	}

	private void updateDisplay(String cache, String input) {

		String lastLine = null;
		mListener.onNewLine(lastLine);
	}

	private long calcTime(long part1, long part2, BasicCommands op) {
		long res = 0;
		switch (op) {
			case PLUS:
				res = part1 + part2;

				break;
			case MINUS:
				res = part1 - part2;
				break;
			case MULTIPLY:
				res = part1 * part2;
				break;
			case DIVIDE:
				if (part2 == 0) {
					// error massage: teilen durch null ist nicht möglich
					throw new ArithmeticException("divide by zero is not possible");
				}
				else {
					res = part1 / part2;
				}
				break;
			default:
				break;
		}

		return res;
	}

	private long convertStringToTime() {
		StringBuilder s = new StringBuilder();
		long miliSecond = 0;
		for (Iterator <Character> iterator = mList.iterator(); iterator.hasNext();) {
			Character character = (Character) iterator.next();
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
		mList.clear();
		return miliSecond;
	}

	private String convertNumberToString(double number) {
		String str = null;
		return str;

	}

	private String convertNumberToString(long number) {
		String str = null;
		if (mTime) {
			str = convertTimeToString(number);
		}
		return str;

	}

	private String convertTimeToString(long time) {

		long milSecInHour = 3600 * 1000;
		long milSecInMinute = 60 * 1000;
		long hour = 0;
		long minute = 0;
		long second = 0;
		long miliSecond = 0;
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

		return hour + "h " + minute + "m " + second + "m " + miliSecond + "ms";

	}

	private boolean verivyInput(int type, int time) {
		switch (type) {
			case Calendar.HOUR:
				return time <= 24;
			case Calendar.MINUTE:
			case Calendar.SECOND:
				return time <= 60;
		}
		return false;
	}

	private void outputResult(boolean operator) {
		long result = 0;
		String s = null;
		try {
			if (mTime) {
				// TODO
				// result = calcTime(convertStringToTime());
				s = convertNumberToString(result);
				// updateDisplay(s, false, operator);
			}

		}
		catch (Exception e) {
			//
		}

	}

}

// switch (op) {
// case DIVIDE:
//
// mComm = BasicOperation.DIVIDE;
// break;
// case MULTIPLY:
//
// mComm = BasicOperation.MULTIPLY;
// break;
// case PLUS:
//
// mComm = BasicOperation.PLUS;
// break;
// case MINUS:
//
// mComm = BasicOperation.MINUS;
// break;
// case CLEAR:
// deleteInputField(true);
// mComm = BasicOperation.NIX;
// break;
// case DELETE:
// deleteInputField(false);
// mComm = BasicOperation.NIX;
// break;
// case RESULT:
//
// mComm = BasicOperation.NIX;
// break;
// default:
// break;
// }
