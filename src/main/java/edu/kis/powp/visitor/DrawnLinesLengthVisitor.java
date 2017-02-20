package edu.kis.powp.visitor;

import edu.iis.powp.command.CommandDrawLineToPosition;
import edu.iis.powp.command.CommandSetPosition;
import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.PlotterCommand;

public class DrawnLinesLengthVisitor implements IVisitor {
	
	private int x;
	private int y;
	
	public DrawnLinesLengthVisitor() {
		this.x = 0;
		this.y = 0;
	}

	public DrawnLinesLengthVisitor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Double visit(CommandDrawLineToPosition command) {
		
		Double length =  Math.sqrt(Math.pow(command.getX() - this.x, 2) + Math.pow(command.getY() - this.y, 2));
		this.x = command.getX();
		this.y = command.getY();
		return length;
	}

	@Override
	public Double visit(CommandSetPosition command) {
		this.x = command.getX();
		this.y = command.getY();
		return 0.0;
	}

	@Override
	public Double visit(ComplexCommand command) {
		Double sum = 0.0;
		for(PlotterCommand cmd : command.GetCommands()){
			
			
			sum += (Double) cmd.accept(new DrawnLinesLengthVisitor(x,y));
			this.x = cmd.getX();
			this.y = cmd.getY();
			
			;
		}
		return sum;
	}

}
