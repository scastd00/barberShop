package barber.shop.windowsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
public class WindowPanel extends JPanel implements ActionListener {

	private static final Logger logger = LogManager.getLogger(WindowPanel.class);
	JButton button = new JButton("Peinado");

	public WindowPanel() {
		this.add(button);
		button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setBackground(Color.BLUE);
	}

}
