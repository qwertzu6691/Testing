package de.lufve.timecomputing.util;

import java.util.EventObject;

public class DisplayEvent extends EventObject {
	
	private static final long serialVersionUID = 4378465833073120159L;
	private String mNumber;

	public DisplayEvent(Object source, String number) {
		super(source);
		this.mNumber = number;
	}

	public String getNumber() {
		return mNumber;
	}

}
