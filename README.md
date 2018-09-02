Overview:
-----------------
This is a Java webscraper that takes information from lolcounter.com and stores upvotes, downvotes, power, lanes, roles, and comments into SQL.  A seperate J2EE application, lolcounter.rest, will take the data and produce the data as JSON.  Another Electron application, lolpick, will receive the data.  Lolpick will read the JSON and present lolcounter information in tandem with real-time draft data sourced from League of Legends game logs.  

The webscraper and REST server will be built in Java utilizing JSoup, Jersey, Hibernate, Rest Assured, Cucumber, and TestNg.  The client application will be built with in TypeScript with Electron and Angular.

Motivations:
-----------------
I used to play a lot of League of Legends before I started working.  I liked to use lolcounter.com to produce recommendations for matchups and gained a good understanding of the relationship between champions.  I want to improve my skills as a software engineer so I married my knowledge of League with my passion for code.  I hope to show these applications to future employers to demonstrate my interests and aptitude.  

I have had the good fortune of seeing amazing applications built with excellent technologies written with beautiful code.  I want to constantly learn how to use those technologies and this application is a product of that desire.  My code is not very beautiful but I will only get better by practicing every day.  I will write code every day until I produce something beautiful and valuable for the sake of producing enjoyable and useful software.

Fails:
-----------------
When scrapers have failed, logs/log.log will contain error messages for the failing entity.  

Run:
-----------------
1.  In src/main/java run com.lolpick.lolcounter.application.Application


Test:
-----------------
1.  In src/main/java run com.lolpick.lolcounter.cucumber.RunCucumberTest