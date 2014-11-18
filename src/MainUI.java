import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceException;

import ku.periodictable.controller.PeriodictableUnmarshaller;
import ku.periodictable.view.PeriodictableUI;

/**
 * Client user interface main class.
 * create controller then inject to user interface.
 * @author Atit Leelasuksan 5510546221
 *
 */
public class MainUI {

	
	private static PeriodictableUnmarshaller controller;
	
	private static String[] options = {"Retry", "Cancel"};
	
	/**
	 * main method to run via IDE
	 * @param args
	 */
	public static void main(String[] args) {
		init();
	}
	
	/**
	 * initialize all component for periodictable webservice client.
	 */
	private static void init() {
		try {
			controller = new PeriodictableUnmarshaller();
		} catch(WebServiceException e) {
			int choose = JOptionPane.showOptionDialog(null, "No network connection!", "Network Error", 
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, 
					options, options[0]);
			if(choose==0) reInit();
			return;
		}
		PeriodictableUI ui = new PeriodictableUI(controller);
		ui.run();
	}
	
	/**
	 * callback method to call init method, 
	 * I try to use this to prevent directly call to init method.
	 * It should use for callback but I still didn't use this.
	 */
	public static void reInit() {
		init();
	}

}
