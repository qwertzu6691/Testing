package de.lufve.timecomputing;

import java.util.ArrayList;

import android.app.Activity;
import android.widget.TextView;
import de.lufve.timecomputing.util.DisplayEventListener;

public class CalcDisplay {

	private static CalcDisplay mInstance = null;
	private Activity mParent;
	private TextView mViewDisplay;
	private TextView mClipBoard;
	private ArrayList <String> mHistory;

	private DisplayEventListener mListener = null;
	private boolean mNewOperation;

	@SuppressWarnings("unused")
	private static final String mTag = "CalcDisplay";

	private CalcDisplay(Activity parent) {
		this.mParent = parent;
		mViewDisplay = (TextView) mParent.findViewById(R.id.txtDisplay);
		mClipBoard = (TextView) mParent.findViewById(R.id.txtMemorryField);
		mHistory = new ArrayList <String>();

	}

	public void setEventListener(DisplayEventListener listener) {
		mListener = listener;
	}

	public static CalcDisplay getInstance(Activity parent) {
		if (parent == null) throw new NullPointerException();
		if (mInstance == null) {
			mInstance = new CalcDisplay(parent);
			// mInstance.mParent = parent;
		}
		return mInstance;

	}

	@SuppressWarnings("unused")
	private void moveHistoryToCache(String newHistory) {
		String lastLine = mClipBoard.getText().toString();
		if (!lastLine.isEmpty()) mListener.onNewLine(lastLine);
		if (newHistory == null || newHistory == "") {
			mClipBoard.setText("");
		} else {
			mClipBoard.setText(newHistory);
		}
	}

	public void clear() {
		mViewDisplay.setText("");
		addNewHistoryLine();
	}

	public void markAsResult() {
		if (!mHistory.contains("=")) updateHistory("=");
		mNewOperation = true;
	}

	// public void setResult(String part) {
	// if (part == null || part.isEmpty()) return;
	//
	// mViewDisplay.setText(part);
	// }

	public void setInput(String input) {
		if (input == null) mViewDisplay.setText("");
		else mViewDisplay.setText(input);
	}

	// public void setIterimResult(String part) {
	// mClipBoard.setText(part);
	// }

	// public void addToClipBoard(String history) {
	// if (history == null || history.isEmpty()) {
	// if (mHistory.get(mHistory.size() - 1) != "") mHistory.add("");
	// } else mHistory.add(history);
	// updateClipBoard();
	// }
	//
	public void addNewHistoryLine() {
		mHistory.add("");
		mClipBoard.setText("");
	}

	// public void appendToHistory(String append) {
	// int count = mHistory.size() - 1;
	// if (count < 0 || append == null) return;
	// String tmp = mHistory.get(count);
	// mHistory.set(count, tmp + append);
	// mClipBoard.setText(mHistory.get(count));
	// }
	//
	public void updateHistory(String update) {
		if (mNewOperation) {
			mNewOperation = false;
			addNewHistoryLine();
		}
		int count = mHistory.size() - 1;
		if (count < 0 || update == null) return;
		mHistory.set(count, update);
		mClipBoard.setText(mHistory.get(count));
	}
	//
	// public void updateClipBoard() {
	// if (mHistory.isEmpty()) return;
	// mClipBoard.setText(mHistory.get(mHistory.size() - 1));
	// }
}

// private List <Character> mList = new ArrayList <Character>();
// private CalcPipeLine mCalcLine;
// private boolean mNewLine = false;
// mList.add(PLUS);
// mCalcLine = CalcPipeLine.getInstance(parent);

// private CalcNumber mParm1;
// private CalcNumber mParm2;
// private InputNumber mResult;
// private String mInput;
// private BasicCommands mComm = BasicCommands.NOTHING;
// private boolean mIsTimeOp;

// private static final char PLUS = Utils.PLUS;
// private static final char MINUS = Utils.MINUS;
// private static final char[] mTimeCharOrder = { 'd', 'h', 'm', 's', 'n' };
// private boolean mSingelPart;

// private static final String mReg = "hmsn";
// private static final String mRegTime =
// "^([0-9][1-9]?h)?([0-9][1-9]?m)?([0-9][1-9]?s{1})?([0-9]{0,3})?$";

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

// public void addBasicOperation(final BasicCommands op) {
//
// String frm = null;
// CalcNumber result;
//
// if (mComm.isOperations()) {
// mIsTimeOp = Utils.isTimeInput(mList);
// result = calc();
// if (op.isOperations()) {
// // res = res + op.getValue();
// frm = formatHistoryString(op, result.toString());
// moveHistoryToCache(frm);
// deleteDisplay(BasicCommands.RESET);
// // updateDisplay(Utils.ZERO);
//
// }
// else if (op == BasicCommands.RESULT) {
// // frm = op.getValue() + res;
// frm = formatHistoryString(mComm, mParm1.toString(), mParm2.toString());
// moveHistoryToCache(frm);
// updateDisplay(result.toString());
// }
// mNewLine = true;
// mComm = op;
// mParm1 = result;
// mParm2.reset();
// // mResult.clear();
//
// // mListener.onNewLine(mShortHistory.getText().toString());
// // updateDisplay(frm, "");
//
// } else {
// mIsTimeOp = Utils.isTimeInput(mList);
// mComm = op;
// try {
// mParm1 = new CalcNumber(mList);
// }
// catch (Exception e) {
// // e.printStackTrace();
// Log.e(mTag, "addBasicOperation: " + "False Timestring " + mList);
// }
// frm = formatHistoryString(op, mParm1.toString());
// System.out.println(mParm1);
// moveHistoryToCache(frm);
// deleteDisplay(BasicCommands.RESET);
// // updateDisplay(Utils.ZERO);

