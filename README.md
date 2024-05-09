This is an implementation of a time efficient new sorting algorithm "BitSort" for big collections of data (under testing) :


Bit Sort is a non-comparative sorting algorithm that operates on integers by considering their individual bits : it doesn't directly compare elements with each other in the traditional way that comparison-based sorting algorithms like Quicksort or Merge Sort do. Instead, it exploits the bitwise operations and the binary representation of integers to sort them based on their individual bits.

The algorithm sorts the integers based on their binary representation. It iteratively examines each bit position, starting from the most significant bit (MSB) to the least significant bit (LSB). At each iteration, it partitions the array into two groups based on the value of the current bit: those with the bit set (1) and those with the bit unset (0). It then recursively applies the same process to each partition until all bits have been considered.

During each iteration, the elements are rearranged in such a way that those with the bit set appear before those with the bit unset. This process effectively sorts the array based on the binary representation of the integers.

The algorithm typically uses a temporary array to store the sorted elements during each iteration. After processing each bit position, the sorted portions are copied back to the original array.

Bit Sort has a time complexity of O(n * log(max)), where n is the number of elements in the array and max is the number of bits of the maximum value in the array. The space complexity is O(n).

Single thread :
BitSort

Single thread + mask :
BitSort4_1

Multiprocessing :
BitSort4_2

Multiprocessing + usage of other sorting algorithms (BitSort4_2, IntroSort, HeapSort, BitSort) depending on the size of the collection :
BitSort4_2_2_1

Example of a comparaison with quicksort : 
- the randomly generated list of numbers is used against each algorithm
- size from 10.000.000 350.000.000
![image](https://github.com/project-13/algoTri/assets/35433969/607e9eba-292f-4cac-ae0f-4225f08910d4)
