package edu.kis.powp.visitor;

import edu.iis.powp.command.CommandDrawLineToPosition;
import edu.iis.powp.command.CommandSetPosition;
import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.PlotterCommand;

public class PlotterFullLengthVisitor implements IVisitor{

	private int x;
	private int y;
	
	public PlotterFullLengthVisitor() {
		this.x = 0;
		this.y = 0;
	}

	@Override
	public Double visit(CommandDrawLineToPosition command) {
		return GetLength(command.getX(), command.getY());
	}

	@Override
	public Double visit(CommandSetPosition command) {
		return GetLength(command.getX(), command.getY());
	}
	
	@Override
	public Double visit(ComplexCommand command) {
		
		Double sum = 0.0;

		for(PlotterCommand cmd : command.GetCommands()){
			sum += (Double) cmd.accept(new PlotterFullLengthVisitor() {});
		}
		return sum;
		
	
	}
	
	private Double GetLength(int commandX, int commandY) {
		Double length =  Math.sqrt(Math.pow(commandX - this.x, 2) + Math.pow(commandY - this.y, 2));
		this.x = commandX;
		this.y = commandY;
		return length;
	}
}
