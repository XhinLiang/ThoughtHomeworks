package com.xhinliang.thoughthomeworks;

import com.xhinliang.thoughthomeworks.i.C;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author xhinliang xhinliang@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(C.a(args[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void mainef(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Talk> talks = new LinkedList<>();
        while (in.hasNextLine()) {
            String input = in.nextLine();
            String[] tempArray = input.split(" ");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < tempArray.length - 1; ++i) {
                builder.append(tempArray[i]);
                builder.append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
            String timeCost = tempArray[tempArray.length - 1];
            if (timeCost.endsWith("min")) {
                Talk newTalk = new Talk(builder.toString(), Integer.parseInt(timeCost.replace("min", "")));
                talks.add(newTalk);
                continue;
            }
            talks.add(new Talk(builder.toString()));
        }
        List<Track> tracks = Conference.buildTrackList(talks);
        for (int i = 0; i < tracks.size(); ++i) {
            System.out.printf("Track %d:\n", i + 1);
            tracks.get(i).print();
            System.out.println();
        }
    }
}
