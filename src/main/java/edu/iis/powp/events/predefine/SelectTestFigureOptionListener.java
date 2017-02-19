package edu.iis.powp.events.predefine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.iis.client.plottermagic.preset.FiguresJane;
import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.adapter.SimpleAbstractPlotterAdapter;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.command.ComplexCommand;
import edu.iis.powp.command.PlotterCommand;
import edu.iis.powp.command.RectangleFactory;
import edu.kis.powp.visitor.DrawnLinesLengthVisitor;
import edu.kis.powp.visitor.IVisitor;
import edu.kis.powp.visitor.TransitionVisitor;

public class SelectTestFigureOptionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Figure Joe 1":
			FiguresJoe.figureScript1(Application.getComponent(DriverManager.class).getCurrentPlotter());
			return;
		case "Figure Joe 2":
			FiguresJoe.figureScript2(Application.getComponent(DriverManager.class).getCurrentPlotter());
			return;
		case "Figure Jane":
			SimpleAbstractPlotterAdapter plotter = new SimpleAbstractPlotterAdapter(
					Application.getComponent(DriverManager.class).getCurrentPlotter());
			FiguresJane.figureScript(plotter);
			return;
		case "Rectangle":
			PlotterCommand command = RectangleFactory.Build(0, 0, 100, 100);
			command.execute(Application.getComponent(DriverManager.class).getCurrentPlotter());
			return;
		case "Rectangle - transition (Visitor)":
			IVisitor transitionVisitor = new TransitionVisitor(-50, -200);
			ComplexCommand command2 = (ComplexCommand) RectangleFactory.Build(0, 0, 100, 100);
			PlotterCommand command3 = (PlotterCommand) command2.accept(transitionVisitor);
			command3.execute(Application.getComponent(DriverManager.class).getCurrentPlotter());
			return;
		case "Rectangle - line length (Visitor)":
			IVisitor lengthVisitor = new DrawnLinesLengthVisitor();
			ComplexCommand command4 = (ComplexCommand) RectangleFactory.Build(0, 0, 100, 100);
			command4.execute(Application.getComponent(DriverManager.class).getCurrentPlotter());
			Double length = (Double)command4.accept(lengthVisitor);
			Logger.getGlobal().log(Level.INFO, "Drawn lines length = " + length);
			return;
		}		

	}
}
