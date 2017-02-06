import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crawler.domain.json.StatusCodeDef;
import com.crawlermanage.service.gsxt.CompanyTaskService;
import com.crawlermanage.service.iecms.IecmsCompanyTaskService;
import com.crawlermanage.service.qiyezhengxin.ZhengxinTaskService;
import com.crawlermanage.service.shixin.ShixinTaskService;
import com.crawlermanage.service.sxjlcxpt.SxjlcxptCompanyTaskService;
import com.crawlermanage.service.zjsfgkw.ZjsfgkwTaskService;
import com.module.dao.entity.fahaicc.FahaiccRoot;
import com.module.dao.entity.gsxt.Company;
import com.module.dao.entity.gsxt.TQyjbxx;
import com.module.dao.entity.gsxt.TResultJson;
import com.module.dao.entity.iecms.IecmsCompany;
import com.module.dao.repository.fahaicc.FahaiccResultRepository;
import com.module.dao.repository.fahaicc.FahaiccRootRepository;
import com.module.dao.repository.gsxt.CompanyRepository;
import com.module.dao.repository.gsxt.ResultJsonRepository;
import com.module.dao.repository.gsxt.TQyjbxxRepository;
import com.module.dao.repository.iecms.IecmsCompanyRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		//"classpath*:/applicationContext-quartz.xml",
		"classpath*:/applicationContext-shiro.xml",
		"classpath*:/datasource/datasource-auth.xml",
		"classpath*:/datasource/datasource-data.xml",
		"classpath*:/datasource/datasource-doc.xml",
		"classpath*:/datasource/datasource-timeTask.xml",
		"classpath*:/datasource/datasource-gsxt.xml",
		"classpath*:/applicationContext.xml", //注意顺序
		"classpath*:/applicationContext-email.xml",
		"classpath*:/applicationContext-redis.xml"})
public class DBTest {
	
	@Resource
	private ResultJsonRepository resultJsonRepository;
	
	@Resource
	private CompanyTaskService companyTaskService;
	
	@Resource
	private CompanyRepository companyRepository;

	@Resource
	private TQyjbxxRepository tQyjbxxRepository;
	
	@Resource
	private IecmsCompanyTaskService iecmsCompanyTaskService;
	
	@Resource
	private IecmsCompanyRepository iecmsCompanyRepository;
	
	@Resource
	private SxjlcxptCompanyTaskService sxjlcxptCompanyTaskService;
	
	@Resource
	private FahaiccRootRepository fahaiccRootRepository;
	
	@Resource
	private FahaiccResultRepository fahaiccResultRepository;
	
	@Resource
	private ShixinTaskService shixinTaskService;
	
	@Resource
	private ZhengxinTaskService zhengxinTaskService;
	
	@Resource
	private ZjsfgkwTaskService zjsfgkwTaskService;
	
	
	@Test
	public void updateDB() {
		
	}
	
	@Test
	public void resultJsonSave() {
		TResultJson tResultJson = new TResultJson("公司名", "返回结果", new Date());
		resultJsonRepository.save(tResultJson);
	}
	
	@Test
	@Transactional //ok
	public void findByCompanyId() {
		Set<TResultJson> set = resultJsonRepository.findByCompanyId(76810L);
		for (TResultJson tResultJson : set) {
			System.out.println(tResultJson);
			System.out.println(tResultJson.getCompany().getTQyjbxxs()); //加上transactional注解不会报延迟加载异常
		}
	}
	
	@Test
	public void excOneceTaskTest() {
		Map<String, Object> resultMap = companyTaskService.excOneceTask("Shanghai");
		System.out.println(resultMap);
	}
	
	@Test
	public void companySaveTest() {
		Company company = new Company();
		
		company.setName("一通科技有限公司");
		company.setState(0);
		company.setCity("Zhejiang");
		
		companyRepository.save(company);
	}
	
	@Test
	public void companyTriggerTest() { //ok
		Company company = companyRepository.findOne(41397L);
		
		company.setState(0);
		
		companyRepository.save(company);
	}
	
