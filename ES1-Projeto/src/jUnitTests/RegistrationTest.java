package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BDA.XMLFile;

class RegistrationTest {

   /**
   * Add a test user to the config.xml, checks if the user was registered.
   */
	
	@Test
	public void test() {
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
		assertEquals(true, userRegistered);
	}

}
