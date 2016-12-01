package lotto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;


public class Lotto_alex {

	public static void main(String[] args)
	{
		new Lotto_alex();
	}
	
	int[] freqTable = new int[49];
	
	public Lotto_alex()
	{
//		Collection<Integer> c = pickNumbers();
//		for(Integer i : c)
//			System.out.println(i);
		


		for (int i = 0; i < 1000000; i++) {
			pickNumbers();
		}
		

		//output
		for (int i = 0; i < freqTable.length; i++) {
			System.out.println((i + 1) + ": " + freqTable[i]);
		}

	}
	
	public Collection<Integer> pickNumbers()
	{
		Random r = new Random();
		HashSet<Integer> hs = new HashSet<Integer>();

		while(hs.size() < 6)
		{
			Integer pick = r.nextInt(49);
			if(hs.add(pick + 1)) //make sure its within 1-49
				freqTable[pick]++; //count the picked number
		}

		return hs;
	}
}
