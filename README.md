# Kaspersky Password Manager to CSV

When you want to give up from Kaspersky Password Manager, you have a problem. You can export your passwords, but they are not on a standard format. This program is your solution, a converter from the Kaspersky Password Manager export format to a LastPass.

## Features
This program is capable of convert the datas from the *Websites*, *Applications* and *Notes* section.
Convert to the [LastPass Generic CSV format ](https://support.lastpass.com/s/document-item?language=en_US&bundleId=lastpass&topicId=LastPass/import-generic-csv.html&_LANG=enus).

## How to use it
it requires the Java 17 jre.

```
$ java -jar KPMtoLastPass-1.0-SNAPSHOT.jar help
Usage: java -jar KPMtoLastPass.jar <inputFile> [<outputFile>]
    - inputFile: is the KasperskyPasswordManager file with the exported data
    - outputFile: is the file that will be used on the LastPass
   or: java -jar KPMtoLastPass.jar help

If no outputFile is specified, the default is 'output.csv'.
Example: java -jar KPMtoLastPass.jar input.txt output.csv

```