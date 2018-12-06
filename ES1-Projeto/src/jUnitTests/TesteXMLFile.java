package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import BDA.*;

class TesteXMLFile {
	private XMLFile xml=new XMLFile();
	
	@Test
	public void testXMLFileFunctions() {
		/**
		 * Add a test user to the config.xml, checks if the user was registered.
		*/ 
		 
		String email = new String("teste@iscte-iul.pt");
		String pass = new String("teste");
		String nome = new String("teste");
		String username = new String("teste");
		String ACKT=new String("vazio");
		String ACST=new String("vazio");
		String AATT=new String("vazio");
		String ACTST=new String("vazio"); 
		String TAF=new String("vazio");
		String l = new String("vazio");
		xml.addUsers(email, pass, nome, username, ACKT, ACST, AATT, ACTST, TAF, l);
		boolean userRegistered = xml.checkIfUserExists(email, pass);
		boolean notReal = xml.checkIfUserExists("notReal", "NotReal");
		assertEquals(true, userRegistered);
		assertEquals(false, notReal);
		
		/**
		 * Checks if a test email is already registered in the XML file.
	   */
		
		String emailNotReal = new String("blahhh");
		assertEquals(true, xml.checkIfUserExistsByEmail(email));
		assertEquals(false, xml.checkIfUserExistsByEmail(emailNotReal));
		
		/**
		 * Test the data changes of a user in the XML file and the getAttributeByEmail function.
  		*/
		
		String attribute = new String("Nome");
		String newValue = new String("novoteste");
		xml.changeAttributte(email, attribute, newValue);
		assertEquals(newValue, xml.getAttributteByEmail(email, attribute));
		
		/**
		 * Test to see if returns a list of services associated with a test user.
		*/

		ArrayList<String> listServices = xml.list_account(email);
		ArrayList<String> expected = new ArrayList<String>();
		boolean result = listServices.isEmpty();
		assertEquals(true, result);
		
	} 
}
