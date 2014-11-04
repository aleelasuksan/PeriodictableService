package net.webservicex.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Element entity with constant of unit.
 * have name, symbol, atomic number, atomic weight, atomic radius, density, boiling point,
 * melting point, ionisation potential, electronegativity.
 * @author Atit Leelasuksan 5510546221
 *
 */
@XmlAccessorType (XmlAccessType.FIELD)
public class Element {

	/**
	 * list of unit constant.
	 */
	public static String WEIGHT_UNIT = "Atomic Mass Unit";
	public static String TEMPERATURE_UNIT = "ÅãK";
	public static String IONISATION_POTENTIAL_UNIT = "kJ/mol";
	public static String ELECTRO_NEGATIVITY_UNIT = "Pauling Scale";
	public static String ATOMIC_RADIUS_UNIT = "Picometer";
	public static String DENSITY_UNIT = "g/cm_cube";
	
	@XmlElement(name = "AtomicNumber")
	private int atomicNumber;
	
	@XmlElement(name = "ElementName")
	private String elementName;
	
	@XmlElement(name = "Symbol")
	private String symbol;
	
	@XmlElement(name = "AtomicWeight")
	private double atomicWeight;
	
	@XmlElement(name = "AtomicRadius")
	private double atomicRadius;
	
	@XmlElement(name = "Density")
	private double density;
	
	@XmlElement(name = "BoilingPoint")
	private double boilingPoint;
	
	@XmlElement(name = "MeltingPoint")
	private double meltingPoint;
	
	@XmlElement(name = "IonisationPotential")
	private double ionisationPotential;
	
	@XmlElement(name = "EletroNegativity")
	private double eletroNegativity;
	
	public Element() {}
	
	public int getAtomicNumber() {
		return atomicNumber;
	}
	
	public String getElementName() {
		return elementName;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public double getAtomicWeight() {
		return atomicWeight;
	}
	
	public double getAtomicRadius() {
		return atomicRadius;
	}
	
	public double getDensity() {
		return density;
	}
	
	public double getBoilingPoint() {
		return boilingPoint;
	}
	
	public double getMeltingPoint() {
		return meltingPoint;
	}
	
	public double getIonisationPotential() {
		return ionisationPotential;
	}
	
	public double getEletroNegativity() {
		return eletroNegativity;
	}
}
