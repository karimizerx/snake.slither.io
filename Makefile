.PHONY: build clean run test initjavadoc javadoc

all: clean build run

build:
	./gradlew build

clean:
	./gradlew clean

run:
	./gradlew run

test:
	./gradlew test

initjavadoc:
	rm -r documentation/javadoc && mkdir documentation/javadoc && ./gradlew javadoc 2>> /dev/null

javadoc: 
	google-chrome documentation/javadoc/index.html