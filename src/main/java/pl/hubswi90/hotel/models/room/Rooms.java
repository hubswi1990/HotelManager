package pl.hubswi90.hotel.models.room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rooms")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rooms implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "room")
	private List<Room> rooms;
	//private List<Room> rooms;
	
	public Rooms() {
		rooms = new ArrayList<Room>();
	}
	
	public Rooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public void addRoomToList(Room room) {
		rooms.add(room);
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
