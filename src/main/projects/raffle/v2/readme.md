# Raffle V2, Project Info
---
A command-line applet written in Java that distributes a prize pool off to players.
The prizes should be split as evenly but also randomly as possible.

> Author: Omgitskuei
> 
> Last Updated: 2022/02/06
> 
> Status: Active Development

### Problem
---
Annually, my Guild Wars 2 raiding party holds two raffles for our members. This is an initiative I started to 
encourage players to play with the raid group long-term, and this applies to both core members of the group as
well as players that 'fill' (come along and cover squad openings on short notice if core members do not show
to raid). The Raffle should also motivate players to play better during raids because the prize pool grows
when players commit mistakes. This has the added benefit that when mistakes happen, others who did not commit
the mistake are indirectly compensated through the raffle for their time.

Distributing the prize pool by hand can lead to complications and can be time-consuming. 
This script will help to distribute the prize quickly and fairly.

### Goal
---
Design a script that distributes a static prize pool to a static group of players in a way where each player
gets something, but each player may get different amounts of the prize and may receive different prizes.
This way, everyone 'wins' but a few 'lucky' players will 'win' more.

### Scope
---
The program should present the result of the distribution in console when run, if this config is turned on.
The program should take text files so the prize pool, the players pool, and configuration may be variable.
The program should allow configurations like;
- debug mode, 
- verbose, 
- how many extra times will randomization occur, 
- whether everyone wins, 
- etc.

### Future design spaces
---
The program can be expanded from a command-line app to a GUI app.

### Change logs
---
| Date | Description |
| ------ | ------ |
| 2021/02/06 | Created the first commit of V2. |

### Running the program
---
Raffle V2 requires JVM and/or Java installed. Java version should be compatible with 9 or newer.
```console
cd <your path to Raffle.java>
javac Raffle.java
```
Or run from an IDE such as Eclipse.
