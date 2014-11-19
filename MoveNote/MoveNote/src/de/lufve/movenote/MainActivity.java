package de.lufve.movenote;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	// private SectionsPagerAdapter mSectionsPagerAdapter;
	private MyAdapter mPageAdapter;
	private ViewPager mViewPager;
	// private ArrayList <Fragment> list;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.container);

		mPageAdapter = new MyAdapter(getFragmentManager(), mViewPager);

		mPageAdapter.addItem(NewStoppageFragment.newInstance(1));
		mPageAdapter.addItem(StoppageFragment.newInstance(2));

		// mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setAdapter(mPageAdapter);

		// try {
		// ViewConfiguration config = ViewConfiguration.get(this);
		// Field menuKeyField =
		// ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
		// if (menuKeyField != null) {
		// menuKeyField.setAccessible(true);
		// menuKeyField.setBoolean(config, false);
		// }
		// }
		// catch (Exception ex) {}

	}

	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.i("SAVE_STATE", "hier saved ");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		Log.i("SAVE_STATE", "onPause");
	}

	public void onClickAdd(View v)
	{
		Fragment fr = new StoppageFragment();
		getFragmentManager().beginTransaction().replace(R.id.container, fr).commit();
	}

	public void onClickStop(View v)
	{
		Fragment fr = new NewStoppageFragment();
		getFragmentManager().beginTransaction().replace(R.id.container, fr).commit();
	}

	public boolean isOnline()
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) { return true; }
		return false;
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
		switch (item.getItemId()) {

		case R.id.menuitem_add:
			// getFragmentManager().beginTransaction().detach(list.get(0)).commit();
			// list.add(TEMP_Fragment.newInstance(3));
			// mPageAdapter = new PagerAdapter(getFragmentManager(),
			// mViewPager);

			mPageAdapter.addItem(0, TEMP_Fragment.newInstance(3));
			// mViewPager.setAdapter(mSectionsPagerAdapter);

			// mViewPager.setCurrentItem(0);
			return true;
		case R.id.menuitem_delete:
			// mPageAdapter.addFirstTab(TEMP_Fragment.newInstance(3));
			return true;
		}
		return false;
	}
}