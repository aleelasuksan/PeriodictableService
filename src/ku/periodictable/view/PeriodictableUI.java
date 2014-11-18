package ku.periodictable.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.ws.WebServiceException;

import ku.periodictable.controller.PeriodictableCache;
import ku.periodictable.controller.PeriodictableUnmarshaller;


import net.webservicex.entity.*;

/**
 * client user interface class.
 * @author Atit Leelasuksan 5510546221
 *
 */
public class PeriodictableUI {

	/**
	 * all componenet that use in user interface.
	 */
	private JFrame frame = new JFrame("Periodictable Service");
	
	private JList<String> elementList;
	
	private JScrollPane listScroller;
	
	private JLabel eleName = new JLabel();
	
	private JLabel eleSymbol = new JLabel();
	
	private JLabel eleAtomNumber = new JLabel();
	
	private JLabel eleAtomWeight = new JLabel();
	
	private JLabel eleAtomRadius = new JLabel();
	
	private JLabel eleDensity = new JLabel();
	
	private JLabel eleBoiling = new JLabel();
	
	private JLabel eleMelting = new JLabel();
	
	private JLabel eleIoPot = new JLabel();
	
	private JLabel eleEleNeg = new JLabel();
	
	private Container contentContainer;
	
	private PeriodictableUnmarshaller controller;
	
	private LoadElementInfoTask lastestTask;
	
	private String[] timeoutOption = {"OK"};
	
	private PeriodictableCache cache;
	
	public PeriodictableUI(PeriodictableUnmarshaller controller) {
		cache = new PeriodictableCache();
		initComponent();
		this.controller = controller;
	}
	
