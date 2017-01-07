package test;

import java.util.Timer;
import java.util.TimerTask;

import record.Ireadfile;
import record.ReadFile_10;

public class ReadFile {

	public static void main(String[] args) throws Exception {
		/*new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
		//		System.exit(1);
				
			}
		}, 10L);*/
		System.out.println("START");
		new ReadFile().filterAndWrite();
		System.out.println("END");
	}
	public void filterAndWrite() throws Exception{
		long tt = System.currentTimeMillis();
		/*Ireadfile ireadfile = new record.ReadFile_018();
		ireadfile.filterAndWrite("/media/clueweb09_1of2/ClueWeb09_English_1/en0002/08.warc.gz"
				, "/home/Lee/data/test/aaa");*/
		ReadFile_10 ireadfile = new record.ReadFile_10();
		ireadfile.filterAndWrite2("/home/Lee/data/test/0000tw-00.warc.gz"
				, "/home/Lee/data/test/aaa");
		System.out.println(System.currentTimeMillis() - tt);
	}

}
