System Reader Libraries:


a) -Spring boot and Mvc 2.5.3

    -jaxb-api for XML parsing : 2.3.0
   
    -Gson: 2.8.6
   
b)  -Use Maven 3.6.3

    -Java 13
    
    -Memory DB = H2   

Staring the Application Mode:
1) From Jar - Go to project path then create your Jar file excuting command : mvn clean -U install
This will create jar file such as :  'event.reader.service-0.0.1-SNAPSHOT.jar'
Then execute java -jar event.reader.service-0.0.1-SNAPSHOT.jar

2) From Intellij (IDE) - open the maven project 
Go to ....\workspace\Menora\src\main\java\com\menora\system\reader folder then run 'EventReaderSystemApplication' class
This will run the App on port 8080

H2 : connection
click : http://localhost:8080/h2-console/ ( You can see the tables)

Get all products by InsuredId groupedBy CompanyName
Clik : http://localhost:8080/showAllProductsByInsuredId

Read Request.xml file content but on Json Format :
click: http://localhost:8080/showDB


Setup of Scheduler Time & Request's folder path 
Open file.properties then :
See on second parameter 'CronTime' , here you can setup the cron time , on this mode the scheduler will be trigerred at 'top of every hour of every day'
If you want to check it change to the trigger to every 40 seconds use this expression: cronTime=*/40 * * * * *
request.folder.path=C:\\Users\\Yaniv\\Download
cronTime=0 0 * * * *

<img width="363" alt="cron" src="https://user-images.githubusercontent.com/94252702/141673988-ebbdd475-7e67-44a3-93e1-716cb70e340a.PNG">


#Regarding the path of the folder where is located the Request.xml file just set it on the first param of the file.properties 'request.folder.path' as shown above

Console Log (default log mode is 'info'  , if you want to see log on debug mode then go to application.properties then change the record below to 'debug'
logging.level.org.springframework = info
