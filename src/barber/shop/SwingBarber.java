package barber.shop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SwingBarber {

	private static final String ERROR = "Error";
	private static final String CUSTOMER_WRONG = "Incorrect customer values. Name: {}, Place: {}";
	private static final String TIME_WRONG = "Incorrect sinkTime values. Hour: {}, Minute: {}";
	private static final String INCORRECT_VALUE = "Incorrect sinkTime value";
	private static final Logger logger = LogManager.getLogger(SwingBarber.class);

	private final JFrame generalFrame;
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
	private final String[] comboBoxList = new String[]{"", "Astorga", "San Justo"};
	private final JComboBox<String> comboBox;
	private final JTable customersTable;
	private final BarberShop barberShop;

	// In order to suppress the recurrent error messages when an error occurs while modifying a reservation.
	private int counterToShowOnlyOneError = 0;

	public SwingBarber() {
		this.generalFrame = new JFrame("Barber Shop Agenda");
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
		this.customersTable = new JTable();
		this.barberShop = new BarberShop();
	}

	/**
	 * Initializes the window and all the components.
	 */
	public void init() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		this.generalFrame.setBounds(0, 0, screenWidth, screenHeight);
		this.generalFrame.setLayout(null);
		this.generalFrame.setTitle("Barber Shop Agenda");
		this.generalFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.modificationFrame.setBounds(50, 200, 600, 400);
		this.modificationFrame.setLayout(null);

		initializeComponents();

		this.panelBarberShop.setVisible(true);
		this.generalFrame.getContentPane().add(this.panelBarberShop);
		this.generalFrame.setVisible(true);

		buttonPressed();
	}

	/**
	 * Initializes all the window components.
	 */
	private void initializeComponents() {
		this.panelBarberShop.setBounds(this.generalFrame.getBounds());
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

		this.customersTable.setFillsViewportHeight(true);
		this.customersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.customersTable.setSurrendersFocusOnKeystroke(true);
		this.customersTable.setModel(new DefaultTableModel(printTableInWindow(), new String[]{"Customer 1", "Customer 2",
				"Customer 3", "Customer 4"}));
		this.customersTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		this.customersTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		this.customersTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		this.customersTable.getColumnModel().getColumn(3).setPreferredWidth(200);
		this.customersTable.setBorder(new LineBorder(Color.BLACK));
		this.customersTable.setBounds(100, 300, 800, 384);
		this.customersTable.setVisible(true);
		this.panelBarberShop.add(this.customersTable);
	}

	/**
	 * When a button is pressed, a special action is done. {@link #addButtonActions()} {@link #modifyButtonActions()}
	 * {@link #cancelButtonActions()}
	 */
	private void buttonPressed() {
		this.addReservationButton.addActionListener(add -> addButtonActions());

		this.modifyReservationButton.addActionListener(modify -> modifyButtonActions());

		this.cancelReservationButton.addActionListener(cancel -> cancelButtonActions());
	}

	/**
	 * Shows the timetable with all the customers in the window.
	 *
	 * @return a String board with all the customers.
	 */
	private String[][] printTableInWindow() {
		String[][] timetable = new String[Constants.MAX_HOUR + 1][4];
		Customer[][] customers = this.barberShop.getTimetable();

		for (int i = 0; i < customers.length; i++) {
			for (int j = 0; j < customers[i].length; j++) {
				if (customers[i][j] != null) {
					timetable[i][j] = customers[i][j].toString();
				}
			}
		}

		return timetable;
	}

	/**
	 * Refreshes the table when a modification is done.
	 */
	private void refreshTable() {
		this.customersTable.setModel(new DefaultTableModel(printTableInWindow(), new String[]{"Customer 1", "Customer 2",
				"Customer 3", "Customer 4"}));
	}

	/**
	 * When the AddReservationButton is pressed, the parameters specified in the TextFields and ComboBox are taken to create a new
	 * Customer.
	 */
	private void addButtonActions() {
		try {
			String place = this.comboBoxList[this.comboBox.getSelectedIndex()];
			int hour = Integer.parseInt(hourTextField.getText());
			int minute = Integer.parseInt(minuteTextField.getText());

			this.barberShop.addReservation(new Customer(fullNameTextField.getText(), hour, minute, place));
			logger.debug(this.barberShop);
			refreshTable();
		} catch (BarberException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), SwingBarber.ERROR, JOptionPane.ERROR_MESSAGE);
			logger.debug(SwingBarber.CUSTOMER_WRONG, fullNameTextField.getText(),
					this.comboBoxList[this.comboBox.getSelectedIndex()]);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, SwingBarber.INCORRECT_VALUE, SwingBarber.ERROR, JOptionPane.ERROR_MESSAGE);
			logger.debug(SwingBarber.TIME_WRONG, hourTextField.getText(), minuteTextField.getText());
		}
	}

	/**
	 * When the CancelReservationButton is pressed, the parameters specified in the TextFields and ComboBox are taken to delete a
	 * Customer.
	 */
	private void cancelButtonActions() {
		try {
			String place = this.comboBoxList[this.comboBox.getSelectedIndex()];
			int hour = Integer.parseInt(hourTextField.getText());
			int minute = Integer.parseInt(minuteTextField.getText());

			this.barberShop.cancelReservation(new Customer(fullNameTextField.getText(), hour, minute, place));
			logger.debug(this.barberShop);
			refreshTable();
		} catch (BarberException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), SwingBarber.ERROR, JOptionPane.ERROR_MESSAGE);
			logger.debug(SwingBarber.CUSTOMER_WRONG, fullNameTextField.getText(),
					this.comboBoxList[this.comboBox.getSelectedIndex()]);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, SwingBarber.INCORRECT_VALUE, SwingBarber.ERROR, JOptionPane.ERROR_MESSAGE);
			logger.debug(SwingBarber.TIME_WRONG, hourTextField.getText(), minuteTextField.getText());
		}
	}

	/**
	 * When the ModifyReservationButton is pressed, the parameters specified in the TextFields and ComboBox of the first window are
	 * taken to delete a Customer. Then, a popup window appears with other TextFields and ComboBox, used to create a new Customer.
	 */
	private void modifyButtonActions() {
		this.modificationFrame.getContentPane().add(this.modificationPanel);
		this.modificationFrame.setVisible(true);

		this.counterToShowOnlyOneError = 0;

		this.confirmButton.addActionListener(confirm -> confirmButtonActions());
	}

	/**
	 * Checks if the parameters in the modification popup window are correct before adding a new Customer to the timetable.
	 */
	private void confirmButtonActions() {
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
				refreshTable();
			} catch (BarberException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), SwingBarber.ERROR, JOptionPane.ERROR_MESSAGE);
				logger.debug(SwingBarber.CUSTOMER_WRONG, fullNameTextField1.getText(),
						this.comboBoxList[this.comboBox1.getSelectedIndex()]);

			} catch (NumberFormatException e) {
				if (this.counterToShowOnlyOneError == 0) {
					JOptionPane.showMessageDialog(null, SwingBarber.INCORRECT_VALUE, SwingBarber.ERROR, JOptionPane.ERROR_MESSAGE);
					logger.debug(SwingBarber.TIME_WRONG, hour1.getText(), minute1.getText());
				}
			} finally {
				this.modificationFrame.setVisible(false);
				this.counterToShowOnlyOneError++;
				logger.debug(this.barberShop);
			}
		}
	}
}
