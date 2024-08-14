package com.jon23d.katas.codeCracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CodeCrackerTest {

    @ParameterizedTest
    @MethodSource("provideEncodeSamples")
    public void encode_encodes_standard_characters(String input, String expected) {
        CodeCracker codeCracker = new CodeCracker();
        var actual = codeCracker.encode(input);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideEncodeSamples() {
        return Stream.of(
                Arguments.of("foo", "*dd"),
                Arguments.of("bar", ")!g"),
                Arguments.of("cde", "\"(£")  // with escaped characters
        );
    }

    @Test
    public void encode_throws_on_nonstandard_characters() {
        CodeCracker codeCracker = new CodeCracker();
        UnencodableException exception = assertThrows(
                UnencodableException.class,
                () -> codeCracker.encode("CAPITALS")
        );

        assertEquals("Input is insane", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideDecodeSamples")
    public void decode_decodes_standard_characters(String input, String expected) {
        CodeCracker codeCracker = new CodeCracker();
        var actual = codeCracker.decode(input);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideDecodeSamples() {
        return Stream.of(
                Arguments.of("onml", "zyxw"),
                Arguments.of("no!)", "yzab"),
                Arguments.of("\"(£", "cde")  // with escaped characters
        );
    }

    @Test
    public void decode_throws_on_nonstandard_characters() {
        CodeCracker codeCracker = new CodeCracker();
        UnencodableException exception = assertThrows(
                UnencodableException.class,
                () -> codeCracker.encode("-{")
        );

        assertEquals("Input is insane", exception.getMessage());
    }
}
