import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * Client main class for console usage.
 * @author Atit Leelasuksan 5510546221
 *
 */
// http://www.webservicex.net/periodictable.asmx
public class Main {

	public static void main(String[] args) {
		Periodictable factory = new Periodictable();
		PeriodictableSoap proxy = factory.getPeriodictableSoap();
		String data = proxy.getAtomicNumber("actinium");
		try {
			JAXBContext context = JAXBContext.newInstance( NewDataSet.class, Element.class);
			Unmarshaller unm = context.createUnmarshaller();
			NewDataSet element = (NewDataSet) unm.unmarshal(new ByteArrayInputStream(data.getBytes()));
			List<Element> table = element.getTableList();
			Element ele = table.get(0);
			System.out.println(ele.getAtomicNumber());
			System.out.println(ele.getAtomicRadius());
			System.out.println(ele.getAtomicWeight());
			System.out.println(ele.getBoilingPoint());
			System.out.println(ele.getMeltingPoint());
			System.out.println(ele.getElementName());
			System.out.println(ele.getDensity());
			System.out.println(ele.getEletroNegativity());
			System.out.println(ele.getIonisationPotential());
			System.out.println(ele.getSymbol());
		} catch (JAXBException | WebServiceException je) {
			//je.printStackTrace();
		}
	}

}
