package files;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Streams {
	/**
	 * Read from an InputStream until a quote character (") is found, then read
	 * until another quote character is found and return the bytes in between the two quotes. 
	 * If no quote character was found return null, if only one, return the bytes from the quote to the end of the stream.
	 * @param in
	 * @return A list containing the bytes between the first occurrence of a quote character and the second.
	 */
	public static List<Byte> getQuoted(InputStream in) throws IOException {
		ArrayList<Byte> result = new ArrayList<Byte>(); //List is aan interface and ArrayList implements it
		int counter = 0;
		boolean seenquote = false;
		for (int ch = in.read(); ch >= 0; ch = in.read()) {
			if (seenquote) {
				if (ch == '"') {
					return result;
				}
				result.add((byte) ch);
			}
			else if(ch == '"') {
				seenquote = true;
			}

		}
		if(!seenquote) return null;
		return result;
	}
	
	
	/**
	 * Read from the input until a specific string is read, return the string read up to (not including) the endMark.
	 * @param in the Reader to read from
	 * @param endMark the string indicating to stop reading. 
	 * @return The string read up to (not including) the endMark (if the endMark is not found, return up to the end of the stream).
	 */
	public static String readUntil(Reader in, String endMark) throws IOException {
		StringBuilder s1 = new StringBuilder();
		for (int ch; (ch = in.read()) != -1; ) {
			s1.append((char) ch);
			if (s1.toString().endsWith(endMark)) {
				return s1.substring(0, s1.length() - endMark.length());
			}
		}
		return s1.toString();
	}
	
	/**
	 * Copy bytes from input to output, ignoring all occurrences of badByte.
	 * @param in
	 * @param out
	 * @param badByte
	 */
	public static void filterOut(InputStream in, OutputStream out, byte badByte) throws IOException {
		for (int b = in.read(); b>=0; b=in.read()) {
			if ((byte)b != badByte){
				out.write((byte)b);
			}
	}
	
	/**
	 * Read a 40-bit (unsigned) integer from the stream and return it. The number is represented as five bytes, 
	 * with the most-significant byte first. 
	 * If the stream ends before 5 bytes are read, return -1.
	 * @param in
	 * @return the number read from the stream
	 */
	public static long readNumber(InputStream in) throws IOException {
		int count = 0;
		long res = 0;
		for (int b= in.read() ; b>=0 && count<5 ; b = in.read() ) {
			res = res << 8;
			res = res | b;
			count++;
		}
		if(count<5) return -1;
		return res;
	}
}
