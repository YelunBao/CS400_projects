run: PhoneBookMapper.class PhoneBookLoader.class PhoneBookFrontend.class Backend.class PhoneBook.class
	java PhoneBookMapper

#Run tests
runTests: runDataWranglerTests runBackendDeveloperTests runFrontendDeveloperTests runAlgorithmEngineerTests

PhoneBookMapper.class: PhoneBookMapper.java
	javac -cp .:junit5.jar PhoneBookMapper.java

############################################################################################################
####### BACKEND DEVELOPER  #################################################################################
############################################################################################################
runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java
	javac -cp .:junit5.jar BackendDeveloperTests.java

Backend.class: Backend.java
	javac -cp .:junit5.jar Backend.java

############################################################################################################
####### DATA WRANGLER  #####################################################################################
############################################################################################################
runDataWranglerTests: DataWranglerTest.class PhoneBook.class PhoneBookLoader.class
	java -jar junit5.jar -cp . --scan-classpath

DataWranglerTest.class: DataWranglerTest.java PhoneBookLoader.class phone_book.xml
	javac -cp .:junit5.jar DataWranglerTest.java

PhoneBookLoader.class: PhoneBookLoader.java PhoneBook.class phone_book.xml
	javac -cp .:junit5.jar PhoneBookLoader.java

PhoneBook.class: PhoneBook.java
	javac -cp .:junit5.jar PhoneBook.java

############################################################################################################
####### FRONTEND DEVELOPER #################################################################################
############################################################################################################
runFrontendDeveloperTests: FrontendDeveloperTests.class PhoneBookFrontend.class BackendHelperFD.class PhoneBook.class test1 test2 test3 test4 test5 PhoneBookLoader.class 
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java

PhoneBookFrontend.class: PhoneBookFrontend.java
	javac PhoneBookFrontend.java

PhoneBookLoader.class: PhoneBookLoader.java
	javac PhoneBookLoader.java

BackendHelperFD.class: BackendHelperFD.java
	javac BackendHelperFD.java

PhoneBookFD.class: PhoneBookFD.java
	javac PhoneBookFD.java

##########################################################################################################
########### ALGORITHM ENGINEER ###########################################################################
##########################################################################################################
runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar --class-path=. --include-classname=.* --select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AE_Validator.class AE_RedBlackTree.class AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

AE_Validator.class: AE_Validator.java
	javac AE_Validator.java

AE_RedBlackTree.class: AE_RedBlackTree.java
	javac AE_RedBlackTree.java

############################################################################################################
####### CLEAN ##############################################################################################
############################################################################################################
clean:
	rm *.class
