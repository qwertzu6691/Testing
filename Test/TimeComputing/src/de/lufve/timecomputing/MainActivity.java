package de.lufve.timecomputing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import de.lufve.timecomputing.util.BasicCommands;

public class MainActivity extends Activity {

	private List <Character> mList = new ArrayList <Character>();
	private List <String> mHistory = new ArrayList <String>();

	private CalcDisplay mDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// mViewDisplay = (TextView) findViewById(R.id.txtDisplay);
		// mShortHistory = (TextView) findViewById(R.id.txtMemorryField);
		mDisplay = CalcDisplay.getInstance(this);
		mDisplay.setEventListener(new EventListener() {

			@Override
			public void onNewLine(String lastLine) {
				// mTime = isTimeInput;
				// setTimeButtonsEnabled(isTimeInput);
				mHistory.add(lastLine);
				setTimeButtonsEnabled(true);
//				setPointButtonEnable(true);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) { return true; }
		return super.onOptionsItemSelected(item);
	}
	public void onClickN(View v){
		System.out.println("CLICK");
//		onClickNum(v);
	}

	public void onClickNum(View v) {
		char ch = 0;
		// if (mNewLine) {
		// putNewLine();
		// mNewLine = false;
		// }
		//
		switch (v.getId()) {
			case R.id.cmd0:
				ch = '0';

				break;
			case R.id.cmd1:
				ch = '1';

				break;
			case R.id.cmd2:
				ch = '2';

				break;
			case R.id.cmd3:
				ch = '3';

				break;
			case R.id.cmd4:
				ch = '4';

				break;
			case R.id.cmd5:
				ch = '5';

				break;
			case R.id.cmd6:
				ch = '6';

				break;
			case R.id.cmd7:
				ch = '7';

				break;
			case R.id.cmd8:
				ch = '8';

				break;
			case R.id.cmd9:
				ch = '9';

				break;

		}
		// updateDisplay(String.valueOf(mLastChar), true, false);
		mDisplay.addChar(ch);
	}

	public void onClickTButton(View v) {
		char ch = 0;
		switch (v.getId()) {
			case R.id.cmdHour:
				ch = 'h';
				// mList.add(mLastChar);
				// TODO later
				if (!verivyInput(Calendar.HOUR, 0)) sendError(Calendar.HOUR);
				break;
			case R.id.cmdMinute:
				ch = 'm';
				// mList.add(mLastChar);
				// TODO later
				if (!verivyInput(Calendar.MINUTE, 0)) sendError(Calendar.MINUTE);
				break;
			case R.id.cmdSekunde:
				ch = 's';
				// mList.add(mLastChar);
				// TODO later
				if (!verivyInput(Calendar.SECOND, 0)) sendError(Calendar.SECOND);
				break;
			case R.id.cmdMs:
				ch = 'n';
				// mList.add('n');
				break;
			default:
				ch = 0;
				break;
		}
		// mTime = true;
		setPointButtonEnable(false);
		mDisplay.addTimeChar(ch);
	}

	public void onClickErase(View v) {
		switch (v.getId()) {
			case R.id.cmdDelete:
				mDisplay.resumeInput(BasicCommands.DELETE);
				break;
			case R.id.cmdClear:
				mDisplay.resumeInput(BasicCommands.CLEAR);
				break;
		}

	}

	private void sendError(int minute) {

	}

	public void onClickOperation(View v) {
		BasicCommands op = BasicCommands.NOTHING;
		// char lastChar = 0;
		// if (mComm != BasicCommands.NOTHING) {
		// outputResult(true);
		// }

		boolean isTime = mDisplay.mustBeTimeInput();
		switch (v.getId()) {
			case R.id.cmdPlus:
				op = BasicCommands.PLUS;
				setTimeButtonsEnabled(isTime);
				setPointButtonEnable(!isTime);
				// lastChar = '+';
				break;
			case R.id.cmdMinus:
				if (mList.isEmpty()) {
					mDisplay.addChar('-');
				} else {
					op = BasicCommands.MINUS;
					setTimeButtonsEnabled(isTime);
					setPointButtonEnable(!isTime);
				}
				// lastChar = '-';
				break;
			case R.id.cmdMal:
				op = BasicCommands.MULTIPLY;
				setTimeButtonsEnabled(!isTime);
				setPointButtonEnable(isTime);
				// lastChar = '*';
				break;
			case R.id.cmdDurch:
				op = BasicCommands.DIVIDE;
				setTimeButtonsEnabled(!isTime);
				setPointButtonEnable(isTime);
				// String s = getString(R.string.division);
				// lastChar = s.charAt(0);
				// mLastChar = '/';
				break;
		// case R.id.cmdGleich:
		// mComm=Commands.RESULT;
		// mComm = Commands.NIX;
		// break;

		}

		mDisplay.addBasicOperation(op);

	}

	public void onclickPoint(View v) {
		// setTimeButtonsEnabled(false);
	}

	public void onClickPlusMinus(View v) {
		mDisplay.setPlusMinus();
	}
	private void setTimeButtonsEnabled(boolean b) {

		findViewById(R.id.cmdHour).setEnabled(b);
		findViewById(R.id.cmdMinute).setEnabled(b);
		findViewById(R.id.cmdSekunde).setEnabled(b);
		findViewById(R.id.cmdMs).setEnabled(b);
	}

	private void setPointButtonEnable(boolean b) {
		// findViewById(R.id.cmdPoin).setEnabled(b);
	}

	public void onClickEqual(View v) {
		mDisplay.addBasicOperation(BasicCommands.RESULT);
		// mComm = BasicCommands.RESULT;
		// outputResult(false);
		// mComm = BasicCommands.NOTHING;
		// setButtonsEnabled(true);
		// mTime = false;
	}

	// private void outputResult(boolean operator) {
	// long result = 0;
	// String s = null;
	// try {
	// if (mTime) {
	// result = calc(convertStringToTime());
	// s = convertToString(result);
	// updateDisplay(s, false, operator);
	// }
	//
	// }
	// catch (Exception e) {
	// //
	// }
	//
	// }

	// private void updateDisplay(String str, boolean append, boolean operator)
	// {
	// StringBuilder tmp = new StringBuilder();
	// if (append) {
	// mViewDisplay.append(str);
	// } else {
	// if (operator) {
	// tmp.append(formatInput());
	// // tmp.append(" ");
	// tmp.append(str);
	// } else {
	// tmp.append(str);
	// }
	// mViewDisplay.setText("");
	// mShortHistory.setText(tmp.toString());
	// }
	// }

	// private Object formatInput() {
	// //
	// return "todo";
	// }

	// private long calc(long par2) {
	// long res = 0;
	// switch (mComm) {
	// case PLUS:
	// res = mMiliSecond + par2;
	// break;
	// case MINUS:
	// res = mMiliSecond - par2;
	// break;
	// case MULTIPLY:
	// res = mMiliSecond * par2;
	// break;
	// case DIVIDE:
	// if (par2 == 0) {
	// // error massage: teilen durch null ist nicht möglich
	// throw new ArithmeticException("divide by zero is not possible");
	// }
	// else {
	// res = mMiliSecond / par2;
	// }
	// break;
	// default:
	// break;
	// }
	//
	// return res;
	// }

	// private int convertStringToTime() {
	// StringBuilder s = new StringBuilder();
	// int miliSecond = 0;
	// for (Iterator <Character> iterator = mList.iterator();
	// iterator.hasNext();) {
	// Character character = (Character) iterator.next();
	// if (character == 'h') {
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
	// if (miliSecond == 0) {
	// try {
	// miliSecond = Integer.parseInt(s.toString());
	// }
	// catch (NumberFormatException e) {}
	//
	// }
	// mList.clear();
	// return miliSecond;
	// }

	// private String convertToString(long duration) {
	//
	// long milSecInHour = 3600 * 1000;
	// long milSecInMiMinute = 60 * 1000;
	// long hour = 0;
	// long minute = 0;
	// long second = 0;
	// long miliSecond = 0;
	// long tmpModule = duration;
	//
	// if (milSecInHour < duration) {
	// hour = tmpModule / milSecInHour;
	// tmpModule = duration % milSecInHour;
	// }
	// if (tmpModule > 0) {
	// minute = tmpModule / milSecInMiMinute;
	// tmpModule = tmpModule % milSecInMiMinute;
	// }
	// if (tmpModule > 0) {
	// second = tmpModule / 1000;
	// tmpModule = tmpModule % 1000;
	// }
	//
	// miliSecond = tmpModule;
	//
	// return hour + "h " + minute + "m " + second + "m " + miliSecond + "ms";
	//
	// }

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
}
