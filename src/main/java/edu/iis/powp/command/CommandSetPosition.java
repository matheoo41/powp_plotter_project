package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

public class CommandSetPosition implements PlotterCommand {

	private final int x, y;
	
	public CommandSetPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void execute(IPlotter plotter) {
		plotter.setPosition(this.x, this.y);
	}

}
