package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
/**
 * 由于ClueWeb12年的数据，用的是Warc10Collection的数据格式
 * 有些结尾的/r和/n符号的存在，如果用BufferedReader这样的字符流，比较麻烦
 * 所以用这个fileinputStream直接是字节刘读取，方便一点，因为字符流的话，它直接就读取不出来最后
 * 到底是/r还是/n,反正一行一行的读取，最后就不管了，这时候，我来处理的话，就麻烦了
 * 所以干脆用这个字节刘把。能把到底有多少个字节算得清清楚楚
 * @author Lee
 *
 */
public class Readline {
	int readLineByteCount = 0;
	String path = "";
	private InputStream is=null;
	boolean eof = false;
	public Readline(String path) {
		try {
			this.path = path;
			is = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void setIs(InputStream is){
		this.is = is;
	}
	public int getReadLineByteCount(){
		return readLineByteCount;
	}
	public String readLine() throws IOException		{
		final StringBuilder s = new StringBuilder();
		int c = 0;
		char ch; 
		char ch2;
		readLineByteCount = 0;
		while(true)
		{
			c = is.read();
			readLineByteCount++;
			if (c == -1){
				eof = true;
				return null;
			}
			ch = (char)c;
			if (ch == '\r'){
				c = is.read(); 
				readLineByteCount++;
				if (c== -1){
					s.append(ch);
					eof = true;
					break;
				}
				ch2 = (char)c;
				if (ch2 == '\n')
					break;
				s.append(ch); s.append(ch2);
			}
			else if (ch == '\n'){
				break;
			}
			else{
				s.append(ch);
			}
		}
		return s.toString();
		}

	public void skip(long x){
		try {
			this.is.skip(x);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
