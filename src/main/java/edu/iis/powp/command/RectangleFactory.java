package edu.iis.powp.command;

public class RectangleFactory {
	public static PlotterCommand Build(int x, int y, int width, int height){
		ComplexCommand command = new ComplexCommand();
		command.Add(new CommandSetPosition(x, y));
		command.Add(new CommandDrawLineToPosition(x+width, y));
		command.Add(new CommandSetPosition(x+width, y));
		command.Add(new CommandDrawLineToPosition(x+width, y+height));
		command.Add(new CommandSetPosition(x+width, y+height));
		command.Add(new CommandDrawLineToPosition(x, y+height));
		command.Add(new CommandSetPosition(x, y+height));
		command.Add(new CommandDrawLineToPosition(x, y));
		command.Add(new CommandSetPosition(x,y));
		return command;
	}
}
