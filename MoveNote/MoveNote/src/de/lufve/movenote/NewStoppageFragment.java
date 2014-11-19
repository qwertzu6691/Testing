package de.lufve.movenote;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewStoppageFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private static final String ARG_SECTION_TITLE = "section_title";
	private static final String mTitle = "Neue Pause";

	public NewStoppageFragment() {}

	public static NewStoppageFragment newInstance(int sectionNumber)
	{
		NewStoppageFragment fragment = new NewStoppageFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		args.putString(ARG_SECTION_TITLE, mTitle);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_new_stoppage, container, false);

		return rootView;
	}

	public String getTitle()
	{
		return mTitle;
	}
}
