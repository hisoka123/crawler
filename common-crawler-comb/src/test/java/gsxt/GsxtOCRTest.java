package gsxt;

import java.io.File;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.module.ocr.utils.AbstractChaoJiYingHandler;

public class GsxtOCRTest extends AbstractChaoJiYingHandler{
	private static final String LEN_MIN = "0";
	private static final String TIME_ADD = "0";
	private static final String STR_DEBUG = "a";
	
	@Override
	public String getVerifycode(File file) throws Exception {
		// TODO Auto-generated method stub
		return super.getVerifycodeByChaoJiYing("5000", LEN_MIN, TIME_ADD, STR_DEBUG, file.getAbsolutePath()); //6003 复杂计算题
	}
	
	public static void main(String[] args) throws Exception{
		Gson gson = new GsonBuilder().create();
		GsxtOCRTest gsxtOCRTest = new GsxtOCRTest();
		File fileBook = new File("C:/TCode/szm");
		File[] listFiles = fileBook.listFiles();
		
		for (File file : listFiles) {
			String chaoJiYingResult = gsxtOCRTest.getVerifycode(file);
			String verifycode = (String) gson.fromJson(chaoJiYingResult, Map.class).get("pic_str");
			System.err.println(file.getName()+":"+verifycode);
		}
		
	}
}
