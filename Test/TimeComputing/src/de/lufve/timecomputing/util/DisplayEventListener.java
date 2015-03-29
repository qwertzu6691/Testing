package de.lufve.timecomputing.util;

import java.util.EventListener;

public interface DisplayEventListener extends EventListener{

	/**
	 * @param cacheLine
	 *            is member that must be moved in the history
	 * @param isTimeInput
	 *            means whether the number in cache is the time number
	 */
	void onNewLine(String cacheLine);
}
