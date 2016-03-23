import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {
	private String xmlFile = "TestLog.xml";

	public List<String> read() {
		List<String> list = new ArrayList<String>();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		org.w3c.dom.Document document = null;
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(new File(xmlFile));
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList nodeList = document.getElementsByTagName("record").item(0).getChildNodes();
		int length = nodeList.getLength();
		for(int i = 1; i < length; i = i+2) {
			String name = nodeList.item(i).getNodeName();
			String value = nodeList.item(i).getTextContent();
			list.add(name + "=" + value);
		}
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new XMLReader().read();
		System.out.println(list);
	}

}
