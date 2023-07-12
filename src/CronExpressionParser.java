import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class CronExpressionParser {
    private static final int[] MIN_VALUES = {0, 0, 1, 1, 0};
    private static final int[] MAX_VALUES = {59, 23, 31, 12, 7};
    private static final String[] FIELD_NAMES = {"minute", "hour", "day of month", "month", "day of week", "command"};

    private static List<Integer> expandField(String field, int fieldId) {
        int minValue = MIN_VALUES[fieldId];
        int maxValue = MAX_VALUES[fieldId];
        List<Integer> values = new ArrayList<>();
        try {
            if (field.equals("*")) {
                for (int i = minValue; i <= maxValue; i++) {
                    values.add(i);
                }
            } else if (field.contains(",")) {
                String[] subFields = field.split(",");
                for (String subField : subFields) {
                    values.addAll(expandField(subField, fieldId));
                }
            } else if (field.contains("-")) {
                String[] range = field.split("-");
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);
                validateFieldValue(start, fieldId);
                validateFieldValue(end, fieldId);

                for (int i = start; i <= end; i++) {
                    if (i >= minValue && i <= maxValue) {
                        values.add(i);
                    }
                }
            } else if (field.contains("/")) {
                String[] step = field.split("/");
                String subField = step[0];
                int interval = Integer.parseInt(step[1]);
                List<Integer> subFieldValues = expandField(String.valueOf(subField), fieldId);
                for (int i = 0; i < subFieldValues.size(); i += interval) {
                    if (i % interval == 0) {
                        values.add(subFieldValues.get(i));
                    }
                }
            } else {
                Integer fieldValue = Integer.parseInt(field);
                validateFieldValue(fieldValue, fieldId);
                values.add(fieldValue);
            }
            return values;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing Value for Field \"" + FIELD_NAMES[fieldId] + "\"");
        }
    }

    private static void validateFieldValue(Integer fieldValue, int fieldId) {
        int minValue = MIN_VALUES[fieldId];
        int maxValue = MAX_VALUES[fieldId];
        if (fieldValue < minValue || fieldValue > maxValue) {
            throw new IllegalArgumentException("Value for Field \"" + FIELD_NAMES[fieldId] + "\" is not in range. Field Value ranges from " + minValue + " to " + maxValue);
        }
    }

    public List<String> parseCronString(String cronString) {
        String[] cronFields = cronString.split(" ");
        validateCronFields(cronFields);

        List<String> expandedFields = new ArrayList<>();
        for (int i = 0; i < cronFields.length; i++) {
            if (i == 5) {
                expandedFields.add(cronFields[i]);
            } else {
                List<Integer> expandedField = expandField(cronFields[i], i);
                TreeSet<Integer> expandedFieldSet = new TreeSet<>(expandedField);
                expandedFields.add(String.join(" ", expandedFieldSet.toString()));
            }
        }
        return expandedFields;
    }

    private void validateCronFields(String[] cronFields) {
        if (cronFields.length != 6) {
            throw new IllegalArgumentException("Cron String passed in not Valid");
        }
    }

}
