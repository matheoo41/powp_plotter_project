package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;
import edu.kis.powp.visitor.IVisitor;

public interface PlotterCommand {
	void execute(IPlotter plotter);
	Object accept(IVisitor visitor);
}
