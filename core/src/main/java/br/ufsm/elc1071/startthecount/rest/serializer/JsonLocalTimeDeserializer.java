package br.ufsm.elc1071.startthecount.rest.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Locale;

public class JsonLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    private static final String TIME_FORMAT_PATTERN = "HH:mm:ss";
    private static final Locale LOCALE = Locale.of("pt", "BR");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT_PATTERN, LOCALE);

    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String string = jsonParser.readValueAs(String.class);

        return LocalTime.parse(string, DATE_TIME_FORMATTER);
    }
}
