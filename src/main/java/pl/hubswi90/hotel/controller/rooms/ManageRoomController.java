package pl.hubswi90.hotel.controller.rooms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import pl.hubswi90.hotel.MyTableModel;
import pl.hubswi90.hotel.models.room.Room;
import pl.hubswi90.hotel.view.ManageRoomComponent;
import pl.hubswi90.hotel.view.RoomsPanel;

public class ManageRoomController implements ActionListener {

	private ManageRoomComponent screen;
	private RoomsPanel parent;
	private boolean isClickedButoonOk, isClickedButoonCancel;
	private MyTableModel model;
	private int status; // 0 - addnewitem, 1 - edit item, 2 - preview item
	int selectedRow;

	public ManageRoomController(RoomsPanel parent, int status) {
		this.parent = parent;
		this.model = (MyTableModel) parent.getTable().getModel();
		screen = new ManageRoomComponent();
		screen.addActionListener(this);
		isClickedButoonOk = false;
		isClickedButoonCancel = false;
		this.status = status;
	}

	public void setVisibleScreen(boolean b) {
		screen.setVisible(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == screen.getOkButton()) {
			switch (status) {
			case 0:
				addNewRoom();
				break;
			case 1:
				editRoom();
				break;
			case 2:
				isClickedButoonOk = true;
				setVisibleScreen(false);
				break;
			default:
				break;
			}
		} else if (e.getSource() == screen.getCancelButton()) {
			isClickedButoonCancel = true;
			screen.dispose();
		}
	}

	public boolean getIsClickedButoonOk() {
		return isClickedButoonOk;
	}
	
	public boolean getIsClickedButoonCancel() {
		return isClickedButoonCancel;
	}

	public boolean doesRoomNumberAlreadyExist(int numberRoom) {
		Iterator<Room> i = parent.getListOfRooms().iterator();
		while (i.hasNext()) {
			if (i.next().getRoomNumber() == numberRoom) {
				return true;
			}
		}
		return false;
	}

	public void setTextfields(int row) {
		this.selectedRow = row;
		
		if (StringUtils.isNotBlank(model.getValueAt(row, 0).toString()))
			screen.getTextFieldRoomNumber().setText(model.getValueAt(row, 0).toString());

		if (StringUtils.isNotBlank(model.getValueAt(row, 1).toString()))
			screen.getTextFieldSingleBeds().setText(model.getValueAt(row, 1).toString());

		if (StringUtils.isNotBlank(model.getValueAt(row, 2).toString()))
			screen.getTextFieldDoubleBeds().setText(model.getValueAt(row, 2).toString());

		if (StringUtils.isNotBlank(model.getValueAt(row, 3).toString()))
			screen.getTextFieldPrice().setText(model.getValueAt(row, 3).toString());

		if (StringUtils.isNotBlank(model.getValueAt(row, 4).toString()))
			screen.getTextFieldFloor().setText(model.getValueAt(row, 4).toString());

/*		if (StringUtils.isNotBlank(model.getValueAt(row, 5).toString()))
			screen.getTextAreaDescription().append(model.getValueAt(row, 5).toString());*/
		if(status == 2) {
			screen.getTextFieldRoomNumber().setEditable(false);
			screen.getTextFieldSingleBeds().setEditable(false);
			screen.getTextFieldDoubleBeds().setEditable(false);
			screen.getTextFieldPrice().setEditable(false);
			screen.getTextFieldFloor().setEditable(false);
			screen.getTextAreaDescription().setEditable(false);
			screen.getTextAreaDescription().setBackground(screen.getContentPanel().getBackground());
		}
	}

