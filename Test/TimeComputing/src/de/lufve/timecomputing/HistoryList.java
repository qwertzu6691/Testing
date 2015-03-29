package de.lufve.timecomputing;

import android.app.Activity;

public class HistoryList {

	private static HistoryList mInstance;
	private Activity mParent;

	private HistoryList(Activity parent) {
		mParent = parent;
	}

	public static HistoryList getInstance(Activity parent) {
		if (parent == null) throw new NullPointerException();
		if (mInstance == null) {
			mInstance = new HistoryList(parent);
		}
		return mInstance;
	}

}
