package eFoods_middleware;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Middleware {
	
	final String TAG_ITEMS = "items";
	final String ATTR_NUMBER = "number";
	final String TAG_QTY = "quantity";
	String poFolderName = "/appData/PO";
	String poProcessedFolderName = "/appData/PO_processed";
	String ordersFolder = "/orders";
	String poPath;
	String poProcessedPath;
	ConsolidateOrder order;
	Middleware(String rootPath)
	{
		poPath = rootPath + poFolderName;
		poProcessedPath = rootPath + poProcessedFolderName;
		order = ConsolidateOrder.getInstance();
	}
	void processing_ea(String fileName)throws Exception
	{
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = dBuilder.parse(new File(fileName));
		NodeList items = doc.getElementsByTagName(TAG_ITEMS);
		
		for(int i = 0; i < items.getLength(); i++)
		{	
			Element itemElement = (Element) items.item(i);
			order.addItem(itemElement);			
		}
	}
	
	void moveProcessed(String fileName)throws Exception
	{
		File ori = new File(poPath + "/" + fileName);
		File dest = new File(poProcessedPath + "/" + fileName);
		InputStream inStream = new FileInputStream(ori);
		OutputStream outStream = new FileOutputStream(dest);
		byte[] buffer = new byte[1024];
		int length;
		while((length = inStream.read(buffer))>0)
		{
			outStream.write(buffer, 0, length);
		}
		inStream.close();
		outStream.close();
		
		ori.delete();
	}
	
	void processing () throws Exception
	{
		String[] filesName = (new File(poPath)).list();
		for(String file: filesName)
		{
			processing_ea(poPath + "/" + file);
			moveProcessed(file);
		}
		order.writeOrder(System.getProperty("user.dir")+ordersFolder);
	}
	
	public static void main(String[] args) throws Exception
	{
		Middleware process = new Middleware(args[0]);	
		process.processing();
		
	}

}
