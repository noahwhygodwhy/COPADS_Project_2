    cs251: CoPaDS Friendly Integer Assignment Status, Analysis and Feedback

    My name is:      Noah Alvard

    Assignment is:   Project 2


    ============= Status ==============================
    Remove choices inside the [] below to leave one answer on the next line:

    My program works.

    Explain your answer with an explanation as appropriate.

	It runs, and the SMP version runs faster than the sequential version

    ============= Analysis ============================

    1. How much time did you spend on this project? 
    Try if you can to identify setup time, (re)design time, debug time,
    and test time as time subsegments of your answer.
	
	I didn't keep track of how much time for which sections. Honestly I didn't keep track of how much time total, sorry.


    2. Identify the host you used for the runs below and
    how many cores the system had; lscpu -e -b will answer that for you.
    You will have to use this in your analysis for the other questions.
	
	Glados, with 12 cores in 2 cpus.

    3. Using the debug=makespan tracker=none command line options of pj2,
    enter results of the following runs of your sequential and parallel programs.

    If you were unable to get your programs working, then use
    Kaminsky's PiSeq and PiSmp example programs, and 
    apply those programs to the value 10,000,000,000 (1 and 10 0s).

    a. java pj2 debug=makespan FriendlySeq 1000 1000000
		{(112/41): [3444, 11070, 61008, 67158, 999744]}
		{(304/111): [3108, 9990, 55056, 60606, 902208]}
		{(80/29): [2436, 7830, 43152, 47502, 707136]}
		{(64/23): [1932, 6210, 34224, 37674, 560832]}
		{(160/57): [1596, 5130, 28272, 31122, 463296]}
		{(48/17): [1428, 4590, 25296, 27846, 414528]}
		{(52/15): [1260, 22320, 27000, 55800, 365760]}
		{(56/15): [16380, 290160, 351000, 669600, 725400]}
		34572 friendly numbers
		Job 1 makespan 17450 msec
		
    java pj2 debug=makespan FriendlySeq 50 2000000//i'm assuming that's what you wanted by putting "50 2000000" here, if not, just ignore this
		{(576/235): [1410, 6580, 116560, 291400, 1910080]}
		{(528/215): [1290, 6020, 106640, 266600, 1747520]}
		{(504/205): [1230, 5740, 101680, 254200, 1666240]}
		{(456/185): [1110, 5180, 1503680, 91760, 229400]}
		{(72/29): [870, 4060, 179800, 1178560, 71920]}
		{(288/115): [690, 3220, 57040, 142600, 934720]}
		{(48/19): [570, 2660, 47120, 117800, 772160]}
		{(216/85): [510, 2380, 42160, 690880, 105400]}
		{(168/65): [390, 1820, 32240, 528320, 80600]}
		{(144/55): [330, 1540, 27280, 68200, 447040]}
		{(8/3): [84, 270, 1488, 1638, 24384]}
		{(640/237): [6636, 21330, 117552, 129402, 1926336]}
		{(592/219): [6132, 19710, 108624, 119574, 1780032]}
		{(192/71): [5964, 19170, 105648, 116298, 1731264]}
		{(544/201): [5628, 18090, 99696, 109746, 1633728]}
		{(496/183): [5124, 16470, 90768, 99918, 1487424]}
		{(160/59): [4956, 15930, 87792, 96642, 1438656]}
		{(144/53): [4452, 14310, 78864, 86814, 1292352]}
		{(128/47): [3948, 12690, 69936, 76986, 1146048]}
		{(352/129): [3612, 11610, 63984, 70434, 1048512]}
		{(112/41): [3444, 11070, 61008, 67158, 999744]}
		{(304/111): [3108, 9990, 55056, 60606, 902208]}
		{(80/29): [2436, 7830, 43152, 47502, 707136]}
		{(64/23): [1932, 6210, 34224, 37674, 560832]}
		{(160/57): [1596, 5130, 28272, 31122, 463296]}
		{(48/17): [1428, 4590, 25296, 27846, 414528]}
		{(32/11): [924, 2970, 16368, 18018, 268224]}
		{(28/9): [540, 3276, 58032, 1862190, 950976]}
		{(16/5): [420, 7440, 8190, 18600, 121920]}
		{(52/15): [1260, 22320, 27000, 55800, 365760]}
		{(192/55): [4620, 1341120, 204600, 81840, 90090]}
		{(32/9): [167400, 3780, 66960, 406224, 1097280]}
		{(56/15): [669600, 16380, 351000, 725400, 290160]}
		69476 friendly numbers
		Job 1 makespan 10676 msec

    b. java pj2 debug=makespan FriendlySmp 1000 1000000
		{(112/41): [3444, 11070, 61008, 67158, 999744]}
		{(304/111): [3108, 9990, 55056, 60606, 902208]}
		{(80/29): [2436, 7830, 43152, 47502, 707136]}
		{(64/23): [1932, 6210, 34224, 37674, 560832]}
		{(160/57): [1596, 5130, 28272, 31122, 463296]}
		{(48/17): [1428, 4590, 25296, 27846, 414528]}
		{(52/15): [1260, 22320, 27000, 55800, 365760]}
		{(56/15): [669600, 16380, 351000, 290160, 725400]}
		34572 friendly numbers
		Job 1 makespan 4746 msec
		
    java pj2 debug=makespan FriendlySmp 50 2000000
		{(576/235): [1410, 6580, 116560, 291400, 1910080]}
		{(528/215): [1290, 6020, 106640, 266600, 1747520]}
		{(504/205): [1230, 5740, 101680, 254200, 1666240]}
		{(456/185): [1110, 5180, 91760, 229400, 1503680]}
		{(72/29): [870, 4060, 71920, 179800, 1178560]}
		{(288/115): [690, 3220, 57040, 142600, 934720]}
		{(48/19): [570, 2660, 47120, 117800, 772160]}
		{(216/85): [510, 2380, 42160, 105400, 690880]}
		{(168/65): [390, 1820, 32240, 80600, 528320]}
		{(144/55): [330, 1540, 27280, 68200, 447040]}
		{(8/3): [84, 270, 1488, 1638, 24384]}
		{(640/237): [6636, 21330, 117552, 129402, 1926336]}
		{(592/219): [6132, 19710, 108624, 119574, 1780032]}
		{(192/71): [5964, 19170, 105648, 116298, 1731264]}
		{(544/201): [5628, 18090, 99696, 109746, 1633728]}
		{(496/183): [5124, 16470, 90768, 99918, 1487424]}
		{(160/59): [4956, 15930, 87792, 96642, 1438656]}
		{(144/53): [4452, 14310, 78864, 86814, 1292352]}
		{(128/47): [3948, 12690, 69936, 76986, 1146048]}
		{(352/129): [3612, 11610, 63984, 70434, 1048512]}
		{(112/41): [3444, 11070, 61008, 67158, 999744]}
		{(304/111): [3108, 9990, 55056, 60606, 902208]}
		{(80/29): [2436, 7830, 43152, 47502, 707136]}
		{(64/23): [1932, 6210, 34224, 37674, 560832]}
		{(160/57): [1596, 5130, 28272, 31122, 463296]}
		{(48/17): [1428, 4590, 25296, 27846, 414528]}
		{(32/11): [924, 2970, 16368, 18018, 268224]}
		{(28/9): [540, 3276, 58032, 950976, 1862190]}
		{(16/5): [420, 7440, 8190, 18600, 121920]}
		{(52/15): [1260, 22320, 27000, 55800, 365760]}
		{(192/55): [4620, 81840, 90090, 204600, 1341120]}
		{(32/9): [3780, 66960, 167400, 406224, 1097280]}
		{(56/15): [16380, 290160, 351000, 669600, 725400]}
		69476 friendly numbers
		Job 1 makespan 42253 msec


    4. speedup and efficiency
    
    a. calculate the speedup of #2 above;
    see lecture notes from week 05 or [BCBD] for how to calculate it.

	The speedup
	
	Speedup = Time(N,1)/Time(N/12)
	Speedup = 17450/4746
	Speedup = ~3.68x
		
    Relate this speedup back to the host environment, and discuss the
    degree of improvement between the sequential and parallel programs.
	
	Moving from a single core to up 12 a performance increase of 3.68 does not seem as impressive as I was hoping. I think there a few reasons for not getting closer to 12x a performance increase.
	Firstly, there are two cpus. Java threading as far as I could find relys on the OS's native threading. While ubuntu does have support for multi cpu systems, there is a lot of overhead in transfering data between cpus. I would bet that it tries to keep any processes generated by the the main process on the same CPU as the main process.
	Assuming this is the case, 3.68/6 isn't looking as bad. From there, there is a lot of overhead just in setting up the multiprocessing. That, plus the constant time required to order and print the results makes me confident that, if not perfect, the program I have created takes appropriate advantage of multiprocessing.

    b. what is the efficiency of the parallel program? Discuss.
	
	Efficiency = T(N,1)/(12*T(N,12))
	Efficiency = 17450/(12*4746)
	Efficiency = 17450/56,952
	Efficiency = 0.3
	
	although per the argument above, I could consider the efficiency to be 
	Efficiency = T(N,1)/(6*T(N,6))
	Efficiency = 17450/(6*4746)
	Efficiency = 17450/28,476
	Efficiency = 0.6
	Which I don't think is bad, especially when considering multiprocessing overhead and the constant time for other parts of the program.

    5. experiment with schedule=XXX and chunk=YYY as command line arguments
    to your program (see edu.rit.pj2.Schedule for XXX values to try, and
    choose various YYY values appropriate to your schedule picks.)
    What did you find in terms of execution times and speedups/slowdowns
    for the various choices you made. 
    NOTE: you may have to use a larger input range to evaluate schedules.

	I was able to get a slight performance increase by switching to a guided schedule with a minimum chunk size of 128. The later numbers have to do more work
	as calculating the abundance for larger numbers takes more work as more divisors must be calculated. With a sample size of 10 runs of smp and 10 runs of seq,
	I was able to achieve a speed up of 4.42, which if we're still considering it to be a 6 core not a 12 core because of the overhead of tranfering data between cores, I think is pretty good.
	
	I found leapfrog to be the worst performing. 
	



    ============= Feedback ============================
    What did I learn?

	I actually learned a lot about java's native threading and parallel processing. I got frustrated with PJ2 and started messing around with it. It was actually kind of fun.
	I tried trecking through PJ2's source. It's uh...messy lol

    What was easy about this assignment?

	Honestly the assignment as a whole wasn't too bad. I'm assuming I have done it correctly, so this might come back to bite me, but it didn't seem that complicated once I got an understanding for PJ2.


    What gave you trouble? Describe specific problems, you had, if any.

	For some reason, edu.rit.util.Pair considers a Pair equal if their keys are equal. It doesn't consider the value. That threw me off for a while.
	Also, when building my own "LongPair" to represent the abundancy value fractions, I found that when doing equal(), this.key==other.key doesn't work, you have to use
	this.key.aslong() or something similar in order to actually get the value. Isn't required for math operations. Also threw me for a loop.

    What can the instructor do to improve this assignment?

	Honsetly I feel that relying on RIT's proprietary libraries puts students at a disadvantage for the future. I understand that there are issues with teaching parallel processing with java's native libraries, but
	I really think that learning what we might actually use in the future would be better.


    Would you have like to add anything further?

	Nop
