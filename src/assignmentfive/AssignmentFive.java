/* Landon Speer */

package assignmentfive;

import java.io.*;
import java.util.*;


public class AssignmentFive {
    
    //attributes
    private long wordsFound = 0;
    private long wordsNotFound = 0;
    private long compsFound = 0;
    private long compsNotFound = 0;
    
    private int[] count = new int[1];
    
    private long avgForFound = 0;
    private long avgForNotFound = 0;
    
    
    //getters
    public long getWordsFound(){
        return wordsFound;
    }
    public long getWordsNotFound(){
        return wordsNotFound;
    }
    public long getCompsFound(){
        return compsFound;
    }
    public long getCompsNotFound(){
        return compsNotFound;
    }
    public double getAvgForFound(){
        return avgForFound;
    }
    public double getAvgForNotFound(){
        return avgForNotFound;
    }
    

    //methods
    /**
     * @param dictionaryList: must take in an array of BinarySearchTrees
     * 
     * @requires: Takes in an array of BinarySearchTrees as a parameter
     * 
     * @ensures: words are converted to lowercase then the words are added to 
     * the array at the index based on their first character value. This sorts 
     * the words of the dictionary into BinarySearchTrees based on their first letter.
     * 
     * This method loads the dictionaryList array with words at a specific index
     * based on their first character value.
     */
    public void loadDictionary(BinarySearchTree[] dictionaryList) {
        try {
            File f = new File("random_dictionary.txt");
            Scanner inputf = new Scanner(f);

            while (inputf.hasNext()) {
                String word = inputf.nextLine().toLowerCase();
                dictionaryList[(int)word.charAt(0)-97].insert(word);
            }//while

        }//try
        catch (IOException e) {
            e.printStackTrace();
        }//catch

    }//readingDictionary
    
    /**
     * @requires: all the data has been configured before executing this method
     * 
     * @ensures: all the data will be displayed to the user
     * 
     * This is a display method that will display all the figured data in the 
     * program.
     */
    public void toDisplay(){
        System.out.println("Words found: "+getWordsFound());
        System.out.println("Comps found: "+getCompsFound());
        System.out.println("Averages for found: "+getAvgForFound());
        System.out.println("Words not found: "+getWordsNotFound());
        System.out.println("Comps not found: "+getCompsNotFound());
        System.out.println("Averages for not found: "+getAvgForNotFound());
    }

    /**
     * @param dictionaryList: must take in an array to hold each BinarySearchTree 
     * for each letter of the alphabet. 
     * 
     * @requires: must take in an array of BinarySearchTrees as a parameter
     * 
     * @ensures: data will be read from oliver.txt file and will be compared to
     * a binary search tree based on the first character of the word. If the word is 
     * found wordsFound gets incremented and the running total count is added to
     * compsFound. If the word is not found wordsNotFound is incremented and the
     * value of count is added to compsNotFound. 
     * 
     * This parser takes in an array of BinarySearchTrees and reads through a text
     * file checking if the words from the text file are in the dictionary search
     * trees based on the first character of the words. If the words are found
     * wordsFound and compsFound are increased. If the words are not found then
     * wordsNotFound and compsNotFound are increased.
     */
    public void parser(BinarySearchTree[] dictionaryList) {

        try {
            FileInputStream inf = new FileInputStream(new File("Oliver.txt"));
            char let;
            String str = ""; 
            int n;

            while ((n = inf.read()) != -1) {

                let = (char) n;
                if (Character.isLetter(let)) {
                    str += Character.toLowerCase(let);
                }//if
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {

                    String word = str;
                    count[0] = 0;
                    if(dictionaryList[(int)word.charAt(0)-97].search(word, count)){
                        wordsFound++;
                        compsFound += count[0];
                    }//if
                    else{
                        wordsNotFound++;
                        compsNotFound += count[0];
                    }//else

                    str = "";
                }//if
                
            }//while
            inf.close();
            
            avgForFound = (compsFound / wordsFound);
            avgForNotFound = (compsNotFound / wordsNotFound);
            
        }//try 
        catch (IOException e) {
            e.printStackTrace();
        }//catch

    }//readingOliver
    
    
    
    
    
    
    public static void main(String[] args) {
        
        AssignmentFive obj = new AssignmentFive();//create a class obj
        BinarySearchTree[] dictionaryList = new BinarySearchTree[26];//create array to hold BinarySearchTrees
        
        for(int i = 0; i < dictionaryList.length; i++){
            dictionaryList[i] = new BinarySearchTree<String>();//each array element contains a new BinarySearchTree
        }//for
        
        obj.loadDictionary(dictionaryList);//load the dictionaryList
                
        obj.parser(dictionaryList);//read the oliver.txt file and compare to dictionaryList
        
        obj.toDisplay();//display all the information
        
        
    }//main
    
}//class