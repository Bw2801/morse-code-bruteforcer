package de.bwueller.morse.code.bruteforcer;

import java.util.Arrays;

public class MorseCodeBruteforcer {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: <program> <pattern>");
            return;
        }
        
        findMatches(args[0]);
    }

    private static final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
        "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static final String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
        "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
        "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."};

    private static void findMatches(String pattern) {
        System.out.println("Searching for matching patterns for [" + pattern + "]");
        bruteforce("", pattern);
    }

    private static void bruteforce(String prefix, String pattern) {
        String currentMorseCode = toMorseCode(prefix).replaceAll(" ", "");
        
        if (!pattern.startsWith(currentMorseCode)) return;
        if (currentMorseCode.length() >= pattern.length()) return;
            
        for (String alphabet1 : alphabet) {
            testForMatch(prefix + alphabet1, pattern);
        }

        for (String alphabet1 : alphabet) {
            if (prefix.length() >= pattern.length()) {
                continue;
            }
            bruteforce(prefix + alphabet1, pattern);
        }
    }

    private static void testForMatch(String string, String pattern) {
        String morseCode = toMorseCode(string);
        if (!morseCode.replaceAll(" ", "").equals(pattern)) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append(" [");
        sb.append(morseCode);
        sb.append("]");
        System.out.println(sb.toString());
    }

    private static String toMorseCode(String string) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            int index = Arrays.asList(alphabet).indexOf("" + string.charAt(i));
            sb.append(morse[index]);
            sb.append(" ");
        }

        return sb.toString().trim();
    }
}
