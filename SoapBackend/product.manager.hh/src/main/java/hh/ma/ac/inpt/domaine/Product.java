package hh.ma.ac.inpt.domaine;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

	@XmlTransient
	private long id;
	private String label;
	private double price;
	
	
	public Product() {
		super();
		
	}

	public Product(long id, String label, double price) {
		super();
		this.id = id;
		this.label = label;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", label=" + label + ", price=" + price + "]";
	}
	
	
	
	
	
	
	

}
