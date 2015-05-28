package de.lufve.timecomputing.util;

public abstract class NumberAdapter implements NumberListerner {

	@Override
	public void onSetInput(NumberEvent ev) {}

	@Override
	public void onChanged(NumberEvent event) {}

	@Override
	public void onClear() {}

}
