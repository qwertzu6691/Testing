package de.lufve.timecomputing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import de.lufve.timecomputing.util.BasicCommands;
import de.lufve.timecomputing.util.CalculationEvent;
import de.lufve.timecomputing.util.NumberEvent;
import de.lufve.timecomputing.util.Utils;

public class CalcPipeLine {
	private static CalcPipeLine mInstance;

	private LinkedList<Object> mStack;
	private List <CalcNumber> mOperandsList;
	private List <BasicCommands> mCommandList;
	private Activity mParent;
	private int mParenthesisCount = 0;

	private static final String mTag = "NumberPipeLine";
	private boolean mIsSinglePart;
	private boolean haveCommand;

	private CalcPipeLine(Activity parent) {
		mInstance.mParent = parent;
		mOperandsList = new ArrayList <CalcNumber>();
		mCommandList = new ArrayList <BasicCommands>();
		mStack=new LinkedList <Object>();
		
	}

	public static CalcPipeLine getInstance(Activity parent) {
		if (parent == null) throw new NullPointerException();
		if (mInstance == null) mInstance = new CalcPipeLine(parent);
		return mInstance;
	}

	public void reset() {

	}

//	public static int getCountOperandors(BasicCommands op) {
//		int count;
//		switch (op) {
//			case MINUS:
//			case PLUS:
//			case DIVIDE:
//			case MULTIPLY:
//				count = 2;
//				break;
//
//			default:
//				count = 0;
//				break;
//		}
//		return count;
//	}
//
//	public void setSiglePart(boolean single) {
//		mIsSinglePart = single;
//	}

	public boolean addOperandor(CalcNumber nm) {
		mOperandsList.add(nm);
		haveCommand = false;
		if (mIsSinglePart && mCommandList.size() > 0) {
			CalcNumber num = (new Operation(mOperandsList, mCommandList.get(0))).getResult();
//			fireNumberEvent(new NumberEvent(this,new CalcNumber()));
			return true;
		}

		return false;
	}

	public boolean addOperation(BasicCommands op) {
		// TODO
		// wenn mehrmalls ein command button gedückt wird
		if (haveCommand) {
			mCommandList.set(mCommandList.size() - 1, op);
		}
		haveCommand = true;
		mCommandList.add(op);
//		fireEvent(new CalculationEvent(this));
		return true;
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

	public double getResult() {

		return 0;
	}

	private void fireNumberEvent(NumberEvent ev) {
		// TODO Auto-generated method stub

	}
}
