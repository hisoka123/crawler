/**
 * 
 */
package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crawler.linkedin.domain.json.UserFeedJson;
import com.module.redis.domain.CookieJson;
import com.module.redis.service.CookieService;
import com.module.redis.service.LinkedinUserService;
import com.module.redis.service.PbccrcPDFCreditReportService;

/**
 * @author kingly
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-redis.xml")
public class RedisTest02 {
	@Autowired
	private CookieService cookieService;
	@Autowired
	private LinkedinUserService linkedinUserService;
	@Autowired
	private PbccrcPDFCreditReportService pbccrcPDFCreditReportService;
	
	
	@Test
	public void testQueryAll() {
		List<CookieJson> cookies = cookieService.queryAllCookies();
		System.out.println("==================================testQueryAll Begin==================================================");
		for (CookieJson cookie : cookies) {
			System.out.println(cookie);
		}
		System.out.println("=======================================testQueryAll End=============================================");
	}
	
	@Test
	public void testSave() {
		System.out.println("====================================testSave Begin================================================");
		String cookies = "[{\"domain\":\".adhigh.net\",\"expirationDate\":\"1469763053.881736\",\"hostOnly\":\"false\",\"httpOnly\":\"false\",\"name\":\"gi_u2\",\"path\":\"/\",\"secure\":\"false\",\"session\":\"false\",\"storeId\":\"0\",\"value\":\"gjQKRqYAjAl\"}]";
		cookieService.save(cookies);
		System.out.println("====================================testSave End================================================");
	}
	
	@Test
	public void testSaddUserProfiles() {
		List<String> profiles = new ArrayList<String>();
		profiles.add("11111111111111");
		profiles.add("22222222222222");
		profiles.add("33333333333333");
		profiles.add("44444444444444");
		Long result = linkedinUserService.saddUserProfiles(profiles);
		System.out.println("============" + result + "======================================");
	}
	
	@Test
	public void testSpopUserProfile() {
		String profile = linkedinUserService.spopUserProfile();
		System.out.println("=========" + profile + "============");
	}
	
	@Test
	public void testHmsetUser() {
		UserFeedJson user = new UserFeedJson();
		user.setUid("");
		user.setName("");
		user.setHeadline("headLine");
		user.setLocation("location");
		user.setIndustry("软件");
		linkedinUserService.hmsetUser(user);
	}
	
	@Test
	public void testDeleteCookies() {
		cookieService.deleteAllCookies();
	}
	
	@Test
	public void testPbccrcPDFCreditReportService() {
		String saveResult = pbccrcPDFCreditReportService.cachePDFReportURL("kinglyjn", "http://www.baidu.com/kinglyjn");
		System.out.println("=============================>" + saveResult);
		
		String pdfUrl = pbccrcPDFCreditReportService.getPDFReportURLByUserName("kinglyjn");
		System.out.println("=============================>" + pdfUrl);
	}
	
}
