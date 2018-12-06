package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import BDA.Gestor;

class TesteGestor {

	@Test
	void test() throws IOException {
		Gestor gestor = new Gestor();
		String email = "123";
		
		boolean online = gestor.isHostAvailable("facebook.com");
		assertEquals(true, online);
		boolean on = gestor.isOnline();
		assertEquals(true, on);
		
		gestor.writeTweetsFile(email);
		File tmpDir = new File("Tweets/" + email +".txt");
		boolean exists = tmpDir.exists();
		assertEquals(true, exists);
		
		gestor.writeFacebookPostsFile(email);
		File f = new File("FBPosts/" + email +".txt");
		boolean ef = f.exists();
		assertEquals(true, ef);
		
		
	}

}
