VIRTUAL MEMORY SIMULATOR
----
Implementation of a virtual memory simulator. 
This simulator consists of several important parts: the CPU which contains the memory management unit and the TLB cache, the virtual page table, physical memory, and the operating system. 


Specifications of the simulated computer
----
	The CPU address width is 16 bits
	Physical memory’s address width is 12 bits
	The page offset is 8 bits.
	The TLB contains 8 entries.
	The OS uses the clock algorithm for page replacement
	The MMU uses FIFO for replacement algorithm
	The OS resets the r-bit every 20 instructions.


Data Files
----
The test files and page files will simulate the various reads and writes to addresses by a running process (test files),
and the process’ pages of data that the OS must load from the hard drive and put into memory. If a write to memory
occurs and the OS needs to evict the dirty page, then the OS must write the page back to the file on the hard disk.
The test file format is:

0

1C85

1

A1B5

8517174

...


Output
----
Each test file will output in a separate CSV file with its corresponding testfile name.
It contains the address, Read or write (0 or 1), The value read or written, Soft miss (0 = false, 1 = true), Hard miss (0 = false, 1 =
true), A hit (0 = false, 1 = true), page number of the evicted page, was that page’s dirty bit set.


How to run the program
----
1. Load the program in your preferred Java IDE. 
2. Compile and run Main.java
3. When prompted for the file location, simply enter the name of the file. Be sure that the testfile is located in Virtual_Memory_Simulator. 
