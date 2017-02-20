package edu.kis.powp.visitor;

import edu.iis.powp.command.CommandDrawLineToPosition;
import edu.iis.powp.command.CommandSetPosition;
import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.PlotterCommand;

public class TransitionVisitor implements IVisitor{

	private final int x;
	private final int y;
	
	public TransitionVisitor(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public PlotterCommand visit(CommandDrawLineToPosition command) {
		return new CommandDrawLineToPosition(command.getX() + this.x, command.getY() + this.y);
	}

	@Override
	public PlotterCommand visit(CommandSetPosition command) {
		return new CommandSetPosition(command.getX() + this.x, command.getY() + this.y);
	}

	@Override
	public PlotterCommand visit(ComplexCommand command) {
		ComplexCommand result = new ComplexCommand();

		
		for(PlotterCommand cmd : command.GetCommands()){
			result.Add((PlotterCommand) cmd.accept(new TransitionVisitor(this.x, this.y) {}));
		}
		return result;
	}
}
