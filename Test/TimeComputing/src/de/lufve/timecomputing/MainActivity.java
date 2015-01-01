package de.lufve.timecomputing;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private int mHour;
	private int mMinute;
	private int mSecond;
	private int mMiliSecond;

	private Character[] mInput = new Character[12];

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b = (Button) findViewById(R.id.button3);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				// TODO

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) { return true; }
		return super.onOptionsItemSelected(item);
	}

	public void onCmd1(View v)
	{
		input('1');
	}

	public void onCmd2(View v)
	{
		input('2');
	}

	public void onCmd3(View v)
	{
		input('3');
	}

	public void onCmd4(View v)
	{
		input('4');
	}

	public void onCmd5(View v)
	{
		input('5');
	}

	public void onCmd6(View v)
	{
		input('6');
	}

	public void onCmd7(View v)
	{
		input('7');
	}

	public void onCmd8(View v)
	{
		input('8');
	}

	public void onCmd9(View v)
	{
		input('9');
	}

	public void onCmd0(View v)
	{
		input('0');
	}

	public void onCmdPlus(View v)
	{
		opration(Commands.PLUS);
	}

	public void onCmdMinus(View v)
	{
		opration(Commands.MINUS);
	}

	public void onCmdMultiply(View v)
	{
		opration(Commands.MULTIPLY);
	}

	public void onCmdDivide(View v)
	{
		opration(Commands.DIVIDE);
	}

	public void onCmdPoint(View v)
	{
		input(Commands.POINT);
	}

	public void onCmdColon(View v)
	{
		input(Commands.COLON);
	}

	public void onCmdResult(View v)
	{
		opration(Commands.RESULT);
	}

	private void input(char c)
	{
		int i = 0;
		mInput[i] = c;
	}

	private void input(Commands cmd)
	{

	}

	private void opration(Commands plus)
	{
		boolean condition = false;
		if (!condition) {

		}
	}

	public void onCllick(View v)
	{
		switch (v.getId()) {
			case R.id.button1:
				break;
			case R.id.button2:
				break;
			case R.id.button3:
				break;
			case R.id.cmdPlus:
				break;
			case R.id.button5:
				break;
			case R.id.button6:
				break;
			case R.id.button7:
				break;
			case R.id.button8:
				break;
			case R.id.button9:
				break;
			case R.id.button20:
				break;
			default:
				break;
		}
	}

}
