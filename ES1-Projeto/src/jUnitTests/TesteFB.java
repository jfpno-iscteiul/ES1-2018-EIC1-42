package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import BDA.Facebook;
import BDA.Gestor;
import BDA.Twitter;

class TesteFB {

	@Test
	void test() {
		
		String email = new String("123");
		String emailTeste = new String("teste@iscte-iul.pt");
		
		/**
		  * TEST TO : Write a file with the contents of each user related to facebook when it is offline and gets posts.
		*/
		
		Gestor.writeFacebookPostsFile(email);
	
		ArrayList<String> fb = Gestor.getFBPosts(email);
		ArrayList<String> facebook = Facebook.getFBNotifications(email);
		ArrayList<String> fbexp = new ArrayList<String>();  
		fbexp.add("Facebook;;Wed Oct 24 16:38:44 BST 2018;;108383060155492_108424016818063;;Starting a new App, so happy!!");
		assertEquals(fbexp, fb);
		assertEquals(fbexp, facebook);
		
		/**
		  * TEST TO : user is online
		*/
	//	boolean isOnline = Gestor.isOnline(email);
	//	assertEquals(true, isOnline);
	//	boolean not = Gestor.isOnline(emailTeste);
	//	assertEquals(false, not);
		
	}

}
