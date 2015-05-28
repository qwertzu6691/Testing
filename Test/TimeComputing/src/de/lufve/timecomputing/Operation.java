package de.lufve.timecomputing;

import java.util.LinkedList;
import java.util.List;

import de.lufve.timecomputing.util.BasicCommands;
import de.lufve.timecomputing.util.ComOperation;
import de.lufve.timecomputing.util.Utils;

public class Operation {

	private CalcNumber mOperand_2;

	public Operation(LinkedList <Object> stack, boolean singleton) {
		super();
	}

	public Operation(List <CalcNumber> operands, BasicCommands op) {
		if (operands.size() != 2) { throw new UnsupportedOperationException("must be 2 operandors"); }
		operands.get(0);
		this.mOperand_2 = operands.get(1);
	}

	public Operation() {}

	public void setContex(BasicCommands op, CalcNumber... num) {
		if (num == null) return;
		if (num.length > 0) {}
		if (num.length > 1) mOperand_2 = num[1];
	}

	public void resetOperand(CalcNumber... num) {
		// perhaps later should be more here
		if (mOperand_2 == null) {
			mOperand_2 = num[0];
		}
	}

	@SuppressWarnings("unused")
	private CalcNumber rCalc(double part, ComOperation op) {

		return null;
	}

	@SuppressWarnings("incomplete-switch")
	public static CalcNumber getResult(CalcNumber part1, CalcNumber part2, BasicCommands op) {
		int operationType = verify(part1, part2, op);
		if (operationType == Utils.ERROR) return null;
		double _part1 = part1.getValue();
		double _part2 = part2.getValue();
		double res = 0;
		switch (op) {
			case PLUS:
				res = _part1 + _part2;
				break;
			case MINUS:
				res = _part1 - _part2;
				break;
			case MULTIPLY:
				res = _part1 * _part2;
				break;
			case DIVIDE:
				res = _part1 / _part2;
				break;
		}
		return new CalcNumber(res, operationType == Utils.TIME ? Utils.TIME : Utils.DOUBLE);
	}

	public static int verify(CalcNumber part1, CalcNumber part2, BasicCommands op) {

		if (op == BasicCommands.DIVIDE && part2.getValue() == 0) { throw new ArithmeticException("divide by zero is not possible"); }
		if (part1.getNumberType() == Utils.TIME || part2.getNumberType() == Utils.TIME) {
			if (part1.getNumberType() == Utils.TIME && part2.getNumberType() == Utils.TIME) {
				if (op == BasicCommands.PLUS || op == BasicCommands.MINUS) return Utils.TIME;
				else if (op == BasicCommands.DIVIDE) return Utils.DOUBLE;
			} else if (part1.getNumberType() == Utils.TIME
				&& part2.getNumberType() == Utils.DOUBLE && op == BasicCommands.DIVIDE) return Utils.TIME;
			else if (op == BasicCommands.MULTIPLY) return Utils.TIME;
		} else return Utils.DOUBLE;
		return Utils.ERROR;
	}
}
