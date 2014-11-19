package de.lufve.movenote;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class MyAdapter extends FragmentStatePagerAdapter {

	private final FragmentManager mFragmentManager;
	private List <Fragment> mFragments = new ArrayList <Fragment>();
	private ViewPager mPager;

	// private int mContainerId;

	public MyAdapter(FragmentManager fm, ViewPager pager) {

		super(fm);
		// mContainerId = containerId;
		mFragmentManager = fm;
		mPager = pager;
	}

	public void addItem(Fragment f)
	{
		mFragments.add(f);
		notifyDataSetChanged();
	}

	public void addItem(int pos, Fragment frag)
	{
		// List <Fragment>frTMP=mFragments;

		for (int i = pos; i < mFragments.size(); i++) {

			mFragmentManager.beginTransaction().remove(mFragments.get(i)).commit();
			// mFragmentManager.executePendingTransactions();
		}

		mFragments.add(pos, frag);
//		for (Fragment fragment : mFragments) {
//			mFragmentManager.beginTransaction().add(fragment, getFragmentTag(pos)).commit();
//			Log.i("ADDITEM", fragment.getTag());
//		}

		notifyDataSetChanged();
		// mFragmentManager.executePendingTransactions();

		mPager.setAdapter(this);
		mPager.setCurrentItem(pos);
		Log.i("ADDIEM", mFragmentManager.toString());
	}

	public void removeItem(int pos)
	{
		for (int i = pos; i < mFragments.size(); i++) {
			mFragmentManager.beginTransaction().remove(mFragments.get(i)).commit();
		}

		mFragments.remove(pos);
		// for (int i = pos; i < mFragments.size(); i++) {
		// mFragmentManager.beginTransaction().add(mContainerId,
		// mFragments.get(i), getFragmentTag(i + 1)).commit();
		// }

		notifyDataSetChanged();
		mPager.setAdapter(this);
		if (pos < mFragments.size()) {
			mPager.setCurrentItem(pos);
		} else {
			mPager.setCurrentItem(pos - 1);
		}
	}

	@Override
	public Fragment getItem(int position)
	{
		return mFragments.get(position);
	}

	@Override
	public int getCount()
	{
		return mFragments.size();
	}

	//
	@Override
	public int getItemPosition(Object object)
	{
		return POSITION_NONE;
	}

	@SuppressWarnings("unused")
	private String getFragmentTag(int pos)
	{
		return "android:switcher:" + mPager.getId() + ":" + pos;
	}

	public String getTitle(int position)
	{
		String name = mFragments.get(position).getClass().getName();
		if (name.equals(""))
			return "- " + position + " -";
		return name;
	}

}
