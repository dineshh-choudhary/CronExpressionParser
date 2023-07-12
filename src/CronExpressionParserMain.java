import java.util.List;
import java.util.Scanner;

public class CronExpressionParserMain {
    public static void main(String[] args) {
        String cronString = null;
        if (args.length < 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Cron String: ");
            cronString = scanner.nextLine();
        }

//        cronString = "*/15 0,23 1-5,30-10 7 1-7 /usr/bin/find";
        try {
            CronExpressionParser cronExpressionParser = new CronExpressionParser();
            List<String> expandedFields = cronExpressionParser.parseCronString(cronString);
            printOutput(expandedFields);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void printOutput(List<String> values) {
        List<String> fieldNames = List.of("minute", "hour", "day of month", "month", "day of week", "command");
        for (int i = 0; i < fieldNames.size(); i++) {
            String fieldName = fieldNames.get(i);
            System.out.printf("%-14s%s%n", fieldName, values.get(i));
        }
    }

}
