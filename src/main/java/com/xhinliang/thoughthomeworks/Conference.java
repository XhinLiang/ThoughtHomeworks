package com.xhinliang.thoughthomeworks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xhinliang xhinliang@gmail.com
 */
public class Conference {

    private static final int CONST_MORNING_MINUTES = (12 - 9) * 60;
    private static final int CONST_MIN_AFTERNOON_MINUTES = (16 - 13) * 60;
    static final int CONST_MAX_AFTERNOON_MINUTES = (17 - 13) * 60;
    private static final int CONST_MIN_DAY_MINUTES = CONST_MORNING_MINUTES + CONST_MIN_AFTERNOON_MINUTES;
    private static final int CONST_MAX_DAY_MINUTES = CONST_MORNING_MINUTES + CONST_MAX_AFTERNOON_MINUTES;

    private List<Talk> talks;
    private int maxTrackSum = -1;
    private int minTrackSum = -1;

    private Conference(List<Talk> talks) {
        this.talks = talks;
        this.initMaxTrackSum();
        this.initMinTrackSum();
        Collections.sort(this.talks, (o1, o2) -> {
            if (o1.getTime() > o2.getTime())
                return 1;
            if (o2.getTime() > o1.getTime())
                return -1;
            return 0;
        });
    }

    public static List<Track> buildTrackList(List<Talk> talks) {
        Conference conference = new Conference(talks);
        List<Track> tracks = conference.getMorningTracks();
        conference.buildAfternoonTracks(tracks);
        return tracks;
    }

    private void buildAfternoonTracks(List<Track> morningTracks) {
        while (true) {
            if (this.talks.size() == 0) {
                boolean isComplete = true;
                for (Track track : morningTracks) {
                    if (!track.isComplete())
                        isComplete = false;
                }
                if (isComplete) {
                    return;
                }
                Track extraTrack = morningTracks.remove(morningTracks.size() - 1);
                talks.addAll(extraTrack.getMorningTalks());
            }
            for (Track track : morningTracks) {
                if (!track.isComplete()) {
                    track.setAfternoonTalks(getAfternoonTalks());
                }
            }
            Talk remainTalk = talks.remove(0);
            for (Track track : morningTracks) {
                if (track.addAfternoonTalk(remainTalk))
                    break;
            }
        }
    }

    private List<Track> getMorningTracks() {
        List<Track> tracks = new LinkedList<>();
        for (int i = 0; i < this.maxTrackSum; ++i) {
            int size = talks.size();
            List<Talk> tempTalks = getMorningTalkExec(new boolean[size], size - 1, 0);
            if (tempTalks == null)
                break;
            tracks.add(new Track(tempTalks));
        }
        return tracks;
    }

    private List<Talk> getAfternoonTalks() {
        int size = talks.size();
        return getAfternoonTalksExec(new boolean[size], size - 1, 0);
    }

    private List<Talk> getAfternoonTalksExec(boolean[] selectArray, int currentIndex, int currentSum) {
        int tempSum = currentSum + talks.get(currentIndex).getTime();
        if (tempSum <= CONST_MAX_AFTERNOON_MINUTES && tempSum >= CONST_MIN_AFTERNOON_MINUTES) {
            return getTalks(selectArray, currentIndex);
        }
        if (currentIndex == 0) {
            return null;
        }
        if (currentSum + talks.get(currentIndex).getTime() + talks.get(currentIndex - 1).getTime() <= CONST_MAX_AFTERNOON_MINUTES) {
            selectArray[currentIndex] = true;
            List<Talk> temp = getAfternoonTalksExec(selectArray, currentIndex - 1, currentSum + talks.get(currentIndex).getTime());
            if (temp != null) {
                return temp;
            }
        }
        if (currentSum + talks.get(currentIndex - 1).getTime() <= CONST_MAX_AFTERNOON_MINUTES) {
            List<Talk> temp = getAfternoonTalksExec(selectArray, currentIndex - 1, currentSum);
            if (temp != null) {
                return temp;
            }
            return null;
        }
        return null;
    }

    private List<Talk> getMorningTalkExec(boolean[] selectArray, int currentIndex, int currentSum) {
        if (currentSum + talks.get(currentIndex).getTime() == CONST_MORNING_MINUTES) {
            return getTalks(selectArray, currentIndex);
        }
        if (currentIndex == 0) {
            return null;
        }
        if (currentSum + talks.get(currentIndex).getTime() + talks.get(currentIndex - 1).getTime() <= CONST_MORNING_MINUTES) {
            selectArray[currentIndex] = true;
            List<Talk> temp = getMorningTalkExec(selectArray, currentIndex - 1, currentSum + talks.get(currentIndex).getTime());
            if (temp != null) {
                return temp;
            }
        }
        if (currentSum + talks.get(currentIndex - 1).getTime() <= CONST_MORNING_MINUTES) {
            List<Talk> temp = getMorningTalkExec(selectArray, currentIndex - 1, currentSum);
            if (temp != null) {
                return temp;
            }
        }
        return null;
    }

    private List<Talk> getTalks(boolean[] selectArray, int currentIndex) {
        selectArray[currentIndex] = true;
        List<Talk> tempTalks = new LinkedList<>();
        for (int i = selectArray.length - 1; i >= currentIndex; --i) {
            if (selectArray[i]) {
                tempTalks.add(talks.get(i));
                talks.remove(i);
            }
        }
        return tempTalks;
    }

    private void initMaxTrackSum() {
        if (maxTrackSum <= 0) {
            int totalTime = getTotalTime();
            maxTrackSum = totalTime / CONST_MIN_DAY_MINUTES;
        }
    }

    private void initMinTrackSum() {
        if (minTrackSum <= 0) {
            int totalTime = getTotalTime();
            minTrackSum = totalTime / CONST_MAX_DAY_MINUTES;
        }
    }

    private int getTotalTime() {
        int total = 0;
        for (Talk talk : talks) {
            total += talk.getTime();
        }
        return total;
    }
}
