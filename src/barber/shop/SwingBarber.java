package barber.shop;

import org.jetbrains.annotations.Contract;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class SwingBarber extends JFrame {

	private static final Logger logger = LogManager.getLogger(SwingBarber.class);
	private final JPanel panelBarberShop;
	private final JPanel modificationPanel;
	private final JButton addReservationButton;
	private final JButton cancelReservationButton;
	private final JButton modifyReservationButton;
	private final JButton confirmButton;
	private final JTextField fullNameTextField;
	private final JTextField hourTextField;
	private final JTextField minuteTextField;
	private final JTextField fullNameTextField1;
	private final JTextField hour1;
	private final JTextField minute1;
	private final JComboBox<String> comboBox1;
	private final String[] comboBoxList = new String[] {"", "Astorga", "San Justo"};
	private final JComboBox<String> comboBox;
	private final BarberShop barberShop;

	public SwingBarber() {
		this.setName("BarberShop");
		this.panelBarberShop = new JPanel();
		this.modificationPanel = new JPanel();
		this.addReservationButton = new JButton("Add Reservation");
		this.cancelReservationButton = new JButton("Cancel Reservation");
		this.modifyReservationButton = new JButton("Modify Reservation");
		this.comboBox = new JComboBox<>(comboBoxList);
		this.fullNameTextField = new JTextField();
		this.hourTextField = new JTextField();
		this.minuteTextField = new JTextField();
		this.fullNameTextField1 = new JTextField();
		this.hour1 = new JTextField();
		this.minute1 = new JTextField();
		this.comboBox1 = new JComboBox<>(comboBoxList);
		this.confirmButton = new JButton("Confirm");
		this.barberShop = new BarberShop();
	}

	public void init() {
		this.setBounds(20, 10, 1400, 700);
		this.setTitle("Barber Shop Agenda");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		initializeComponents();

		this.panelBarberShop.setVisible(true);
		this.getContentPane().add(this.panelBarberShop);
		this.setVisible(true);

		actionPerformed();
	}

	private void initializeComponents() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		this.panelBarberShop.setBounds(0, 10, this.getBounds().width, this.getBounds().height);
		this.panelBarberShop.setBackground(Color.GRAY);
		this.panelBarberShop.setLayout(null);

		this.modificationPanel.setBounds(50, 200, 500, 200);
		this.modificationPanel.setBackground(Color.GRAY);
		this.modificationPanel.setLayout(null);

		this.addReservationButton.setBounds(23, 169, 150, 25);
		this.panelBarberShop.add(this.addReservationButton);

		this.modifyReservationButton.setBounds(225, 169, 170, 25);
		this.panelBarberShop.add(this.modifyReservationButton);

		this.cancelReservationButton.setBounds(443, 169, 170, 25);
		this.panelBarberShop.add(this.cancelReservationButton);

		this.comboBox.setBounds(235, 107, 180, 24);
		this.panelBarberShop.add(comboBox);

		this.fullNameTextField.setBounds(235, 22, 181, 19);
		this.panelBarberShop.add(this.fullNameTextField);

		this.hourTextField.setBounds(235, 63, 51, 19);
		this.panelBarberShop.add(this.hourTextField);

		this.minuteTextField.setBounds(298, 63, 51, 19);
		this.panelBarberShop.add(this.minuteTextField);

		this.comboBox1.setBounds(0, 100, 180, 24);
		this.modificationPanel.add(comboBox1);

		this.fullNameTextField1.setBounds(0, 0, 181, 19);
		this.modificationPanel.add(fullNameTextField1);

		this.hour1.setBounds(0, 50, 70, 19);
		this.modificationPanel.add(hour1);

		this.minute1.setBounds(105, 50, 85, 19);
		this.modificationPanel.add(minute1);

		this.confirmButton.setBounds(15, 150, 10, 20);
		this.modificationPanel.add(confirmButton);

		JLabel fullNameLabel = new JLabel("Complete Name");
		fullNameLabel.setBounds(105, 22, 150, 20);
		fullNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		this.panelBarberShop.add(fullNameLabel);

		JLabel timeLabel = new JLabel("Hour : Minute");
		timeLabel.setBounds(120, 63, 150, 20);
		timeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		this.panelBarberShop.add(timeLabel);

		JLabel doubleDots = new JLabel(":");
		doubleDots.setBounds(290, 61, 20, 20);
		doubleDots.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
		this.panelBarberShop.add(doubleDots);

		JLabel placeSelection = new JLabel("Select a place");
		placeSelection.setBounds(117, 109, 150, 20);
		placeSelection.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		this.panelBarberShop.add(placeSelection);
	}

	@Contract(pure = true)
	private void actionPerformed() {
		this.addReservationButton.addActionListener(add -> {
			try {
				String place = this.comboBoxList[this.comboBox.getSelectedIndex()];
				int hour = Integer.parseInt(hourTextField.getText());
				int minute = Integer.parseInt(minuteTextField.getText());

				this.barberShop.addReservation(new Customer(fullNameTextField.getText(), hour, minute, place));
				logger.info(this.barberShop.toString());
			} catch (BarberException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				logger.debug("Incorrect customer values. Name: {}, Place: {}", fullNameTextField.getText(),
					this.comboBoxList[this.comboBox.getSelectedIndex()]);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Incorrect time value", "Error", JOptionPane.ERROR_MESSAGE);
				logger.debug("Incorrect time values. Hour: {}, Minute: {}", hourTextField.getText(), minuteTextField.getText());
			}
		});

//		this.modifyReservationButton.addActionListener(add -> {
//			try {
//				String place = this.comboBoxList[this.comboBox.getSelectedIndex()];
//				int hour = Integer.parseInt(hourTextField.getText());
//				int minute = Integer.parseInt(minuteTextField.getText());
//
//				this.barberShop.modifyReservation(new Customer(fullNameTextField.getText(), hour, minute, place));
//				logger.info(this.barberShop.toString());
//			} catch (BarberException e) {
//				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//				logger.debug("Incorrect customer values. Name: {}, Place: {}", fullNameTextField.getText(),
//					this.comboBoxList[this.comboBox.getSelectedIndex()]);
//			} catch (NumberFormatException e) {
//				JOptionPane.showMessageDialog(null, "Incorrect time value", "Error", JOptionPane.ERROR_MESSAGE);
//				logger.debug("Incorrect time values. Hour: {}, Minute: {}", hourTextField.getText(), minuteTextField.getText());
//			}
//		});

		this.cancelReservationButton.addActionListener(add -> {
			try {
				String place = this.comboBoxList[this.comboBox.getSelectedIndex()];
				int hour = Integer.parseInt(hourTextField.getText());
				int minute = Integer.parseInt(minuteTextField.getText());

				this.barberShop.cancelReservation(new Customer(fullNameTextField.getText(), hour, minute, place));
				logger.info(this.barberShop.toString());
			} catch (BarberException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				logger.debug("Incorrect customer values. Name: {}, Place: {}", fullNameTextField.getText(),
					this.comboBoxList[this.comboBox.getSelectedIndex()]);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Incorrect time value", "Error", JOptionPane.ERROR_MESSAGE);
				logger.debug("Incorrect time values. Hour: {}, Minute: {}", hourTextField.getText(), minuteTextField.getText());
			}
		});
	}
}
