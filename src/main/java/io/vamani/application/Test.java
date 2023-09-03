package io.vamani.application;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test{
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            System.out.println(simpleDateFormat.parse("02-Apr-2022"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, String> maps = new HashMap<>();
        maps.put("A", "A");
        maps.put("B", "B");
        maps.put("C", "C");

        for(String key : maps.keySet()) {
            System.out.println(key  +" : " + maps.get(key));
        }
    }
}
