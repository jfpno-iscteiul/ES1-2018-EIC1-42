package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import BDA.*;

class TesteXMLFile {
	
	@Test
	public void testXMLFileFunctions() {
		/**
		 * Add a test user to the config.xml, checks if the user was registered.
		*/ 
		 
		String email = new String("teste@iscte-iul.pt");
		String pass = new String("teste");
		String nome = new String("teste");
		String username = new String("teste");
		String ACKT=new String("teste");
		String ACST=new String("teste");
		String AATT=new String("teste");
		String ACTST=new String("teste"); 
		String TAF=new String("teste");
		XMLFile.addUsers(email, pass, nome, username, ACKT, ACST, AATT, ACTST, TAF);
		boolean userRegistered = XMLFile.checkIfUserExists(email, pass);
		boolean notReal = XMLFile.checkIfUserExists("notReal", "NotReal");
		assertEquals(true, userRegistered);
		assertEquals(false, notReal);
		
		/**
		 * Checks if a test email is already registered in the XML file.
	   */
		
		String emailNotReal = new String("blahhh");
		assertEquals(true, XMLFile.checkIfUserExistsByEmail(email));
		assertEquals(false, XMLFile.checkIfUserExistsByEmail(emailNotReal));
		
		/**
		 * Test the data changes of a user in the XML file and the getAttributeByEmail function.
  		*/
		
		String attribute = new String("Nome");
		String newValue = new String("novoteste");
		XMLFile.changeAttributte(email, attribute, newValue);
		assertEquals(newValue, XMLFile.getAttributteByEmail(email, attribute));
		
		/**
		 * Test to see if returns a list of services associated with a test user.
		*/

		ArrayList<String> listServices = XMLFile.list_account(email);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("Twitter");
		expected.add("Facebook");
		boolean result = listServices.equals(expected);
		assertEquals(true, result);
		
	} 

	

}
