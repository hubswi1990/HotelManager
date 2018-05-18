package pl.hubswi90.hotel.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.xml.bind.JAXBException;

import pl.hubswi90.hotel.configuration.Configuration;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class HotelView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	
	
	private RoomsPanel roomsPanel;
	
	private JMenuItem menuItemQuit;
	private JMenuItem menuItemRooms;
	private JTabbedPane tabbedPane;

	/**
	 * Create the frame.
	 */
	public HotelView() {
		setTitle("Hotel Manager");
		setBounds(Configuration.centeringScreenOnWidth(FRAME_WIDTH), Configuration.centeringScreenOnHeight(FRAME_HEIGHT),
			 FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFie = new JMenu("File");
		menuBar.add(menuFie);
		
		menuItemQuit = new JMenuItem("Quit");
		menuItemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		menuFie.add(menuItemQuit);
		
		JMenu menuReception = new JMenu("Reception");
		menuBar.add(menuReception);
		
		menuItemRooms = new JMenuItem("Rooms");
		menuReception.add(menuItemRooms);
		
		JSeparator separator = new JSeparator();
		menuReception.add(separator);
		
		JMenu menuCustomer = new JMenu("Customers");
		menuReception.add(menuCustomer);
		
		JMenuItem mntmCustomers = new JMenuItem("View Customers");
		menuCustomer.add(mntmCustomers);
		
		JMenuItem mntmNewCustomer = new JMenuItem("New Customer");
		menuCustomer.add(mntmNewCustomer);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		roomsPanel = new RoomsPanel();

		tabbedPane.addTab("Rooms", roomsPanel);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnRomms = new JButton("romms");
		toolBar.add(btnRomms);
		
		JButton btnCustomer = new JButton("customer");
		toolBar.add(btnCustomer);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				roomsPanel.changeListOfRooms();
				try {
					Configuration.getInstance().saveConfigurationToFile();
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				super.windowClosing(e);
			}
			
		});
	}
	
	public void addActionListener(ActionListener event) {
		menuItemQuit.addActionListener(event);
	}
	
	public JMenuItem getMenuItemQuit() {
		return menuItemQuit;
	}
	public JMenuItem getMenuItemRooms() {
		return menuItemRooms;
	}
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
