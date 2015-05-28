package de.lufve.timecomputing.util;

import java.util.EventListener;

public interface NumberListerner extends EventListener {

	// void onSetNumber(NumberEvent ev);

	void onSetInput(NumberEvent ev);

	void onChanged(NumberEvent event);

	void onClear();

//	void onSetResult(NumberEvent event);

//	void onSetIterimResult(NumberEvent event);

	

}
