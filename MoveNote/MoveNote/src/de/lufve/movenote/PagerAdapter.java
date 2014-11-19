package de.lufve.movenote;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.app.Fragment;
import android.view.ViewGroup;

public class PagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

	private List <Fragment> mFragments = new ArrayList <Fragment>();
	private ViewPager mPager;
	// private ActionBar mActionBar;
	private static final String ARG_SECTION_TITLE = "section_title";

	private Fragment mPrimaryItem;

	public PagerAdapter(FragmentManager fm, ViewPager vp) {
		super(fm);
		mPager = vp;
		mPager.setAdapter(this);
		mPager.setOnPageChangeListener(this);
		
		// mActionBar = ab;
	}

	public void addAllPages(ArrayList <Fragment> frags)
	{
		mFragments = frags;
		// for (Fragment fragment : frags) {
		// mFragments.add(fragment);
		// mActionBar.addTab(mActionBar.newTab().setText(fragment.getArguments().getString(ARG_SECTION_TITLE)));
		// }

		notifyDataSetChanged();
	}

	public void addPage(Fragment frag, int pos)
	{
		mFragments.add(pos, frag);
		// mActionBar.addTab(mActionBar.newTab().setText(frag.getArguments().getString(ARG_SECTION_TITLE)).setTabListener(this));

		mPager.setAdapter(null);
		
		notifyDataSetChanged();
		mPager.setAdapter(this);
	}

	public void addFirstPage(Fragment frag)
	{
		mFragments.add(0, frag);
		// mActionBar.addTab(mActionBar.newTab().setText(frag.getArguments().getString(ARG_SECTION_TITLE)).setTabListener(this),
		// 0, true);
		mPager.removeAllViews();
		mPager.setAdapter(null);
		
		mPager.setAdapter(this);
		notifyDataSetChanged();
	}

	/**
	 * This method removes the pages from ViewPager
	 */
	public void removePages()
	{
		// mActionBar.removeAllTabs();

		// call to ViewPage to remove the pages
		mPager.removeAllViews();
		mFragments.clear();

		// make this to update the pager
		mPager.setAdapter(null);
		mPager.setAdapter(this);
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentStatePagerAdapter#setPrimaryItem(android.view.ViewGroup,
	 *      int, java.lang.Object)
	 */
	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object)
	{
		super.setPrimaryItem(container, position, object);
		mPrimaryItem = (Fragment) object;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#getItemPosition(java.lang.Object)
	 */
	@Override
	public int getItemPosition(Object object)
	{
		// if (object == mPrimaryItem) { return POSITION_UNCHANGED; }
		// return POSITION_NONE;

//		int index = mFragments.indexOf(object);
//		if (index == -1) return POSITION_NONE;
//		else return index;
		return POSITION_NONE;
	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{}

	@Override
	public void onPageSelected(int position)
	{
		// mActionBar.setSelectedNavigationItem(position);
	}

	// @Override
	// public void onTabUnselected(Tab tab, FragmentTransaction ft)
	// {
	// // TODO Auto-generated method stub
	//
	// }
	// @Override
	// public void onTabSelected(Tab tab, FragmentTransaction ft)
	// {
	// mPager.setCurrentItem(tab.getPosition());
	// }
	// @Override
	// public void onTabReselected(Tab tab, FragmentTransaction ft)
	// {
	// // TODO Auto-generated method stub
	//
	// }

}