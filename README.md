# Cron Expression Parser

The Cron Expression Parser is a Java program that parses and expands a cron expression. It takes a cron string as input and expands it to provide the values for the minute, hour, day of month, month, day of week, and command fields. The expanded fields are then printed as output.

## About Cron Expressions

A valid cron expression consists of six space-separated fields representing different aspects of time. Here's an overview of the format and allowed values for each field:

    Minute (0-59): The minute field specifies the minute of the hour. Valid values range from 0 to 59.

    Hour (0-23): The hour field specifies the hour of the day. Valid values range from 0 to 23.

    Day of Month (1-31): The day of month field specifies the day of the month. Valid values range from 1 to 31.

    Month (1-12): The month field specifies the month of the year. Valid values range from 1 to 12.

    Day of Week (0-7): The day of week field specifies the day of the week. Both 0 and 7 represent Sunday. Valid values range from 0 to 7.

    Command: The command field represents the command or script to be executed.

Each field can be specified using various patterns:

    An asterisk () represents all possible values for that field. For example, "*" in the minute field means every minute.

    A comma-separated list represents individual values or a set of values. For example, "1,15,30" in the minute field means the 1st, 15th, and 30th minutes.

    A hyphen (-) represents a range of values. For example, "1-5" in the day of month field means the 1st to 5th days of the month.

    A forward slash (/) represents increments or steps. For example, "*/15" in the minute field means every 15 minutes.

For more detailed information on cron expressions, refer to the documentation or online resources specific to your platform or cron implementation.

## Getting Started

To use the Cron Expression Parser, follow these steps:

1. Ensure you have Java installed on your system. You can check by running the following command in the terminal or command prompt:
   ```
   java -version
   ```
   If Java is not installed, download and install it from the official Oracle website: [Java SE Downloads](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

2. Open a terminal or command prompt and navigate to the directory where the source code is located.

3. Compile the Java source files by running the following command:
   ```
   javac CronExpressionParserMain.java
   ```

4. Run the program using the following command:
   ```
   java CronExpressionParserMain */15 0,23 1-5 7 1,7 /usr/bin/find
   ```
   or
   ```
   java CronExpressionParserMain
   ```
5. If no command-line arguments are provided, the program will prompt you to enter a cron string via the console.

6. The program will parse the cron string, expand the fields, and print the output showing the values for each field.

## Example

Here's an example of how the Cron Expression Parser can be used:

```
java CronExpressionParserMain */15 0,23 1-5 7 1,7 /usr/bin/find
```

Output:
```
minute        [0, 15, 30, 45]
hour          [0, 23]
day of month  [1, 2, 3, 4, 5]
month         [7]
day of week   [1, 7]
command       /usr/bin/find
```

## Troubleshooting

- If you encounter an `IllegalArgumentException`, then please ensure that the cron string you provide follows the correct format. Refer to the cron expression documentation for more details on how to format the string correctly.

## Contributing

Contributions to the Cron Expression Parser are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

The Cron Expression Parser is licensed under the [MIT License](LICENSE).

---

Feel free to customize the README.md file based on your specific needs or add additional sections as necessary.