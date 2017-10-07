{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf760
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;\red19\green19\blue19;}
{\*\expandedcolortbl;;\cssrgb\c9412\c9412\c9412;}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs24 \cf0 Thomas Nguyen\
01/15/17\
CSE 373\
TA: Raquel Van Hofwegen\
HW #1\
\
1) First, I tested if every line of code I wrote was doing the desired function. So I tested the case when I wanted it to throw the EmptyStackException (peek/pop an empty stack), pushed to it, popped elements off, etc. In order to test if my stack implementations were correct, I tested my implementations against the Java library Stack class. Whatever I did to the Java Stack, I also did with my renditions of a stack i.e. push() 21 times, pop() 4 times, push some more, etc., and my stacks were able to replicate the functionality. Also, I thought of the property of a stack where it would reverse the order sequence of the items that were pushed onto it when they are all removed right after. This means that if I pushed, for example 10 items, into the stack then popped those 10 items off into am arraylist, then the sequential order of the 10 items in the arraylist would be the backwards order of how they went into the stack. However, if I repeated this process one more time, the sequence that enters the arraylist should be the same sequence that entered the stack for the first time. Using this logic, that means if I ran reverse twice onto one of the provided .wav files, I should get the original back, which I successfully tested on the bot.wav.\
\
2) Assuming that the array starts at a length of 2, it would take 19 resizes to store 1000000 lines, 29 resizes for 1000000000 lines, and 39 resizes for 1000000000000 lines. Doubling the array size is essentially doing \'93array.length * 2^n = desired size\'94 and just solving that expression for n. It also seems like for each \'93lines * 1000\'94, n will grow by a factor of 10.\
\
3) You could implement a stack with two FIFO Queue objects. You would just have to keep juggling the elements in the stack between the two Queues to grab the last element. However, you would only have to edit either the push() or the pop() as depicted below. \
\
PSUEDO-CODE\
\
have one queue that is empty\
have another queue that currently holds the data\
\
push method \{\
	add the new element to the queue that is holding the data\
	increment size\
\}\
\
pop \{\
	check if the stack is empty to throw error\
	for 0 to size - 2 \{ // one less than the size of the stack\
		remove an element from the queue holding the data\
		add the same element that was removed to the empty queue\
	\}\
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0
\cf0 	decrement size\
	double data = remove last element \
	reassign the queues so the correct ones are empty/holding the data\
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0
\cf0 	return the last element variable\
\}\
\
\
4) It\'92s clear that the implementation with the queues will require more memory (space) since you are creating two data structures vs one array. Additionally, although the push() method is essentially the same process, the runtime would be greater because of the pop method since you have to transfer the \'92n - 1\'92 number of elements over to the other queue, O(n) runtime, to \'93find\'94 the \'93top\'94 element before you\'92re able to actually able to pop it. Whereas with the array stack, you know that the latest element that was stored is in the size - 1 index so you can easily find it, making the pop() operation an O(1) runtime.\
\
5) My project goes a bit \'93above and beyond\'94 because I included more functionality than was asked. From my experience, the methods listed in the interface are only required and you are not limited to writing others. I thought it was worth it to include a size() and a toString() method in both the array and linked list implementations because both helped with debugging code and in my opinion, these are very practical, important functions for a client\'92s disposal. In addition to this, I was able to add the downsizing functionality when the array was 3/4 empty (realized that the downsizing should only be able to occur when the stack is popped so I thought it was practical to start the initial size of the array at a smaller number than 10 so I would not have to check to downsize in the push process), and I was able to decipher the backwards message (see 7).\
\
6) I enjoyed that this assignment really showed the mechanics and infrastructure of a stack. Also, I like how the assignment brought together the application of code into a real world scenario, but I guess that could apply to all well developed CS homework assignments. What I did not appreciate about the assignment was the somewhat ambiguity because I am more of a concrete and straight shooter so I do what is told of me but I guess that is natural for a CS assignment. In a sense, it builds the skill of thinking for ourselves.\
\
7) Secret message: \'93\cf2 \expnd0\expndtw0\kerning0
the scent of bitter almonds always reminded him of the fate of unrequited love\'94\cf0 \kerning1\expnd0\expndtw0 \
\
\
\
\
\
}