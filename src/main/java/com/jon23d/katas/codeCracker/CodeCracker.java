package com.jon23d.katas.codeCracker;

/**
 * This code is meant to solve the problem described at https://codingdojo.org/kata/CodeCracker/
 */
public class CodeCracker {

    private final String encodeKey = "abcdefghijklmnopqrstuvwxyz";
    private final String decodeKey = "!)\"(£*%&><@abcdefghijklmno";

    public String encode(String input) throws UnencodableException {
        return transform(input, encodeKey, decodeKey);
    }

    public String decode(String input) {
        return transform(input, decodeKey, encodeKey);
    }

    private String transform(String input, String from, String to) {
        StringBuilder retval = new StringBuilder();

        input.chars()
                .mapToObj(c -> (char) c)
                .map(c -> to.charAt(getIndexOfCharOrThrow(from, c)))
                .forEach(retval::append);

        return retval.toString();
    }

    private int getIndexOfCharOrThrow(String key, char c) {
        int index = key.indexOf(c);
        if (index == -1) {
            throw new UnencodableException("Input is insane");
        }
        return index;
    }
}
