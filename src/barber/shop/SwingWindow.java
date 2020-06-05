package barber.shop;

import barber.shop.windowsystem.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingWindow {

	private JFrame frmBarberShop;
	private BarberShop bs;
	private WindowInterface wi;
	private JTextField txtCompleteName;
	private JTextField txtHour;
	private JTextField txtMinute;

	/**
	 * Create the application.
	 */
	public SwingWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Toolkit screen = Toolkit.getDefaultToolkit();
		Dimension screenSize = screen.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		bs = new BarberShop();
		wi = new WindowInterface(bs);

		frmBarberShop = new JFrame();
		frmBarberShop.setFont(new Font("Monospaced", Font.BOLD, 12));
		frmBarberShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBarberShop.setTitle("Barber Shop");
		frmBarberShop.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmBarberShop.setVisible(true);

		/**
		 * Selection of the place
		 */
		JComboBox<String> comboBox = new JComboBox<>();
		String[] comboBoxList = new String[] { "Select a Place", "Astorga", "San Justo de la Vega" };
		comboBox.setModel(new DefaultComboBoxModel<>(comboBoxList));
		comboBox.setBounds(200, 123, 185, 24);

		/**
		 * Add reservation button
		 */
		JButton btnAddReservation = new JButton("Add Reservation");
		btnAddReservation.setBounds(62, 234, 131, 25);
		btnAddReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent add) {
				String place = comboBoxList[comboBox.getSelectedIndex()];
				wi.actionPerformed(add.getActionCommand(), txtCompleteName.getText(), txtHour.getText(),
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
				wi.actionPerformed(cancel.getActionCommand(), txtCompleteName.getText(), txtHour.getText(),
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
				wi.actionPerformed(modify.getActionCommand(), txtCompleteName.getText(), txtHour.getText(),
						txtMinute.getText(), place);
			}
		});

		frmBarberShop.getContentPane().add(btnModifyReservation);
		frmBarberShop.getContentPane().setLayout(null);
		frmBarberShop.getContentPane().add(btnAddReservation);
		frmBarberShop.getContentPane().add(btnCancelReservation);

		txtCompleteName = new JTextField();
		txtCompleteName.setText("Complete Name");
		txtCompleteName.setBounds(200, 30, 294, 19);
		frmBarberShop.getContentPane().add(txtCompleteName);
		txtCompleteName.setColumns(10);

		txtHour = new JTextField();
		txtHour.setText("Hour");
		txtHour.setBounds(200, 61, 185, 19);
		frmBarberShop.getContentPane().add(txtHour);
		txtHour.setColumns(10);

		txtMinute = new JTextField();
		txtMinute.setText("Minute");
		txtMinute.setBounds(200, 92, 185, 19);
		frmBarberShop.getContentPane().add(txtMinute);
		txtMinute.setColumns(10);

		frmBarberShop.getContentPane().add(comboBox);
		frmBarberShop.setBounds(250, 250, screenWidth, screenHeight);
		txtMinute.getText();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
