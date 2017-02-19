package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;
import edu.kis.powp.visitor.IVisitor;

public class CommandDrawLineToPosition implements PlotterCommand {

	private final int x,y;
	
	public CommandDrawLineToPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public void execute(IPlotter plotter) {
		plotter.drawTo(this.x, this.y);
		
	}
	
	@Override
	public Object accept(IVisitor visitor) {
		return visitor.visit(this);
	}
}
