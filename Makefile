.PHONY: build clean run initjavadoc javadoc

all: clean build run

build:
	./gradlew build

run:
	./gradlew run

clean:
	./gradlew clean

initjavadoc:
	rm -r documentation/javadoc && mkdir documentation/javadoc && ./gradlew javadoc 2>> /dev/null

javadoc: 
	google-chrome documentation/javadoc/index.html 