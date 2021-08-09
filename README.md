# Todolistwebapp
Create a full-stack web application following the Enterprise Architecture Model, using:
•	an application back-end developed using the language from your Programming Fundamentals module (e.g. Java)
•	a managed database hosted locally or within the Cloud Provider examined during your Cloud Fundamentals module (e.g. MySQL in GCP)
•	a front-end developed using the language from your Front-End Web Technologies module (e.g. JavaScript)
etting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

Prerequisites-
Git download.

Eclipse download.

Visual Studio Code download.

Installing
A step by step series of examples that tell you how to get a development env running

Copy this project url: https://github.com/simonwhiteQA/TDL-PROJECT.git

Open Git bash

Git clone the project url to your workspace.

Open Eclipse IDE

The maven project should now be available for development and testing

Run the executable .jar file

Open visual studio code

Open folder "TDL-PROJECT"

Open live server from index.html file

Interact with front end of web application

Running the tests
Running automated tests:

Unit Tests: Right click TDL-PROJECT/src/test/java/com.qa.spring.service in Eclipse -> coverage as -> junit test This will return all the unit tests and the coverage the tests cover for the system.

Integration tests: Right click TDL-PROJECT/src/test/java/com.qa.spring.rest in Eclipse -> run as -> junit test This will return all the integration tests and the coverage the tests cover for the system.

Selenium tests: Right click TDL-Selenium/src/selenium.tests in Eclipse -> run as -> junit test This will return all the front end tests for selenium to run. It has been included in a seperate project folder as it is not dependent on the TDL-PROJECT folder.

Deployment
After cloning the repository to Git you will be able to run the TDL application on command line via the jar file:

Eclipse -> right click project file -> properties -> location (show in system explorer) -> right click -> git bash here -> cd target -> java -jar jar[FileNameIncludingDependencies].jar
Built With
Maven - Dependency Management
Versioning
We use SemVer for versioning.

Authors
Megan Crouch
Jira TDL
License
This project is licensed under the MIT license - see the LICENSE.md file for details

Acknowledgments
I would like to thank Jordan Benbelaid for all the support and guidance he gave me throughout the project.

I would also like to thank Niall Duggan and Simon White for all of the continued support they have given me when i needed any advice throughout the project.

About
No description, website, or topics provided.
Resources
 Readme
License
 MIT License
Releases
No releases published
Packages
No packages published
Languages
-HTML
-Java
-JavaScript
-CSS

© 2021 GitHub, Inc.
