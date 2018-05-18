package pl.hubswi90.hotel;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	
	public MyTableModel() {
		super();
	}

	public MyTableModel(String[][] data, String[] colums) {
		super(data, colums);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
