/**
 * LongPair.java
 *
 * This class extends RIT's pair. I wrote it because the pair in edu.rit.util
 * decided that a pair is equal if only the keys are equal, not both the keys and value
 * I also ended up needing to write a custom compareTo and toString so...
 *
 * LongPair is a Pair implementation specifically designed to hold two Longs
 * as if they were a fraction. It cannot hold other types of values like a
 * normal Pair because I only need it to hold longs.
 *
 * @author Noah Alvard
 */


import edu.rit.util.Pair;
//because this pair^ only compares on key, not on key and value, like, why
//i looked it up, it's the fault of some guy called Zack Zatkin-Gold, whoever that is

public class LongPair extends Pair<Long,Long> implements Comparable<LongPair>
{

    public LongPair()
    {
        super();
    }
    public LongPair(Long k, Long v)
    {
        super(k, v);
    }

    /**
     * Equals
     *
     * Two LongPairs are equal if their keys as longs are equal
     * and their values as longs are equal.
     * If the other isn't a LongPair, then they cannot be equal.
     *
     * @param o The other object
     * @return true if the keys are equal and values are equal. Otherwise false.
     */
    @Override
    public boolean equals(Object o)
    {
        if(o instanceof LongPair)
        {
            LongPair otherPair = (LongPair) o;
            return this.key.longValue() == otherPair.key.longValue() && this.value.longValue() == otherPair.value.longValue();
        }
        return false;
    }


    /**
     * ToString
     *
     * Constructs a string representation of the pair in the form of a fraction
     *
     * @return "(key/value)"
     */
    @Override
    public String toString()
    {
        return "(" + this.key + "/" + this.value + ")";
        //return "(" + ((double)this.key / this.value) + ")";
    }

    /**
     * CompareTo
     *
     * A compare function. Designed to make a ordered collection of LongPairs
     * ascend by the value of the fraction it represents.
     *
     * @param o The other LongPair
     * @return 1 if this is greater, -1 if the other is greater, and 0 if they are equal.
     */
    @Override
    public int compareTo(LongPair o)
    {
        double frac1 = (double)this.key/this.value;
        double frac2 = (double)o.key/o.value;
        if(frac1 == frac2)
        {
            return 0;
        }
        else return frac1 > frac2 ? 1 : -1;
    }
}