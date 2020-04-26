package barber.shop;

import java.awt.*;
import javax.swing.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("serial")
public class WindowInterface extends JFrame {

    private static final Logger logger = LogManager.getLogger(WindowInterface.class);

    public WindowInterface(){
    	Toolkit screen = Toolkit.getDefaultToolkit();
    	Dimension screenSize = screen.getScreenSize();
    	//Image icon = screen.getImage("etc/icon.png");
    	//this.setIconImage(icon);
    	
    	int screenHeight = screenSize.height;
    	int screenWidth = screenSize.width;

        this.setVisible(true);
        this.setTitle("Barber Shop App");
        //this.setBounds(400, 300, 400, 300);
        this.setSize(screenWidth/2, screenHeight/2);
        this.setLocation(screenWidth/4, (int) (screenHeight/3.5));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        //this.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}