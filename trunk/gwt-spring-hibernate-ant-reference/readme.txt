GWT Spring Hibernate Reference Web Application
==============================================

Welcome to the reference web application that includes the following
components:
1) Ant build file (builds, localizes, deploys, runs unit tests, 
   generates coverage reports, genrates DDL, etc)
2) Eclipse pre-configured project (Googlipse integration currently 
   broken)
3) GWT support (with Spring IoC for server-side components)
4) Spring Inversion of Control for all components
5) Hibernate ORM (using JDK 1.5 annotations)
6) JUnit unit tests (using JDK 1.5 annotated classes, DAO code tested
   against H2 DB)
7) Cobertura unit test code coverage & reporting
8) Log4J Logging (plus a logging facade that makes dropping logging 
   statements into new classes easier -- at least I think so!)

Before Getting Started
----------------------
Please be aware that this is a work-in-progress and may contain ant targets
that are unused or broken... just because it exsits doesn't mean it is
used or functional.

Getting Started: Eclipse
------------------------
1) Ensure that you have all dependencies installed (see Dependencies)
2) From Eclipse: 
    * File > New > Project...
    * Choose: General > Project
    * Click 'Next'
    * Enter the following:
      - Project Name: proto
      - Uncheck 'Use Default Location'
      - Click 'Browse' and browse to the location of the extracted reference
        project
    * Click 'Finish'
    * (At this point the project should be loaded and fully 'aware' of its
      Java nature and whatnot)
3) Make sure the Ant View is showing
4) Add the build.xml file as an ant file.
5) Run the "war.exploded" target

Getting Started: Deploying
--------------------------
1) Ensure that you have all dependencies installed (see Dependencies)
2) Run the ant-target "war.exploded"
3) Start Tomcat
4) Navigate to: http://localhost:8080/proto/
5) Click the button... you should see some junk to the right about
   customers in the DB. Go verify that the button adds 'John Doe'
   to the one and only 'customers' table.

Getting Started: Testing
------------------------
1) Ensure that you have all dependencies installed (see Dependencies)
2) Run the ant-target "junit"
3) Examine code-coverage by opening:
   <projectroot>/build/reports/artifacts/coverage/index.html
   
You can also run unit tests from within Eclipse, but the code coverage
system (cobertura) will not be used.

Dependencies
------------
1) A user environment per-user, per-host (see below)
2) A database (MySQL 5.0.20-standard preferred)
3) A J2EE Web container (apache-tomcat-5.5.17 preferred)

User Environments (UE)
-----------------
The ant buildfile assumes that every developer has a different environment
per user (username), per computer (hostname).

The ant buildfile expects the following files to exist:
<projectroot>/conf/env/user/<username>.<hostname>.properties

Let's call this file a UE for brevity. Each UE contains the user's environment,
which is essentially a link to a file containing shared configuration. My
UE is at the path:
<projectroot>/conf/env/user/jeremy.jerry.properties
and uses the environment 'development', which is located at:
<projectroot>/conf/env/development.properties

My username is 'jeremy' and my computer's hostname is 'jerry' (I love 
Seinfeld!), thus the name jeremy.jerry.properties. Each user must create
their own UE that matches their specific environment.

Database
--------
When building this reference project, I was using MySQL 5 with a database
called 'proto' username 'proto' and password 'proto'. The proto user had
full permissions on the DB. Hibernate (at least with my UE) will automatically
update the DDL within the DB (see this line in my UE: 
hibernate.hbm2ddl.auto=update)

My configuration is not guaranteed to be perfect or optimized (nor is the DDL).
My goal was to get all the big-picture parts hooked all together so that the
tuning could be done for whatever specific application you have in mind.

H2, the database I used for testing will automatically be configured, and 
populated by the single unit test. I typically delete all data at the beginning
of a unit test and leave it at the end of the test since I sometimes want to
inspect the state of the DB when a test fails. You can connect to H2 via
DBVisualizer using the H2 JDBC driver (beyond the scope of this document).

J2EE Web Container
------------------
This reference project creates a WAR file was implemented using 
Apache Tomcat 5.5. For the initial development of this reference
project, a plain-vanilla installation of Tomcat 5.5.17 was used
with all examples removed. 
