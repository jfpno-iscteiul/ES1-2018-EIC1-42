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

	/** * @param    email is the email relative to the user.
	 * @param    password is the password relative to the user.
	 * @param    nome is the name relative to the user.
	 *  @param    username is the username relative to the user.
	 * @param    ACKT is the AuthConsumerKeyTwitter relative to the user.
	 * @param    ACST is the AuthConsumerSecretTwitter relative to the user.
	 * @param    AATT is the AuthAccessTokenTwitter relative to the user.
	 * @param    ACTST is the AuthAccessTokenSecretTwitter relative to the user.
	 * @param    TAF is the TokenAccessFacebook relative to the user.
	 * @param    passmail is the password of the email relative to the user.
	 */


	public  void addUsers(String email, String password, String nome, String username, String ACKT,String ACST,String AATT,String ACTST, String TAF, String passmail) {
		try {	
			File inputFile = new File("resources/config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();         
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			XPathExpression expr = xpath.compile("/XML/Service/@*");
			@SuppressWarnings("unused")
			NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);         
			expr = xpath.compile("/XML/Paths/docPath");
			@SuppressWarnings("unused")
			String str = (String) expr.evaluate(doc, XPathConstants.STRING);

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
			newElement1.setAttribute("PassEmail", passmail);

			// Add new nodes to XML document (root element)      
			Node n = doc.getDocumentElement();
			n.appendChild(newElement1);         

			// Save XML document
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult(new FileOutputStream("resources/config.xml"));
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
		} catch (Exception e) { e.printStackTrace(); }
	}


	/**
	 * Checks if a given email with a given password is already registered in the XML file.
	 */

	/** * @param    email is the email relative to the user.
	 * @param    pass is the password relative to the user.
	 * @return      true if a given email with a given password is already registered in the XML file.
	 */

	public boolean checkIfUserExists(String email, String pass) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("resources/config.xml"));
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
	 * Checks if the user have facebook.
	 */

	/** * @param    email is the email relative to the user.
	 * @return      true if a user have a valid TokenAccessFacebook in the XML file.
	 */

	public boolean haveFacebook(String email) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("resources/config.xml"));
			NodeList nodeList = document.getElementsByTagName("Utilizador");
			for(int x=0,size= nodeList.getLength(); x<size; x++) {
				String mail = nodeList.item(x).getAttributes().getNamedItem("Email").getNodeValue();
				if (mail.equals(email)) {

					if(!nodeList.item(x).getAttributes().getNamedItem("TokenAccessFacebook").getNodeValue().equals("vazio")) {
						return true;
					}else
						return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}


	public boolean haveEmail(String email) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("resources/config.xml"));
			NodeList nodeList = document.getElementsByTagName("Utilizador");
			for(int x=0,size= nodeList.getLength(); x<size; x++) {
				String mail = nodeList.item(x).getAttributes().getNamedItem("Email").getNodeValue();
				if (mail.equals(email)) {
					if(!nodeList.item(x).getAttributes().getNamedItem("PassEmail").getNodeValue().equals("vazio")) {
						return true;
					} else
						return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * Checks if the user have twitter.
	 */

	/** * @param    email is the email relative to the user.
	 * @return      true if a user have a valid TokenAccessFacebook in the XML file.
	 */

	public boolean haveTwitter(String email) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("resources/config.xml"));
			NodeList nodeList = document.getElementsByTagName("Utilizador");
			for(int x=0,size= nodeList.getLength(); x<size; x++) {
				String mail = nodeList.item(x).getAttributes().getNamedItem("Email").getNodeValue();
				if (mail.equals(email)) {

					if(!(nodeList.item(x).getAttributes().getNamedItem("AuthAccessTokenSecretTwitter").getNodeValue().equals("vazio") &&
							nodeList.item(x).getAttributes().getNamedItem("AuthConsumerKeyTwitter").getNodeValue().equals("vazio") &&
							nodeList.item(x).getAttributes().getNamedItem("AuthConsumerSecretTwitter").getNodeValue().equals("vazio")&& 
							nodeList.item(x).getAttributes().getNamedItem("AuthAccessTokenTwitter").getNodeValue().equals("vazio"))){
						return true;
					}else
						return false;
				}
			}
		} catch (Exception e) {return false;}
		return false;

	}

	/**
	 * Checks if a given email is already registered in the XML file.
	 */


	/**  @param    email is the email relative to the user.
	 * @return      true if a user exists with that email
	 */

	public boolean checkIfUserExistsByEmail(String email) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("resources/config.xml"));
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

	/**  @param    email is the email relative to the user.
	 * @param    Attributte is the name of the attribute to modify.
	 * @param    newValue is the new value to assign.
	 */

	public void changeAttributte(String email, String Attributte, String newValue) {
		String filePath = "resources/config.xml";
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
			StreamResult result = new StreamResult(new File("resources/config.xml"));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);


		} catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
			e1.printStackTrace();
		}
	}



	/**
	 * Changes the data of a user in the XML file.
	 */

	/** * @param    email is the email relative to the user.
	 * @param    Attributte is the name of the attribute to modify.
	 * @param    newValue is the new value to assign.
	 * @param    doc is the DocumentBuilderFactory.
	 */


	private void updateAttributeValue(Document doc, String email, String Attributte, String newValue) {
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

	/** * @param    email is the email relative to the user.
	 * @param    attribute is the name of the attribute to modify.
	 * @return      the attributte asked in the method
	 */

	public String getAttributteByEmail(String email, String attribute) {
		String value= null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("resources/config.xml"));
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

	/** * @param    email is the email relative to the user.
	 * @return     a list of services associated with a particular user.
	 */
	public ArrayList<String> list_account(String email){
		ArrayList<String> res= new ArrayList<String>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new File("resources/config.xml"));
			NodeList nodeList = document.getElementsByTagName("Utilizador");
			for(int x=0,size= nodeList.getLength(); x<size; x++) {
				String mail = nodeList.item(x).getAttributes().getNamedItem("Email").getNodeValue();
				if (mail.equals(email)) {

					if(!nodeList.item(x).getAttributes().getNamedItem("AuthConsumerKeyTwitter").getNodeValue().equals("vazio"))
						res.add("Twitter");
					if(!nodeList.item(x).getAttributes().getNamedItem("TokenAccessFacebook").getNodeValue().equals("vazio"))
						res.add("Facebook");
					if(!nodeList.item(x).getAttributes().getNamedItem("PassEmail").getNodeValue().equals("vazio"))
						res.add("Email");
				}
			}
		} catch (Exception e) {}
		return res;
	}
}