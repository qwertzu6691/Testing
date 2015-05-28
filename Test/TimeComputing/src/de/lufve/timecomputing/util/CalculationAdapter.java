package de.lufve.timecomputing.util;

public abstract class CalculationAdapter implements CalculationListener {

	@Override
	public void onAddNumber(NumberEvent ev) {}

	@Override
	public void onAddOperation(OperationEvent ev) {}

	@Override
	public void onResult(NumberEvent ev) {}

	@Override
	public void onChanged(NumberEvent event) {}

	
}
