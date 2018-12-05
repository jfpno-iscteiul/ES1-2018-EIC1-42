package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BDA.Data;

class TesteData {

		@Test
		void test() {
			Data teste = new Data(2,2,2018,22,12,54);
			
			int dia = teste.getDia();
			int mes = teste.getMes();
			int ano = teste.getAno();
			int hora = teste.getHora();
			int minuto = teste.getMin();
			int segundo = teste.getSeg();
			
			assertEquals(dia,2);
			assertEquals(mes,2);
			assertEquals(ano,2018);
			assertEquals (hora,22);
			assertEquals(minuto,12);
			assertEquals(segundo,54);
			
			int res = teste.compareTo(teste);
			assertEquals(res,0);
			
			Data teste1 = new Data(2,2,2017,22,12,54);
			res = teste1.compareTo(teste);
			assertEquals(res,-1);
			
			teste1 = new Data(2,1,2018,22,12,54);
			res = teste1.compareTo(teste);
			assertEquals(res,-1);
			
			teste1 = new Data(2,1,2018,22,12,54);
			res = teste1.compareTo(teste);
			assertEquals(res,-1);
			
			teste1 = new Data(2,2,2018,21,12,54);
			res = teste1.compareTo(teste);
			assertEquals(res,-1);
			
			teste1 = new Data(2,2,2018,22,11,54);
			res = teste1.compareTo(teste);
			assertEquals(res,-1);
			
			teste1 = new Data(2,2,2018,21,12,53);
			res = teste1.compareTo(teste);
			assertEquals(res,-1);
			
			teste1 = new Data(2,2,2019,21,12,54);
			res = teste1.compareTo(teste);
			assertEquals(res,1);
			
			
		}

	}