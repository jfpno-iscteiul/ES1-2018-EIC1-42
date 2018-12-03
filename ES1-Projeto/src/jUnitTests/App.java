package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BDA.SetPasswordPage;
import BDA.XMLFile;

class App {

	@Test
	void test() {
		String email="123";
		String password="123";
		String password2="123";
		String password3="123";
		SetPasswordPage sp = new SetPasswordPage();
		boolean visible = sp.getFrame().isVisible();
		int w = sp.getFrame().getWidth();
		int h = sp.getFrame().getHeight();
		assertEquals(450, w);
		assertEquals(350, h);
		assertEquals(true, visible);
		
		sp.setPasswords(email, password, password2, password3);
		assertEquals("123", XMLFile.getAttributteByEmail(email, "password"));
	}

}
