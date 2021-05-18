# Word square solver app
Built using Spring Boot. Java version 1.16.

The `com.code.challenge.dictionary-file` variable must be set to the location of a file containing valid words separated
by line. This can be set in the application.yaml file.

Usage: `mvn spring-boot:run -Dspring-boot.run.arguments="[WORD-LENGTH] [LETTERS]"`

Where `[WORD-LENGTH]` is an integer and `[LETTERS]` is a string of letters. The number of letters should equal the
square of `[WORD-LENGTH]`.

