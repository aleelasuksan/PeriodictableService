package net.webservicex.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "NewDataSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewDataSet {

	@XmlElement(name = "Table")
	private List<Element> table = null;
	
	public NewDataSet() { }
	
	public List<Element> getTableList() {
		return table;
	}
	
	public boolean isTableExist() {
		return table != null;
	}
	
}
