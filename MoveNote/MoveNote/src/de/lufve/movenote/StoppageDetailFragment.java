package de.lufve.movenote;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StoppageDetailFragment extends Fragment {

	private static final String mTitle = "Auflistung";
	private static final String ARG_SECTION_TITLE = "section_title";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_pause_detail, container, false);

		return rootView;
	}

	public String getTitle()
	{
		return mTitle;
	}
}
