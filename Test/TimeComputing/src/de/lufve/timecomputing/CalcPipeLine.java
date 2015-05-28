package de.lufve.timecomputing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.text.TextUtils;
import de.lufve.timecomputing.util.BasicCommands;
import de.lufve.timecomputing.util.NumberEvent;
import de.lufve.timecomputing.util.NumberListerner;
import de.lufve.timecomputing.util.Utils;

public class CalcPipeLine {
	private static CalcPipeLine mInstance;

	private LinkedList <Object> mStack;

	@SuppressWarnings("unused")
	private Activity mParent;
	@SuppressWarnings("unused")
	private int mParenthesisCount = 0;

	@SuppressWarnings("unused")
	private static final String mTag = "NumberPipeLine";

	// private String mInputString;
	private CalcDisplay mDisplay;
	private boolean mSinglePart = true;

	NumberInput mInputNumber;

	private int mEqualClickCount = 0;

	private CalcPipeLine(Activity parent) {
		this.mParent = parent;
		mStack = new LinkedList <Object>();
		mInputNumber = NumberInput.getInstance(parent);
		mInputNumber.addNumberListerner(new NumberListerner() {

			@Override
			public void onSetInput(NumberEvent ev) {
				mDisplay.setInput(ev.getNumberString());
			}

			@Override
			public void onChanged(NumberEvent event) {
				// mDisplay.updateInput(event.getNumberString());
			}

			@Override
			public void onClear() {
				mDisplay.clear();
			}

			// @Override
			// public void onSetResult(NumberEvent event) {
			// mDisplay.setResult(event.getNumberString());
			// }

			// @Override
			// public void onSetIterimResult(NumberEvent event) {
			// // mDisplay.setIterimResult();
			// }
		});

	}

	public static CalcPipeLine getInstance(Activity parent) {
		if (parent == null) throw new NullPointerException();
		if (mInstance == null) mInstance = new CalcPipeLine(parent);

		return mInstance;
	}

	public void setDisplay(CalcDisplay display) {
		mDisplay = display;
	}

	public void setMultiPart(boolean multiPart) {
		mSinglePart = !multiPart;
	}

	public void clear() {
		mStack.clear();
		mDisplay.clear();
		mInputNumber.reset();
	}

	public boolean doCalculation() {
		if (!mInputNumber.isEmpty()) updateItem(mInputNumber.getNumber());

		if (mStack.size() == 0 && mInputNumber.getNumberType() == Utils.TIME) {
			String displaySring = Utils.convertTimeStrings(mInputNumber.getNumber(), mEqualClickCount);
			mInputNumber.reInitInput(displaySring);
			if (mEqualClickCount < 4) mEqualClickCount++;
			else mEqualClickCount = 0;

			return true;
		}
		mInputNumber.mNewOperation = true;
		mEqualClickCount = 0;
		return mInputNumber.reInitInput(getResult());
	}

	public boolean addOperand(CalcNumber nm) {
		if (mStack.isEmpty() || mStack.getLast() instanceof BasicCommands) {
			// mStack.add(nm);
			updateItem(nm);

			return true;
		}
		return false;
	}

	public boolean addOperand(String archivedValue) {
		if (Utils.isVerifiedTimeString(archivedValue)) {
			Long time = Utils.convertStringToTime(archivedValue);
			CalcNumber num = new CalcNumber(time, Utils.TIME);
			updateItem(num);
			return true;
		}
		return false;
	}

