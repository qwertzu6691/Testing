package de.lufve.timecomputing;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
		
	}

	public void onCmdMinus(View v)
	{
		
	}

	public void onCmdMultiply(View v)
	{
		
	}

	public void onCmdDivide(View v)
	{
		
	}

	public void onCmdPoint(View v)
	{
		input('.');
	}

	public void onCmdColon(View v)
	{
		
	}

	public void onCmdResult(View v)
	{
		
	}

	private void input(char c)
	{
		
	}
}
