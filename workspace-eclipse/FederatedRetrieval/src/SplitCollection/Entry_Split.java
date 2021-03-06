package SplitCollection;

import java.io.File;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class Entry_Split {
	public static Stack<Boolean> STACK = new Stack<>();
	public static void main(String[] args) {
		new Entry_Split().run();
		TimerTask timerTask = new TimerTask() {
					
			@Override
			public void run() {
				if (STACK.isEmpty()){
					System.out.println("@Entry_Split\tEND");
					System.exit(1);
				}
				
			}
		};
		Timer timer = new Timer(true);
		timer.schedule(timerTask, 0, 60000);
	}
	public  void run() {

		String inputPath = "/home/Lee/data/D1/ClueWeb12_";
		String outputPath = "/home/Lee/data/D2/ClueWeb12_";
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
						System.exit(1);
					}
				}
			
			new MyThread(input, output).start();
	//		new MyThread(input, output).start();;
			}
		}
	}
}
class MyThread extends Thread{
	String intputPath = "";
	String outputPath = "";
	public MyThread(String i, String o) {
		intputPath = i;
		outputPath = o;
	}
	@Override
	public void run() {
		super.run();
		Entry_Split.STACK.add(false);
		File file = new File(intputPath);
		File [] files = file.listFiles();
		for (File tempf: files){
			String filename = tempf.getName();
			try {
				new splitCollection(intputPath+"/"+filename, outputPath+"/"+filename).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Entry_Split.STACK.pop();
	}
}