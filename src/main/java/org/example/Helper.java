package org.example;
import java.nio.charset.StandardCharsets;
public class Helper{
    public static String convertToUTF8(String text){
        byte[] utf8Bytes = text.getBytes(StandardCharsets.UTF_8);
        String utf8String = new String(utf8Bytes, StandardCharsets.UTF_8);
        return utf8String;
    }
}