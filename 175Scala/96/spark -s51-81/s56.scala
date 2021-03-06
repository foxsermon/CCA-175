Problem Scenario 56 : You have been given below code snippet. 

val a = sc.parallelize(1 to 20, 4) 
operation 1 

res12: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)

Write a correct code snippet for operation1 which will produce desired output, shown below. 

Array[Array[Int]] = Array(Array(1,2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,26,27,28,29,30,31,32,33), 
Array(34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 6O, 61, 62, 63, 64, 65, 66), 
Array(67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 
94, 95, 96,97, 98, 99, 100)) 
Solution : 

scala> a.glom.collect
res13: Array[Array[Int]] = Array(Array(1, 2, 3, 4, 5), Array(6, 7, 8, 9, 10), Array(11, 12, 13, 14, 15), Array(16, 17, 18, 19, 20))

scala> a.glom.foreach(_.take(5).foreach(println))
1
11
12
13
14
16
15
6
7
8
9
10
2
3
4
5
17
18
19
20

scala> a.glom.foreach(_.foreach(println))
1
6
16
7
8
9
10
11
12
13
14
15
17
18
19
20
2
3
4
5


glom 
Assembles an array that contains all elements of the partition and embeds it in an RDD. Each returned array contains the contents of one partition. 
