package barber.shop.windowsystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
public class WindowPanel extends JPanel implements ActionListener {

	private static final Logger logger = LogManager.getLogger(WindowPanel.class);
	private JButton button = new JButton("Peinado");
	private JButton button2 = new JButton("Peinado 2");

	public WindowPanel() {
		this.add(button);
		this.add(button2);
		button.addActionListener(this);
		button2.addActionListener(this);
		button.setBounds(100, 200, 100, 100);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setBackground(Color.BLUE);
		logger.warn(e.getActionCommand());
		logger.info("hola");
	}

}
