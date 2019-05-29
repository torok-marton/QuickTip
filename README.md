# QuickTip
Generate random numbers based on configuration.

## Building from source
Build with Maven, eg.:
```sh
$ mvn install
```

## Configuration
Run the generated jar with setting the 'path' VM variable to the relative location of the configuration XML file:
```sh
$ java -Dpath="parameters.xml" -jar quicktip-1.0-SNAPSHOT-jar-with-dependencies.jar
```
Example configuration XML:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<parameters>
	<algorithm>3</algorithm>
	<piecesOfSheets>10</piecesOfSheets>
	<piecesOfRandomNumbers>5</piecesOfRandomNumbers>
	<maxRandomNumber>90</maxRandomNumber>
</parameters>
```

