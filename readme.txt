Programming Assignment 4: Slider Puzzle


/* *****************************************************************************
 *  Explain briefly how you represented the Board data type.
 **************************************************************************** */

I represented my Board data type as the initial 2d array of tiles that was passed
in and represented a board. It then got the specific hamming and manhatten distances from it.
The Board data type consisted of the neighbors of the initial board that got put in and made
sure the board was solvalble. It saw that if the array was the goal board.
Finally, it saw that if one board was equal to another and the to string of it.


/* *****************************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).
 **************************************************************************** */

I represented the search node data type as a configuration of board, number of moves,
and the previous search node. The moves in this was used to get the priority of
that specific board in the search node by adding the moves to the manhatten distance.



/* *****************************************************************************
 *  Explain briefly how you detected unsolvable puzzles.
 *
 *  What is the order of growth of the running time of your isSolvable()
 *  method in the worst case as function of the board size n? Use big Theta
 *  notation to simplify your answer, e.g., Theta(n log n) or Theta(n^3).
 **************************************************************************** */

Description: I detected unsolvale puzzles by using a quadruple for loop that
took each of the elements in the board and compared it with the elements in front of it.
After this it got the inversions and depending if the board was even(got zero row)
or odd it would then determine if the board was solvable or not.



Order of growth of running time: Theta( N^4  )



/* *****************************************************************************
 *  For each of the following instances, give the minimum number of moves to
 *  solve the instance (as reported by your program). Also, give the amount
 *  of time your program takes with both the Hamming and Manhattan priority
 *  functions. If your program can't solve the instance in a reasonable
 *  amount of time (say, 5 minutes) or memory, indicate that instead. Note
 *  that your program may be able to solve puzzle[xx].txt even if it can't
 *  solve puzzle[yy].txt and xx > yy.
 **************************************************************************** */


                 min number          seconds
     instance     of moves     Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt       28        0.17            .024
   puzzle30.txt       30        .024            .028
   puzzle32.txt       32        memory              .044
   puzzle34.txt       34        memory            .03
   puzzle36.txt       36        memory             .028
   puzzle38.txt       38        memory             .031
   puzzle40.txt       40        memory           .032
   puzzle42.txt       42        memory             .061



/* *****************************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer: a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 **************************************************************************** */

 I would want a more memory because as the puzzles get bigger and bigger,
 the amount of memory you need on your minPQ gets bigger and bigger.





/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

Known limitations are the memory space for the priority queue that when the amount
of steps get bigger, the memory gets a lot more crowded.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */


Got help from Jack(Solver while loop, isSolvable), Professor Han(manhatten distance and Solver),
Daruis(isSolvable, neighbors), Nina(toString, solutions, moves).




/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */

For isSolvable, I had to troubleshoot a lot of times and had to do a lot of tracing.
Had a lot of wrong answers like setting y =i and z = j and creating a quadruple nested
for loop to see if y == i then check elements in front of j. Came up with the
algorithm to keep track of where the first element and the second element
it was comparing it with.


/* *****************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **************************************************************************** */







/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */


