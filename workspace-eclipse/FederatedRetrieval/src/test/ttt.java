package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
/**
 * 测试：
 * 结论：br初始化，temp = br.realine();sysout(br.readline);
 * 		结果与：br初始化：br.skip(temp+1);sysout(br.readline);
 * 		相同！，注意的是，一定要+1
 * @author Lee
 *
 */
public class ttt {

	public static void main(String[] args) throws Exception {
		
		String aa="";
		System.out.println(aa.isEmpty());
		BufferedReader br22 = new BufferedReader(new FileReader("/home/Lee/data/test/aaa"));
		int length = 0;
		while ((aa =br22.readLine())!=null){
			length+=aa.length()+1;
			System.out.println(aa.length());
		}

		System.out.println("++++++++++++++"+length);
		
		
		BufferedReader br = new BufferedReader(new FileReader("/media/clueweb09_1of2/ClueWeb09_English_1/en0000/00.warc"));
		String temp = br.readLine();
		int x = temp.length();
		System.out.println(br.read());
		System.out.println(br.readLine());
		br.close();
		br = new BufferedReader(new FileReader("/media/clueweb09_1of2/ClueWeb09_English_1/en0000/00.warc"));
		br.skip((long) x+1);
	//	System.out.println(br.read());
		System.out.println(br.readLine());
	}

}
