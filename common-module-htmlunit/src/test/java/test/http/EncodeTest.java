package test.http;

import com.module.htmlunit.unit.BaseUnit;

public class EncodeTest {

	public static void main(String[] args) {
		String url  = "http://s.weibo.com/weibo/%2523%25E7%25AA%2581%25E5%258F%2591%2523&rd=newTips";
		String text = BaseUnit.encode(url, "utf8");
		System.out.println(text);
	}

}
