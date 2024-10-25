package hh.ma.ac.inpt.domaine;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsWrapper {
	
	@XmlElement(name = "product")
	private List<Product> products;
	
	public ProductsWrapper() {
		super();
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
