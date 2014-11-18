package ku.periodictable.view;

import java.awt.Frame;

import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceException;

import ku.periodictable.controller.PeriodictableUnmarshaller;

/**
 * Client user interface main class.
 * create controller then inject to user interface.
 * @author Atit Leelasuksan 5510546221
 *
 */
public class MainUI {

	
	private static PeriodictableUnmarshaller controller;
	
	private static PeriodictableUI ui;
	private static String[] options = {"Retry", "Cancel"};
	
	/**
	 * main method to run via IDE
	 * @param args
	 */
	public static void main(String[] args) {
		ui = new PeriodictableUI();
		ui.run();
	}
	
	/**
	 * callback method for ui to retrieve a controller.
	 */
	public static PeriodictableUnmarshaller createController(Frame frame) {
		try {
			controller = new PeriodictableUnmarshaller();
			return controller;
		} catch(WebServiceException e) {
			int choose = JOptionPane.showOptionDialog(null, "No network connection!", "Network Error", 
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, 
					options, options[0]);
			if(choose==0) {
				return createController(frame);
			} else {
				frame.dispose();
				return null;
			}
		}
	}

}
