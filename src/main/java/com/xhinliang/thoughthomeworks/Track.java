package com.xhinliang.thoughthomeworks;

import java.util.List;

/**
 * @author xhinliang xhinliang@gmail.com
 */
public class Track {
    private int timeHour = 9;
    private int timeMin = 0;
    private boolean isPM = false;

    private List<Talk> morningTalks;
    private List<Talk> afternoonTalks;


    Track(List<Talk> morningTalks) {
        this.morningTalks = morningTalks;
    }

    void setAfternoonTalks(List<Talk> afternoonTalks) {
        this.afternoonTalks = afternoonTalks;
    }

    private boolean canAddAfternoonTalk(Talk talk) {
        int totalAfternoonTime = geTotalAfternoonTime();
        return totalAfternoonTime + talk.getTime() <= Conference.CONST_MAX_AFTERNOON_MINUTES;
    }

    private int geTotalAfternoonTime() {
        int totalAfternoonTime = 0;
        for (Talk afternoonTalk : afternoonTalks) {
            totalAfternoonTime += afternoonTalk.getTime();
        }
        return totalAfternoonTime;
    }

    boolean addAfternoonTalk(Talk talk) {
        if (canAddAfternoonTalk(talk)) {
            this.afternoonTalks.add(talk);
            return true;
        }
        return false;
    }

    List<Talk> getMorningTalks() {
        return morningTalks;
    }

    boolean isComplete() {
        return afternoonTalks != null;
    }


    private void printTime() {
        if (isPM) {
            System.out.printf("%02d:%02dPM ", timeHour, timeMin);
            return;
        }
        System.out.printf("%02d:%02dAM ", timeHour, timeMin);
    }

    private void addTime(Talk talk) {
        timeMin += talk.getTime();
        if (timeMin < 60) {
            return;
        }
        ++timeHour;
        timeMin -= 60;
    }

    public void print() {
        for (Talk talk : morningTalks) {
            printTime();
            System.out.println(talk.toString());
            addTime(talk);
        }
        System.out.println("12:00PM Lunch");
        timeHour = 13;
        timeMin = 0;
        isPM = true;
        for (Talk talk : afternoonTalks) {
            printTime();
            System.out.println(talk.toString());
            addTime(talk);
        }
        printTime();
        System.out.println("Networking Event");
    }

}