	public boolean addOperator(BasicCommands op) {

		boolean returnValue = false;

		// always
		if (!mInputNumber.isEmpty()) mStack.add(mInputNumber.getNumber());
		if (mStack.size() == 0) return false;
		// always too

		Object lastItem = mStack.getLast();

		BasicCommands opTMP;
		if (lastItem instanceof BasicCommands) {
			opTMP = (BasicCommands) op;

			if (Utils.getAssociativity(opTMP) == 'R') {
				if (mSinglePart) updateItem(getResult(), op); // actually it is
																// not
																// possible

				else updateItem(op);

				mInputNumber.reset();
				returnValue = true;
			} else {
				// TODO
				// replace last operator
			}

		} else {
			if (lastItem instanceof CalcNumber) {
				int count = Utils.getAssociativity(op) == 'R' ? 2 : 3;

				if (mSinglePart) {
					if (mStack.size() == count) updateItem(getResult(), op);
				}
				else updateItem(op);

				mInputNumber.reset();
				returnValue = true;
			}

		}
		/**
		 * * 1- bilateral Operator 2- Unary operator 4- Empty
		 */
		int flagLastItem;
		if (lastItem instanceof BasicCommands) {
			BasicCommands tmpOp = (BasicCommands) lastItem;
			if (Utils.getAssociativity(tmpOp) == 'L') flagLastItem = 1;
			else flagLastItem = 2;
		}
		else flagLastItem = 4;

		int flagOperator = 0;
		if (Utils.getAssociativity(op) == 'L') flagOperator = 1;
		else flagOperator = 2;

		int flagInput = 0;
		if (mInputNumber.isEmpty()) flagInput = 2;
		else flagInput = 1;

		if ((flagLastItem == 1 && flagInput == 2) || (flagLastItem == 2 && flagInput == 1)) return false;
		if (flagLastItem == 2 && flagInput == 2 && mSinglePart) return false;
		// if (mInputNumber.isEmpty()) {
		// if (flagLastItem == 1) return false;
		// } else {
		// if (flagLastItem == 2 || flagLastItem == 4) return false;

		if (flagLastItem == 4 && flagInput == 2) mInputNumber.addCharachter('0');
		// }
		// CalcNumber resultNumber;
		// if (!mInputNumber.isEmpty()) mStack.add(mInputNumber.getNumber());
		// mStack.add(op);

		// if (flagLastItem == 1) mStack.add(mInputNumber.getNumber());
		if (mSinglePart) {
			if (flagLastItem == 4) {
				updateItem(mInputNumber.getNumber(), op);
				if (flagOperator == 2) {
					mInputNumber.reInitInput(getResult());
					mDisplay.markAsResult();
				} else mInputNumber.reset(true);

			} else if (flagLastItem == 1) {
				updateItem(mInputNumber.getNumber());
				getResult();
				updateItem(op);
				if (flagOperator == 2) {
					mInputNumber.reInitInput(getResult());
					mDisplay.markAsResult();
				} else mInputNumber.reset(true);

			}
		} else {
			if (flagLastItem == 1 || flagLastItem == 4) updateItem(mInputNumber.getNumber());
			updateItem(op);
		}
		return returnValue;
	}

	private void updateItem(Object... ob) {
		for (Object object : ob) {
			mStack.add(object);
		}
		mDisplay.updateHistory(this.toString());
	}

	public String toString() {
		if (mStack.isEmpty()) return "";
		StringBuilder tmp = new StringBuilder();
		for (Object object : mStack) {
			if (object instanceof BasicCommands) tmp.append(((BasicCommands) object).getValue());
			else tmp.append(((CalcNumber) object).toString());
		}
		return tmp.toString();
	}

	public boolean addLeftParenthesis() {
		// TODO
		mParenthesisCount++;
		return true;
	}

	public boolean addRightParenthesis() {
		// TODO
		mParenthesisCount--;
		return true;
	}

	public CalcNumber getResult() {
		List <Object[]> list = getOrderOperation();
		if (list != null) {
			for (Object[] ob : list) {
				int index = (Integer) ob[1];
				BasicCommands op = (BasicCommands) ob[0];
				char ch = Utils.getAssociativity(op);
				if (ch == 'L') {
					if (index > 0 && index + 1 < mStack.size()) {
						CalcNumber num1 = (CalcNumber) mStack.get(index - 1);
						CalcNumber num2 = (CalcNumber) mStack.get(index + 1);
						CalcNumber res = Operation.getResult(num1, num2, op);
						mStack.set(index - 1, res);
						mStack.remove(index + 1);
						mStack.remove(index);
					} else {
						// if there is one operand only

					}
				}
			}
			// what to do after calculation
			if (mStack.size() == 1) {
				Object ob = mStack.get(0);
				if (ob instanceof CalcNumber) { return (CalcNumber) ob; }
			} else if (mStack.size() == 2) {
				// TODO
			}
		}
		return null;
	}

