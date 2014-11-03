import controller.PeriodictableUnmarshaller;
import view.PeriodictableUI;


public class MainUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PeriodictableUnmarshaller controller = new PeriodictableUnmarshaller();
		PeriodictableUI ui = new PeriodictableUI(controller);
		ui.run();
	}

}
