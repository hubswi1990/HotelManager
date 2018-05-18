package pl.hubswi90.hotel.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.hubswi90.hotel.view.HotelView;

public class HotelSystem implements ActionListener {

	private HotelView screen;
	
	public HotelSystem() {
		screen = new HotelView();
		screen.addActionListener(this);
		screen.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == screen.getMenuItemQuit()) {
			screen.dispose();
			System.exit(0);
		}
	}
}
