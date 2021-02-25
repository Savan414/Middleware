package eFoods_middleware;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.w3c.dom.Element;

class ConsolidateOrder 
{
	final String TAG_ITEMS = "items";
	final String TAG_NAME = "name";
	final String TAG_NUMBER = "number";
	final String TAG_QTY = "quantity";
	final String TAG_PRICE = "price";

	private static ConsolidateOrder instance = null;
	Map<String, Item> to_orders;
	private ConsolidateOrder()
	{
		to_orders = new HashMap<String,  Item>();
	}
	
	public static ConsolidateOrder getInstance()
	{
		if(instance == null)
			instance = new ConsolidateOrder();
		return instance;
	}

	synchronized void addItem(Element itemElement)
	{
		String number = itemElement.getElementsByTagName(TAG_NUMBER ).item(0).getTextContent();
		int quantity = Integer.parseInt(itemElement.getElementsByTagName(TAG_QTY).item(0).getTextContent());
		if (!to_orders.containsKey(number))
		{
			String name = itemElement.getElementsByTagName(TAG_NAME).item(0).getTextContent();
			double price = Double.parseDouble(itemElement.getElementsByTagName(TAG_PRICE).item(0).getTextContent());
			to_orders.put(number, new Item(name, number, price, 0));
		}
		Item item = to_orders.get(number);
		item.setQuantity(item.getQuantity() + quantity);
		to_orders.put(number, item);
	}
	
	void writeOrder(String dirPath) throws Exception
	{
		String idOrder = Long.toString(System.currentTimeMillis());
		Date date = new Date();
		String filePath = dirPath + "/order_" + idOrder + ".xml";
		System.out.println("filePath="+filePath);
		File file = new File(filePath);
		List<Item> items = new ArrayList<Item>(to_orders.values());
		Order order = new Order(items, idOrder, date);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(order, file);
//		jaxbMarshaller.marshal(order, System.out);
		
	}
}
