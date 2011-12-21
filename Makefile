# Makefile by kidanger
JAVA_FILES=$(wildcard */*.java)
JAVA_FILES+=$(wildcard */*/*.java)
JAVA_FILES+=$(wildcard */*/*/*.java)
JAVA_FILES+=$(wildcard */*/*/*/*.java)
JAVA_FILES+=$(wildcard */*/*/*/*/*.java)


CLASS_FILES=$(patsubst %.java,%.class,$(JAVA_FILES))

JAVAC_OPTIONS=

ARGS=

all: build

%.class: %.java
	javac $(JAVAC_OPTIONS) $<

build: ${CLASS_FILES}

run: build
	java $(JAVA_OPTIONS) com.mojang.ld22.Game $(ARGS)

clean:
	-@rm */*.class */*/*.class */*/*/*.class */*/*/*/*.class */*/*/*/*/*.class
	@echo "Class files deleted."

