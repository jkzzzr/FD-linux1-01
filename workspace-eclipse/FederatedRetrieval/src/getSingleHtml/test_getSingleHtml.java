package getSingleHtml;

public class test_getSingleHtml {

	public static void main(String[] args) {
		test2();
	}

	public static void test1(){
		GetSingleHtml getSingleHtml = new GetSingleHtml();
		getSingleHtml.docid = "clueweb09-en0002-08-00000";
		getSingleHtml.index = 419L;
		getSingleHtml.input = "/media/clueweb09_1of2/ClueWeb09_English_1/en0002/08.warc.gz";
		getSingleHtml.output = "/home/Lee/data/test/111";
		getSingleHtml.run(1);
		System.out.println("end");
	}
	public static void test2(){
		GetSingleHtml getSingleHtml = new GetSingleHtml();
		getSingleHtml.docid = "clueweb12-0000tw-00-24460";
		getSingleHtml.index = 74425451L;
		getSingleHtml.input = "//home/Lee/data/test/0000tw-00.warc.gz";
		getSingleHtml.output = "/home/Lee/data/test/111";
		getSingleHtml.run(2);
		System.out.println("end");
	}
}
