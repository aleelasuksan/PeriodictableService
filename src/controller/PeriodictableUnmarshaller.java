package controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.webservicex.Periodictable;
import net.webservicex.PeriodictableSoap;
import net.webservicex.entity.Element;
import net.webservicex.entity.NewDataSet;

public class PeriodictableUnmarshaller {

	private PeriodictableSoap service;
	
	private Unmarshaller unm;
	
	private String s;
	
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
	
	public List<Element> getAllElement() {
		String allElement = service.getAtoms();
		try {
			NewDataSet element = (NewDataSet) unm.unmarshal(new ByteArrayInputStream(allElement.getBytes()));
			return element.getTableList();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Element getElementInfo(String elementName) {
		String information = service.getAtomicNumber(elementName);
		try {
			NewDataSet element = (NewDataSet) unm.unmarshal(new ByteArrayInputStream(information.getBytes()));
			if(element.getTableList().size()>0)
				return element.getTableList().get(0);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
