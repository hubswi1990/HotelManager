package pl.hubswi90.hotel;

import pl.hubswi90.hotel.configuration.Configuration;
import pl.hubswi90.hotel.controller.HotelSystem;

public class Main {
	

	public static void main(String[] args) {
		
		Configuration.getInstance();
		new HotelSystem();
	}

}
