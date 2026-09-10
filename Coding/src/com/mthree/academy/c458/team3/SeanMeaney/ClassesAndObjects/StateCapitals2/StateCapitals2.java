package com.mthree.academy.c458.team3.SeanMeaney.ClassesAndObjects.StateCapitals2;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class StateCapitals2 {
    public static void main(String[] args) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        Random RNG = new Random();

        Scanner aScanner = new Scanner(
                new BufferedReader(new FileReader("src/com/mthree/academy/c458/team3/SeanMeaney/ClassesAndObjects/StateCapitals2/StateCapitals.txt")));

        int lines = 0;
        while (aScanner.hasNextLine()) {
            lines++;
            UnmarshalLine(map, aScanner.nextLine());
        }
        System.out.println(map.size()+"/"+lines+" STATES & CAPITALS ARE LOADED");
        System.out.println("""
                =========
                HERE ARE THE STATES :
                """+map.keySet().toString().replace("[", "").replace("]", ""));

        int gameIndex = RNG.nextInt(0,map.size());
        String state = map.keySet().toArray()[gameIndex].toString();
        String capital = map.values().toArray()[gameIndex].toString();
        System.out.println("READY TO TEST YOUR KNOWLEDGE? WHAT IS THE CAPITAL OF '"+state+"'?");

        Scanner userScanner = new Scanner(System.in);

        if (userScanner.nextLine().equals(capital)) {
            System.out.println("NICE WORK! "+capital+" IS CORRECT!");
        } else {
            System.out.println("INCORRECT! "+capital+" WAS THE CORRECT ANSWER!");
        }


    }

    private static void UnmarshalLine (HashMap<String,String> map, String line) {
        String[] data = line.split("::");
        map.put(data[0],data[1]);
    }
}

