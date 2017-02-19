package edu.kis.powp.visitor;

import edu.iis.powp.command.CommandDrawLineToPosition;
import edu.iis.powp.command.CommandSetPosition;
import edu.iis.powp.command.ComplexCommand;

public interface IVisitor {
	Object visit(ComplexCommand command);
	Object visit(CommandDrawLineToPosition command);
	Object visit(CommandSetPosition command);
}
