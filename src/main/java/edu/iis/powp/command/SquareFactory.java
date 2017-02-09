package edu.iis.powp.command;

public class SquareFactory {
	public static PlotterCommand Build(int x, int y, int size){
		ComplexCommand command = new ComplexCommand();
		command.Add(new CommandSetPosition(x, y));
		command.Add(new CommandDrawLineToPosition(x+size, y));
		command.Add(new CommandDrawLineToPosition(x+size, y+size));
		command.Add(new CommandDrawLineToPosition(x, y+size));
		command.Add(new CommandDrawLineToPosition(x, y));
		return command;
	}
}
