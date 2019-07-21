package com.toonext.util;


import com.toonext.log.Lg;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern uuidPattern = Pattern.compile("/^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/");
    private static final Map<String, String> collation = new HashMap<String, String>();

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static boolean checkByPattren(String value, String p) {
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }


    public static String getRndText() {
        return genRndText("qwertyuiopasdfghjklzxcvbnm", 10);
    }

    public static String getRndText(int len) {
        return genRndText("qwertyuiopasdfghjklzxcvbnm", len);
    }

    public static String getRndColor() {
        Random rand = new Random();
        int myRandomNumber = rand.nextInt(0x10) + 0x10; // Generates a random number between 0x10 and 0x20
        //System.out.printf("%x\n",myRandomNumber); // Prints it in hex, such as "0x14"
        return Integer.toHexString(myRandomNumber);
    }

    public static String genRndText(String setOfTheLetters, int len) {
        Random r = new Random();
        String key = "";
        char[] letters = new char[setOfTheLetters.length() + 10];

        for (int i = 0; i < 10; i++) {
            letters[i] = Character.forDigit(i, 10);
        }

        for (int i = 0; i < setOfTheLetters.length(); i++) {
            letters[i + 10] = setOfTheLetters.charAt(i);
        }

        for (int i = 0; i < len; i++) {
            key += letters[Math.abs(r.nextInt()) % letters.length];
        }

        return key;
    }

    public static String cleanFromMarkdown(String text) {
        String resultText = text.replace("*", "").replace("**", "").replaceAll("`", "");

        return resultText;
    }

    public static String kindToKeyword(String kind) {
        return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(kind), "_").toLowerCase();
    }



    public static String convertStringToURL(String text) {
        if (Pattern.matches(".*\\p{InCyrillic}.*", text)) {
            return convertRusToLat(text.trim()).replaceAll(" ", "_").toLowerCase();
        } else {
            return text;
        }
    }

    public static String convertRusToLat(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            String l = text.substring(i, i + 1);
            if (collation.containsKey(l)) {
                sb.append(collation.get(l));
            } else {
                sb.append(l);
            }
        }
        return sb.toString();
    }

    public static String readResource(String file) {
        InputStreamReader reader = null;
        try {
            InputStream in = Object.class.getClass().getResourceAsStream(file);
            reader = new InputStreamReader(in);
            String myInputStream = IOUtils.toString(reader);

            return myInputStream;
        } catch (FileNotFoundException e) {
            Lg.exception(e);
        } catch (IOException e) {
            Lg.exception(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Lg.exception(e);
            }
        }
        return "";
    }


}
