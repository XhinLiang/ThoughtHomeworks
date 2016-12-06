package com.xhinliang.thoughthomeworks;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xhinliang xhinliang@gmail.com
 */
public class Test {
    private BufferedReader reader;

    @org.junit.Before
    public void setUp() {
        reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data.txt")));
    }

    @org.junit.Test
    public void testInput() {
        String line;
        List<Talk> talks = new LinkedList<>();
        try {
            while ((line = reader.readLine()) != null) {
                String[] tempArray = line.split(" ");
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
                Assert.assertNotNull(talks);
            }
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
        List<Track> tracks = Conference.buildTrackList(talks);
        Assert.assertNotNull(tracks);
        tracks.forEach(Assert::assertNotNull);
    }

}
