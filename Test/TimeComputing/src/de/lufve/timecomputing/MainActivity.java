package de.lufve.timecomputing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	// private int mHour = 0;
	// private int mMinute = 0;
	// private int mSecond = 0;
	private int mMiliSecond = 0;
	// private int mPar = 0;
	private boolean mTime = false;
	private BasicCommands mComm = BasicCommands.NOTHING;

	// private Character[] mInput = new Character[12];
	private List <Character> mList = new ArrayList <Character>();
	private List <String> mHistory = new ArrayList <String>();

	private TextView mViewDisplay;
	private TextView mShortHistory;
	private boolean mNewLine;
	private char mLastChar;
	private CalcDisplay display;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// mViewDisplay = (TextView) findViewById(R.id.txtDisplay);
		// mShortHistory = (TextView) findViewById(R.id.txtMemorryField);
		display = CalcDisplay.getInstance(this);
		display.setEventListener(new EventListener() {

			

			@Override
			public void onNewLine(String lastLine) {
				// TODO Auto-generated method stub
				
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

	public void onClick(View v) {
		if (mNewLine) {
			putNewLine();
			mNewLine = false;
		}

		switch (v.getId()) {
			case R.id.cmd0:
				mLastChar = '0';
				mList.add(mLastChar);
				break;
			case R.id.cmd1:
				mLastChar = '1';
				mList.add(mLastChar);
				break;
			case R.id.cmd2:
				mLastChar = '2';
				mList.add(mLastChar);
				break;
			case R.id.cmd3:
				mLastChar = '3';
				mList.add(mLastChar);
				break;
			case R.id.cmd4:
				mLastChar = '4';
				mList.add(mLastChar);
				break;
			case R.id.cmd5:
				mLastChar = '5';
				mList.add(mLastChar);
				break;
			case R.id.cmd6:
				mLastChar = '6';
				mList.add(mLastChar);
				break;
			case R.id.cmd7:
				mLastChar = '7';
				mList.add(mLastChar);
				break;
			case R.id.cmd8:
				mLastChar = '8';
				mList.add(mLastChar);
				break;
			case R.id.cmd9:
				mLastChar = '9';
				mList.add(mLastChar);
				break;

		}
		updateDisplay(String.valueOf(mLastChar), true, false);
	}

	private void putNewLine() {
		// TODO

	}

	public void onClickTime(View v) {

		switch (v.getId()) {
			case R.id.cmdHour:
				mLastChar = 'h';
				mList.add(mLastChar);
				mTime = true;
				// TODO later
				if (!verivyInput(Calendar.HOUR, 0)) sendError(Calendar.HOUR);
				break;
			case R.id.cmdMinute:
				mLastChar = 'm';
				mList.add(mLastChar);
				mTime = true;
				// TODO later
				if (!verivyInput(Calendar.MINUTE, 0)) sendError(Calendar.MINUTE);
				break;
			case R.id.cmdSekunde:
				mLastChar = 's';
				mList.add(mLastChar);
				mTime = true;
				// TODO later
				if (!verivyInput(Calendar.SECOND, 0)) sendError(Calendar.SECOND);
				break;
			case R.id.cmdMs:

				mList.add('n');
				mTime = true;
				break;
		}
		updateDisplay(String.valueOf(mLastChar), true, false);
	}

	private void sendError(int minute) {

	}

	public void onClickOp(View v) {
		char lastChar = 0;
		if (mComm != BasicCommands.NOTHING) {
			outputResult(true);
		}

		switch (v.getId()) {
			case R.id.cmdPlus:
				mComm = BasicCommands.PLUS;
				setButtonsEnabled(mTime);
				lastChar = '+';
				break;
			case R.id.cmdMinus:
				mComm = BasicCommands.MINUS;
				setButtonsEnabled(mTime);
				lastChar = '-';
				break;
			case R.id.cmdMal:
				mComm = BasicCommands.MULTIPLY;
				setButtonsEnabled(!mTime);
				lastChar = '*';
				break;
			case R.id.cmdDurch:
				mComm = BasicCommands.DIVIDE;
				setButtonsEnabled(!mTime);
				String s = getString(R.string.division);
				lastChar = s.charAt(0);
				// mLastChar = '/';
				break;
		// case R.id.cmdGleich:
		// mComm=Commands.RESULT;
		// mComm = Commands.NIX;
		// break;

		}
		updateDisplay(String.valueOf(lastChar), false, true);

	}

	private void setButtonsEnabled(boolean b) {

		findViewById(R.id.cmdHour).setEnabled(b);
		findViewById(R.id.cmdMinute).setEnabled(b);
		findViewById(R.id.cmdSekunde).setEnabled(b);
		findViewById(R.id.cmdMs).setEnabled(b);
	}

	public void onClickResult(View v) {
		mComm = BasicCommands.RESULT;
		outputResult(false);
		mComm = BasicCommands.NOTHING;
		setButtonsEnabled(true);
		mTime = false;
	}

	private void outputResult(boolean operator) {
		long result = 0;
		String s = null;
		try {
			if (mTime) {
				result = calc(convertStringToTime());
				s = convertToString(result);
				updateDisplay(s, false, operator);
			}

		}
		catch (Exception e) {
			//
		}

	}

	private void updateDisplay(String str, boolean append, boolean operator) {
		StringBuilder tmp = new StringBuilder();
		if (append) {
			mViewDisplay.append(str);
		} else {
			if (operator) {
				tmp.append(formatInput());
				// tmp.append(" ");
				tmp.append(str);
			} else {
				tmp.append(str);
			}
			mViewDisplay.setText("");
			mShortHistory.setText(tmp.toString());
		}
	}

	private Object formatInput() {
		// TODO Auto-generated method stub
		return "todo";
	}

	private void insertToHistory(String str) {
		mHistory.add(str);
	}

	private long calc(long par2) {
		long res = 0;
		switch (mComm) {
			case PLUS:
				res = mMiliSecond + par2;
				break;
			case MINUS:
				res = mMiliSecond - par2;
				break;
			case MULTIPLY:
				res = mMiliSecond * par2;
				break;
			case DIVIDE:
				if (par2 == 0) {
					// error massage: teilen durch null ist nicht möglich
					throw new ArithmeticException("divide by zero is not possible");
				}
				else {
					res = mMiliSecond / par2;
				}
				break;
			default:
				break;
		}

		return res;
	}

	private int convertStringToTime() {
		StringBuilder s = new StringBuilder();
		int miliSecond = 0;
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

	private String convertToString(long duration) {

		long milSecInHour = 3600 * 1000;
		long milSecInMiMinute = 60 * 1000;
		long hour = 0;
		long minute = 0;
		long second = 0;
		long miliSecond = 0;
		long tmpModule = duration;

		if (milSecInHour < duration) {
			hour = tmpModule / milSecInHour;
			tmpModule = duration % milSecInHour;
		}
		if (tmpModule > 0) {
			minute = tmpModule / milSecInMiMinute;
			tmpModule = tmpModule % milSecInMiMinute;
		}
		if (tmpModule > 0) {
			second = tmpModule / 1000;
			tmpModule = tmpModule % 1000;
		}

		miliSecond = tmpModule;

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
}
