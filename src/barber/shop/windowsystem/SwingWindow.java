package barber.shop.windowsystem;

import barber.shop.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class SwingWindow {

	private JFrame frmBarberShop;
	private JTextField txtCompleteName;
	private JTextField txtHour;
	private JTextField txtMinute;
	private BarberShop bs;
	private GUI gui;
	private JTable table;

	/**
	 * Create the application.
	 */
	public SwingWindow() {
		frmBarberShop = new JFrame();
		bs = new BarberShop();
		gui = new GUI(bs);
	}

	public void init() {
		Toolkit screen = Toolkit.getDefaultToolkit();
		Dimension screenSize = screen.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		String[] comboBoxList = new String[] {"", "Astorga", "San Justo"};


		frmBarberShop.setBackground(Color.LIGHT_GRAY);
		frmBarberShop.setTitle("Barber Shop");
		frmBarberShop.setFont(new Font("Monospaced", Font.BOLD, 12));
		frmBarberShop.setBounds(100, 100, screenWidth, screenHeight);
		frmBarberShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(comboBoxList));
		comboBox.setBounds(235, 107, 181, 24);
		frmBarberShop.getContentPane().add(comboBox);

		// Buttons start //

		/*
		 * Add reservation button
		 */
		JButton btnAddReservation = new JButton("Add Reservation");
		btnAddReservation.setBounds(23, 169, 150, 25);
		btnAddReservation.addActionListener(add -> {
			String place = comboBoxList[comboBox.getSelectedIndex()];
			gui.actionPerformed(add.getActionCommand(), txtCompleteName.getText(), txtHour.getText(),
				txtMinute.getText(), place);
		});

		/*
		 * Cancel reservation button
		 */
		JButton btnCancelReservation = new JButton("Cancel Reservation");
		btnCancelReservation.setBounds(443, 169, 170, 25);
		btnCancelReservation.addActionListener(cancel -> {
			String place = comboBoxList[comboBox.getSelectedIndex()];
			gui.actionPerformed(cancel.getActionCommand(), txtCompleteName.getText(), txtHour.getText(),
				txtMinute.getText(), place);
		});

		/*
		 * Modify reservation button
		 */
		JButton btnModifyReservation = new JButton("Modify Reservation");
		btnModifyReservation.setBounds(225, 169, 170, 25);
		btnModifyReservation.addActionListener(modify -> {
			String place = comboBoxList[comboBox.getSelectedIndex()];
			gui.actionPerformed(modify.getActionCommand(), txtCompleteName.getText(), txtHour.getText(),
				txtMinute.getText(), place);
		});

		// Buttons end //

		frmBarberShop.getContentPane().add(btnAddReservation);
		frmBarberShop.getContentPane().add(btnModifyReservation);
		frmBarberShop.getContentPane().add(btnCancelReservation);
		frmBarberShop.getContentPane().setLayout(null);
		txtCompleteName = new JTextField();
		txtCompleteName.setText("Complete Name");
		txtCompleteName.setBounds(235, 22, 181, 19);
		frmBarberShop.getContentPane().add(txtCompleteName);
		txtCompleteName.setColumns(10);

		txtHour = new JTextField();
		txtHour.setText("Hour");
		txtHour.setBounds(235, 63, 51, 19);
		frmBarberShop.getContentPane().add(txtHour);
		txtHour.setColumns(10);

		txtMinute = new JTextField();
		txtMinute.setText("Minute");
		txtMinute.setBounds(298, 63, 57, 19);
		frmBarberShop.getContentPane().add(txtMinute);
		txtMinute.setColumns(10);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Name", "Time", "Place"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(0).setMinWidth(250);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);
		table.getColumnModel().getColumn(2).setMinWidth(125);
		table.setBorder(new LineBorder(Color.DARK_GRAY));
		table.setBounds(650, 65, 427, 144);
		frmBarberShop.getContentPane().add(table);

		// THIS MUST BE HERE
		frmBarberShop.setVisible(true);
	}
}
