package de.lufve.timecomputing.util;

import java.util.EventListener;

public interface CalcPipeLineListener extends EventListener {
	void onResult(String s);
}
