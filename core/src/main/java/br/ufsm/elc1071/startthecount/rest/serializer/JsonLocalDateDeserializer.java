package br.ufsm.elc1071.startthecount.rest.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class JsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final String DATE_FORMAT_PATTERN = "dd/MM/yyyy";
    private static final Locale LOCALE = Locale.of("pt", "BR");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN, LOCALE);

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String string = jsonParser.readValueAs(String.class);

        return LocalDate.parse(string, DATE_TIME_FORMATTER);
    }
}
