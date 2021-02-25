package eFoods_middleware;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Order
{
//	CustomerBean customer
	List<Item> items;
	String id;
	Date submitted;
	
	
	public Order(List<Item> items, String id, Date submitted)
	{
		super();
		this.items = items;
		this.id = id;
		this.submitted = submitted;
	}
	
	public Order()
	{
		super();
		this.items = null;
	}
	public List<Item> getItems()
	{
		return items;
	}
	@XmlElement
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	public String getId()
	{
		return id;
	}
	@XmlAttribute
	public void setId(String id)
	{
		this.id = id;
	}
	
	public Date getSubmitted()
	{
		return submitted;
	}
	@XmlElement
	public void setSubmitted(Date submitted)
	{
		this.submitted = submitted;
	}
	
}