package eFoods_middleware;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item
{
	String name;
	String number;
	double price;
	int quantity;
	public Item(String name, String number, double price, int quantity) {
		super();
		this.name = name;
		this.number = number;
		this.price = price;
		this.quantity = quantity;
	}
	public Item()
	{
		
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	@XmlAttribute
	public void setNumber(String number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	@XmlElement
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	@XmlElement
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ItemBean [name=" + name + ", number=" + number + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	
	
}