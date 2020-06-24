package barber.shop.windowsystem;

import barber.shop.BarberShop;
import barber.shop.Customer;
import barber.shop.UI;
import barber.shop.exceptions.BarberException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class BarberFrame extends JFrame {

	private static final Logger logger = LogManager.getLogger(BarberFrame.class);
	private final JPanel panelBarberShop;
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
	private final UI ui;
	private JComboBox<String> comboBox;
	private String[] comboBoxList = new String[] {" ", "Astorga", "San Justo"};
	private BarberShop barberShop;

	public BarberFrame() {
		this.setName("BarberShop");
		this.panelBarberShop = new JPanel();
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


		panelBarberShop.setBackground(Color.LIGHT_GRAY);
		this.setFont(new Font("Monospaced", Font.BOLD, 12));
		panelBarberShop.setBounds(0, 10, 1000, 200);
		this.setBounds(0, 10, screenWidth, screenHeight);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);


		this.comboBox = new JComboBox<>(comboBoxList);
		comboBox.setBounds(235, 107, 180, 24);
		panelBarberShop.add(comboBox);

		// Buttons start //

		/*
		 * Add reservation button
		 */
		this.addReservationButton.setBounds(23, 169, 150, 25);
		this.addReservationButton.addActionListener(add -> {
			String place = comboBoxList[comboBox.getSelectedIndex()];
			this.ui.actionPerformed(add.getActionCommand(), fullNameTextField.getText(), hourTextField.getText(),
				minuteTextField.getText(), place);
		});

		/*
		 * Cancel reservation button
		 */
		this.cancelReservationButton.setBounds(443, 169, 170, 25);
		this.cancelReservationButton.addActionListener(cancel -> {
			String place = comboBoxList[comboBox.getSelectedIndex()];
			this.ui.actionPerformed(cancel.getActionCommand(), fullNameTextField.getText(), hourTextField.getText(),
				minuteTextField.getText(), place);
		});

		/*
		 * Modify reservation button
		 */
		this.modifyReservationButton.setBounds(225, 169, 170, 25);
		this.modifyReservationButton.addActionListener(modify -> {
			String place = comboBoxList[comboBox.getSelectedIndex()];
			modifyReservationOption();
		});

		// Buttons end //

		panelBarberShop.add(this.addReservationButton);
		panelBarberShop.add(this.modifyReservationButton);
		panelBarberShop.add(this.cancelReservationButton);
		panelBarberShop.setLayout(null);
		this.fullNameTextField.setBounds(235, 22, 181, 19);
		panelBarberShop.add(this.fullNameTextField);

		this.hourTextField.setBounds(235, 63, 51, 19);
		panelBarberShop.add(this.hourTextField);

		this.minuteTextField.setBounds(298, 63, 51, 19);
		panelBarberShop.add(this.minuteTextField);


		// THIS MUST BE HERE
		panelBarberShop.setVisible(true);
		this.getContentPane().add(this.panelBarberShop);
		this.setVisible(true);
	}

	private void modifyReservationOption() {
		JFrame modify = new JFrame("Modify Reservation");
		JPanel panel = new JPanel();

		modify.setBounds(235, 500, 800, 650);
		comboBox1.setBounds(0, 100, 180, 24);
		name1.setBounds(0, 0, 181, 19);
		hour1.setBounds(0, 50, 70, 19);
		minute1.setBounds(105, 50, 85, 19);
		confirmButton.setBounds(15, 150, 100, 20);

		panel.setBounds(modify.getBounds());
		panel.add(comboBox1);
		panel.add(name1);
		panel.add(hour1);
		panel.add(minute1);
		panel.add(confirmButton);

		modify.getContentPane().add(panel);
		modify.setVisible(true);

		confirmButton.addActionListener(confirm -> {
			try {
				Customer c0 = new Customer(fullNameTextField.getText(), Integer.parseInt(hourTextField.getText()),
					Integer.parseInt(minuteTextField.getText()), comboBoxList[comboBox.getSelectedIndex()]);
				Customer c1 = new Customer(name1.getText(), Integer.parseInt(hour1.getText()),
					Integer.parseInt(minute1.getText()), comboBoxList[comboBox1.getSelectedIndex()]);

				this.barberShop.modifyReservation(c0, c1);
			} catch (BarberException e) {
				logger.warn(e.getMessage());
			} catch (NumberFormatException e) {
				logger.warn("Error en la hora o minuto");
			}

			if (confirmButton.isEnabled()) {
				modify.setVisible(false);
			}
//			data = null;
		});
	}
}
