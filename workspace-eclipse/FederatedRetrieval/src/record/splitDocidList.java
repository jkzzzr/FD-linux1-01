package record;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class splitDocidList {

	public static String inputPath ="/media/clueweb09_1of2/ClueWeb09_English_";
	public static String outputPath ="/home/Lee/音乐/123/";
	public static void main(String[] args) throws Exception {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/Lee/音乐/doclist09-11"),"UTF-8"));
		
		//将en0000与外层的文件夹对应起来ClueWeb09_English_1
		//记录每个ClueWeb09_English_1下面存储的en0000文件夹有哪些
		HashMap<String, String> hMap = new HashMap<>();
		for (int i1 = 1; i1 < 11; i1++){
			File file = new File(inputPath + i1 +"/");
		//	System.out.println(file.isDirectory() +"\t"+file.getAbsolutePath());
			File [] flordNames = file.listFiles();
			for (File string: flordNames){
				System.out.println(string.getName());
				hMap.put(string.getName(), "ClueWeb09_English_" + i1);
			}
		}
		//ClueWeb09_English_1与clueweb09-en0000-00-00000对应起来
		//记录下ClueWeb09_English_1文件夹下面包含了哪些我们想要的文件
		HashMap<String, TreeSet<String>> hMap2 = new HashMap<>();
		
		String temp = "";
		while ((temp = bReader.readLine())!=null){
			System.out.println(temp);
			String flod = temp.substring(10,16);
			String key = (String)hMap.get(flod);
			if (!hMap2.containsKey(key)){
				TreeSet<String> hSet2 = new TreeSet<>();
				hMap2.put(key,hSet2);
			}else {
				TreeSet<String> hSet2 = hMap2.get(key);
				if (hSet2.isEmpty() ||!hSet2.contains(temp)){
					hSet2.add(temp);
				}else {
				}
				hMap2.put(key, hSet2);
			}
		}
		//输出ClueWeb09_English_1文件，文件中写了这个文件夹下面包含的我们给出的列表
/*		for (String flodString : hMap2.keySet()){
			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputPath + flodString),"UTF-8"));
			TreeSet<String> hSet = hMap2.get(flodString);
			Iterator<String> iterator = hSet.iterator();
			while (iterator.hasNext()){
				bWriter.write(iterator.next());
				bWriter.newLine();
			}
			bWriter.close();
		}*/
		//多线程实现上面注释掉的那部分
		for (String flodString : hMap2.keySet()){
			Thread2 thread2 = new Thread2("Thread"+ flodString);
			thread2.hMap2 = hMap2;
			thread2.flodString = flodString;
			thread2.start();
		}
	}
}
	class Thread2 extends Thread{
		public HashMap<String, TreeSet<String>> hMap2 = null;
		public String flodString = "";
		public Thread2(String string) {
			super(string);
		}
		@Override
		public void run() {
			BufferedWriter bWriter = null;
			try {
				bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("" + flodString),"UTF-8"));
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TreeSet<String> hSet = hMap2.get(flodString);
			Iterator<String> iterator = hSet.iterator();
			while (iterator.hasNext()){
				try {
					bWriter.write(iterator.next());
					bWriter.newLine();
					bWriter.close();
				}  catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

