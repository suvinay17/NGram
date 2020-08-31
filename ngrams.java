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
* String token: Input token for which we are calculating n-gram
* This method is the driver function and calls all other methods that compute N grams.
*/

public static void main(String args[]){

int part = Integer.parseInt(args[0]);
int k = Integer.parseInt(args[1]);
int n = Integer.parseInt(args[2]);
String filePath = args[3];
String token = args[4];
String history = args[5];
String hist[] = history.split("//s");

String lines = readFile(filePath);
lines = putTokens(lines);
HashMap<String, Integer> counts = getCounts(lines, n);
printResults(counts, n, k, part, token, hist);
}

/*
* This method prints the counts or the probability of the k most frequent words
* Parmaters:
* HashMap<String,Integer> count: stores the counts of each token or merged tokens(String) based on n(Integer)gram model
* int k: the top k frequent counts/ probabilities to be output
* int part: the question part (decides to output probability or count)
* String token: Input token for which we are calculating n-gram
*/
public static void printResults(HashMap<String, Integer> counts,int n, int k, int part, String token, String[] hist){
  String a;
  Double prob;
  PriorityQueue<String> top = new PriorityQueue<>((x, y) -> ( y.getValue() - x.getValue())); // Comparator based on counts
  PriorityQueue<String> topbi = new PriorityQueue<>((x, y) -> ( y.getValue() - x.getValue()));

  for(Map.Entry<String, Integer> x: counts.entrySet()){ //iterating through map to put things into priority queue
     String s = x.getKey();
     String m[] = s.split("//s");
     if(m.length == 1)
      top.offer(x.getKey());
     if(m.length == 2)
      topbi.offer(x.getKey());
   }

  if(part == 1){ // Programming part q1.
     System.out.println("unigrams: ");
     for(int i = 0 ; i < k; i++){
       a = top.poll() // using PriorityQueue to get top k elements efficiently
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
     g = calculateNGRAM(token, history, n, 1.0, counts);
     System.out.println(n+" gram probability : "+ g );
    }

  }




/*
* This method reads the file for the corpus
* Parameters:
* String path: Stores the file path of the input text file
* Returns the text file as a String
*/
public static String readFile(String path){

  try{
    BufferedReader br = new BufferedReader(new FileReader(path));
    StringBuilder lines = new StringBuilder();
    String line = br.readLine();

    while(line != null)
      lines.append(line);
}
  catch(Exception e){ throw FileNotFoundException;}
  return lines.toString();
}


/*
* This method puts start and end of line tokens into the corpus string using regex
* Parameters:
* String lines: stores the string read from input file
* Returns the String with start and end tokens added.
*/
public static String putTokens(String lines){

  StringBuilder sb = new StringBuilder();
  String[] sentences = lines.split("[.!?]");
  for(String s : sentences){
    s.trim();
    sb.append("<s> ");
    sb.append(s);
    sb.append(" </s> ");
  }
  return sb.toString().trim();
}


/*
* This method computes the counts for all occurences of words, part-sentences, and sentences
* Parameters:
* String lines: stores the string read from input file with beginning and end tokens
* int n: to set the n in ngram model
* Returns HashMap<String, Integer> counts, with counts of words, part-sentences, and sentences.
*/
public static HashMap<String, Integer> getCounts(String lines, int n){

  StringBuilder sb = new StringBuillder();
  HashMap<String, Integer> counts = new HashMap<>();
  String x;
  sb.append("<s> ");
  String[] words = lines.split("// ");

  if(words.length > 1){
    for(int i = 1; i < words.length; i++){
      words[i] = words[i].trim();
      sb.append(words[i]);
      x = sb.toString()
      counts.put(words[i] , counts.getOrDefault(words[i], 0) + 1);
      counts.put(x , counts.getOrDefault(x, 0) + 1);
      sb.append(" ");
      if(words[i].equals("</s>"))
        sb = new StringBuilder();
      if(i == words.length - 1 && !words[i].equals("</s>")){ // edge case end of sentence
        counts.put("</s>" , counts.getOrDefault(words[i], 0) + 1);
        counts.put(x + "</s>" , counts.getOrDefault(words[i], 0) + 1);
      }

  }
  return counts;
}

  else if(word.length == 1){return "<s>" + word[0] + "</s>";} //edge case

  else
    return "";
}


/*
* This private method calculates NGRAM probabilities
* Input:
* String []hist : contains the history of the token
* int n : n in ngrams
* double prob: stores the ngram probability of that tokens
* HashMap<String, Integer> counts : stores the counts of each token or merged tokens(String) based on n(Integer)gram model
* Returns (recursively): double prob the ngram probability of the token given the history
*/
private static double calculateNGRAM(String[] hist, String token, int n, double prob, HashMap<String, Integer> counts){
    StringBuilder sb = new StringBuilder();
    for(int i = 0 + n; i < f; i++){
      sb.append(hist[i]);
      sb.append(" ");
    }
    double c = counts.getOrDefault(sb.toString(), 0));
    double z = counts.getOrDefault(token);
      prob += Math.log(z /(c));
    }
  calculateNGRAM(hist, token, n, prob, counts);

}



}
