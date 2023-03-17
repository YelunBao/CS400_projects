run: FlightPlanFrontend.class
	java FlightPlanFrontend

FlightPlanFrontend.class: FlightPlanFrontend.java
	javac FlightPlanFrontend.java

runTests: runBackendDeveloperTests runFrontendDeveloperTests

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java
	javac -cp .:junit5.jar BackendDeveloperTests.java

runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java

clean:
	rm *.class

