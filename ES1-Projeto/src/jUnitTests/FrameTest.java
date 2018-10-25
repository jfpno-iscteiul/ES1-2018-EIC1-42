package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BDA.MainWindow;

class FrameTest {

	   /**
	   * Test if the frame is visible, and opens with the correct width and height.
	   */
	
	@Test
	public void test() {
		MainWindow window = new MainWindow();
		boolean visible = window.getFrame().isVisible();
		int w = window.getFrame().getWidth();
		int h = window.getFrame().getHeight();
		assertEquals(779, w);
		assertEquals(410, h);
		assertEquals(true, visible);
	}
}
