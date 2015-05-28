package de.lufve.timecomputing.util;

import java.util.EventObject;

public class OperationEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3626852269811628491L;
	private BasicCommands mOp;

	public OperationEvent(Object source, BasicCommands op) {
		super(source);
		mOp = op;
	}
	public BasicCommands getOperation() {
		return mOp;
	}
}
