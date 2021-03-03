/**
 * FriendlySmp.java
 *
 * "Usage: java pj2 FriendlySmp start-integer end-integer # 0 < start < end");
 *
 * This class has to be called by PJ2. main() is a program to calculate the
 * abundancy values for numbers between start-integer and end-integer.
 * https://en.wikipedia.org/wiki/Abundant_number
 * This version is the parallel version. It uses PJ2's Task to run multiple processes
 * in parallel in order to find the abundancy values more quickly.
 * Prints the abundancy values belonging to the largest groups of numbers
 * along with the groups of numbers in ascending order of abundancy values
 *
 * @author Noah Alvard
 */


import edu.rit.pj2.Task;
import edu.rit.util.Pair;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.rit.pj2.LongLoop;

public class FriendlySmp extends Task
{

    
    /**
     * main
     * @param args
     * 0: start-integer - the integer to start finding abundancy values at
     * 1: end-integer - the integer to stop finding abundancy values at (inclusive)
     */
    public void main(String[] args)
    {
        //get the min and max values
        Pair<Long, Long> brackets = Common.processArgs(args);

        //create the map of friendliness
        ConcurrentHashMap<LongPair, ConcurrentLinkedQueue<Long>> friendliness = new ConcurrentHashMap<LongPair, ConcurrentLinkedQueue<Long>>();
        //ConcurrentHashMap.KeySetView

        //consecutively calculates abundancy values for each number
        parallelFor(brackets.key(), brackets.value() + 1).exec(new LongLoop()
        {
            @Override
            public void run(long i){Common.findAbundancy(i, friendliness);}
        });

        //print the results
        Common.printResult(friendliness);
    }
}