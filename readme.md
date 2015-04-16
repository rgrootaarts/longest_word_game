Installation instructions

1. Install JDK 1.7 as described here http://docs.oracle.com/javase/7/docs/webnotes/install/
2. Install Maven as described here: https://maven.apache.org/download.cgi
3. Pull the remote repository of the application (https://github.com/rgrootaarts/longest_word_game.git)

4. open a command line in the main folder 
5. mvn clean install
6. mvn package

to run:
java -jar target/longestwordgame-0.1.0.jar

Tools:

I developed this application on Windows with JDK 7, Maven and IntelliJ 13 as tools. When I read the assignment, I figured the ease of installing and running the application was the most important factor. So I searched for a framework that can accommodate for that. I heard lots of good stuff about Docker, but didn't choose for it now, because I still had to setup an entire environment for an image, with all the configuration needed. Instead I choose another technology I never worked with before: Spring Boot.

It has a couple of advantages:
1. A small application server is wrapped in the jar, so no need to configure a separate server on the machine
2. Every dependency is included in one single jar. Deploying to any machine is easy that way, or you could deploy it on a cloud based solution
3. Spring boot has much less configuration than 'Ordinary' Spring, making it easier to understand for people that are not that into Spring.
4. If any auto-configuration code has to be changed (like the port tomcat listens at), it's still possible.

The disadvantages are:
1. It's harder to put the application in another web server. It is possible, but you have to change the pom, add dependencies that are normally auto-configured and make sure it creates a war, instead of a jar.
2. If you want another logging mechanism than logback, slf4j or log4j, you have to be careful, sometimes things go awry with dependencies and classpaths.


The implementation idea:

First of all I set up a basic Spring Boot environment. The needed dependencies in the pom file, an application class, application controller and a couple of html pages used for input and result.

The controller first checks if the entry is valid. I used a regular expression for the validation of the raw string itself. It's readable, it's fast and there is no need to read every char myself. If the rules would change, it's easier to change a expression than a complete algorithm in code.

The part I didn't finish yet is to call a dictionary using a rest api. I want to use a third party one, preferably 
http://developer.oxforddictionaries.com/developer-resources/api-reference-guide/intro-using-the-oxford-dictionaries-api/ They keep the dictionary updated and save us the trouble of setting up and maintaining our own database of words.

If the JSON call would return an successful result the score would be calculated and added to the view model.

If I would persist the entry data I would have used a mysql docker image, so it would be easy to install and would have not much configuration, only the docker client. The database would be immediately available, also on Ubuntu. I won't use Jetty or any other in-memory database for this, because we want to keep the data, even if we close the application service for some reason.

To connect to and query the database, the JdbcTemplate of Spring should be used. It takes care of transaction management and automatic connection management so resources are taken care of without boiler plate code. And it has better exception handling that gives an informative error message you can understand.

Using the template I would first check the entries of the entered user name and query for the max score of that person. In case of equal scores, the score is not beaten, so the user doesn't get the 'winner' message.
In case the score has been beaten, the new record is added to the database. A query is needed that can check if the user's old personal high score is still in the top 10. If not, it can be deleted.

After that I would check all entries to see if the score is globally the best.
In case of equal scores, the score is not beaten, so the user doesn't get the 'winner' message.
In case the score has been beaten, the new record is added to the database.
No records are deleted, because we don't want to delete the high scores of other users.

Another template query would be used to show the top 10 of scores of all entries.






