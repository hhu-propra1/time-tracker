# What's the time-tracker?
The time-tracker is a nifty little tool to track the time you spent on your courses.


## Why does the time-tracker exist?
This project was created as part of a live stream, to give students an insight into working with Git and more.


## How do I use the time-tracker?
First you have to create a shadow jar with the imported gradle plugin via tasks > shadow > shadowJar.

After that you direct your terminal to the directory of the shadow jar (it's in time-tracker/build/libs by default) and use the time-tracker with the following commands.

To add an entry to your tracked events, use

`java -jar SHADOW_JAR_FILE_NAME.jar -a <YYYY-MM-DD>,<minutes>,<project>,"<description>"`
To sum all the time spent with those events and print it to the console, use

`java -jar SHADOW_JAR_FILE_NAME.jar -s`

To sum up the time spent on a particular event and print it to the console, use

`java -jar SHADOW_JAR_FILE_NAME.jar --sumof <project>`

To get help for all available functions, use

`java -jar SHADOW_JAR_FILE_NAME.jar -h`

To print all entries to the console as a table, use

`java -jar SHADOW_JAR_FILE_NAME.jar -t`

## Development

This is a gradle project. Check out the repository and configure it in your IDE as a gradle project. We are using the latest LTS JDK, which is currently version 17.

### How do I contribute?

Check for [open issues](https://github.com/hhu-propra1/time-tracker/issues), select one you want to solve, [fork](https://github.com/hhu-propra1/time-tracker/fork) the project, fix the issue and send a pull request!

You can also create your own issues and tell us what needs to be fixed or added.

### Distributing the application

Build a jar with this command:

    ./gradlew shadowJar

Native builds can be achieved using this command:

    ./gradlew nativeBuild

Keep in mind, that you need a valid graalvm-installation on your machine for this command.

## License
This project uses the [MIT License](https://github.com/hhu-propra1/time-tracker/blob/main/LICENSE).
