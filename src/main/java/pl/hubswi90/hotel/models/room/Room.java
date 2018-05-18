package pl.hubswi90.hotel.models.room;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "room")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room implements Serializable, Comparable<Room> {
	
	private static final long serialVersionUID = 1L;
	
	//@XmlAttribute(name = "")
	private int roomNumber;
	private byte singleBeds, doubleBeds;
	private double price;
	private int floor;
	private String description;
	
	/*
	 * Do NOT modify and do NOT use this constructor. Access modifier is public
	 * because of the JAXB use.
	 */
	@Deprecated
	public Room() {}

	public Room(int roomNumber, byte singleBeds, byte doubleBeds, double price,
			int floor) {
		this.roomNumber = roomNumber;
		this.singleBeds = singleBeds;
		this.doubleBeds = doubleBeds;
		this.price = price;
		this.floor = floor;
	}
	
	public Room(int roomNumber, byte singleBeds, byte doubleBeds, double price,
			int floor, String description) {
		this.roomNumber = roomNumber;
		this.singleBeds = singleBeds;
		this.doubleBeds = doubleBeds;
		this.price = price;
		this.floor = floor;
		this.description = description;
	}
	
	
	/**
	 * copy constructor
	 */
	public Room(Room room) {
		this.roomNumber = room.getRoomNumber();
		this.singleBeds = room.getSingleBeds();
		this.doubleBeds = room.getDoubleBeds();
		this.price = room.getPrice();
		this.floor = room.getFloor();
		this.description = room.getDescription();
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public byte getSingleBeds() {
		return singleBeds;
	}

	public void setSingleBeds(byte singleBeds) {
		this.singleBeds = singleBeds;
	}

	public byte getDoubleBeds() {
		return doubleBeds;
	}

	public void setDoubleBeds(byte doubleBeds) {
		this.doubleBeds = doubleBeds;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Room compareRoom) {
		int compareNumberRoom = ((Room) compareRoom).getRoomNumber();
		
		return this.roomNumber - compareNumberRoom;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", singleBeds=" + singleBeds
				+ ", doubleBeds=" + doubleBeds + ", price=" + price
				+ ", floor=" + floor + ", description=" + description + "]";
	}

}
