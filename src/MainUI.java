import controller.PeriodictableUnmarshaller;
import view.PeriodictableUI;

/**
 * Client user interface main class.
 * create controller then inject to user interface.
 * @author Atit Leelasuksan 5510546221
 *
 */
public class MainUI {

	
	/**
	 * main method to run via IDE
	 * @param args
	 */
	public static void main(String[] args) {
		PeriodictableUnmarshaller controller = new PeriodictableUnmarshaller();
		PeriodictableUI ui = new PeriodictableUI(controller);
		ui.run();
	}

}
