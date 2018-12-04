package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BDA.Notification;
import BDA.SetPasswordPage;
import BDA.XMLFile;

class Notifications {

	@Test
	void test() {
			String Plataform = "Twitter";
			String Sender = "ISTAR-TESTE";
			String Date = "2018-12-04";
			String Content = "Teste JUnit das notificações";
			String Subject =  "";
			// String subject
			Notification n = new Notification(Plataform,Sender,Subject,Date,Content);
			
			boolean visible = Notification.getFrame().isVisible();
			int w = Notification.getFrame().getWidth();
			int h = Notification.getFrame().getHeight();
			assertEquals(858, w);
			assertEquals(560, h);
			assertEquals(true, visible);
			

		
	}

}
