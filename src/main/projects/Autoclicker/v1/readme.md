# cmdLAutoclicker V.1, Project Info
---
A command-line applet written in Java that automates mouse clicks between randomized intervals with Java Robot.
> Author: Omgitskuei
> 
> Last Updated: 2022/06/16
> 
> Status: Complete

### Problem
---
Computer at work will self-lock after an undetermined duration of inactivity.
Inactivity is defined as no keyboard input as well as no mouse input.
User finds repeatedly unlocking the computer 'inactivity' frustrating,
since these gaps could actually be halfway through reading documentation, etc. 
When locks occur, timestamps are taken by the management software, and these 
periods of inactivity can count negatively towards workplace performance.

### Goal
---
Design a quick-to-configure program that allows users to set up automated mouse clicking with randomized intervals 
in between clicks. This serves to break periods of inactivity and prevent computer from self-locking.
The clicking intervals need to be somewhat random to not arouse suspicion, if activity is monitored by employee
management software.

### Scope
---
The program should be light weight and configurable. No UI is needed to meet user needs.
The program should take minimum and maximum intervals from the user to form a range between which to randomize.
The program should also take the total number of clicks from the user in order to set a limit on how long to run 
the automation for. Because it's a command-line script, user can terminate the program early by Stopping the 
program in Eclipse.

### Future design spaces
---
The program can be expanded to:
- Include GUI so that the program does not need Eclipse to run and stop.

### Change logs
---
| Date | Description |
| ------ | ------ |
| 2021/12/23 | Created the first version of the program |
| 2022/03/10 | Tried to allow minimum and maximum intervals to be the same, so that randomization of intervals is optional. Led to runtime errors. |
| 2022/06/16 | Refactored program moving parts into private methods, and improving readability of output messages like Total Runtime

### Running the program
---
Autoclicker V1 requires JVM and/or Java installed. Java version should be compatible with 9 or newer.
```console
cd <your path to Autoclicker.java>
javac Autoclicker.java
```
Or run from an IDE such as Eclipse.