package barber.shop.windowsystem;

import java.awt.*;
import javax.swing.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("serial")
public class WindowInterface extends JFrame {

	private static final Logger logger = LogManager.getLogger(WindowInterface.class);
	private WindowPanel panel;

	public WindowInterface() {
		Toolkit screen = Toolkit.getDefaultToolkit();
		Dimension screenSize = screen.getScreenSize();
		// Image icon = screen.getImage("etc/icon.png");
		// this.setIconImage(icon);

		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		// this.setSize(screenWidth / 2, screenHeight / 2);
		this.setVisible(true);
		logger.debug("Window created");
		this.setTitle("Barber Shop App");
		this.setBounds(400, 100, 700, 500);

		this.setLocation(screenWidth / 4, (int) (screenHeight / 3.5));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		// this.setExtendedState(MAXIMIZED_BOTH);

		this.panel = new WindowPanel();
		add(panel);
	}
}