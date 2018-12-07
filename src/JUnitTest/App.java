package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import BDA.Gestor;
import BDA.SetPasswordPage;
import BDA.XMLFile;

class App {
	private XMLFile xml= new XMLFile();
	private Gestor gestor = new Gestor();

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
		assertEquals("123", xml.getAttributteByEmail(email, "Password"));
		
		
		String test2="Apenas para testar";
		String test3="Ola";
		String test4="Vamos verificar se funciona";
		ArrayList<String> list = new ArrayList<String>();
		list.add(test2);
		list.add(test3);
		list.add(test4);
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add(test2);
		assertEquals(list2, gestor.filterByWord(list, "testar"));
		
	}

}
