package de.lufve.movenote;

import android.app.Fragment;

public abstract class AbstrctFragment extends Fragment {
	public final static String TAG;

	static {
		TAG = Class.class.getName();
		
	}

	public abstract String getName();

	public abstract String setName();
}
