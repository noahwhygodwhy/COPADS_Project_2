/**
 * Common.java
 * This class is made up entirely of static methods. It's purpose is to have methods that are used
 * by both the concurrent and parallel versions of the friendly number finders, FriendlySeq and FriendlySmp
 * Every function operates in a concurrent fashion within whatever process it's called.
 *
 * @author Noah Alvard
 */


import edu.rit.util.Pair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class Common
{

    public static ConcurrentHashMap.KeySetView largestGroups; //TODO: the threads group the groups into the largest group groups

    /**
     * GetDivisors
     *
     * Takes in a long value, and returns a set of all integers that divide evenly into it.
     * Uses the square root method to reduce the amount of work. First finding all divisors
     * lesser or equal to the square root, and then also including the value divided by the divisor
     * to the list of divisors, as if that second value is not equal to the first, it is
     * also a divisor
     *
     * @param val The number to find the divisors of.
     * @return A set containing the divisors of val.
     */
    public static Set<Long> getDivisors(long val)
    {
        HashSet<Long> divisors = new HashSet<Long>();
        long ii = (long) Math.ceil(Math.sqrt(val));

        for(long i = 1; i <= ii; i++)
        {
            if(val % i == 0)
            {
                divisors.add(i);
                long otherPossible = val/i;
                if(otherPossible != i)
                {
                    divisors.add(otherPossible);
                }
            }
        }
        return divisors;
    }

    /**
     * GetGCD
     *
     * Finds an integer that is the greatest common denominator for a and b.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The greatest number that devides evenly into a and b.
     */
    public static long getGCD(long a, long b)
    {
        while (b!=0)
        {
            long t = a;
            a = b;
            b = t%b;
        }
        return a;
    }

    /**
     * ReduceFraction
     *
     * Takes a fraction in as a pair of two numbers, the key being the numerator
     * and the value being the denominator, and finds the reduced form
     * of that fraction. Returns it as a pair in the same form as it received it in.
     *
     * @param in The fraction as a pair of two numbers
     * @return The reduced form of the fraction in as a pair of two numbers
     */
    public static LongPair reduceFraction(LongPair in)
    {
        long gcd = getGCD(in.key(), in.value());
        return new LongPair(in.key()/gcd, in.value()/gcd);
    }

    /**
     * FindLargestGroups
     *
     * Takes a mapping of abundancy values to groups of numbers who have said abundancy value.
     * Iterates through the entry set both finding the largest size of any group in the mapping,
     * and collecting said groups in an ordered map. The ordered map sorts by the abundancy
     * values of the groups, ascending.
     *
     * @param groups The mapping of all abundancy values to groups of numbers.
     * @return The ordered mapping of abundancy values to groups of numbers, only containing
     *         abundancy values whose groups are of the largest size. The keys ordered in ascending order.
     */
    public static Map<LongPair, ConcurrentLinkedQueue<Long>> findLargestGroups(Map<LongPair, ConcurrentLinkedQueue<Long>> groups)
    {
        Map<LongPair, ConcurrentLinkedQueue<Long>> toReturn = new ConcurrentSkipListMap<LongPair, ConcurrentLinkedQueue<Long>>(); //ordered maps wooo
        int groupSize = 2; //starting off on two, as no point in anything smaller
        for(Map.Entry<LongPair, ConcurrentLinkedQueue<Long>> group : groups.entrySet())
        {
            if(group.getValue().size() > groupSize)
            {
                groupSize = group.getValue().size();
                toReturn = new ConcurrentSkipListMap<LongPair, ConcurrentLinkedQueue<Long>>();
            }
            if (group.getValue().size() == groupSize)
            {
                toReturn.put(group.getKey(), group.getValue());
            }
        }

        return toReturn;
    }

    /**
     * PrintLargestGroups
     *
     * Takes a mapping of abundancy values to groups of numbers who have said abundancy value,
     * uses findLargestGroups to filter that map down to only the abundancy values whose groups
     * are of the largest size, and then prints the map entries in ascending order by key value
     * in this format: "{(x/y):[a,b,c,...]}", where x/y is a fraction representing the abundancy
     * value of the numbers a, b, c, and any others that might also have the same abundancy value.
     *
     * @param groups a mapping of abundancy values to groups of numbers who have said abundancy value.
     */
    public static void printLargestGroups(Map<LongPair, ConcurrentLinkedQueue<Long>> groups)
    {
        Map<LongPair, ConcurrentLinkedQueue<Long>> largestGroups = Common.findLargestGroups(groups);

        for(Map.Entry<LongPair, ConcurrentLinkedQueue<Long>> group : largestGroups.entrySet())
        {
            System.out.print("{" + group.getKey() +": [");

            TreeSet<Long> g = new TreeSet<Long>(group.getValue());

            String toPrint = "";
            int j = 0;
            for(long i : g)
            {
                System.out.print(i);
                if(j++ < g.size()-1)
                {
                    System.out.print(", ");
                }

            }
            System.out.println("]}");
        }
    }

    /**
     * ProcessArgs
     *
     * Processes the command line arguments for the friendly number finder program.
     * The usage is [programName] [start-number] [end-number] This function parses the start
     * and and numbers, ensures they are good values, and returns them in a LongPair
     * If the values are bad, prints a usage statement to err and terminates the program.
     *
     * @param args The program arguments given to main()
     * @return A pair containing the parsed start-number and end-number
     */
    public static LongPair processArgs(String[] args)
    {
        long minN = 1;
        long maxN = 0;
        try
        {
            if (args.length == 2)
            {
                minN = Long.parseLong(args[0]);
                maxN = Long.parseLong(args[1]);
            } else if (args.length == 1)
            {
                maxN = Long.parseLong(args[0]);
            } else
            {
                throw new Exception();
            }
        } catch (Exception e)
        {
            System.err.println("\n" + "Usage: java pj2 FriendlySeq start-integer end-integer # 0 < start < end");
            System.exit(-1);
        }
        return new LongPair(minN, maxN);
    }

    /**
     * PrintResults
     *
     * Takes a mapping of abundancy values to groups of numbers who have said abundancy value
     * Counts the number of values in groups of size of 2 or larger.
     * Uses printLargestGroups() to print the largest groups out.
     * Then prints the total number of friendly numbers counted previously.
     *
     * @param friendliness a mapping of abundancy values to groups of numbers who have said abundancy value
     */
    public static void printResult(Map<LongPair, ConcurrentLinkedQueue<Long>> friendliness)
    {
        int totalFriends = 0;

        for(Map.Entry<LongPair, ConcurrentLinkedQueue<Long>> friends : friendliness.entrySet())
        {
            if(friends.getValue().size() > 1)
            {
                totalFriends += friends.getValue().size();
            }
        }

        Common.printLargestGroups(friendliness);
        System.out.println(totalFriends + " friendly numbers");
    }


    /**
     * findAbundancy
     *
     * Calculates the abundancy value of a number in these steps:
     * 1. Finds all divisors of the value
     * 2. Sums the divisors
     * 3. Creates a fraction of the sum over the value and reduces it to it's simplest form
     * 4. That's the abundancy value
     *
     * If the abundancy value already exists in the map, adds the number to the list for that value
     * Otherwise, creates the list for the abundancy value, and then adds the number to the list
     *
     * @param value The value to find the abundancy for
     * @param friendliness The map to put the abundancy value into #SideEffects. MUST be a concurrency safe map
     *                     if multiple processes are used.
     */
    public static void findAbundancy(long value, Map<LongPair, ConcurrentLinkedQueue<Long>> friendliness)
    {
        Set<Long> divisors = Common.getDivisors(value);

        long numerator = divisors.stream().reduce(0L, Long::sum);//sum all divisors

        LongPair friendlinessValue = Common.reduceFraction(new LongPair(numerator, value));

        friendliness.computeIfAbsent(friendlinessValue, f->new ConcurrentLinkedQueue<Long>());

//        if (!friendliness.containsKey(friendlinessValue))
//        {
//            friendliness.put(friendlinessValue, new ConcurrentLinkedQueue<Long>());
//        }
        friendliness.computeIfPresent(friendlinessValue, (key, val)->{val.add(value); return val;});
        //friendliness.get(friendlinessValue).add(value);
    }
}
