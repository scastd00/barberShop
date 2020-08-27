package barber.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.awt.*;

public class SwingBarber extends JFrame {

	private static final Logger logger = LogManager.getLogger(SwingBarber.class);
	private final JFrame modificationFrame;
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

	// In order to suppress the recurrent error messages when an error occurs while modifying a reservation.
	private int counterToShowOnlyOneError = 0;

	public SwingBarber() {
		this.setName("BarberShop");
		this.modificationFrame = new JFrame("Customer Modification");
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
		this.setBounds(0, 0, 800, 700);
		this.setLayout(null);
		this.setTitle("Barber Shop Agenda");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.modificationFrame.setBounds(50, 200, 600, 400);
		this.modificationFrame.setLayout(null);

		initializeComponents();

		this.panelBarberShop.setVisible(true);
		this.getContentPane().add(this.panelBarberShop);
		this.setVisible(true);

		buttonPressed();
	}

	private void initializeComponents() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		this.panelBarberShop.setBounds(this.getBounds());
		this.panelBarberShop.setBackground(Color.GRAY);
		this.panelBarberShop.setLayout(null);

		this.modificationPanel.setBounds(0, 0, this.modificationFrame.getWidth(), this.modificationFrame.getHeight());
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

		this.comboBox1.setBounds(comboBox.getBounds());
		this.modificationPanel.add(comboBox1);

		this.fullNameTextField1.setBounds(fullNameTextField.getBounds());
		this.modificationPanel.add(fullNameTextField1);

		this.hour1.setBounds(hourTextField.getBounds());
		this.modificationPanel.add(hour1);

		this.minute1.setBounds(minuteTextField.getBounds());
		this.modificationPanel.add(minute1);

		this.confirmButton.setBounds(235, 150, 100, 19);
		this.modificationPanel.add(confirmButton);

		JLabel fullNameLabel = new JLabel("Complete name");
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

		JLabel newNameLabel = new JLabel("New complete name");
		newNameLabel.setBounds(70, 22, 150, 20);
		newNameLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		this.modificationPanel.add(newNameLabel);

		JLabel newTimeLabel = new JLabel("New Hour : Minute");
		newTimeLabel.setBounds(82, 63, 150, 20);
		newTimeLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		this.modificationPanel.add(newTimeLabel);

		JLabel newDoubleDots = new JLabel(":");
		newDoubleDots.setBounds(doubleDots.getBounds());
		newDoubleDots.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
		this.modificationPanel.add(newDoubleDots);

		JLabel newPlaceSelection = new JLabel("Select the new place");
		newPlaceSelection.setBounds(65, 109, 150, 20);
		newPlaceSelection.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		this.modificationPanel.add(newPlaceSelection);
	}

	@Contract(pure = true)
	private void buttonPressed() {
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

		this.modifyReservationButton.addActionListener(modify -> {
			this.modificationFrame.getContentPane().add(this.modificationPanel);
			this.modificationFrame.setVisible(true);

			this.counterToShowOnlyOneError = 0;

			this.confirmButton.addActionListener(confirm -> {
				if (this.counterToShowOnlyOneError == 0) {
					try {
						String place = this.comboBoxList[this.comboBox.getSelectedIndex()];
						int hour = Integer.parseInt(this.hourTextField.getText());
						int minute = Integer.parseInt(this.minuteTextField.getText());

						String place2 = this.comboBoxList[this.comboBox1.getSelectedIndex()];
						int hour2 = Integer.parseInt(this.hour1.getText());
						int minute2 = Integer.parseInt(this.minute1.getText());

						this.barberShop.modifyReservation(
							new Customer(fullNameTextField.getText(), hour, minute, place),
							new Customer(fullNameTextField1.getText(), hour2, minute2, place2));

						this.counterToShowOnlyOneError++;
					} catch (BarberException e) {
						if (this.counterToShowOnlyOneError == 0) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							logger.debug("Incorrect customer values. Name: {}, Place: {}", fullNameTextField1.getText(),
								this.comboBoxList[this.comboBox1.getSelectedIndex()]);

							this.counterToShowOnlyOneError++;
						}

					} catch (NumberFormatException e) {
						if (this.counterToShowOnlyOneError == 0) {
							JOptionPane.showMessageDialog(null, "Incorrect time value", "Error", JOptionPane.ERROR_MESSAGE);
							logger.debug("Incorrect time values. Hour: {}, Minute: {}", hour1.getText(), minute1.getText());

							this.counterToShowOnlyOneError++;
						}
					} finally {
						this.modificationFrame.setVisible(false);
						logger.info(this.barberShop);
					}
				}
			});
		});

		this.cancelReservationButton.addActionListener(cancel -> {
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
