# ThoughtHomeWorks

## Problem Two: Conference Track Management

#### Description
You are planning a big programming conference and have received many proposals which have passed the initial screen process but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities! So you write a program to do it for you.

- The conference has multiple tracks each of which has a morning and afternoon session.
- Each session contains multiple talks.
- Morning sessions begin at 9am and must finish by 12 noon, for lunch.
- Afternoon sessions begin at 1pm and must finish in time for the networking event.
- The networking event can start no earlier than 4:00 and no later than 5:00.
- No talk title has numbers in it.
- All talk lengths are either in minutes (not hours) or lightning (5 minutes).
- Presenters will be very punctual; there needs to be no gap between sessions.

Note that depending on how you choose to complete this problem, your solution may give a different ordering or combination of talks into tracks. This is acceptable; you don’t need to exactly duplicate the sample output given here.

#### Test Input
Writing Fast Tests Against Enterprise Rails 60min
Overdoing it in Python 45min
Lua for the Masses 30min
Ruby Errors from Mismatched Gem Versions 45min
Common Ruby Errors 45min
Rails for Python Developers lightning
Communicating Over Distance 60min
Accounting-Driven Development 45min
Woah 30min
Sit Down and Write 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Ruby on Rails: Why We Should Move On 60min
Clojure Ate Scala (on my project) 45min
Programming in the Boondocks of Seattle 30min
Ruby vs. Clojure for Back-End Development 30min
Ruby on Rails Legacy App Maintenance 60min
A World Without HackerNews 30min
User Interface CSS in Rails Apps 30min

#### Test Output

Track 1:
09:00AM Ruby on Rails Legacy App Maintenance 60min
10:00AM Ruby on Rails: Why We Should Move On 60min
11:00AM Rails Magic 60min
12:00PM Lunch
13:00PM Clojure Ate Scala (on my project) 45min
13:45PM Pair Programming vs Noise 45min
14:30PM Accounting-Driven Development 45min
15:15PM Common Ruby Errors 45min
16:00PM Rails for Python Developers lightning
16:05PM Lua for the Masses 30min
16:35PM Networking Event

Track 2:
09:00AM Communicating Over Distance 60min
10:00AM Writing Fast Tests Against Enterprise Rails 60min
11:00AM User Interface CSS in Rails Apps 30min
11:30AM A World Without HackerNews 30min
12:00PM Lunch
13:00PM Ruby Errors from Mismatched Gem Versions 45min
13:45PM Overdoing it in Python 45min
14:30PM Ruby vs. Clojure for Back-End Development 30min
15:00PM Programming in the Boondocks of Seattle 30min
15:30PM Sit Down and Write 30min
16:00PM Woah 30min
16:30PM Networking Event

### Run Tips
Notice there may be some incompatible property between different environments.
My build environment:
- Intel i5-3380m @2.9GHz
- Ubuntu 14.04 LTS 64bit
- Oracle JDK 8u101 64bit
- Gradle 2.14.1
- JUnit 4.11

#### Install Gradle and Oracle-JDK 8
It's very easy to do this.

#### Build
``` bash
gradle build
```
> The Unit Test will be run when the project is building.
> You can find the test report in `out/reports/tests`

#### Run
**With your manual input.**
``` bash
java -jar out/libs/thoughthomeworks-1.0.jar
```

**With your input text file.**
``` bash
java -jar out/libs/thoughthomeworks-1.0.jar < /path/to/your/file
```


