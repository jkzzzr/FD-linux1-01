package test;

import java.util.Timer;
import java.util.TimerTask;

public class ReadFile {

	public static void main(String[] args) throws Exception {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
		//		System.exit(1);
				
			}
		}, 10L);
		new ReadFile().filterAndWrite();
	}
	public void filterAndWrite() throws Exception{
		long tt = System.currentTimeMillis();
		record.ReadFile.filterAndWrite("/media/clueweb09_1of2/ClueWeb09_English_1/en0000/00.warc.gz"
				, "/home/Lee/data/test/aaa");
		System.out.println(System.currentTimeMillis() - tt);
	}

}
