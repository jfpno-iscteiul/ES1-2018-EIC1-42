package BDA;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
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
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class XMLFile {
	
	/**
	 * Creates an XML file where new users can be saved.
	 */
	
   public static void addUsers(String email, String password, String nome, String username, String ACKT,String ACST,String AATT,String ACTST, String TAF) {
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
	         newElement1.setAttribute("AuthConsumerKeyTwitter", ACKT);
	         newElement1.setAttribute("AuthConsumerSecretTwitter", ACST);
	         newElement1.setAttribute("AuthAccessTokenTwitter", AATT);
	         newElement1.setAttribute("AuthAccessTokenSecretTwitter", ACTST);
	         newElement1.setAttribute("TokenAccessFacebook", TAF);
	         
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
   
   
   /**
	* Checks if a given email with a given password is already registered in the XML file.
	*/
   
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
   
   
   /**
	* Checks if a given email is already registered in the XML file.
	*/
   
   public static boolean checkIfUserExistsByEmail(String email) {
	   try {
		   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	       DocumentBuilder db = dbf.newDocumentBuilder();
	       Document document = db.parse(new File("config.xml"));
	       NodeList nodeList = document.getElementsByTagName("Utilizador");
	       for(int x=0,size= nodeList.getLength(); x<size; x++) {
	    	  String mail = nodeList.item(x).getAttributes().getNamedItem("Email").getNodeValue();
	    	  if (mail.equals(email)) {
	    			  return true;
	    		  }
	    	  }
	   } catch (Exception e) {}
	return false;
   }
   
   /**
  	* Changes the data of a user in the XML file.
  	*/
   public static void changeAttributte(String email, String Attributte, String newValue) {
	   String filePath = "config.xml";
       File xmlFile = new File(filePath);
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder;
       try {
           dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(xmlFile);
           doc.getDocumentElement().normalize();
           
           //update attribute value
           updateAttributeValue(doc,email, Attributte, newValue );
           
           //write the updated document to file or console
           doc.getDocumentElement().normalize();
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           DOMSource source = new DOMSource(doc);
           StreamResult result = new StreamResult(new File("config.xml"));
           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
           transformer.transform(source, result);

           
       } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
           e1.printStackTrace();
       }
   }
	   
   
   
   /**
 	* Changes the data of a user in the XML file.
 	*/
   
   private static void updateAttributeValue(Document doc, String email, String Attributte, String newValue) {
       NodeList users = doc.getElementsByTagName("Utilizador");
       Element user;
       //loop for each Utilizador
       for(int x=0,size= users.getLength(); x<size; x++) {
    	   String mail = users.item(x).getAttributes().getNamedItem("Email").getNodeValue();
           user = (Element) users.item(x);
           if(mail.equalsIgnoreCase(email)){
               user.setAttribute(Attributte, newValue);
           }
       }
   }
   
   /**
 	* Returns an attribute of a particular user.
 	*/
   
   public static String getAttributteByEmail(String email, String attribute) {
	   String value= null;
	   try {
		   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	       DocumentBuilder db = dbf.newDocumentBuilder();
	       Document document = db.parse(new File("config.xml"));
	       NodeList nodeList = document.getElementsByTagName("Utilizador");
	       for(int x=0,size= nodeList.getLength(); x<size; x++) {
	    	  String mail = nodeList.item(x).getAttributes().getNamedItem("Email").getNodeValue();
	    	  if (mail.equals(email)) {
	    		  value = nodeList.item(x).getAttributes().getNamedItem(attribute).getNodeValue();
	    	  }
	       }
	   } catch (Exception e) {} 
	       return value;
	   
	   
   }
   
   
   /**
	* Returns a list of services associated with a particular user.
	*/
   public static ArrayList<String> list_account(String email){
	   ArrayList<String> res= new ArrayList<String>();
	   try {
		   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	       DocumentBuilder db = dbf.newDocumentBuilder();
	       Document document = db.parse(new File("config.xml"));
	       NodeList nodeList = document.getElementsByTagName("Utilizador");
	       for(int x=0,size= nodeList.getLength(); x<size; x++) {
	    	  String mail = nodeList.item(x).getAttributes().getNamedItem("Email").getNodeValue();
	    	  if (mail.equals(email)) {
	    		  
	    			if(!nodeList.item(x).getAttributes().getNamedItem("AuthConsumerKeyTwitter").getNodeValue().equals("vazio"))
	    				res.add("Twitter");
	    			if(!nodeList.item(x).getAttributes().getNamedItem("TokenAccessFacebook").getNodeValue().equals("vazio"))
	    				res.add("Facebook");
	    		  }
	    	  }
	   } catch (Exception e) {}
	return res;
   }
   }
   
   