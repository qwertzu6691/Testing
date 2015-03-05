package de.lufve.timecomputing;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.TextView;
import de.lufve.timecomputing.util.BasicCommands;
import de.lufve.timecomputing.util.Utils;

public class CalcDisplay {

	private static CalcDisplay mInstance = null;
	private Activity mParent;
	private List <Character> mList = new ArrayList <Character>();
	private TextView mViewDisplay;
	private TextView mShortHistory;
	private InputNumber mParm1;
	private InputNumber mParm2;
	private InputNumber mResult;
	// private String mInput;
	private BasicCommands mComm = BasicCommands.NOTHING;
	private boolean mIsTimeOp;
	private EventListener mListener = null;
	private boolean mNewLine = false;
	private static final char EMPTY = Utils.EMPTY;
	private static final char MINUS = Utils.MINUS;

	// private static final String mReg = "hmsn";
	// private static final String mRegTime =
	// "^([0-9][1-9]?h)?([0-9][1-9]?m)?([0-9][1-9]?s{1})?([0-9]{0,3})?$";

	private CalcDisplay(Activity parent) {
		this.mParent = parent;
		mViewDisplay = (TextView) mParent.findViewById(R.id.txtDisplay);
		mShortHistory = (TextView) mParent.findViewById(R.id.txtMemorryField);
		mList.add(EMPTY);
	}

	// {
	// Pattern.compile(mReg);
	// Pattern.compile(mRegTime);
	// }

	public void setEventListener(EventListener listener) {
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

	public void addChar(Character ch) {
		if (mNewLine) {
			String parm = mResult.toString();
			moveNumberToCache(parm);
			mNewLine = false;
			mIsTimeOp = false;
			mViewDisplay.setText(Utils.ZERO);
		}

		String s=TextUtils.join("", mList);
		if (Utils.verifyIsTimeString(TextUtils.join("", mList) + ch)) {
			mList.add(ch);
			updateDisplay(ch);
		}
	}

	public void addTimeChar(Character ch) {
		if (mList.size()==1) {
			addChar('0');
		}
		mNewLine=false;
		mIsTimeOp = true;
		addChar(ch);
	}

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

	public boolean mustBeTimeInput() {
		return mIsTimeOp;
	}

	public void addBasicOperation(final BasicCommands op) {

		String frm = null;

		if (mComm.isOperations()) {
			mIsTimeOp = Utils.isTimeInput(mList);
			calc();
			if (op.isOperations()) {
				// res = res + op.getValue();
				frm = formatHistoryString(op, mResult.toString());
				moveNumberToCache(frm);
				updateDisplay(Utils.ZERO);

			}
			else if (op == BasicCommands.RESULT) {
				// frm = op.getValue() + res;
				frm = formatHistoryString(mComm, mParm1.toString(), mParm2.toString());
				moveNumberToCache(frm);
				updateDisplay(BasicCommands.RESULT.getValue() + " " + mResult.toString());
			}
			mNewLine = true;
			mComm = op;
			mParm1 = mResult;
			mParm2.clear();
			mResult.clear();

			// mListener.onNewLine(mShortHistory.getText().toString());
			// updateDisplay(frm, "");

		} else {
			mIsTimeOp = Utils.isTimeInput(mList);
			mComm = op;
			mParm1 = new InputNumber(mList);
			frm = formatHistoryString(op, mParm1.toString());
			moveNumberToCache(frm);
			updateDisplay(Utils.ZERO);
		}
		mList.clear();
		mList.add(EMPTY);
	}

	private String formatHistoryString(BasicCommands comm, String... parm) {
		StringBuilder build = new StringBuilder();
		String tmp1 = parm[0];
		build.append(tmp1);
		build.append(comm.getValue());
		if (parm.length > 1) {
			String tmp2 = parm[1];
			build.append(tmp2);
		}

		return build.toString();
	}

	// private String formatHistoryString(BasicCommands comm, long... parm) {
	// return null;
	//
	// }

	private String formatHistoryString(BasicCommands comm, String parm) {
		StringBuilder build = new StringBuilder();
		build.append(parm);
		build.append(comm.getValue());
		return build.toString();
	}

	@SuppressWarnings("incomplete-switch")
	private void calc() {
		mParm2 = new InputNumber(mList);
		// double result = calcTime((long) mParm1.getValue(), (long)
		// mParm2.getValue(), mComm);
		double part1 = mParm1.getValue();
		double part2 = mParm2.getValue();
		double res = 0;
		switch (mComm) {
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
				if (part2 == 0) throw new ArithmeticException("divide by zero is not possible");
				res = part1 / part2;
				break;
		}
		mResult = new InputNumber(res, mIsTimeOp ? Utils.TIME : Utils.DOUBLE);
	}

	// private void clearCache() {
	String lastLine = null;

	//
	// mListener.onNewLine(lastLine);
	//
	// }

	private void moveNumberToCache(String parm) {
		String lastLine = mShortHistory.getText().toString();
		if (!lastLine.isEmpty()) mListener.onNewLine(lastLine);
		mShortHistory.setText(parm);
	}

	private void deleteInput(BasicCommands op) {
		if (op == BasicCommands.CLEAR) {
			mViewDisplay.setText("");
			mList.clear();
		} else {
			// char c = mList.remove(mList.size() - 1);
			mIsTimeOp = Utils.isTimeInput(mList);
			mViewDisplay.setText(TextUtils.join("", mList));
		}

	}

	private void updateDisplay(Object input) {
		if (input instanceof String) {
			mViewDisplay.setText((String) input);
		}
		else if (input instanceof Character) {
			mViewDisplay.append(input.toString());
		}

	}

	public void resumeInput(BasicCommands op) {
		deleteInput(op);
	}

	public void setPlusMinus() {
		char c = mList.get(0);
		mList.set(0, (c == MINUS ? EMPTY : MINUS));
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
