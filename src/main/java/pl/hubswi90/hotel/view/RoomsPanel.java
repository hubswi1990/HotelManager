package pl.hubswi90.hotel.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.hubswi90.hotel.MyTableModel;
import pl.hubswi90.hotel.configuration.Configuration;
import pl.hubswi90.hotel.controller.rooms.ManageRoomController;
import pl.hubswi90.hotel.models.room.Room;

public class RoomsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ManageRoomController mrc;
	private List<Room> listOfRooms;

	private final String[] columns = { "Number", "Bed (SB)", "Bed (DB)", "price", "Floor", "Description" };

	private JTable table;
	private MyTableModel model;

	private JButton btnPreviewRoom;
	private JButton btnDeleteRoom;
	private JButton btnEditRoom;
	private JButton btnNewRoom;
	private JTextField textField;
	private JButton btnRefreshDataInTable;
	private JButton btnSearch;
	
	@SuppressWarnings("rawtypes")
	private JComboBox searchComboBox;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("rawtypes")
	public RoomsPanel() {
		setForeground(Color.WHITE);

		btnNewRoom = new JButton("New");

		JScrollPane scrollPane = new JScrollPane();

		btnEditRoom = new JButton("Edit");
		btnEditRoom.setEnabled(false);

		btnDeleteRoom = new JButton("Delete");
		btnDeleteRoom.setEnabled(false);

		btnPreviewRoom = new JButton("Preview");
		btnPreviewRoom.setEnabled(false);

		JLabel lblSearchBy = new JLabel("Search by");

		searchComboBox = new JComboBox(columns);

		JLabel lblSearchText = new JLabel("search text");

		textField = new JTextField();
		textField.setColumns(10);

		btnSearch = new JButton("search");

		btnRefreshDataInTable = new JButton("Refresh List");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createSequentialGroup()
														.addComponent(btnRefreshDataInTable)
														.addPreferredGap(ComponentPlacement.RELATED, 83,
																Short.MAX_VALUE).addComponent(btnPreviewRoom)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnDeleteRoom)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnEditRoom)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnNewRoom))
										.addGroup(
												groupLayout
														.createSequentialGroup()
														.addComponent(lblSearchBy)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(searchComboBox, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblSearchText)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textField, GroupLayout.PREFERRED_SIZE, 132,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnSearch))).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSearchBy)
										.addComponent(searchComboBox, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSearchText)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE).addComponent(btnSearch))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
						.addGap(18)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnNewRoom)
										.addComponent(btnEditRoom).addComponent(btnDeleteRoom)
										.addComponent(btnPreviewRoom).addComponent(btnRefreshDataInTable))
						.addContainerGap()));

		listOfRooms = new ArrayList<Room>();
		for (Room room : Configuration.getInstance().getHotel().getListOfRooms().getRooms()) {
			listOfRooms.add(new Room(room));
		}

		model = new MyTableModel();
		model.setColumnIdentifiers(columns);

		Object[] row = new Object[6];
		for (Room room : listOfRooms) {
			row[0] = room.getRoomNumber();
			row[1] = room.getSingleBeds();
			row[2] = room.getDoubleBeds();
			row[3] = room.getPrice();
			row[4] = room.getFloor();
			row[5] = room.getDescription();
			model.addRow(row);
		}

		table = new JTable(model);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		addActionListener();
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())
		        {
		            boolean rowsAreSelected = table.getSelectedRowCount() > 0;
		            btnPreviewRoom.setEnabled(rowsAreSelected);
		            btnDeleteRoom.setEnabled(rowsAreSelected);
		            btnEditRoom.setEnabled(rowsAreSelected);
		            
		        }
			}
		});
	}

	public void addActionListener() {
		btnSearch.addActionListener(this);
		btnRefreshDataInTable.addActionListener(this);
		btnPreviewRoom.addActionListener(this);
		btnDeleteRoom.addActionListener(this);
		btnEditRoom.addActionListener(this);
		btnNewRoom.addActionListener(this);
	}

	public JButton getBtnPreviewRoom() {
		return btnPreviewRoom;
	}

	public JButton getBtnDeleteRoom() {
		return btnDeleteRoom;
	}

	public JButton getBtnEditRoom() {
		return btnEditRoom;
	}

	public JButton getBtnNewRoom() {
		return btnNewRoom;
	}

	public JButton getBtnRefreshDataInTable() {
		return btnRefreshDataInTable;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public JTable getTable() {
		return table;
	}
	
	@SuppressWarnings("rawtypes")
	public JComboBox getSearchComboBox() {
		return searchComboBox;
	}

	public List<Room> getListOfRooms() {
		return listOfRooms;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnNewRoom) {
			if (mrc == null)
				mrc = new ManageRoomController(this, 0);
			
			mrc.setVisibleScreen(true);
			if (mrc.getIsClickedButoonOk() || mrc.getIsClickedButoonCancel())
				mrc = null;
		} else if (e.getSource() == btnEditRoom) {
			if (mrc == null)
				mrc = new ManageRoomController(this, 1);
			
			int row = table.getSelectedRow();
			mrc.setTextfields(row);
			mrc.setVisibleScreen(true);
			if (mrc.getIsClickedButoonOk() || mrc.getIsClickedButoonCancel())
				mrc = null;
		} else if (e.getSource() == btnDeleteRoom) {
			int row = table.getSelectedRow();
			
			int type = JOptionPane.showConfirmDialog(this, "Are you sure delete room number: " +model.getValueAt(row, 0).toString(),
					"delete room", JOptionPane.YES_NO_OPTION);
			
			if(type == JOptionPane.YES_OPTION) {
				getListOfRooms().remove(row);
				model.removeRow(row);
				
			}
		} else if (e.getSource() == btnPreviewRoom) {
			if (mrc == null)
				mrc = new ManageRoomController(this, 2);
			
			int row = table.getSelectedRow();
			mrc.setTextfields(row);
			mrc.setVisibleScreen(true);
			if (mrc.getIsClickedButoonOk() || mrc.getIsClickedButoonCancel())
				mrc = null;
		} else if (e.getSource() == btnRefreshDataInTable) {
			refreshDataInTable();
		}

	}

	private void refreshDataInTable() {
		Collections.sort(getListOfRooms());

		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}

		for (int i = 0; i < getListOfRooms().size(); i++) {
			Object[] row = new Object[6];
			Room newRoom = getListOfRooms().get(i);
			row[0] = newRoom.getRoomNumber();
			row[1] = newRoom.getSingleBeds();
			row[2] = newRoom.getDoubleBeds();
			row[3] = newRoom.getPrice();
			row[4] = newRoom.getFloor();
			row[5] = newRoom.getDescription();
			model.insertRow(i, row);
		}
	}

	public void changeListOfRooms() {
		Configuration.getInstance().getHotel().getListOfRooms().setRooms(listOfRooms);
	}
}
