package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.restfb.util.DateUtils;

import BDA.Gestor;
import BDA.Twitter;

class TesteTwitter {

	@Test
	void test() {
		String email = new String("123");
		
		/**
		  * TEST TO : Write a file with the contents of each user related to twitter when it is offline and gets posts.
		*/
		
		Gestor.writeTweetsFile(email);
		ArrayList<String> tw = Gestor.getTweets(email);
		ArrayList<String> twexp = new ArrayList<String>(); 
	//	twexp.add("Twitter;;Fri Nov 23 14:34:38 GMT 2018;;ISTAR-IUL;;Computa��o e Sociedade | Funda��o Calouste Gulbenkian https://t.co/2JMlXjITON");   
	//	twexp.add("Twitter;;Fri Nov 23 11:14:43 GMT 2018;;CEI-IUL;;A investigadora do CEI-IUL C�tia Miriam Costa comenta, num artigo de opini�o para O Jornal Econ�mico, pr�ticas e caracter�sticas das democracias atuais. https://t.co/XMHROIhpUd");
	//	twexp.add("Twitter;;Fri Nov 23 10:12:05 GMT 2018;;CEI-IUL;;Hoje, no ISCTE-IUL, decorre a segunda edi��o do Semin�rio da Imprensa Peri�dica e Mem�ria. A confer�ncia de abertura  � com Nelson Ribeiro da FCH-Cat�lica \"Politicamente s� existe aquilo que o p�blico sabe que... https://t.co/QwWnDqTSjS");
		twexp.add("Twitter;;Sun Nov 25 18:55:30 GMT 2018;;EIC142;;Hello World!");
		
		assertEquals(twexp, tw);
		
		/**
		  * TEST TO : Tweets a particular content.
		*/
		
		final long start = System.nanoTime();
		Twitter.tweet("Just doing a test!", email);
		final long end = System.nanoTime();
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND,(int) (calendar.get(Calendar.SECOND) - ((end - start) / 1000000000) - 2));
		System.out.println(calendar.getTime().toString());
		twexp.add("Twitter;;" + calendar.getTime() + ";;EIC142;;Just doing a test!");
		Gestor.writeTweetsFile(email);
		tw = Gestor.getTweets(email);
		Collections.sort(twexp, Collections.reverseOrder());
		assertEquals(twexp, tw);
	}

}
