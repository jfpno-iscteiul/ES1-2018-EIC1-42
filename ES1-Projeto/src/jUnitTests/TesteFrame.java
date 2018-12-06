package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BDA.*;

class TesteFrame {

	@Test
	void test() {
		
		 /**
		   * Test if the frame is visible, and opens with the correct width and height.
		  */
		
		String teste =  "123";
		
		MainWindow window = new MainWindow();
		boolean visible = window.getFrame().isVisible();
		int w = window.getFrame().getWidth();
		int h = window.getFrame().getHeight();
		assertEquals(863, w);
		assertEquals(594, h);
		assertEquals(true, visible);
		
		SignUp sign = new SignUp(window.getFrame());
		boolean sv = sign.getFrame().isVisible();
		w = sign.getFrame().getWidth();
		h = sign.getFrame().getHeight();
		assertEquals(true, sv);
		assertEquals(779, w);
		assertEquals(410, h);
		
		Timeline timeline = new Timeline(window.getFrame(), teste);
		visible = timeline.getFrame().isVisible();
		w = timeline.getFrame().getWidth();
		h = timeline.getFrame().getHeight();
		assertEquals(true, visible);
		assertEquals(863, w);
		assertEquals(594, h);
		
		Configurations conf = new Configurations(window.getFrame(), "teste@iscte-iul.pt");
		visible = conf.getFrame().isVisible();
		w = conf.getFrame().getWidth();
		h = conf.getFrame().getHeight();
		assertEquals(true, visible);
		assertEquals(863, w);
		assertEquals(594, h);
		
		TwitterPage tp = new TwitterPage(window.getFrame(), teste);
		visible = tp.getFrame().isVisible();
		w = tp.getFrame().getWidth();
		h = tp.getFrame().getHeight();
		assertEquals(true, visible);
		assertEquals(863, w);
		assertEquals(594, h);
		
		ConfigurationsRem confrem = new ConfigurationsRem(window.getFrame(), teste);
		visible = confrem.getFrame().isVisible();
		w = confrem.getFrame().getWidth();
		h = confrem.getFrame().getHeight();
		assertEquals(true, visible);
		assertEquals(863, w);
		assertEquals(594, h);
		
		SendEmailPage send = new SendEmailPage(teste);
		visible = send.getFrame().isVisible();
		w = send.getFrame().getWidth();
		h = send.getFrame().getHeight();
		assertEquals(true, visible);
		assertEquals(594, w);
		assertEquals(400, h);
		
		EmailPage ep = new EmailPage(window.getFrame(), "cacaa@iscte-iul.pt");
		visible = ep.getFrame().isVisible();
		w = ep.getFrame().getWidth();
		h = ep.getFrame().getHeight();
		assertEquals(true, visible);
		assertEquals(863, w);
		assertEquals(594, h);
		
		ShowSentEmail se = new ShowSentEmail("cacaa@iscte-iul.pt");
		visible = se.getFrame().isVisible();
		w = se.getFrame().getWidth();
		h = se.getFrame().getHeight();
		assertEquals(true, visible);
		assertEquals(600, w);
		assertEquals(450, h);
		
		FacebookPage fp = new FacebookPage(window.getFrame(), teste);
		visible = fp.getFrame().isVisible();
		w = fp.getFrame().getWidth();
		h = fp.getFrame().getHeight();
		assertEquals(true, visible);
		assertEquals(863, w);
		assertEquals(594, h);
	}

}
