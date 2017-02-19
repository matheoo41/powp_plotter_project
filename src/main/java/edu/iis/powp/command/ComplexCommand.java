package edu.iis.powp.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import edu.iis.client.plottermagic.IPlotter;
import edu.kis.powp.visitor.IVisitor;

public class ComplexCommand implements PlotterCommand {

	private final ArrayList<PlotterCommand> commands;
	
	public ComplexCommand() {
		this.commands = new ArrayList<>();
	}
	
	public void Add(PlotterCommand command){
		this.commands.add(command);
	}
	
	public Collection<PlotterCommand> GetCommands(){
		return Collections.unmodifiableCollection(this.commands);
	}
	
	@Override
	public void execute(IPlotter plotter) {
		for(PlotterCommand command : this.commands){
			command.execute(plotter);
		}	
	}
	
	@Override
	public Object accept(IVisitor visitor) {
		return visitor.visit(this);
	}

}
