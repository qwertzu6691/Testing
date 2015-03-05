package de.lufve.timecomputing;

public interface EventListener {

	/**
	 * @param cacheLine
	 *            is member that must be moved in the history
	 * @param isTimeInput
	 *            means whether the number in cache is the time
	 */
	void onNewLine(String cacheLine);
}
