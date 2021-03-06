Problem Scenario 89 : You have been given below patient data in csv format. 

patients.csv 

patientlD,name,dateOtBirth,lastVisitDate 

1001,Ah Teck,1991-12-31,2012-01-20
1002, Kumar, 2011-10-29,2012-09-20 
1003,Ali,2011-01-30,2012-10-21

Accomplish following activities. 

1. Find all the patients whose lastVisitDate between current time and '2012-09-15'
2. Find all the patients who born in 2011
3. Find all the patients age
4. List patients whose last visited more than 60 days ago
5. Select patients 18 years old or younger

Solution : 

Step 1 . 
hdfs dfs -mkdir sparksq13 
hdfs dfs -put patients.csv sparksq13/ 

Step 2 : Now in spark shell 

//SQLContext entry point tor working with structured data 
val sqlContext = new org.apache.spark.sql.SQLContext(sc) 
//this is used to implicitly convert an RDD to a DataFrame. 

import sqlContext.implicits. 
//Import Spark SQL data types and Row. 

import org.apache.spark.sql. 
//load the data into a new RDD 

val patients = sc.textFile("sparksq13/patients.csv") 

//Return the first element in this RDD 
patients.first() 

//define the schema using a case class 
case class Patient(patientid: Integer, name: String, dateOfBirth:String , lastVisitDate: String) 

//create an RDD of Product objects 
val patRDD = patients.map(_.split(",")).map(p => Patient(p(O).tolnt,p(1),p(2),p(3))) 
patRDD.first() 
patRDD.count() 

//change RDD of Product objects to a DataFrame 
val patDF = patRDD.tODF() 

//register the DataFrame as a temp table 
patDF.registerTempTable("patients") 

//Select data from table 
val results = sqlContext.sql( "SELECT * from patients" ) 

//display datatrame in a tabular format 
results.show() 

//FROM patients, find all the patients whose lastVisitDate between current time and '2012-09-15' 
val results = sqlContext.sql( "SELECT * FROM patients WHERE year(to_date(cast(unix_timestamp(lastvisitdate,'yyyy-mm-dd') as timestamp))between '2012-09-15' and current_timestamp() order by lastvisitdate")
results.show() 

//Find all the patients who born in 2011 
val results= sqlcontext.sql("SELECT * from  patients where year(to_date(cast(unix_timesTamp(dateofbirth, 'yyyy-mm-dd') as timestamp))) =2011")

//find all the patients age 
val results = sqlContext.sql("Select name,dateofbirth,datediff(current_date(), To_date(cast(unix_timestamp(dateofbirth,'yyyy-mm-dd')as timestamp)))/365 as age FROM patients")
results.show() 

//List patients whose last visited more than 60 days ago 
--List patients whose last visited more than 60 days ago 
val results = sqcontext.sql("SELECT name, lastVisitdate FROM patients WHERE datediff(current_date(),to_date(cast(unix_timestamp(lastvisitdate, 'yyyy-mm-dd') as timestamp))).60");
results.show(); 

--Select patients 18 years old or younger 
SELECT * FROM patients WHERE to_date(cast(unix_timestamp(dateofbirth, 'yyyy-mm-dd') AS TIMESTAMP)) > date_sub(current_date(),INTERval 18year); 

val results = sqlContext.sql("select * from patients where to_date(cast(unix_timestamp(dateofbirth,'yyyy-mm-dd')as timestamp))>date_sub(current_date(),18*365)"); 
results.show(); 

val results = sqlContext.sql("select date_sub(current_date(),18*365)from patients"); 
results.show(); 
