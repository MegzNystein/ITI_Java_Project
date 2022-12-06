# Project: Fire Alarm 
### Group number: 3
### This instruction was updated on: Dec 6, 2022.
### Supervised by: Eng.Eman Hesham
************************
#### Table of contents:
   * Contributers Name
   * Project Document
   * Project Video
   * Project Description
   * Java Program Explanation
   * Arduino Program Explanation
   * Project Courses
   * Programming Language
   * Technologies
        
##### Contributers Name:
   * Mohamed Khalid Shahat
   * Yahya Tarek Ahmed
   * Abdelrahman Mohamed Sharaf
   * Esraa Mohamed Mostafa
   * Aliaa Ahmed Mohamed

##### Project Document
   <br /> Google docs link:https://drive.google.com/file/d/1f9HdXkWU9fNg4_Oy0v9ZJ8tJzrS-5_6H/view?usp=share_link

##### Project Video
   <br /> Google drive link:

##### Project Description: 
   * Display the current temprature through a guage Gui
   * Alarm (Sound - Light - Warning sign in the GUI) when detect fire accident 
   * Press "Stop" button to stop the alarm if it is wrong detection
   * Press "History" button to Display the data of fires accident its content is:
       * Fire accident ID: the number of fire since operation
       * Fire accident date: day/month/year
       * Fire accident time: hours:miniutes:seconds  am/pm

##### Java Program Explanation:

   * project_gui
       * Communication package
           * SerialProtocol class: Connect with arduino, send and receive data
       * project_gui package
           * Clint class: communicate with the server for two requests
              * Tell server to record a fire accident 
              * Tell server show the recorded fires list of fire accidents "History" 
           * FXMLDocumentController class: Client thread and the main thread to checking fire accident
           * FireAccident class: Main functionality to check fire accident and take actions
           * Project_Gui class: Main method for launching to the GUI
     
   * project_server
       * ClientHandler class: Checking the requests from the client and take actions 
       * FireAccidant class:  Represents the fire accident data for each accident which are
           * The fire accident temp 
           * The fire accident date  
           * The fire accident time  
           * The fire accident id
           * The total number of fires accident
       * ListofFireAccidant class:  List of fire accidents 
           * Append new accident
           * Delete accident
           * Record data
           * Open list of the fire accidents
       * Server class: Waits for client requests
       * ServerGUI class: Main method for launching to the server
  
  ##### Arduino Program Explanation:
   * including DHT sensor library
   * Checking data sending to Arduino if:
       * data equal '0' Start the connection
       * data equal '1' There is a fire accident
       * data equal '2' Stop the alarm

##### Project courses:
   * Java project for Java course
   * Embedded Systems internship Intake 43
 
##### Programming Language: 
   * Java for application
   * C++ for arduino

##### Technologies:
   * Apache NetBeans IDE version: 15
   * Java developer kit: JDK 8
   * Scene Builder GLUON version: 8.5.0
   * Medusa library version: 8.0
