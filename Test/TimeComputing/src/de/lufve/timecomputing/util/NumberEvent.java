package de.lufve.timecomputing.util;

import java.util.EventObject;

import de.lufve.timecomputing.*;

public class NumberEvent extends EventObject {

	private static final long serialVersionUID = -1786341310803511676L;
	private String mNumber;

	// public NumberEvent(Object source, CalcNumber number) {
	// super(source);
	// mNumber = number;
	// }

	public NumberEvent(Object source, String number) {
		super(source);
		mNumber = number;
	}

	public NumberEvent(Object source) {
		super(source);
	}

	public String getNumberString() {
		return mNumber;
	}

}
