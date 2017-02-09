package edu.iis.powp.command;

import java.util.ArrayList;

import edu.iis.client.plottermagic.IPlotter;

public class ComplexCommand implements PlotterCommand {

	private final ArrayList<PlotterCommand> commands;
	
	public ComplexCommand() {
		this.commands = new ArrayList<>();
	}
	
	public void Add(PlotterCommand command){
		this.commands.add(command);
	}
	
	@Override
	public void execute(IPlotter plotter) {
		for(PlotterCommand command : this.commands){
			command.execute(plotter);
		}	
	}

}
