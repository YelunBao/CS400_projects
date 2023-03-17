run: IterableMap.class BookMapperFrontend.class BookMapperBackend.class BookLoader.class ISBNValidator.class BookMapper.class
	java BookMapper
#FD
BookMapperFrontend.class: BookMapperFrontend.java
	javac BookMapperFrontend.java
#BD
BookMapperBackend.class: BookMapperBackend.java
	javac BookMapperBackend.java
BookMapper.class: BookMapper.java
	javac BookMapper.java
#AE
IterableMap.class: IterableMap.java
	javac IterableMap.java
ISBNValidator.class: ISBNValidator.java
	javac ISBNValidator.java
#DW (the code is done by BD)
BookLoader.class: BookLoader.java
	javac BookLoader.java



runTests: runFrontendTests.class runAlgorithmEngineerTests.class runBackendDeveloperTests.class
	java runFrontendTests
	java runAlgorithmEngineerTests
	java runBackendDeveloperTests

runFrontendTests.class: runFrontendTests.java
	javac runFrontendTests.java
runAlgorithmEngineerTests.class: runAlgorithmEngineerTests.java
	javac runAlgorithmEngineerTests.java
runBackendDeveloperTests.class: runBackendDeveloperTests.java
	javac runBackendDeveloperTests.java

clean:
	rm *.class;
