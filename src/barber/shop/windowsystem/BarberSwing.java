package barber.shop.windowsystem;

import barber.shop.BarberShop;
import barber.shop.UI;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.*;

public class BarberSwing {
	private final JFrame frmBarberShop;
	private final JButton addReservationButton;
	private final JButton cancelReservationButton;
	private final JButton modifyReservationButton;
	private final JButton confirmButton;
	private final JTextField fullNameTextField;
	private final JTextField hourTextField;
	private final JTextField minuteTextField;
	private final JTextField name1;
	private final JTextField hour1;
	private final JTextField minute1;
	private final JComboBox<String> comboBox1;
	private JComboBox<String> comboBox;
	private final UI ui;
	private String[] comboBoxList = new String[] {" ", "Astorga", "San Justo"};
	private BarberShop barberShop;

	public BarberSwing() {
		this.frmBarberShop = new JFrame("BarberShop");
		this.addReservationButton = new JButton("Add Reservation");
		this.cancelReservationButton = new JButton("Cancel Reservation");
		this.modifyReservationButton = new JButton("Modify Reservation");
		this.comboBox = new JComboBox<>();
		this.fullNameTextField = new JTextField("Complete Name");
		this.hourTextField = new JTextField("Hour");
		this.minuteTextField = new JTextField("Minute");
		this.name1 = new JTextField("New name");
		this.hour1 = new JTextField("New hour");
		this.minute1 = new JTextField("New minute");
		this.comboBox1 = new JComboBox<>(comboBoxList);
		this.confirmButton = new JButton("Confirm");
		this.barberShop = new BarberShop();
		this.ui = new UI(barberShop);
	}

	public void init() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;


		frmBarberShop.setBackground(Color.LIGHT_GRAY);
		frmBarberShop.setFont(new Font("Monospaced", Font.BOLD, 12));
		frmBarberShop.setBounds(0, 10, screenWidth, screenHeight);
		frmBarberShop.setDefaultCloseOperation(EXIT_ON_CLOSE);


		this.comboBox = new JComboBox<>(comboBoxList);
		comboBox.setBounds(235, 107, 180, 24);
		frmBarberShop.getContentPane().add(comboBox);

		// Buttons start //

		/*
		 * Add reservation button
		 */
		this.addReservationButton.setBounds(23, 169, 150, 25);
		this.addReservationButton.addActionListener(add -> {
			String place = comboBoxList[comboBox.getSelectedIndex()];
			this.ui.actionPerformed(add.getActionCommand(), fullNameTextField.getText(), hourTextField.getText(),
				minuteTextField.getText(), place);
			this.barberShop.printHash();
		});

		/*
		 * Cancel reservation button
		 */
		this.cancelReservationButton.setBounds(443, 169, 170, 25);
		this.cancelReservationButton.addActionListener(cancel -> {
			String place = comboBoxList[comboBox.getSelectedIndex()];
			this.ui.actionPerformed(cancel.getActionCommand(), fullNameTextField.getText(), hourTextField.getText(),
				minuteTextField.getText(), place);
			this.barberShop.printHash();
		});

		/*
		 * Modify reservation button
		 */
		this.modifyReservationButton.setBounds(225, 169, 170, 25);
		this.modifyReservationButton.addActionListener(modify -> {
			String place = comboBoxList[comboBox.getSelectedIndex()];
			modifyReservationOption(modify.getActionCommand(), fullNameTextField.getText(), hourTextField.getText(),
				minuteTextField.getText(), place);
		});

		// Buttons end //

		frmBarberShop.getContentPane().add(this.addReservationButton);
		frmBarberShop.getContentPane().add(this.modifyReservationButton);
		frmBarberShop.getContentPane().add(this.cancelReservationButton);
		frmBarberShop.getContentPane().setLayout(null);
		this.fullNameTextField.setBounds(235, 22, 181, 19);
		frmBarberShop.getContentPane().add(this.fullNameTextField);
		this.fullNameTextField.setColumns(10);

		this.hourTextField.setBounds(235, 63, 51, 19);
		frmBarberShop.getContentPane().add(this.hourTextField);
		this.hourTextField.setColumns(10);

		this.minuteTextField.setBounds(298, 63, 51, 19);
		frmBarberShop.getContentPane().add(this.minuteTextField);
		this.minuteTextField.setColumns(10);





		// THIS MUST BE HERE
		frmBarberShop.setVisible(true);
	}

	private void modifyReservationOption(String op, String name, String hour, String minute, String place) {
		JPanel modify = new JPanel();
		modify.setBounds(235, 250, 400, 400);
		modify.add(comboBox1);
		modify.add(name1);
		modify.add(hour1);
		modify.add(minute1);
		modify.add(confirmButton);
		comboBox1.setBounds(0, 100, 180, 24);
		name1.setBounds(0, 0, 181, 19);
		hour1.setBounds(0, 50, 70, 19);
		minute1.setBounds(105, 50, 85, 19);
		confirmButton.setBounds(15, 150, 100, 20);
		modify.setVisible(true);
		this.frmBarberShop.getContentPane().add(modify);
		this.frmBarberShop.setVisible(true);

		confirmButton.addActionListener(confirm -> {
			String[][] data = {{op, name1.getText()}, {hour, hour1.getText()}, {minute, minute1.getText()}, {place,
				comboBoxList[comboBox1.getSelectedIndex()]}};
			this.ui.actionModifyCustomer(data[0], data[1], data[2], data[3]);
			modify.setVisible(false);
			this.barberShop.printHash();
		});
	}
}
