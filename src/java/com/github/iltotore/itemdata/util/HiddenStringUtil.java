package com.github.iltotore.itemdata.util;

import org.bukkit.ChatColor;

import java.nio.charset.StandardCharsets;

public class HiddenStringUtil {

    // String constants. TODO Change them to something unique to avoid conflict with other plugins!
    private static final String SEQUENCE_HEADER = "" + ChatColor.RESET + ChatColor.UNDERLINE + ChatColor.RESET;
    private static final String SEQUENCE_FOOTER = "" + ChatColor.RESET + ChatColor.ITALIC + ChatColor.RESET;


    public static String encodeString(String hiddenString) {
        return quote(stringToColors(hiddenString));
    }

    public static boolean hasHiddenString(String input) {
        if (input == null) {
            return false;
        }
        return input.contains(SEQUENCE_HEADER) && input.contains(SEQUENCE_FOOTER);
    }

    public static String extractHiddenString(String input) {
        return colorsToString(extract(input));
    }

    public static String replaceHiddenString(String input, String hiddenString) {
        if (input == null) {
            return null;
        }

        int start = input.indexOf(SEQUENCE_HEADER);
        int end = input.indexOf(SEQUENCE_FOOTER);

        if (start < 0 || end < 0) {
            return null;
        }

        return input.substring(0, start + SEQUENCE_HEADER.length()) + stringToColors(hiddenString) + input.substring(end);
    }

    public static String removeHiddenString(String input) {
        return input.replaceAll(SEQUENCE_HEADER + ".*" + SEQUENCE_FOOTER, "");
    }

    /**
     * Internal stuff.
     */
    private static String quote(String input) {
        if (input == null) {
            return null;
        }
        return SEQUENCE_HEADER + input + SEQUENCE_FOOTER;
    }

    private static String extract(String input) {
        if (input == null) {
            return null;
        }

        int start = input.indexOf(SEQUENCE_HEADER);
        int end = input.indexOf(SEQUENCE_FOOTER);

        if (start < 0 || end < 0) {
            return null;
        }

        return input.substring(start + SEQUENCE_HEADER.length(), end);
    }

    private static String stringToColors(String normal) {
        if (normal == null) {
            return null;
        }

        byte[] bytes = normal.getBytes(StandardCharsets.UTF_8);
        char[] chars = new char[bytes.length * 4];

        for (int i = 0; i < bytes.length; i++) {
            char[] hex = byteToHex(bytes[i]);
            int index = i * 4;
            chars[index] = ChatColor.COLOR_CHAR;
            chars[index + 1] = hex[0];
            chars[index + 2] = ChatColor.COLOR_CHAR;
            chars[index + 3] = hex[1];
        }

        return new String(chars);
    }

    private static String colorsToString(String colors) {
        if (colors == null) {
            return null;
        }

        colors = colors.toLowerCase().replace(String.valueOf(ChatColor.COLOR_CHAR), "");

        if (colors.length() % 2 != 0) {
            colors = colors.substring(0, (colors.length() / 2) * 2);
        }

        char[] chars = colors.toCharArray();
        byte[] bytes = new byte[chars.length / 2];

        for (int i = 0; i < chars.length; i += 2) {
            bytes[i / 2] = hexToByte(chars[i], chars[i + 1]);
        }

        return new String(bytes, StandardCharsets.UTF_8);
    }

    private static int hexToUnsignedInt(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        } else if (c >= 'a' && c <= 'f') {
            return c - 87;
        }
        throw new IllegalArgumentException("Invalid hex char: out of range");
    }

    private static char unsignedIntToHex(int i) {
        if (i >= 0 && i <= 9) {
            return (char) (i + 48);
        } else if (i >= 10 && i <= 15) {
            return (char) (i + 87);
        }
        throw new IllegalArgumentException("Invalid hex int: out of range");
    }

    private static byte hexToByte(char hex1, char hex0) {
        return (byte) (((hexToUnsignedInt(hex1) << 4) | hexToUnsignedInt(hex0)) + Byte.MIN_VALUE);
    }

    private static char[] byteToHex(byte b) {
        int unsignedByte = (int) b - Byte.MIN_VALUE;
        return new char[]{unsignedIntToHex((unsignedByte >> 4) & 0xf), unsignedIntToHex(unsignedByte & 0xf)};
    }
}
