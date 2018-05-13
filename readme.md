selenium + testNG + reportNG

the code in src/main is framework code, usually not need to change.

the code in src/test are page objects and test scripts

page object please look at LoginPage.java.

test script please look at LoginTest.java

please put your testNG config files in config folder.

put your test data in excel file in data folder.

the exe in driver folder usually no need to change, if there is an new version, put it in driver folder and update config/config.properties

you can run with  
~~~
mvn test
~~~

or with
~~~
mvn test -DsuiteXmlFile=config\testNG1.xml
~~~

when test finished,  you can check result at target\surefire-reports
~~~
html folder are for the report
logs folder are for the logs
screenShoot folder are for screenShoot when test success of test failed
~~~
