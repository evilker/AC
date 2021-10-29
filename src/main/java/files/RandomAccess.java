package files;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
	
	/**
	 * Treat the file as an array of (unsigned) 8-bit values and sort them 
	 * in-place using a bubble-sort algorithm.
	 * You may not read the whole file into memory! 
	 * @param file
	 */
	public static void sortBytes(RandomAccessFile file) throws IOException {
		boolean swapped = false;
		do{
			swapped = false;
			int prev = -1;
			long pos = 0;
			file.seek(pos);
			int cur = file.read();
			int temp=0;
			for(pos = 0;pos<file.length();pos++) {
				file.seek(pos);
				cur = file.read();
				if(cur < prev) {
					//swap
					temp = prev;
					file.seek(pos-1);
					file.write(cur);
					file.seek(pos);
					file.write(temp);
					swapped = true;
				}
				else
				{
					prev = cur;
				}
			}

		}while(swapped);
	}

	/**
	 * Treat the file as an array of unsigned 24-bit values (stored MSB first) and sort
	 * them in-place using a bubble-sort algorithm. 
	 * You may not read the whole file into memory! 
	 * @param file
	 * @throws IOException
	 */
	public static void sortTriBytes(RandomAccessFile file) throws IOException {
		boolean swapped = false;
		long pos = 0;
		do{
			swapped = false;
			int prev = -1;
			int cur = 0;
			int temp=0;
			pos = 0;
			while(pos<file.length()) {
				cur = 0; //NEW
				for(int i=0;i<=2;i++)
				{
					file.seek(pos);
					cur = cur<<8;
					cur = cur | file.read();
					pos++;
				}

				if(cur < prev)
				{//We need to swap
					temp = prev;
					file.seek(pos-6);
					file.write(cur);
					file.seek(pos-3);
					file.write(temp);
					swapped = true;
				}
				else
				{
					prev = cur;
				}
			}

		}while(swapped);
	}
