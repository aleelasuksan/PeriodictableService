package controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.WebServiceException;

import net.webservicex.Periodictable;
import net.webservicex.PeriodictableSoap;
import net.webservicex.entity.Element;
import net.webservicex.entity.NewDataSet;

/**
 * Controller class for handle service request from user interface.
 * have 2 function of request,
 * first is request all element that service provided.
 * second is request specific information of element by element name.
 * Second function support only element name, can't use symbol.
 * @author Atit Leelasuksan 5510546221
 *
 */
public class PeriodictableUnmarshaller {

	/**
	 * service to use.
	 */
	private PeriodictableSoap service;
	
	/**
	 * unmarshaller to unmarshall xml to java object.
	 */
	private Unmarshaller unm;
	
	/**
	 * default constructor.
	 */
	public PeriodictableUnmarshaller() {
		Periodictable factory = new Periodictable();
		service = factory.getPeriodictableSoap();
		try {
			JAXBContext context = JAXBContext.newInstance( NewDataSet.class, Element.class);
			unm = context.createUnmarshaller();
		} catch (JAXBException je) {
			je.printStackTrace();
		}
	}
	
	/**
	 * first function of this service.
	 * get all element that have information in service.
	 * @return list of all element.
	 */
	public List<Element> getAllElement() {
		try {
			String allElement = service.getAtoms();
			NewDataSet element = (NewDataSet) unm.unmarshal(new ByteArrayInputStream(allElement.getBytes()));
			return element.getTableList();
		} catch (JAXBException | WebServiceException e) {
			e.printStackTrace();
			System.out.println("There is error occured with internet or unmarshall");
		}
		return null;
	}
	
	/**
	 * second function of this service.
	 * get element information from specific element name.
	 * @param elementName to search.
	 * @return element object which have element information.
	 */
	public Element getElementInfo(String elementName) {
		try {
			String information = service.getAtomicNumber(elementName);
			NewDataSet element = (NewDataSet) unm.unmarshal(new ByteArrayInputStream(information.getBytes()));
			if(element.getTableList().size()>0)
				return element.getTableList().get(0);
		} catch (JAXBException | WebServiceException e) {
			e.printStackTrace();
			System.out.println("There is error occured with internet or unmarshall");
		}
		return null;
	}
}
