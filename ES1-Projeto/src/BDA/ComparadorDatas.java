package BDA;

import java.util.Comparator;

/**
 * Compares to different dates.
 */


public class ComparadorDatas implements Comparator<Data>{
		
		@Override
		public int compare(Data arg0, Data arg1) {
			
			return arg0.compareTo(arg1);
		}


}