	private void initComponent() {
		frame.setSize(500,350);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		elementList = new JList<String>();
		elementList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		elementList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String eleName = (((JList<String>)e.getSource()).getSelectedValue());
				if(cache.containsKey(eleName)) {
					updateElementInfo(cache.getInfo(eleName));
				}
				else {
					lastestTask = new LoadElementInfoTask(eleName);
					lastestTask.execute();
					try {
						lastestTask.get(10, TimeUnit.SECONDS);
					} catch (InterruptedException | ExecutionException | TimeoutException | WebServiceException e1) {
						JOptionPane.showOptionDialog(frame, "Request Timeout!", "Timeout", 
								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, 
								timeoutOption, timeoutOption[0]);
					}
				}
			}
		});
		listScroller = new JScrollPane(elementList);
		listScroller.setPreferredSize(new Dimension(120,250));
		
		JLabel name = new JLabel("Name: ");
		JPanel namePane = new JPanel();
		namePane.setLayout(new FlowLayout(FlowLayout.LEFT));
		namePane.add(name);
		namePane.add(eleName);
		
		JLabel symbol = new JLabel("Symbol: ");
		JPanel symPane = new JPanel();
		symPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		symPane.add(symbol);
		symPane.add(eleSymbol);
		
		JLabel number = new JLabel("Atomic Number: ");
		JPanel numPane = new JPanel();
		numPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		numPane.add(number);
		numPane.add(eleAtomNumber);
		
		JLabel weight = new JLabel("Atomic Weight: ");
		JPanel weightPane = new JPanel();
		weightPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		weightPane.add(weight);
		weightPane.add(eleAtomWeight);
		
		JLabel rad = new JLabel("Atomic Radius: ");
		JPanel radPane = new JPanel();
		radPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		radPane.add(rad);
		radPane.add(eleAtomRadius);
		
		JLabel dens = new JLabel("Density: ");
		JPanel densPane = new JPanel();
		densPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		densPane.add(dens);
		densPane.add(eleDensity);
		
		JLabel boil = new JLabel("Boiling Point: ");
		JPanel boilPane = new JPanel();
		boilPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		boilPane.add(boil);
		boilPane.add(eleBoiling);
		
		JLabel melt = new JLabel("Melting Point: ");
		JPanel meltPane = new JPanel();
		meltPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		meltPane.add(melt);
		meltPane.add(eleMelting);
		
		JLabel iopot = new JLabel("Ionisation Potential: ");
		JPanel ioPane = new JPanel();
		ioPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		ioPane.add(iopot);
		ioPane.add(eleIoPot);
		
		JLabel eleneg = new JLabel("Electronegativity: ");
		JPanel elenegPane = new JPanel();
		elenegPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		elenegPane.add(eleneg);
		elenegPane.add(eleEleNeg);
		
		Container resultContainer = new Container();
		resultContainer.setLayout(new BoxLayout(resultContainer, BoxLayout.Y_AXIS));
		resultContainer.add(namePane);
		resultContainer.add(symPane);
		resultContainer.add(numPane);
		resultContainer.add(weightPane);
		resultContainer.add(radPane);
		resultContainer.add(densPane);
		resultContainer.add(boilPane);
		resultContainer.add(meltPane);
		resultContainer.add(ioPane);
		resultContainer.add(elenegPane);
		
		contentContainer = new Container();
		contentContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
		contentContainer.add(listScroller);
		contentContainer.add(resultContainer);
		
		Container outer = new Container();
		outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));
		outer.add(contentContainer);
		JButton reset = new JButton("Reset Service");
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetService();
			}
		});
		outer.add(reset);
		
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
		pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pane.add(outer);
		frame.add(pane);
		frame.setResizable(false);
	}
	
	public void run() {
		frame.setVisible(true);
		(new LoadAllElementTask()).execute();
	}
	
	/**
	 * load all element to list.
	 */
	private void loadAllElementtoList() {
		elementList.setModel(getElementArray());
		listScroller.updateUI();
	}
	
	/**
	 * Convert element's data to list model so it can use with JList
	 * @return ListModel<String> of each element's name.
	 */
	public ListModel<String> getElementArray() {
		DefaultListModel<String> list = new DefaultListModel<String>();
		try{
			List<Element> allElement = controller.getAllElement();
			for(Element ele : allElement) {
				list.addElement(ele.getElementName());
			}
			return list;
		} catch( WebServiceException e) {
			String[] options = {"Retry", "Cancel"};
			int choose = JOptionPane.showOptionDialog(frame, "Network error occured!", "Network Error", 
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, 
					options, options[0]);
			if(choose==0) loadAllElementtoList();
			else frame.dispose();
		}
		return null;
	}
	
	/**
	 * update element information label from input.
	 * @param elementInfo to update.
	 */
	public void updateElementInfo(Element elementInfo) {
		eleName.setText(elementInfo.getElementName());
		eleSymbol.setText(elementInfo.getSymbol());
		eleAtomNumber.setText(elementInfo.getAtomicNumber()+"");
		eleAtomWeight.setText(elementInfo.getAtomicWeight()+" "+Element.WEIGHT_UNIT);
		eleAtomRadius.setText(elementInfo.getAtomicRadius()+" "+Element.ATOMIC_RADIUS_UNIT);
		eleBoiling.setText(elementInfo.getBoilingPoint()+" "+Element.TEMPERATURE_UNIT);
		eleMelting.setText(elementInfo.getMeltingPoint()+" "+Element.TEMPERATURE_UNIT);
		eleDensity.setText(elementInfo.getDensity()+" "+Element.DENSITY_UNIT);
		eleIoPot.setText(elementInfo.getIonisationPotential()+" "+Element.IONISATION_POTENTIAL_UNIT);
		eleEleNeg.setText(elementInfo.getEletroNegativity()+" "+Element.ELECTRO_NEGATIVITY_UNIT);
	}
	
	private void resetService() {
		controller = new PeriodictableUnmarshaller();
		(new LoadAllElementTask()).execute();
	}
	
	/**
	 * task to handle load all element in background.
	 * @author Atit Leelasuksan 5510546221
	 *
	 */
	class LoadAllElementTask extends SwingWorker<List<Element>, Object> {

		@Override
		protected List<Element> doInBackground() throws Exception {
			loadAllElementtoList();
			return null;
		}
		
		@Override
		protected void done() {
			listScroller.updateUI();
		}
		
	}
	
	/**
	 * task to handle load element info in background.
	 * @author Atit Leelasuksan 5510546221
	 *
	 */
	class LoadElementInfoTask extends SwingWorker<Element, Object> {

		private String elementName;
		
		LoadElementInfoTask(String elementName) {
			this.elementName = elementName;
		}
		
		@Override
		protected Element doInBackground() throws Exception {
			return controller.getElementInfo(elementName);
		}
		
		@Override
		protected void done() {
			try {
				cache.addToCache(elementName, get());
				updateElementInfo(get());
			} catch (InterruptedException | ExecutionException e) {
				
			}
		}
	}
}
