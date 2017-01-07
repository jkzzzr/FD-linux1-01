package record;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.InitialContext;

public class Entry_ReadFile {

	public static Stack<Boolean>  STACK= new Stack<>();
	public static void main(String[] args) throws IOException {
		long tt = System.currentTimeMillis();
		new Entry_ReadFile().fun2_Thread();
		System.err.println(System.currentTimeMillis() - tt);
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				if (STACK.isEmpty()){
					System.out.println("@Entry_ReadFile\n\tEND");
					System.exit(1);
				}
				
			}
		};
		Timer timer = new Timer(true);
		timer.schedule(timerTask, 0, 10000);
	}
	public void fun2(){
			String inputPath = "/media/ClueWeb12_CatB/ClueWeb12_";
		String outputPath = "/home/Lee/data/D1/ClueWeb12_";
		for (int i = 0; i <=19; i++){
			String istr = String.format("%02d", i);
			String tempInput = inputPath + istr;
			String tempoutput = outputPath + istr;
			File outputFile = new File(tempoutput);
			if (!outputFile.exists()){
				outputFile.mkdirs();
			}
			File file = new File(tempInput);
			File[] files = file.listFiles();
			for (File tempf : files){
				String en = tempf.getName();
	
				String input= "";
				input = inputPath+istr+"/"+en;
				
				String output = "";
				output = outputPath+istr+"/"+en;
				File outputFile2 = new File(output);
				if (!outputFile2.exists()){
					try{
					outputFile2.mkdir();
					}catch (Exception e) {
						System.err.println(output);
					}
				}
				
				run(input, output, new ReadFile_10());
		//		new MyThread(input, output).start();;
			}
		}
		
	}
	public void fun2_Thread(){
		
	
		String inputPath = "/media/ClueWeb12_CatB/ClueWeb12_";
	String outputPath = "/home/Lee/data/D1/ClueWeb12_";
	for (int i = 0; i <=19; i++){
		String istr = String.format("%02d", i);
		String tempInput = inputPath + istr;
		String tempoutput = outputPath + istr;
		File outputFile = new File(tempoutput);
		if (!outputFile.exists()){
			outputFile.mkdirs();
		}
		File file = new File(tempInput);
		File[] files = file.listFiles();
		for (File tempf : files){
			
			String en = tempf.getName();

			String input= "";
			input = inputPath+istr+"/"+en;
			
			String output = "";
			output = outputPath+istr+"/"+en;
			File outputFile2 = new File(output);
			if (!outputFile2.exists()){
				try{
				outputFile2.mkdir();
				}catch (Exception e) {
					System.err.println(output);
				}
			}
			
			new MyThread(input, output,new ReadFile_10()).start();
	//		new MyThread(input, output).start();;
		}
	}
}
	public void fun1() throws IOException{
		String inputPath = "/media/clueweb09_1of2/ClueWeb09_English_";
		String outputPath = "/home/Lee/data/test/ClueWeb09_English_";
		for (int i = 1; i <=10; i++){
			String tempInput = inputPath + i;
			String tempoutput = outputPath + i;
			File outputFile = new File(tempoutput);
			if (!outputFile.exists()){
				outputFile.mkdirs();
			}
			File file = new File(tempInput);
			File[] files = file.listFiles();
			for (File tempf : files){
				String en = tempf.getName();

				String input= "";
				input = inputPath+i+"/"+en;
				
				String output = "";
				output = outputPath+i+"/"+en;
				File outputFile2 = new File(output);
				if (!outputFile2.exists()){
					try{
					outputFile2.mkdir();
					}catch (Exception e) {
						System.err.println(output);
					}
				}
				run(input, output, new ReadFile_018());
			}
		}
	}
	public void run(String intputPath, String outputPath,Ireadfile ireadfile){
		
		File file = new File(intputPath);
		File [] files = file.listFiles();
		System.out.println(intputPath+"\t"+outputPath);
	
		for (File tempf: files){
			String filename = tempf.getName();
			try {
				ireadfile.filterAndWrite(intputPath+"/"+filename, outputPath+"/"+filename.replaceAll(".warc.gz", ""));
				
			} catch (Exception e) {
				System.err.println(intputPath+"\t"+outputPath+"\t"+filename.replaceAll(".warc.gz", ""));
				e.printStackTrace();
			}
		}
		
	}
}
class MyThread extends Thread{
	String intputPath = "";
	String outputPath = "";
	Ireadfile ireadfile = null;
	public MyThread(String i, String o, Ireadfile ireadfile) {
		intputPath = i;
		outputPath = o;
		this.ireadfile = ireadfile;
	}
	@Override
	public void run() {
		super.run();
		Entry_ReadFile.STACK.add(false);
		File file = new File(intputPath);
		File [] files = file.listFiles();
		for (File tempf: files){
			String filename = tempf.getName();
			try {
				ireadfile.filterAndWrite(intputPath+"/"+filename, outputPath+"/"+filename.replaceAll(".warc.gz", ""));
			} catch (Exception e) {
				System.out.println(intputPath+"\t"+outputPath+"\t"+filename.replaceAll(".warc.gz", ""));
				e.printStackTrace();
			}
		}
		Entry_ReadFile.STACK.pop();
	}
}
class Mytimer extends TimerTask{

	@Override
	public void run() {
		
		
	}
	
}