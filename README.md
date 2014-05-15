ANXJavaStreamingClient
======================

This project provides a Java client to communicate with ANX streaming API.

NOTE BIEN: need to run https://github.com/btcdude/anx-sample/blob/master/wsproxy.js first before running this project \n
Steps to get above wsproxy.js running: \n
0. install node or nodejs (will use nodejs for example) as well as node package manager (npm) \n
1. git clone https://github.com/btcdude/anx-sample.git \n
2. cd to anx-sample repository \n
3. npm install \n
4. nodejs wsproxy.js \n

To run the Java client: \n
Approach 1. \n
If you have Gradle (http://www.gradle.org/) installed, run below command when you are in /ANXJavaStreamingClient/AnxStreamingApiJavaClient repository: \n
gradle -PmainClass=examples.basic.AnxStreamingClient execute 
\n
Approach 2. \n
AnxStreamingClient.java can be run directly in intellij IDE \n

If you're on unix/linux OS, recommended way to install Gradle: \n
1. install gvm following instruction at: http://gvmtool.net/ \n
2. gvm install gradle \n