	@Test
	public void findTopByCityAndStateInAndNumLessThanOrderByPriorityDescTest() {
		Company company = companyRepository.findTopByCityAndStateInAndNumLessThanOrderByIdAscPriorityDesc("Beijing", StatusCodeDef.NEED_QUERY_STATES, StatusCodeDef.UPPER_LIMIT_NUM);
		//System.out.println(company);
	}
	
	@Test
	public void qyjbxxDeleteTest() {
		List<TQyjbxx> findByCompany_idIsNull = tQyjbxxRepository.findByCompany_idIsNull();
		tQyjbxxRepository.delete(findByCompany_idIsNull);
	}
	
	@Test
	public void companyInsertTest() { //ok
		Company company = new Company();
		company.setName("太原市晋源区万归公信养殖场");
		company.setCity("Shanxi");
		company.setState(0);
		companyRepository.save(company);
	}
	
	@Test
	public void CreditrRecordQueryPlatformCompanyTaskTest() {
		Map<String, Object> resultMap = sxjlcxptCompanyTaskService.excOneceTask();
		System.out.println(resultMap);
	}
	
	@Test
	public void testIecmsCompanyRepository() {
		IecmsCompany iecmsCompany = iecmsCompanyRepository.findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc("1", StatusCodeDef.NEED_QUERY_STATES,StatusCodeDef.UPPER_LIMIT_NUM);
		System.out.println(iecmsCompany);
	}
	
	@Test
	public void testFahaiccRootSave() {
		FahaiccRoot fahaiccRoot1 = new FahaiccRoot();
		fahaiccRoot1.setKeyword("南阳市凌峰电子科技公司");
		fahaiccRoot1.setType(FahaiccRootRepository.TYPE_NAME);
		fahaiccRootRepository.save(fahaiccRoot1);
		
		FahaiccRoot fahaiccRoot2 = new FahaiccRoot();
		fahaiccRoot2.setKeyword(FahaiccRootRepository.TYPE_NAME);
		fahaiccRoot2.setType("公司");
		fahaiccRootRepository.save(fahaiccRoot2);
		
		FahaiccRoot fahaiccRoot3 = new FahaiccRoot();
		fahaiccRoot3.setKeyword(FahaiccRootRepository.TYPE_NAME);
		fahaiccRoot3.setType("公司");
		fahaiccRootRepository.save(fahaiccRoot3);
		
		FahaiccRoot fahaiccRoot4 = new FahaiccRoot();
		fahaiccRoot4.setKeyword(FahaiccRootRepository.TYPE_NAME);
		fahaiccRoot4.setType("公司");
		fahaiccRootRepository.save(fahaiccRoot4);
		
		FahaiccRoot fahaiccRoot5 = new FahaiccRoot();
		fahaiccRoot5.setKeyword(FahaiccRootRepository.TYPE_NUM);
		fahaiccRoot5.setType("个人");
		fahaiccRootRepository.save(fahaiccRoot5);
	}
	
	@Test
	public void testFahaiccRootUpdate() {
		FahaiccRoot fahaiccRoot = fahaiccRootRepository.findOne(3L);
		System.out.println(fahaiccRoot);
		fahaiccRoot.setState(7);
		fahaiccRoot.setNum(0);
		fahaiccRootRepository.save(fahaiccRoot);
	}
	
	@Test
	public void testFahaiccRootFindTest() {
		FahaiccRoot fahaiccRoot = fahaiccRootRepository.findTopByTypeAndStateInAndNumLessThanOrderByPriorityDesc(FahaiccRootRepository.TYPE_NUM, StatusCodeDef.NEED_QUERY_STATES, StatusCodeDef.UPPER_LIMIT_NUM);
		System.out.println(fahaiccRoot);
	}
	
	@Test
	public void shixinTaskServiceTest() throws IOException, JSONException {
		Map<String, Object> resultMap = shixinTaskService.excOneceTask();
		System.out.println(resultMap);
	}
	
	@Test
	public void zhengxinTaskServiceTest() throws IOException, JSONException {
		Map<String, Object> resultMap = zhengxinTaskService.excOneceTask();
		System.out.println(resultMap);
	}
	
	@Test
	public void zjsfgkwTaskServiceTest() throws IOException, JSONException {
		Map<String, Object> resultMap = zjsfgkwTaskService.excOneceTask();
		System.out.println(resultMap);
	}
	
}
