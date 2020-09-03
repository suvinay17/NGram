# NGram
NLP Language Model: Uses n-gram language model with an input text file as a corpus to predict the k most probable words based on an input n for a ngram model, and an input token and history.

The program is written in Java and is meant to be run on command line/ terminal, please follow the instructions:

to compile : javac ngrams.java

to execute : java ngrams.java arg[0] arg[1] arg[2] arg[3] arg[4] arg[5]

Where: arg[0] : The part of the question you want answered. Valid options : 1,2,3,4

       arg[1] : int k, where k specifies the top k unigrams/bigrams/ngrams/conditional probabilities to return
       
       arg[2] : int n, This part is where you input n for ngrams, based on this program will calculate n-1 grams
       
       arg[3] : String filePath, the path of the file to be read as corpus[must be input for any code to run] for eg: input.utf8.txt
       
       arg[4] : String Token, the input token that you want to look for when calculating n-1 gram probability with history. eg: Sam
       
       arg[5] : String History, please follow the correct way to input this, anything entered in this argument must start with a " quotation marks and end with                     quotation marks. Example usage: "My name is Sam."
                
