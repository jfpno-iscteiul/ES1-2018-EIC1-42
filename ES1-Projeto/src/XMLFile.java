import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;



public class XMLFile {
	
   public static void addUsers(String email, String password, String nome, String username) {
	   try {	
	         File inputFile = new File("config.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();         
	         System.out.println("\n----- Search the XML document with xpath queries -----");  
	         // Query 1 
	         System.out.println("Query 1: ");
	         XPathFactory xpathFactory = XPathFactory.newInstance();
	         XPath xpath = xpathFactory.newXPath();
	         XPathExpression expr = xpath.compile("/XML/Service/@*");
	         NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
	         for (int i = 0; i < nl.getLength(); i++) {
	             System.out.print(nl.item(i).getNodeName()  + ": ");
	             System.out.println(nl.item(i).getFirstChild().getNodeValue());
	         }
	         // Query 2
	         System.out.println("\nQuery 2: ");         
	         expr = xpath.compile("/XML/Paths/docPath");
	         String str = (String) expr.evaluate(doc, XPathConstants.STRING);
	         
	         System.out.println("docPath: " + str);
	         
	         // Adding new element Service with attributes to the XML document (root node)
	         System.out.println("\n----- Adding new element <Service> with attributes to the XML document -----");

	         Element newElement1 = doc.createElement("Utilizador");
	         newElement1.setAttribute("Nome", nome);
	         newElement1.setAttribute("Username", username);
	         newElement1.setAttribute("Email", email);
	         newElement1.setAttribute("Password", password);
	         
	         // Add new nodes to XML document (root element)
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());         
	         Node n = doc.getDocumentElement();
	         n.appendChild(newElement1);         
	       
	         // Save XML document
	         System.out.println("\nSave XML document.");
	         Transformer transformer = TransformerFactory.newInstance().newTransformer();
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	         StreamResult result = new StreamResult(new FileOutputStream("config.xml"));
	         DOMSource source = new DOMSource(doc);
	         transformer.transform(source, result);
	      } catch (Exception e) { e.printStackTrace(); }
	   }
   
   
   public static boolean checkIfUserExists(String email, String pass) {
	   try {
		   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	       DocumentBuilder db = dbf.newDocumentBuilder();
	       Document document = db.parse(new File("config.xml"));
	       NodeList nodeList = document.getElementsByTagName("Utilizador");
	       for(int x=0,size= nodeList.getLength(); x<size; x++) {
	    	  String mail = nodeList.item(x).getAttributes().getNamedItem("Email").getNodeValue();
	    	  if (mail.equals(email)) {
	    		  if(nodeList.item(x).getAttributes().getNamedItem("Password").getNodeValue().equals(pass)) {
	    			  return true;
	    		  }
	    	  }
	       }
	   } catch (Exception e) {}
	return false;
   }
   
}
   
   