	private void addNewRoom() {
		boolean AllFieldAreFilled = false;

		String numberRoomText = screen.getTextFieldRoomNumber().getText().trim();
		String singleBedsText = screen.getTextFieldSingleBeds().getText().trim();
		String doubleBedsText = screen.getTextFieldDoubleBeds().getText().trim();
		String priceText = screen.getTextFieldPrice().getText().trim();
		String floorText = screen.getTextFieldFloor().getText().trim();
		String description = screen.getTextAreaDescription().getText().trim();

		if (StringUtils.isNotBlank(numberRoomText) && StringUtils.isNotBlank(singleBedsText)
				&& StringUtils.isNotBlank(doubleBedsText) && StringUtils.isNotBlank(priceText)
				&& StringUtils.isNotBlank(floorText)) {
			AllFieldAreFilled = true;
		}

		if (AllFieldAreFilled) {
			try {
				int NumberOfNewRoom = Integer.parseInt(numberRoomText);
				byte singleBeds = Byte.parseByte(singleBedsText);
				byte doubleBeds = Byte.parseByte(doubleBedsText);
				double price = Double.parseDouble(priceText);
				int floor = Integer.parseInt(floorText);

				boolean existNumberRoom = doesRoomNumberAlreadyExist(NumberOfNewRoom);

				if (existNumberRoom) {
					JOptionPane.showMessageDialog(screen, NumberOfNewRoom
							+ " already exist!\nPlease use another Number");
				} else {

					Room newRoom = new Room(NumberOfNewRoom, singleBeds, doubleBeds, price, floor);

					if (StringUtils.isNotBlank(description))
						newRoom.setDescription(description);

					parent.getListOfRooms().add(newRoom);
					Collections.sort(parent.getListOfRooms());

					while (model.getRowCount() > 0) {
						model.removeRow(0);
					}

					for (int i = 0; i < parent.getListOfRooms().size(); i++) {
						Object[] row = new Object[6];
						newRoom = parent.getListOfRooms().get(i);
						row[0] = newRoom.getRoomNumber();
						row[1] = newRoom.getSingleBeds();
						row[2] = newRoom.getDoubleBeds();
						row[3] = newRoom.getPrice();
						row[4] = newRoom.getFloor();
						row[5] = newRoom.getDescription();
						model.insertRow(i, row);
					}
					isClickedButoonOk = true;
					setVisibleScreen(false);
				}
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(screen, "Some field is not a number!");
			}
		}
	}

	private void editRoom() {
		boolean AllFieldAreFilled = false;

		String numberRoomText = screen.getTextFieldRoomNumber().getText().trim();
		String singleBedsText = screen.getTextFieldSingleBeds().getText().trim();
		String doubleBedsText = screen.getTextFieldDoubleBeds().getText().trim();
		String priceText = screen.getTextFieldPrice().getText().trim();
		String floorText = screen.getTextFieldFloor().getText().trim();
		String description = screen.getTextAreaDescription().getText().trim();

		if (StringUtils.isNotBlank(numberRoomText) && StringUtils.isNotBlank(singleBedsText)
				&& StringUtils.isNotBlank(doubleBedsText) && StringUtils.isNotBlank(priceText)
				&& StringUtils.isNotBlank(floorText)) {
			AllFieldAreFilled = true;
		}

		if (AllFieldAreFilled) {
			Room room = parent.getListOfRooms().get(selectedRow);
			
			model.setValueAt(numberRoomText, selectedRow, 0);
			room.setRoomNumber(Integer.parseInt(numberRoomText));
			
			model.setValueAt(singleBedsText, selectedRow, 1);
			room.setSingleBeds(Byte.parseByte(singleBedsText));
			
			model.setValueAt(doubleBedsText, selectedRow, 2);
			room.setDoubleBeds(Byte.parseByte(doubleBedsText));
			
			model.setValueAt(priceText, selectedRow, 3);
			room.setPrice(Double.parseDouble(priceText));
			
			model.setValueAt(floorText, selectedRow, 4);
			room.setFloor(Integer.parseInt(floorText));
			
		}
		isClickedButoonOk = true;
		setVisibleScreen(false);
	}
}
