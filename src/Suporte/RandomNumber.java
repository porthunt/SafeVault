package Suporte;

import java.util.Random;

public class RandomNumber {

	String number;

	public RandomNumber() {
	}
	
	public String randomize() {
		Random rand = new Random();
		int min=0; int max=9;
		String randomize = new String();
		
		for(int i=0;i<9;i++) {
			int randomNum = rand.nextInt((max - min) + 1) + min;
			randomize = randomize+randomNum;
		}
		
		return randomize;
	}
	
	public String completelyRandomize() {
		Random rand = new Random();
		int min=0; int max=9;
		String randomize = new String();
		
		while(true) {
			Integer randomNum = rand.nextInt((max - min) + 1) + min;
			if(!randomize.contains(randomNum.toString()))
				randomize = randomize+randomNum;
			else
				continue;
			
			if(randomize.length()==10) {
				break;
			}
		}
		
		return randomize;
	}
	
}
