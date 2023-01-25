package br.ufsm.elc1071.startthecount.util;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.regex.Pattern;

public class StringUtil {

    private static final Pattern IS_NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static final Integer BASE_10_RADIX = 10;

    public static Integer toInteger(@NonNull String string) {
        return Integer.parseInt(string, BASE_10_RADIX);
    }

    public static boolean isNumeric(@NonNull String string) {
       return IS_NUMERIC_PATTERN.matcher(string).matches();
    }

    public static LocalDate toLocalDate(String date, String format) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static LocalTime toLocalTime(String time, String format) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(format));
    }
}
