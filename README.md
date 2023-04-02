# Skyler's golf betting

## Who, what, when, why?
This program will act as a fake golf league with players in 
tournaments. The interface will allow users to gamble their cash on the outcomes
of the games as well as the individual player performances. This will 
hopefully educate people not to bet their money away.


## User stories
**As a user, I want to...**
- Setup and see result of golf games
- Add golfers to the league
- Add courses to the league
- Make bets on player performances and game outcomes
- Have the option to save my golf betting progress
- Have the option to load my golf betting progress

## Instructions for Grader
**To add Golfers to Golfers**
 - Launch
 - Click "Golfers Menu"
 - You can see the current golfers on the left
 - You may add a new one with a name on the right

**To add Course to Courses**
- Launch
- Click "Courses Menu"
- You can see the current courses on the left
- You may add a new one with a name and a number of holes on the right
**Play a game**
- Launch
- Click "Start Game"
- You'll see the previous holes' performance for all the golfers
- Click on a golfer to bet that they'll win that round
- You'll see the players playing on the right!
**Save and load game state**
- Launch
- Click save or load

**Phase 4: Task 2**\
Made new league\
Added new golfer: Bob Odenkirk \
Added new golfer: Bryan Cranston \
Added new golfer: Aaron Paul\
Added new golfer: Billiam Zero\
Added new course: WestField Golf\
Added new course: Albuquerque's finest\
Added new course: Tiny Golf!\
played game on WestField Golf\
Added new GAP on WestField Golf\
Added new golfer: asdljh\
Added new course: ello matey\
played game on ello matey\
Added new GAP on ello matey\
Added new golfer: timothy vibes\

**Phase 4: Task 3**\
By far the most complex piece of the UML diagram is the connections between 
the game package and the performance package. Each of them accesses golfer 
and either course or hole with varying multiplicities. This is genuinely 
easier to see in the code than in the diagram, but it is still confusing. 
I'd like to design a 2d HashMap data structure to store everything with one 
abstracted GAP. It is important to note that only GAP is a mutable class, 
the others are simply for passing as return values and parameters (they are 
final). The 2d hashmap would eliminate all need for these classes and would 
simplify my project significantly. After switching to GUI I was much less 
inclined to use the "simplicity" of my league abstraction (the use of names 
and not the golfer/course reference itself) so I'd likely remove it. though it 
was vital in the JsonReader class.

###### Persistence model is adapted from the JsonSerializationDemo
###### Logging model is adapted from the AlarmSystemDemo