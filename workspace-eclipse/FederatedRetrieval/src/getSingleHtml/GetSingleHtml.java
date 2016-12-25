package getSingleHtml;

import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
/**
 * 根据文档id在硬盘中找到对应的文档，然后输出到单独的文件中去
 * @author Lee
 */
public class GetSingleHtml {
	public GetSingleHtml(){
		
	}
	public GetSingleHtml(String input, String output, String docid, long index) {
		super();
		this.input = input;
		this.output = output;
		this.docid = docid;
		this.index = index;
	}
	String input = "";
	String output = "";
	String docid = "";
	long index = 0;
	public void run(){
		String html;
		try {
			html = getHtml(input, docid, index);
			write(output+"/"+docid, html);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 取硬盘中找文档，然后返回文档的html源文件
	 * @param inputPath	输入文件路径 /media/clueweb09_1of2/ClueWeb09_English_1/en0000/00.warc.gz
	 * @param docid	文档id
	 * @param index	文档在warc.zf文件中的位置
	 * @return	返回文档的html源文件
	 * @throws Exception
	 */
	public String getHtml(String inputPath,  String docid, long index) throws Exception{
		GZIPInputStream gzipInputStream = new GZIPInputStream(
				new FileInputStream(inputPath));
		BufferedReader bReader = new BufferedReader(
				new InputStreamReader(gzipInputStream, "UTF-8"));
		
		String line = "";
		
		bReader.skip(index);
		while (true){
			line = bReader.readLine();
			if (line == null){
				break;
			}
			if (line .startsWith("WARC/0.18")){
				bReader.readLine();
				bReader.readLine();
				bReader.readLine();
				bReader.readLine();
				bReader.readLine();
				line = bReader.readLine();break;
			}else if (line .startsWith("WARC-Type:")){
				bReader.readLine();
				bReader.readLine();
				bReader.readLine();
				bReader.readLine();
				line = bReader.readLine();break;
			}else if (line .startsWith("WARC-Target-URI:")){
				bReader.readLine();
				bReader.readLine();
				bReader.readLine();
				line = bReader.readLine();break;
			}else if (line .startsWith("WARC-Warcinfo-ID:")){
				bReader.readLine();
				bReader.readLine();
				line = bReader.readLine();break;
			}else if (line .startsWith("WARC-Date:")){
				bReader.readLine();
				line = bReader.readLine();break;
			}else if (line .startsWith("WARC-Record-ID")){
				line = bReader.readLine();break;
			}
			if (line.startsWith("WARC-TREC-ID:")){
				String tempdoc = line.substring(line.indexOf("clueweb"));
				if (tempdoc.equals(docid)){
					break;
				}else {
					continue;
				}
			}
		}
		//就是没找到想要的文本喽
		if (line==null){
			return null;
		}
		//不为空则找到了对应文档编号的文档
		//且line停留在WARC-TREC-ID: clueweb09-en0000-00-00000这一行
		String tt = "";
		while (true){
			if ((tt = bReader.readLine()).contains("Content-Length:")){
				break;
			}
		}
		StringBuffer HtmlBuffer = new StringBuffer();
		StringBuffer headbuffer = new StringBuffer();
		boolean hasLength = false;
		//真正的文本都是存放在conteng-length之后的，但是有的文本的content-length有两个，有的没有两个
		//所以，往下找20行，如果遇到了第二个content-length，则现在作为起点。
		//如果没有遇到，说明，刚刚的conteng-length，就是正式文本开始的起点。那就，把他再加回来
		for (int i = 0; i < 20; i++){
			String temp = bReader.readLine();
			headbuffer.append(temp);
			if (temp.contains("Content-Length:")){
				hasLength = true;
				break;
			}
		}
		if (!hasLength){
			HtmlBuffer.append(headbuffer.toString());
		}else {
			HtmlBuffer = new StringBuffer();
		}
		while (true){
			String realline = bReader.readLine();
			if (realline == null){
				break;
			}
			else if (realline.equals("WARC/0.18")){
				break;
			}
			//下面就是正式文本了
			HtmlBuffer.append(realline+"\n");
		}
		bReader.close();
		return HtmlBuffer.toString();
	}
	/**
	 * 将html源文件写入输出文件中
	 * @param outputPath
	 * @param html
	 * @throws Exception
	 */
	public void write(String outputPath, String html) throws Exception{
		File file = new File(outputPath);
		if (!file.exists()){
			file .createNewFile();
		}
		BufferedWriter bWriter = new BufferedWriter(new FileWriter(outputPath));
		if (html !=null){
			bWriter.write(html);
		}
		bWriter.close();
	}
}
