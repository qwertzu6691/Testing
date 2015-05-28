package de.lufve.timecomputing.util;

import java.util.EventListener;

public interface DisplayEventListener extends EventListener{

	
	void onNewLine(String cacheLine);
	
	void onNumberRetrieve(String archivedValue);
}
