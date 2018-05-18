package pl.hubswi90.hotel.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.hubswi90.hotel.models.room.Rooms;

@XmlRootElement(name = "hotel")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hotel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private Rooms listOfRooms;

	public Hotel() {
		listOfRooms = new Rooms();
	}

	public Hotel(Rooms listOfRooms) {
		this.listOfRooms = listOfRooms;
	}

	public Rooms getListOfRooms() {
		return listOfRooms;
	}

	public void setListOfRooms(Rooms listOfRooms) {
		this.listOfRooms = listOfRooms;
	}
	
	
	
	
}