	/**
	 * 
	 * @return the list with three elements index 0 - operation self index 1 -
	 *         index of operation in the Stack index 2 - precedent of operation
	 */
	private List <Object[]> getOrderOperation() {
		List <Object[]> opList = new ArrayList <Object[]>();
		Iterator <Object> iter = mStack.iterator();
		Object ob;
		boolean verified = true;

		do {
			if (iter.hasNext()) ob = iter.next();
			else break;
			if (ob instanceof BasicCommands) {

				int p = Utils.getPrecedent((BasicCommands) ob);
				int index = mStack.indexOf(ob);
				Object[] part = new Object[3];
				part[0] = (BasicCommands) ob;
				part[1] = index;
				part[2] = p;
				opList.add(part);
				for (int j = opList.size() - 1; j >= 1; j--) {
					Object[] o1 = opList.get(j - 1);
					Object[] o2 = opList.get(j);
					int p1 = (Integer) o1[2];
					int p2 = (Integer) o2[2];
					if (p2 > p1) {
						opList.set(j - 1, o2);
						opList.set(j, o1);
					}
				}

			}

		}
		while (true);
		return verified ? opList : null;
	}

	@SuppressWarnings("unused")
	private void fireNumberEvent(NumberEvent ev) {
		// TODO Auto-generated method stub

	}

	static class NumberInput {

		private static NumberInput mInstance;
		private Set <NumberListerner> mListeners;
		private int mNumberType;
		private List <Character> mInput;
		private int mSign = 1;
		private int mTimeCharIndex = -1;
		private boolean mHasPoint;
		private boolean mNewOperation = true;
		private static final Character[] mTimeCharOrder = { 'd', 'h', 'm', 's', 'n' };

		private NumberInput(Activity parent) {

			mListeners = new HashSet <NumberListerner>();
			mInput = new ArrayList <Character>();

		}

		public int getNumberType() {
			return mNumberType;
		}

		private static NumberInput getInstance(Activity parent) {
			if (parent == null) throw new NullPointerException();
			if (mInstance == null) mInstance = new NumberInput(parent);
			return mInstance;
		}

		public void addNumberListerner(NumberListerner listener) {
			if (!mListeners.contains(listener)) {
				this.mListeners.add(listener);
			}
		}

		public void removeNumberListerner(NumberListerner listener) {
			if (mListeners.contains(listener)) {
				this.mListeners.remove(listener);
			}
		}

		private void fireOnChangedEvent(NumberEvent ev) {
			for (NumberListerner numberListerner : mListeners) {
				numberListerner.onChanged(ev);
			}
		}

		private void fireOnReinitInput(NumberEvent ev) {
			for (NumberListerner numberListerner : mListeners) {
				numberListerner.onSetInput(ev);;
			}
		}

		// private void fireOnSetResultEvent(NumberEvent ev) {
		// for (NumberListerner numberListerner : mListeners) {
		// numberListerner.onSetResult(ev);
		// }
		// }

		private void fireOnClearInput() {
			for (NumberListerner numberListerner : mListeners) {
				numberListerner.onClear();
			}
		}

		// public void setNumber(String number) {
		//
		// // if (number == null || number.isEmpty()) return;
		// // int len = number.length();
		// //
		// // for (int i = 0; i < len; i++) {
		// // mInput.add(number.charAt(i));
		// // }
		// fireEvent(new NumberEvent(this, number));
		// }

