package edu.iis.powp.adapter;

import edu.iis.client.plottermagic.AbstractPlotter;
import edu.iis.client.plottermagic.IPlotter;

public class SimpleAbstractPlotterAdapter extends AbstractPlotter {

	private final IPlotter plotter;
	
	public SimpleAbstractPlotterAdapter(IPlotter plotter) {
		super(0, 0);
		this.plotter = plotter;	
		this.plotter.setPosition(0, 0);
	}

	@Override
	public void drawTo(int x, int y) {
		this.plotter.drawTo(x, y);
	}

}
