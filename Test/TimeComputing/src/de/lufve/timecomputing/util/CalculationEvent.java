package de.lufve.timecomputing.util;

import java.util.EventObject;

public class CalculationEvent extends EventObject {
	private BasicCommands mOp;

	/**
	 * 
	 */
	private static final long serialVersionUID = -818923605935426432L;

	public CalculationEvent(Object source,BasicCommands op) {
		super(source);
		mOp=op;
	}
	
	public BasicCommands getOperation() {
		return mOp;
	}

}
