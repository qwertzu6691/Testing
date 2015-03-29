package de.lufve.timecomputing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.text.TextUtils;
import de.lufve.timecomputing.util.BasicCommands;
import de.lufve.timecomputing.util.NumberListerner;
import de.lufve.timecomputing.util.Utils;

public class NumberInput {

	private static NumberInput mInstance;
	private Set <NumberListerner> mListeners;
	private int mTypeNumber;
	private List <Character> mInput;
	private int mSign = 1;
	private boolean mIsTimeNumber;
	private int mTimeCharIndex = -1;
	private boolean mHasPoint;
	private static final Character[] mTimeCharOrder = { 'd', 'h', 'm', 's', 'n' };

	private NumberInput(Activity parent) {

		mListeners = new HashSet <NumberListerner>();
		mInput = new ArrayList <Character>();

	}

	public static NumberInput getInstance(Activity parent) {
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

	private void fireMyEvent() {
		// new NumberEvent(this, (mSign == 1 ? "" : "-") + TextUtils.join("",
		// mInput))
	}

	private double convertInputToNumber() {
		// long longNumber = 0;
		double miliSecond = 0;
		String input = TextUtils.join("", mInput);
		if (input == null || input.length() == 0) return 0;
		if (!Utils.isTimeInput(input)) {
			mTypeNumber = Utils.DOUBLE;
			try {
				miliSecond = Double.parseDouble(input);
			}
			catch (NumberFormatException e) {}
		} else {
			StringBuilder s = new StringBuilder();
			mTypeNumber = Utils.TIME;

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

	public CalcNumber getNumber() {
		CalcNumber num = new CalcNumber(convertInputToNumber(), mTypeNumber);
		reset();
		return num;
	}

	public boolean addNumber(Character nm) {
		mInput.add(nm);
		fireMyEvent();
		return true;
	}

	public boolean addTimeCharachter(Character t) {

		char c = mInput.get(mInput.size() - 1);
		if (Character.isDigit(c)) {
			int index = Arrays.asList(mTimeCharOrder).indexOf(t);
			if (mTimeCharIndex < index) {
				mTimeCharIndex = index;
				mIsTimeNumber = true;
				mHasPoint = false;
				mInput.add(t);

				fireMyEvent();
				return true;
			}
		}
		return false;
	}

	public boolean addPoint() {
		// TODO
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
		fireMyEvent();
		return true;
	}

	public boolean deleteLastCarachter() {
		mInput.remove(mInput.size() - 1);
		fireMyEvent();
		return true;
	}

	public void setPlusMinus() {
		mSign *= -1;
		fireMyEvent();
	}

	public void reset() {
		mInput.clear();
		mIsTimeNumber = false;
		mTypeNumber = Utils.DOUBLE;
		mSign = 1;
		mHasPoint = false;
		mTimeCharIndex = -1;
	}
}
