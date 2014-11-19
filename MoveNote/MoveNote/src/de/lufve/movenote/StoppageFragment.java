package de.lufve.movenote;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StoppageFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private static final String ARG_SECTION_TITLE = "section_title";
	private static final String mTitle = "Pausen";

	public StoppageFragment() {}

	public static StoppageFragment newInstance(int sectionNumber)
	{
		StoppageFragment fragment = new StoppageFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		args.putString(ARG_SECTION_TITLE, mTitle);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_stoppage, container, false);

		// to delete late
		ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		TextView text = (TextView) rootView.findViewById(R.id.txtCoordinate);
		if (netInfo != null && netInfo.isConnected()) {
			text.setText(netInfo.getTypeName());
		}
		//
		return rootView;
	}

	public String getTitle()
	{
		return mTitle;
	}
}