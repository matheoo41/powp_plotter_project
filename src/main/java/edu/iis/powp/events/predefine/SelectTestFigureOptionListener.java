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
import edu.iis.powp.command.*;
import edu.iis.powp.command.PlotterCommand;
import edu.iis.powp.command.RectangleFactory;
import edu.kis.powp.visitor.DrawnLinesLengthVisitor;
import edu.kis.powp.visitor.IVisitor;
import edu.kis.powp.visitor.PlotterFullLengthVisitor;
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
		case "Rectangle - line plot length (Visitor)":
			IVisitor lengthVisitor = new DrawnLinesLengthVisitor();
			IVisitor plotVisitor = new PlotterFullLengthVisitor();
			PlotterCommand commanda = RectangleFactory.Build(0, 0, 100, 100);
			PlotterCommand commandb = RectangleFactory.Build(100, 100, 100, 100);
			ComplexCommand cc = new ComplexCommand();
			ComplexCommand command4 = (ComplexCommand) RectangleFactory.Build(50, 50, 100, 100);
			cc.Add(commanda);
			cc.Add(command4);
			cc.Add(commandb);
			
			cc.execute(Application.getComponent(DriverManager.class).getCurrentPlotter());
			Double length = (Double)cc.accept(lengthVisitor);
			Logger.getGlobal().log(Level.INFO, "Drawn lines length = " + length);
			length = (Double)cc.accept(plotVisitor);
			Logger.getGlobal().log(Level.INFO, "plotter head lines length = " + length);

			return;
		case "test":
			IVisitor lengthVisitor2 = new DrawnLinesLengthVisitor();
			IVisitor plotVisitor2 = new PlotterFullLengthVisitor();
			
			ComplexCommand test = new ComplexCommand();
			//PlotterCommand commandUp = new CommandDrawLineToPosition(0,-100);
			//PlotterCommand commandR = new CommandDrawLineToPosition(200,-100);
			//PlotterCommand commandD = new CommandDrawLineToPosition(200,0);
			//ComplexCommand cc2 = new ComplexCommand();
			PlotterCommand semi1 = SemiRectangleFactory.Build(-250, 0, 200, 100);
			PlotterCommand semi2 = SemiRectangleFactory.Build(50, 0, 200, 100);

			test.Add(semi1);
			test.Add(semi2);
			
			test.execute(Application.getComponent(DriverManager.class).getCurrentPlotter());
			Double length2 = (Double)test.accept(lengthVisitor2);
			Logger.getGlobal().log(Level.INFO, "Drawn lines length = " + length2);
			length2 = (Double)test.accept(plotVisitor2);
			Logger.getGlobal().log(Level.INFO, "plotter head lines length = " + length2);
			return;
		}		

	}
}
