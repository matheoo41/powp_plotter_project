package edu.iis.powp.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.iis.client.plottermagic.ClientPlotter;
import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.adapter.LinePlotterAdapter;
import edu.iis.powp.adapter.SimplePlotterAdapter;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.Context;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.appext.ApplicationWithDrawer;
import edu.iis.powp.events.predefine.SelectTestFigureOptionListener;
import edu.kis.powp.drawer.panel.DrawPanelController;
import edu.kis.powp.drawer.shape.LineFactory;


public class TestPlotSoftPatterns
{
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
    /**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param context Application context.
	 */
	private static void setupPresetTests(Context context) {
	    SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener();
		
		context.addTest("Figure Joe 1", selectTestFigureOptionListener);
		context.addTest("Figure Joe 2", selectTestFigureOptionListener);
		context.addTest("Figure Jane", selectTestFigureOptionListener);
		context.addTest("Rectangle", selectTestFigureOptionListener);
		context.addTest("Rectangle - transition (Visitor)", selectTestFigureOptionListener);
		context.addTest("Rectangle - line plot length (Visitor)", selectTestFigureOptionListener);
		context.addTest("test", selectTestFigureOptionListener);

	}

	/**
	 * Setup driver manager, and set default IPlotter for application.
	 * 
	 * @param context Application context.
	 */
	private static void setupDrivers(Context context) {
		IPlotter clientPlotter = new ClientPlotter();
		context.addDriver("Client Plotter", clientPlotter);
		Application.getComponent(DriverManager.class).setCurrentPlotter(clientPlotter);
		DrawPanelController drawPanelController = ApplicationWithDrawer.getDrawPanelController();
		IPlotter plotter = new SimplePlotterAdapter(drawPanelController);
		context.addDriver("Simple plotter", plotter);
		IPlotter linePlotter = new LinePlotterAdapter(drawPanelController, LineFactory.getSpecialLine());
		context.addDriver("Special line plotter", linePlotter);

		context.updateDriverInfo();
	}

	
	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param context Application context.
	 */
	private static void setupLogger(Context context) {
		Application.addComponent(Logger.class);
		context.addComponentMenu(Logger.class, "Logger", 0);
		context.addComponentMenuElement(Logger.class, "Clear log", (ActionEvent e) -> context.flushLoggerOutput());
		context.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> LOGGER.setLevel(Level.FINE));
		context.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> LOGGER.setLevel(Level.INFO));
		context.addComponentMenuElement(Logger.class, "Warning level", (ActionEvent e) -> LOGGER.setLevel(Level.WARNING));
		context.addComponentMenuElement(Logger.class, "Severe level", (ActionEvent e) -> LOGGER.setLevel(Level.SEVERE));
		context.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> LOGGER.setLevel(Level.OFF));
	}
	
		
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ApplicationWithDrawer.configureApplication();
                Context context = Application.getComponent(Context.class);
                 
            	setupDrivers(context);
            	setupPresetTests(context);
            	setupLogger(context);
            }

        });
    }

}