// private void deleteInput(BasicCommands op) {
//
// }

// private void updateDisplay(Object input) {
// if (input instanceof Character) {
// if ((Character) input == 'n') {
// mViewDisplay.append("ms");
// } else {
// mViewDisplay.append(input.toString());
// }
// mList.add((Character) input);
// } else if (input instanceof String) {
// mViewDisplay.setText((String) input);
// mList.clear();
// mList.add(PLUS);
// }
//
// }

// public void deleteDisplay(BasicCommands op) {
//
// if (op == BasicCommands.RESET || op == BasicCommands.CLEAR || mList.size() ==
// 2) {
// mViewDisplay.setText("");
// if (op == BasicCommands.CLEAR) moveHistoryToCache(null);
// mList.clear();
// mList.add(PLUS);
// mIsTimeOp = false;
// Log.i(mTag, "deleteDisplay " + op + " " + mList);
// } else if (op == BasicCommands.DELETE && mList.size() > 2) {
// @SuppressWarnings("unused")
// char c = mList.remove(mList.size() - 1);
// mIsTimeOp = Utils.isTimeInput(mList);
// List <Character> list = mList.subList(1, mList.size() - 1);
// mViewDisplay.setText((mList.get(0) == PLUS ? "" : MINUS) + TextUtils.join("",
// list));
// }
//
// }

// public void setPlusMinus() {
// char c = mList.get(0);
// // char c1 = (c == MINUS ? PLUS : MINUS);
// // mList.set(0, c1);
// CharSequence cd = mViewDisplay.getText();
// String s = "";
// if (cd.charAt(0) == MINUS) {
// if (c == PLUS) {
// System.out.println("Falsche setzug der Vorzeichens");
// }
// s = cd.toString().substring(1);
// mList.set(0, PLUS);
// } else {
// s = MINUS + cd.toString();
// mList.set(0, MINUS);
// }
// mViewDisplay.setText(s);
// }

// public void addPoint() {
// //
//
// }
//
// public void setSinglePart(boolean isChecked) {
// mSingelPart = isChecked;
// }
// }
// // mList.clear();
// // mList.add(PLUS);
// }

// void verifyInput(List<Character> list) {
// // mTime = true;
// // if (!mTime) { throw new UnsupportedOperationException(); }
// //
// // boolean b;
// mTime = mPattr.matcher(TextUtils.join("", list)).matches();
// }
//
// static boolean verifyInputOrder(List<Character> list,Character inputChar)
// {
// return mPattrTimeInput.matcher(TextUtils.join("", list) +
// inputChar).matches();
// }

// @SuppressWarnings("incomplete-switch")
// private CalcNumber calc() {
// try {
// mParm2 = new CalcNumber(mList);
// }
// catch (Exception e) {
// // e.printStackTrace();
// Log.e(mTag, "calc: " + "False Timestring " + mList);
// }
// // double result = calcTime((long) mParm1.getValue(), (long)
// // mParm2.getValue(), mComm);
// double part1 = mParm1.getValue();
// double part2 = mParm2.getValue();
// double res = 0;
// switch (mComm) {
// case PLUS:
// res = part1 + part2;
// break;
// case MINUS:
// res = part1 - part2;
// break;
// case MULTIPLY:
// res = part1 * part2;
// break;
// case DIVIDE:
// if (part2 == 0) throw new
// ArithmeticException("divide by zero is not possible");
// res = part1 / part2;
// break;
// }
// boolean isTimeOp = mParm1.getTypeNumber() == Utils.TIME ||
// mParm2.getTypeNumber() == Utils.TIME;
// return new CalcNumber(res, isTimeOp ? Utils.TIME : Utils.DOUBLE);
// }

// public void addChar(Character ch) {
// if (mNewLine) {
// String parm = mViewDisplay.getText().toString();
// moveHistoryToCache(parm);
// mNewLine = false;
// mIsTimeOp = false;
// deleteDisplay(BasicCommands.RESET);
// // mViewDisplay.setText(Utils.ZERO);
// }
// // String s = TextUtils.join("", mList);
// char c = ch;
// if (ch == 'n') {
//
// }
// if (Utils.isVerifiedTimeString(TextUtils.join("", mList) + c)) {
// Log.i(mTag, "addChar " + c);
// // mList.add(ch);
// updateDisplay(c);
// }
// }

// public void addTimeChar(Character ch) {
// if (mList.size() == 1) {
// addChar('0');
// }
//
// mNewLine = false;
// mIsTimeOp = true;
// addChar(ch);
// }
//
// public boolean mustBeTimeInput() {
// return mIsTimeOp;
// }
//
// public void addBasicOperation(final BasicCommands op) {
//
// }

// private String formatHistoryString(BasicCommands comm, String... parm) {
// StringBuilder build = new StringBuilder();
// String tmp1 = parm[0];
// build.append(tmp1);
// build.append(comm.getValue());
// if (parm.length > 1) {
// String tmp2 = parm[1];
// build.append(tmp2);
// build.append(" =");
// }
//
// return build.toString();
// }

// private String formatHistoryString(BasicCommands comm, long... parm) {
// return null;
//
// }

// private String formatHistoryString(BasicCommands comm, String parm) {
// StringBuilder build = new StringBuilder();
// build.append(parm);
// build.append(comm.getValue());
// System.out.println(build);
// return build.toString();
// }

// private void clearCache() {
// String lastLine = null;

//
// mListener.onNewLine(lastLine);
//
// }

