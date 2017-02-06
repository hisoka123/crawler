package gsxt;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 
 */

/**
 * @author kingly
 * @date 2016年5月31日
 * gson解析List测试
 */
public class GsonParseList {
	public static void main(String[] args) throws IOException {
		Gson gson = new GsonBuilder().create();
		String json = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\test.txt"));
		
		List<Map<String, String>> list = gson.fromJson(json, new TypeToken<List<Map<String, String>>>(){}.getType()); 
		for (Map<String, String> map : list) {
			System.out.println(map);
		}
	}
}
