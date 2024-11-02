package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;

public class StringToList {

    public List<Integer> stringToListOfInteger(String input) {
        // Split the string based on the delimiter
        String[] strArray = input.split(",");
        
        // Create a list to hold the integers
        List<Integer> intList = new ArrayList<>();
        
        // Parse each string element to an integer and add to the list
        for (String str : strArray) {
            try {
                intList.add(Integer.parseInt(str.trim()));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid integer value: " + str);
            }
        }
        
        return intList;
    }
    
}
