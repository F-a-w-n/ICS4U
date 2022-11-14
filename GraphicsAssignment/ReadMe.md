Quick Explanation Time (I don't use markdown sorry if this is formatted weird):

Firstly, files are as follows:
>Frogger - main class, pretty much 700 lines of wrangling together the rest of my classes
>images - stores photos for sprites and backgrounds as well as sound files
>Sprite - very important since most classes are children of this, controls movement, painting, collisions, etc. for moving objects like the frog and the cars
>Backtile - the little slices of background you see in game
>Button - I made my own button class so I could add options, highscores, menu buttons, etc.
>Car - sprite that moves each time, has simpler move method than log and frog since they always move and don't affect other sprite's moves
>Frog - sprite controlled by player, queues moves and then hops 50px in the indicated direction (I decided to let it go diagonal as well since it makes for more interesting gameplay), has nice collisions and such built on top of the basic sprite class
>Log - a sprite similar in many ways to the cars from how they derive directions and move, but the movements need to be returned back out so the frog can sit on the log as it goes
>Score - formats and manages highscores from the player and saved to file so I can have an easier time adding highscore functionality
>Sounds - stores and controls sound files played in game, made this before you posted the example so A) it's questionable and B) I'm cool

Some small notes:
- Sounds is plural as a stylistic choice as the files contain more than one it totally wasn't a typo
- I left my highscores to brag but also so it's easier to test, feel free to reset them in options if I intimidate you too much
- I realized there was a built-in JFrame button class after making my own and decided that my black rectangle theme was too valuable to convert over to it
- The music is actually kinda bad I recommend turning sound off
- Arrow keys are default
- score system is built around going level 1-2-3, so if you go back levels and replay them it doesn't stop you from playing level 1 indefinitely for a ton of points

That's pretty much it! Enjoy frogging!

-Fawn