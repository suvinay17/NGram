# NGram
Please note: for the part argument, add exact question part: [1-4]    
part 1 returns top 10 unigrams, top 10 bigrams, and top 10 ngrams based on input n
part 2 returns conditional probability of token given history, top 10 conditional probabilites for bigrams without smoothing
part 3 returns top conditional probabilities for bigrams without smoothing
part 4 returns top 20 bigram conditional probabilities without smoothing.


The program is written in Java and is meant to be run on command line/ terminal, please follow the instructions:

to compile : javac ngrams.java

to execute : java ngrams.java arg[0] arg[1] arg[2] arg[3] arg[4] 
example: java ngrams.java 1 3 input.utf8.txt Sam "I am"

Where: 

       arg[0] : The part of the question you want answered. Valid options : 1,2,3,4
       
       arg[1] : int n, This part is where you input n for ngrams
       
       arg[2] : String filePath, the path of the file to be read as corpus[must be input for any code to run] for eg: input.utf8.txt
       
       arg[3] : String Token, the input token that you want to look for when calculating n-1 gram probability with history. eg: Sam
       
       arg[4] : String History, please follow the correct way to input this, anything entered in this argument must start with a " quotation marks and end with                     quotation marks. Example usage: "My name is Sam."
       
Specific Design choices: 
       
       1. Line 24 has int k, which specifies the top k probabilities to return. Change this if you want more or less.
       
       2. The input string History is treated as one sentence, and hence only has one set of start of line and end of line tokens. A future improvement                       would be to allow for multiple sentences in the history.
 
       3. The top k conditional probabilities prints undefined if either the numerator or denominator is 0 because we are using ln(x) probabilities.
       
       4. The conditional probability for the token given history returns Postitive Infinity if either the numerator or denominator is 0, because we are using ln(x)
          probabilities.
  
 
                
