/* Class written by Suvinay Bothra on August 28th to compute ngram probabilities to predict the next word
* Assignment for cmsc395 (NLP) by Dr. Park
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
public class ngrams{

/*
* Command Line input :
* int part: the question number, int k: k most frequent counts/elements needed
* int n : specifies n grams, String filePath: specifies the file path to be read.
* String token: Input token for which we are calculating n-gram
* This method is the driver function and calls all other methods that compute N grams.
*/

public static void main(String args[]){

int part = Integer.parseInt(args[0]);
int k = Integer.parseInt(args[1]);
int n = Integer.parseInt(args[2]);
n--;
String filePath = args[3];
String token = args[4];
String history = args[5];

ArrayList<String> lines = readFile(filePath);
ArrayList<String> line = putTokens(lines);
HashMap<String, Integer> counts = getCounts(line, n);
printResults(counts, n, k, part, token, history);
}

/*
* This method prints the counts or the probability of the k most frequent words
* Parmaters:
* HashMap<String,Integer> count: stores the counts of each token or merged tokens(String) based on n(Integer)gram model
* int k: the top k frequent counts/ probabilities to be output
* int part: the question part (decides to output probability or count)
* String token: Input token for which we are calculating n-gram
*/
public static void printResults(HashMap<String, Integer> counts,int n, int k, int part, String token, String hist){
  String a;
  Double prob;
  PriorityQueue<String> top = new PriorityQueue<>((x, y) -> ( counts.get(y) - counts.get(x) )); // Comparator based on counts
  PriorityQueue<String> topbi = new PriorityQueue<>((x, y) -> ( counts.get(y) - counts.get(x) ));

  for(Map.Entry<String, Integer> x: counts.entrySet()){ //iterating through map to put things into priority queue
     String s = x.getKey();
     String m[] = s.split(" ");
     if(m.length == 1)
      top.offer(x.getKey());
     if(m.length == 2)
      topbi.offer(x.getKey());
   }

  if(part == 1){ // Programming part q1.
     System.out.println("unigrams: ");
     for(int i = 0 ; i < k; i++){
       a = top.poll(); // using PriorityQueue to get top k elements efficiently
       System.out.println("Word: "+a+" Count: "+counts.get(a));
     }
     System.out.println("bigrams: ");
     for(int i = 0 ; i < k; i++){
       a = topbi.poll(); // using PriorityQueue to get top k elements efficiently
       System.out.println("Word: "+a+" Count: "+counts.get(a));
     }
   }

   double g = 0;
   if(part == 2 || part == 3){
     g = calculateNGRAM(token, hist, n, 1.0, counts);
     System.out.println(n+" gram probability : "+ g );
    }

  }




/*
* This method reads the file for the corpus
* Parameters:
* String path: Stores the file path of the input text file
* Returns the text file as a String
*/
public static ArrayList<String> readFile(String path){

  ArrayList<String> lines = new ArrayList<String>();
  try{
    BufferedReader br = new BufferedReader(new FileReader(path));
    String line;
    while( (line = br.readLine()) != null)
      lines.add(line);
}
  catch(Exception e){ System.out.print(" File not found/IOExcetption. Please run the program with a correct file path. Please ignore any other output in thus run");}
  return lines;
}


/*
* This method puts start and end of line tokens into the corpus string using regex
* Parameters:
* String lines: stores the string read from input file
* Returns the String with start and end tokens added.
*/
public static ArrayList<String> putTokens(ArrayList<String> lines){
  for(int i = 0; i < lines.size(); i++){
    StringBuilder sb = new StringBuilder();
    sb.append("<s> ");
    sb.append(lines.get(i).trim());
    sb.append(" </s> ");
    lines.set(i, sb.toString());
  }
  return lines;
}


/*
* This method computes the counts for all occurences of words, part-sentences, and sentences
* Parameters:
* String lines: stores the string read from input file with beginning and end tokens
* int n: to set the n in ngram model
* Returns HashMap<String, Integer> counts, with counts of words, part-sentences, and sentences.
*/
public static HashMap<String, Integer> getCounts(ArrayList<String> lines, int n){

  StringBuilder sb = new StringBuilder(); //String builder for efficient String building since Strings are immutable in java
  HashMap<String, Integer> counts = new HashMap<>();
  String x;
  int b = 0;
  for(String line: lines){
    System.out.println(line);
    String[] words = line.split(" ");
    for(int i = 0; i < words.length; i++){
      words[i] = words[i].trim();
      counts.put(words[i] , counts.getOrDefault(words[i], 0) + 1);
      b = i;
      sb.append(words[i]);
      sb.append(" ");
      for(int j = i + 1; (j <= b + n ) && (j < words.length) ; j++){
        sb.append(words[j]);
        x = sb.toString();
        counts.put(x , counts.getOrDefault(x, 0) + 1);
        sb.append(" ");
        if(words[i].equals("</s>"))
          sb = new StringBuilder();
        }
      sb = new StringBuilder();
  }
}
  return counts;
}


/*
* This private method calculates NGRAM probabilities
* Input:
* String hist : contains the history of the token
* int n : n in ngrams
* double prob: stores the ngram probability of that tokens
* HashMap<String, Integer> counts : stores the counts of each token or merged tokens(String) based on n(Integer)gram model
* Returns : double prob the ngram probability of the token given the history
*/
private static double calculateNGRAM(String hist, String token, int n, double prob, HashMap<String, Integer> counts){
      hist = hist.trim(); //cleaning up for edge cases
      String history[] = hist.split(" "); //split tokens based on space
      StringBuilder sb = new StringBuilder(); // StringBuilder for efficient concatenation
      for(int i = history.length - n; (i >= 0) && (i < history.length); i--){
        sb.append(history[i]);
        sb.append(" ");
      }
      return Math.log(counts.getOrDefault(token,0)/(counts.getOrDefault(sb.toString().trim(), 0)));
    }

}