		private double convertInputToNumber() {
			// long longNumber = 0;
			double miliSecond = 0;
			String input = TextUtils.join("", mInput);
			if (input == null || input.length() == 0) return 0;
			if (!Utils.isTimeInput(input)) {
				mNumberType = Utils.DOUBLE;
				try {
					miliSecond = Double.parseDouble(input);
				}
				catch (NumberFormatException e) {}
			} else {
				StringBuilder s = new StringBuilder();
				mNumberType = Utils.TIME;

				for (int i = 0; i < input.length(); i++) {
					Character character = input.charAt(i);

					if (character == 'd') {
						miliSecond += Integer.parseInt(s.toString()) * 24 * 3600 * 1000;
						s = new StringBuilder();
					} else if (character == 'h') {
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
				miliSecond = miliSecond * mSign;
			}
			return miliSecond;
		}

		private CalcNumber getNumber() {
			CalcNumber num = new CalcNumber(convertInputToNumber(), mNumberType);
			// reset();
			return num;
		}

		public boolean addCharachter(Character nm) {
			if (mNewOperation) {
				mNewOperation = false;
				reset(false);
			}
			mInput.add(nm);
			fireOnChangedEvent(new NumberEvent(this, TextUtils.join("", mInput)));
			return true;
		}

		public boolean addTimeCharachter(Character t) {

			if (mNewOperation) {
				mNewOperation = false;
				mInput.clear();
			}
			if (mInput.isEmpty()) mInput.add('0');
			char c = mInput.get(mInput.size() - 1);
			if (Character.isDigit(c)) {
				int index = Arrays.asList(mTimeCharOrder).indexOf(t);
				if (mTimeCharIndex < index) {
					mTimeCharIndex = index;
					mHasPoint = false;
					mInput.add(t);

					fireOnChangedEvent(new NumberEvent(this, TextUtils.join("", mInput)));
					return true;
				}
			}
			return false;
		}

		public boolean addPoint() {

			if (mNewOperation) {
				mNewOperation = false;
				mInput.clear();
			}
			if (mHasPoint) return false;
			if (mInput.isEmpty()) {
				mInput.add('0');
			} else {
				char c = mInput.get(mInput.size() - 1);
				if (!Character.isDigit(c)) {
					mInput.add('0');
				}
			}
			mInput.add(BasicCommands.POINT.getValue());
			mHasPoint = true;
			fireOnChangedEvent(new NumberEvent(this, TextUtils.join("", mInput)));
			return true;
		}

		public void deleteCarachter() {
			deleteCarachter(mInput.size() - 1);
		}

		public boolean deleteCarachter(int index) {
			if (index >= mInput.size() || index < 0) return false;
			mInput.remove(index);
			fireOnChangedEvent(new NumberEvent(this, TextUtils.join("", mInput)));
			return true;
		}

		public void setPlusMinus() {
			mSign *= -1;
			String s = (mSign < 0 ? "-" : "");
			fireOnChangedEvent(new NumberEvent(this, s + TextUtils.join("", mInput)));
		}

		/**
		 * reset without event
		 */
		private void reset() {
			reset(false);
		}

		private void reset(boolean withEvent) {
			mInput.clear();
			mNumberType = Utils.DOUBLE;
			mSign = 1;
			mHasPoint = false;
			mTimeCharIndex = -1;
			if (withEvent) fireOnClearInput();
		}

		private boolean reInitInput(CalcNumber number) {

			if (number == null) return false;

			mInput.clear();
			mHasPoint = false;
			mTimeCharIndex = -1;
			mNumberType = number.getNumberType();

			int start = 0;
			if (number.getValue() < 0) {
				start = 1;
				mSign = -1;
			}
			else mSign = 1;

			String tmp = number.toString();
			for (int i = start; i < tmp.length(); i++) {
				mInput.add(tmp.charAt(i));
			}

			fireOnReinitInput(new NumberEvent(this, tmp));
			mNewOperation = true;
			return true;
		}

		private boolean reInitInput(String number) {

			if (number.isEmpty()) return false;
			mInput.clear();
			mHasPoint = false;
			mTimeCharIndex = -1;
			mNumberType = Utils.isTimeInput(number) ? Utils.TIME : Utils.DOUBLE;

			int start = 0;
			if (number.charAt(0) == '-') {
				start = 1;
				mSign = -1;
			}
			else mSign = 1;

			for (int i = start; i < number.length(); i++) {
				mInput.add(number.charAt(i));
			}

			fireOnReinitInput(new NumberEvent(this, number));
			mNewOperation = true;
			return true;

		}

		public boolean isEmpty() {
			return mInput.isEmpty();
		}
	}
}

// public static int getCountOperandors(BasicCommands op) {
// int count;
// switch (op) {
// case MINUS:
// case PLUS:
// case DIVIDE:
// case MULTIPLY:
// count = 2;
// break;
//
// default:
// count = 0;
// break;
// }
// return count;
// }
//
// public void setSiglePart(boolean single) {
// mIsSinglePart = single;
// }