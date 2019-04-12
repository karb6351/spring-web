package project;

import org.springframework.ui.ModelMap;

public class Helper {
    public static String[] splitStringToArray(String string){
        System.out.println(string.substring(1, string.length() - 1));
        String[] test = string.substring(1, string.length() - 1).split(", ");
        for(String temp: test){
            System.out.println(temp);
        }
        return test;
    }
}
