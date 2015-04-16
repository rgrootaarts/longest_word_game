Installation instructions
Install JDK 1.7
Install Maven 
Pull repository Git (https://github.com/rgrootaarts/longest_word_game.git)

open a command line in the main folder 
mvn clean install
mvn package

to run:
java -jar target/longestwordgame-0.1.0.jar

I developed this application with Windows, JDK 7, Maven and IntelliJ 13 as tools. When starting the application, I figured the ease of installing and running the application was the most important factor. I heard lots of good stuff about Docker, but I am unfamiliar with it, so that would be a disadvantage for the time permitted for this assignment.

I used Spring Boot for the purpose of easy installing. It has two advantages:
1. A small application server is wrapped in the jar, so no need to configure a separate server on the machine
2. Every dependency is included in one single jar. Deploying to any machine is easy that way, or you could deploy it on a cloud based solution

First of all I set up a basic Spring Boot environment. The needed dependencies in the pom file, an application class, application controller and a couple of html pages used for input and result.

The controller first checks if the entry is valid. I used a regular expression for the validation of the raw string itself. It's readable, it's fast and there is no need to read every char myself. If the rules would change, it's easier to change a expression than a complete algorithm in code.

The part I didn't finish yet is to call a dictionary using a rest api. I want to use a third party one, preferably 
http://developer.oxforddictionaries.com/developer-resources/api-reference-guide/intro-using-the-oxford-dictionaries-api/ They keep the dictionary updated and save us the trouble of setting up and maintaining our own database of words.

If the JSON call would return an successful result the score would be calculated and added to the view model.

If I would persist the entry data I would have used a mysql docker image, so it would be easy to install and would have not much configuration, only the docker client. The database would be immediately available, also on Ubuntu. I won't use Jetty or any other in-memory database for this, because we want to keep the data, even if we close the application service for some reason.

To connect to and query the database, the JdbcTemplate of Spring should be used. It has transaction management, automatic connection management and exception handling that give an informative error message you can understand.

Using the template I would first check the entries of the entered user name and query for the max score of that person. In case of equal scores, the score is not beaten, so the user doesn't get the 'winner' message.
In case the score has been beaten, the new record is added to the database. A query is needed that can check if the user's old personal high score is still in the top 10. If not, it can be deleted.

After that I would check all entries to see if the score is globally the best.
In case of equal scores, the score is not beaten, so the user doesn't get the 'winner' message.
In case the score has been beaten, the new record is added to the database.
No records are deleted, because we don't want to delete the high scores of other users.

Another template query would be used to show the top 10 of scores of all entries.







