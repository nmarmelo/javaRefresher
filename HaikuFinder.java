package javaRefresher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HaikuFinder {

	private static String TEXT_FILE = "C:\\simple\\guten_text.txt";
	public ArrayList<String> allWords = new ArrayList<String>();
	public ArrayList<String> allHaikus = new ArrayList<String>();
	public char[] vowels = {'a','e','i','o','u','y'};
	
	public void readFile(){
	    //read text and store all words, separated by a space, as strings in allWords
	    try {
			String word = "";
			@SuppressWarnings("resource")
			Scanner inFile1 = new Scanner(new File(TEXT_FILE)).useDelimiter("[\\s ]+");
			while (inFile1.hasNext()) {
				word = inFile1.next().trim();
			    if(word != null){
			    	word = word.toLowerCase();
			    	allWords.add(word);
//			    	System.out.println(word);
//			    	countSyls(word);
			    }
			}
			inFile1.close();
			} catch (FileNotFoundException e) {
				System.out.println("Trouble with the readFile method.");
				e.printStackTrace();
			}
	}
	
	private int countSyls(String s){
	    int syls = 0;
	    
	    for(int i = 0; i < s.length(); i++){
	    	if(i == 0 && isVowel(s.charAt(i))  == true)	//check first character
	    		syls++;
	    	else if(i>0 && isVowel(s.charAt(i)) == true) 	//gotta lookout for dipthongs
	    		if(!isVowel(s.charAt(i-1)))		//if previous character is NOT a vowel, +1 to syllable count 
	    			syls++; 
	    	
	    	if(i>0 && i == s.length()-1){		//catch exception of silent e or ending 'le'/'es'
	    		if(s.charAt(i) == ('e')){
	    			if(s.charAt(i-1) == 'l')
	    				syls++; 
	    		}
	    		else if(s.charAt(i) == ('s') || s.charAt(i-1) == ('e')) //to catch ending es
	    			syls--;
	    	}
	    	
	    	if (i>0 && s.charAt(i) == 'a' && s.charAt(i-1) == 'i')
	    		syls++;
	    }
	    
	    if (syls == 0)
	    	syls++;
	    
	    //System.out.println(syls+" syllables");
	    
		return syls;   
	}
	
	private boolean isVowel(char c){
		boolean boo = false;
	
		for(int j = 0; j < vowels.length; j++){
            if(c == vowels[j])
            	boo = true;
		}
		if(boo  == true)
			return true;
		else
			return false;
	}
	
	private void findHaikus(){
		
		for(int loc = 0; loc < allWords.size()-15; loc++){
		    int i = loc;
		    int sylCount = 0;
		    String line1 = "";
		    String line2 = "";
		    String line3 = "";
		    String haiku = "";

		    while(sylCount < 5){
		        sylCount += countSyls(allWords.get(i)); //add syllables of current word to syllable count
		        if(sylCount<=5){
			        line1 += allWords.get(i)+" ";               //add current word to line1
			        i++;    									//increment i by 1 in order to get the next word
		        }
		        
		        if(sylCount == 5){                  			//if 5 syllables are counted, repeat the process for the 7 syllable line
		            
		            while(sylCount < 12){
		                sylCount += countSyls(allWords.get(i)); //add syllables of current word to syllable count
		                if(sylCount<=12){
			                line2 += allWords.get(i)+" ";           //add current word to line2
			                i++;
		                }
		            
		                if(sylCount == 12){
		                    
		                    while(sylCount<17){
		                        sylCount += countSyls(allWords.get(i));
		                        if(sylCount<=17){
			                        line3 += allWords.get(i)+" ";
			                        i++;
		                        }
		                        if(sylCount == 17){
		                        	haiku = line1+"\n"+line2+"\n"+line3+"\n\n";
		                        	//allHaikus.add(haiku);
		                        	System.out.println(haiku);
		                        }
		                    }
		                }
		            }
		        }               
		    }
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HaikuFinder search = new HaikuFinder();
		
		search.readFile();	
		search.findHaikus();
		//search.countSyls("");
	}

}
