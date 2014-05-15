ANXJavaStreamingClient
======================

This project provides a Java client to communicate with ANX streaming API.

NOTE BIEN: need to run https://github.com/btcdude/anx-sample/blob/master/wsproxy.js first before running this project 

Steps to get above wsproxy.js running: 

0. install node or nodejs (will use nodejs for example) as well as node package manager (npm) 

1. git clone https://github.com/btcdude/anx-sample.git 

2. cd to anx-sample repository 

3. npm install 

4. nodejs wsproxy.js 

To run the Java client: 

Approach 1. 

If you have Gradle (http://www.gradle.org/) installed, run below command when you are in /ANXJavaStreamingClient/AnxStreamingApiJavaClient repository: 

gradle -PmainClass=examples.basic.AnxStreamingClient execute 


Approach 2. 

AnxStreamingClient.java can be run directly in intellij IDE 


If you're on unix/linux OS, recommended way to install Gradle: 

1. install gvm following instruction at: http://gvmtool.net/ 

2. gvm install gradle 


