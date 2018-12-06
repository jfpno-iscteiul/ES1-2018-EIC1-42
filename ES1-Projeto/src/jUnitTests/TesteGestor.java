package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import BDA.ComparadorDatas;
import BDA.Data;
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
		
		gestor.writeEmailsFile("cacaa@iscte-iul.pt");
		File fe = new File("Emails/cacaa@iscte-iul.pt.txt");
		boolean a = fe.exists();
		assertEquals(true, a);
		
		
		assertEquals("01", gestor.auxDate("Jan"));
		assertEquals("02", gestor.auxDate("Feb"));
		assertEquals("03", gestor.auxDate("Mar"));
		assertEquals("04", gestor.auxDate("Apr"));
		assertEquals("05", gestor.auxDate("May"));		
		assertEquals("06", gestor.auxDate("Jun"));
		assertEquals("07", gestor.auxDate("Jul"));	
		assertEquals("08", gestor.auxDate("Aug"));
		assertEquals("09", gestor.auxDate("Sep"));
		assertEquals("10", gestor.auxDate("Oct"));
		assertEquals("11", gestor.auxDate("Nov"));
		assertEquals("12", gestor.auxDate("Dec"));
		
		Data d = new Data(1, 1, 2018, 1, 1, 1);
		assertEquals(1, d.getDia());
		assertEquals(1, d.getHora());
		assertEquals(1, d.getMin());
		assertEquals(1, d.getSeg());
		assertEquals(1, d.getMes());
		assertEquals(2018, d.getAno());
		
		String s = "Data [dia=" + 1 + ", mes=" + 1 + ", ano=" + 2018 + ", hora=" + 1 + ", min=" + 1 + ", seg=" + 1 + "]";
		assertEquals(s, d.toString());
		
		Data dat = new Data(1, 1, 2018, 1, 1, 1);
		Data less = new Data(1,1,2010, 1, 1, 1);
		Data m = new Data(1, 1, 2018, 1, 1, 2);
		Data l = new Data(1, 1, 2018, 1, 2, 1);
		assertEquals(-1, d.compareTo(l));
		assertEquals(-1, d.compareTo(m));
		assertEquals(1 , d.compareTo(less));
		assertEquals(0, d.compareTo(dat));
		
		ComparadorDatas cd = new ComparadorDatas();
		assertEquals(0, cd.compare(d, dat));
	}

}
