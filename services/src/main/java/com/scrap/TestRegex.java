package com.scrap;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {



    public static void main(String[] args) throws  Exception{
               while (true) {


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("%nEnter your regex: ");
            Pattern pattern =
                    Pattern.compile(br.readLine());

            System.out.format("Enter input string to search: ");
            Matcher matcher =
                    pattern.matcher(br.readLine());

            boolean found = false;
            while (matcher.find()) {
                System.out.printf(
                        String.format("I found the text" +
                                " \"%s\" starting at " +
                                "index %d and ending at index %d.%n",
                        matcher.group(),
                        matcher.start(),
                        matcher.end()));
                found = true;
            }
            if (!found) {
                System.out.printf(String.format("No match found.%n"));
            }
        }


    }

}
