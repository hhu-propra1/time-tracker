# What's the time-tracker?
The time-tracker is a nifty little tool to track the time you spent on your courses.


## Why does the time-tracker exist?
This project was created as part of a live stream, to give students an insight into working with Git and more.


## How do I use the time-tracker?
First you have to create a shadow jar with the imported gradle plugin via tasks > shadow > shadowJar.

After that you direct your terminal to the directory of the shadow jar (it's in time-tracker/build/libs by default) and use the time-tracker with the following commands.

To add an entry to your tracked events, use

`java -jar SHADOW_JAR_FILE_NAME.jar -a <YYYY-MM-DD*>,<minutes>,<project>,"<description>"`

_*(or use "today" instead of "YYYY-MM-DD" to use current date)_
 
To sum all the time spent with those events and print it to the console, use

`java -jar SHADOW_JAR_FILE_NAME.jar -s`

To sum up the time spent on a particular event and print it to the console, use

`java -jar SHADOW_JAR_FILE_NAME.jar --sumof <project>`

To get help for all available functions, use

`java -jar SHADOW_JAR_FILE_NAME.jar -h`

To print all entries to the console as a table, use

`java -jar SHADOW_JAR_FILE_NAME.jar -t`


## How do I contribute?
First, please make sure to follow the [Code of Conduct](https://github.com/hhu-propra1/time-tracker/blob/main/CODE_OF_CONDUCT.md) if you want to contribute.

Check for [open issues](https://github.com/hhu-propra1/time-tracker/issues), select one you want to solve, [fork](https://github.com/hhu-propra1/time-tracker/fork) the project, fix the issue and send a pull request!

You can also create your own issues and tell us what needs to be fixed or added.


## License
This project uses the [MIT License](https://github.com/hhu-propra1/time-tracker/blob/main/LICENSE).