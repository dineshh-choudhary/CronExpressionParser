import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CronExpressionParserTest {

    CronExpressionParser cronExpressionParser = new CronExpressionParser();

    @Test
    public void testValidCronExpression() {
        String cronString = "*/15 0,3 1-5 7 1-5 /usr/bin/find";
        List<String> expandedFields = cronExpressionParser.parseCronString(cronString);

        Assertions.assertEquals(6, expandedFields.size());
        Assertions.assertEquals("[0, 15, 30, 45]", expandedFields.get(0));
        Assertions.assertEquals("[0, 3]", expandedFields.get(1));
        Assertions.assertEquals("[1, 2, 3, 4, 5]", expandedFields.get(2));
        Assertions.assertEquals("[7]", expandedFields.get(3));
        Assertions.assertEquals("[1, 2, 3, 4, 5]", expandedFields.get(4));
        Assertions.assertEquals("/usr/bin/find", expandedFields.get(5));
    }

    @Test
    public void testInvalidCronExpression() {
        String cronString = "30 0 1 1 *"; // Missing the command field

        String expectedMessage = "Cron String passed in not Valid";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> cronExpressionParser.parseCronString(cronString));

        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testInvalidCronExpressionWithDoubleValues() {
        String cronString = "*/15 0,3.2 1-5 7 1-5 /usr/bin/find"; // Float Value in Expression

        String expectedMessage = "Error parsing Value for Field \"hour\"";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> cronExpressionParser.parseCronString(cronString));

        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void testInvalidCronExpressionWithInvalidRangeForMinute() {
        String cronString = "0-120 0,3 1-5 7 1-5 /usr/bin/find"; // Minute Value inValid Range

        String expectedMessage = "Value for Field \"minute\" is not in range. Field Value ranges from 0 to 59";
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> cronExpressionParser.parseCronString(cronString));

        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

}
