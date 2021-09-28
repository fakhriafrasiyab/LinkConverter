package com.example.linkconverter;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MatchingParameters {

    public Optional<String> matchParam(String source, String paramName) {
        Pattern logEntry = Pattern.compile(paramName + "=(.*?)(&|$)");

        Matcher matchPattern = logEntry.matcher(source);

        while (matchPattern.find()) {
            return Optional.of(matchPattern.group(1));
        }
        return Optional.empty();
    }

    public Optional<String> matchContentId(String source, String paramName) {
        Pattern logEntry = Pattern.compile(paramName + "(\\d+)");

        Matcher matchPattern = logEntry.matcher(source);

        while (matchPattern.find()) {
            return Optional.of(matchPattern.group(1));
        }
        return Optional.empty();
    }
}
