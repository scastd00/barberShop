package barber.shop.windowsystem;

import barber.shop.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SwingWindow {

	private JFrame frmBarberShop;
	private JTextField txtCompleteName;
	private JTextField txtHour;
	private JTextField txtMinute;
	private BarberShop bs;
	private GUI gui;

	/**
	 * Create the application.
	 */
	public SwingWindow() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmBarberShop = new JFrame();
		bs = new BarberShop();
		gui = new GUI(bs);
		Toolkit screen = Toolkit.getDefaultToolkit();
		Dimension screenSize = screen.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		String[] comboBoxList = new String[] { "", "Astorga", "San Justo" };
		

		frmBarberShop.setBackground(Color.LIGHT_GRAY);
		frmBarberShop.setTitle("Barber Shop");
		frmBarberShop.setFont(new Font("Monospaced", Font.BOLD, 12));
		frmBarberShop.setBounds(100, 100, screenWidth, screenHeight);
		frmBarberShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(comboBoxList));
		comboBox.setBounds(284, 175, 158, 24);
		frmBarberShop.getContentPane().add(comboBox);

		// Buttons start //

		/**
		 * Add reservation button
		 */
		JButton btnAddReservation = new JButton("Add Reservation");
		btnAddReservation.setBounds(62, 234, 131, 25);
		btnAddReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent add) {
				String place = comboBoxList[comboBox.getSelectedIndex()];
				gui.actionPerformed(add.getActionCommand(), txtCompleteName.getText(), txtHour.getText(),
						txtMinute.getText(), place);
			}
		});

		/**
		 * Cancel reservation button
		 */
		JButton btnCancelReservation = new JButton("Cancel Reservation");
		btnCancelReservation.setBounds(526, 234, 170, 25);
		btnCancelReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent cancel) {
				String place = comboBoxList[comboBox.getSelectedIndex()];
				gui.actionPerformed(cancel.getActionCommand(), txtCompleteName.getText(), txtHour.getText(),
						txtMinute.getText(), place);
			}
		});

		/**
		 * Modify reservation button
		 */
		JButton btnModifyReservation = new JButton("Modify Reservation");
		btnModifyReservation.setBounds(272, 234, 170, 25);
		btnModifyReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent modify) {
				String place = comboBoxList[comboBox.getSelectedIndex()];
				gui.actionPerformed(modify.getActionCommand(), txtCompleteName.getText(), txtHour.getText(),
						txtMinute.getText(), place);
			}
		});

		// Buttons end //

		frmBarberShop.getContentPane().add(btnAddReservation);
		frmBarberShop.getContentPane().add(btnModifyReservation);
		frmBarberShop.getContentPane().add(btnCancelReservation);
		frmBarberShop.getContentPane().setLayout(null);
		txtCompleteName = new JTextField();
		txtCompleteName.setText("Complete Name");
		txtCompleteName.setBounds(284, 33, 114, 19);
		frmBarberShop.getContentPane().add(txtCompleteName);
		txtCompleteName.setColumns(10);

		txtHour = new JTextField();
		txtHour.setText("Hour");
		txtHour.setBounds(284, 74, 114, 19);
		frmBarberShop.getContentPane().add(txtHour);
		txtHour.setColumns(10);

		txtMinute = new JTextField();
		txtMinute.setText("Minute");
		txtMinute.setBounds(284, 125, 114, 19);
		frmBarberShop.getContentPane().add(txtMinute);
		txtMinute.setColumns(10);
		
		// THIS MUST BE HERE
		frmBarberShop.setVisible(true);
	}
}
