package de.lufve.timecomputing.util;

import java.util.EventListener;

public interface CalculationListener extends EventListener {

	void onAddNumber(NumberEvent ev);

	void onAddOperation(OperationEvent ev);

	void onResult(NumberEvent ev);

	void onChanged(NumberEvent event);
}
