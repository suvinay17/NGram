/* Class written by Suvinay Bothra on August 28th to compute ngram probabilities to predict the next word
* Assignment for cmsc395 (NLP) by Dr. Park
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.io.BufferedReader;
public class ngrams{

/*
* Command Line input :
* int part: the question number, int k: k most frequent counts/elements needed
* int n : specifies n grams, String filePath: specifies the file path to be read.
* This method is the driver function and calls all other methods that compute N grams.
*/

public static void main(String args[]){

int part = Integer.parseInt(args[0]);
int k = Integer.parseInt(args[1]);
int n = Integer.parseInt(args[2]);
String filePath = args[3];

HashMap<String, Integer> counts = new HashMap<>();

ArrayList<String> lines = readFile(filePath);
lines = putTokens(lines);
lines = computeNGrams(lines, n, hm);
printCounts(counts, k, part);
}

/*
*This method prints the counts or the probability of the k most frequent words
*Parmaters:
*HashMap<String,Integer> count: stores the counts of each token or merged tokens(String) based on n(Integer)gram model
*int k: the top k frequent counts/ probabilities to be output
*int part: the question part (decides to output probability or count)
*/
public static void count(HashMap<String, Integer> counts, int k, int part){
  String a, b;
  Double prob;
  PriorityQueue<String> top = new PriorityQueue<>((x, y) -> ( y.getValue() - x.getValue())); // Comparator based on counts

  for(Map.Entry<String, Integer> x: counts.entrySet()) //iterating through map to put things into priority queue
     top.offer(x.getKey());

  if(part == 1){ // Programming part q1.
     for(int i = 0 ; i < k; i++){
       a = top.poll() // using PriorityQueue to get top k elements efficiently
       System.out.println("Word: "+a+" Count: "+counts.get(a));
     }
   }

   if(part == 2 || part == 3){
     for(int i = 0 ; i < k; i++){
       a = top.poll()
       b = getPreviousTokens(a,n);
       prob = (counts.get(a) * counts.get(b)) / counts.get(b));
       System.out.println("P( "+ a +" | "+ b +" )"+" = "+ prob);
   }
}


/*
* This method reads the file for the corpus
* Parameters:
* String path: Stores the file path of the input text file
* Returns the text file as an ArrayList<String> of the text file
*/
public ArrayList<String> readFile(String path){

}



}
