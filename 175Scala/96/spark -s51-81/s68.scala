Problem Scenario 68 : You have given a file as below. 
/home/paslechoix/Lorem.txt

File contain some text. As given in RHS (Righ hand side). 
A bigram is pair of successive tokens in some sequence. Please build bigrams from the sequences of words in each sentence, 
and then try to find the most frequently occuring ones. 

=========================================================================
Solution : 

Step 1 : Create all three files in hdfs (We will do using Hue). However, you can first create in local filesystem and then upload it to hdfs. 

Step 2 : The first problem is that values in each partition ot our initial RDD describe lines from the file rather than sentences. 

Sentences may be split over multiple lines. 

The glom() RDD method is used to create a single entry for each document containing the list of all lines, we can then join the lines up, 
then resplit them into sentences using “.” as the separator, using flatMap so that every object in our RDD is now a sentence. 
sentences = sc.textFile("Content.txt").glom().map(lambda x: "".join(x)).flatMap(lambda x: x.split(".")) 

Step 3 : Now we have isolated each sentence we can split it into a list of words and extract the word bigrams from it. Our new ROD contains tuples 
containing the word bigram (itself a tuple containing the first and second word) as the first value and the number 1 as the second value. 
bigrams = sentences.map(lambda x:x.split()) \ 
.flatMap(lambda x:[((x[i],x[i+1]),1) for i in range(0,len(x)-1)]) 
Step 4 : Finally we can apply the same reduceByKey and sort steps that we used in the wordcount example, to count up the bigrams and sort 
them in order ot descending frequency. In reduceByKey the key is not an individual word but a bigram. 
freq_bigrams = bigrams.reduceByKey(lambda x,y:x+y) \ 
.map(lambda x: (x[1].x[0])) \ 
.sortByKey(False) 
freq_bigrams.take(10)
