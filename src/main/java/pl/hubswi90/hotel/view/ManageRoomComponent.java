package pl.hubswi90.hotel.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import pl.hubswi90.hotel.configuration.Configuration;

public class ManageRoomComponent extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final int FRAME_WIDTH = 640;
	private static final int FRAME_HEIGHT = 480;
	
	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;
	private JTextField textFieldRoomNumber;
	private JTextField textFieldDoubleBeds;
	private JTextField textFieldSingleBeds;
	private JTextField textFieldPrice;
	private JTextField textFieldFloor;
	private JButton okButton;
	private JTextArea textAreaDescription;

	/**
	 * Create the dialog.
	 */
	public ManageRoomComponent() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(Configuration.centeringScreenOnWidth(FRAME_WIDTH), Configuration.centeringScreenOnHeight(FRAME_HEIGHT),
				FRAME_WIDTH, FRAME_HEIGHT);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Basic data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblRoomNumber = new JLabel("Room Number");
		
		textFieldRoomNumber = new JTextField();
		textFieldRoomNumber.setColumns(10);
		
		JLabel lblSingleBeds = new JLabel("Number of single beds");
		
		JLabel lblDoubleBeds = new JLabel("Number of double beds");
		
		JLabel lblPrice = new JLabel("Price");
		
		JLabel lblFloor = new JLabel("Floor");
		
		JLabel lblDescription = new JLabel("Description");
		
		textFieldDoubleBeds = new JTextField();
		textFieldDoubleBeds.setColumns(10);
		
		textFieldSingleBeds = new JTextField();
		textFieldSingleBeds.setColumns(10);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		
		textFieldFloor = new JTextField();
		textFieldFloor.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDoubleBeds)
						.addComponent(lblSingleBeds)
						.addComponent(lblRoomNumber)
						.addComponent(lblPrice)
						.addComponent(lblFloor)
						.addComponent(lblDescription))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(textFieldRoomNumber, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
							.addComponent(textFieldSingleBeds)
							.addComponent(textFieldDoubleBeds)
							.addComponent(textFieldPrice)
							.addComponent(textFieldFloor, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
					.addGap(221))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRoomNumber)
						.addComponent(textFieldRoomNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSingleBeds)
						.addComponent(textFieldSingleBeds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDoubleBeds)
						.addComponent(textFieldDoubleBeds, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrice)
						.addComponent(textFieldPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFloor)
						.addComponent(textFieldFloor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDescription)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(215, Short.MAX_VALUE))
		);
		
		textAreaDescription = new JTextArea();
		scrollPane.setViewportView(textAreaDescription);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	public JButton getOkButton() {
		return okButton;
	}
	public JTextArea getTextAreaDescription() {
		return textAreaDescription;
	}
	public JTextField getTextFieldFloor() {
		return textFieldFloor;
	}
	public JTextField getTextFieldPrice() {
		return textFieldPrice;
	}
	public JTextField getTextFieldDoubleBeds() {
		return textFieldDoubleBeds;
	}
	public JTextField getTextFieldSingleBeds() {
		return textFieldSingleBeds;
	}
	public JTextField getTextFieldRoomNumber() {
		return textFieldRoomNumber;
	}
	
	public void addActionListener(ActionListener event) {
		okButton.addActionListener(event);
		cancelButton.addActionListener(event);
	}
	public JPanel getContentPanel() {
		return contentPanel;
	}
}
