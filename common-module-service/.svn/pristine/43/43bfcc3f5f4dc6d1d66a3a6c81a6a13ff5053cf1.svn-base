package com.crawlermanage.service.creditchina;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.htmlparser.GsonFactory;
import com.crawlermanage.service.transfer.CreditchinaPoVoTransfer;
import com.module.dao.entity.creditchinatwo.BDResultJson;
import com.module.dao.entity.creditchinatwo.CompanyRecord;
import com.module.dao.entity.creditchinatwo.CreditCompany;
import com.module.dao.repository.creditchinatwo.BDResultJsonRepository;
import com.module.dao.repository.creditchinatwo.CompanyRecordRepository;
import com.module.dao.repository.creditchinatwo.CreditCompanyRepository;

@Service
public class CreditchinaTaskService {

	@Autowired
	private CreditCompanyRepository creditCompanyRepository;
	@Autowired
	private CompanyRecordRepository companyRecordRepository;
	@Autowired
	private BDResultJsonRepository bDResultJsonRepository;
	@Autowired
	private CreditchinaSearchService creditchinaSearchService;

	private static final boolean IS_DEBUG = true;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CreditchinaTaskService.class);

	@Transactional
	public Map<String, Object> creditchinaTask() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CreditCompany creditCompany = null;
		try {
			creditCompany = creditCompanyRepository
					.findTopByStateInAndNumLessThanOrderByPriorityDesc(
							StatusCodeDef.NEED_QUERY_STATES,
							StatusCodeDef.UPPER_LIMIT_NUM);
			if (creditCompany != null) {
				creditCompany.setState(Integer
						.parseInt(StatusCodeDef.DOING_STATE));
				creditCompany = creditCompanyRepository.save(creditCompany);
				Date executetime = new Date();
				Method m1 = ReflectionUtils
						.findMethod(CreditchinaSearchService.class,
								"searchWithOCR", String.class, String.class,
								boolean.class, String.class);
				Object o1 = ReflectionUtils.invokeMethod(m1,
						creditchinaSearchService, creditCompany.getName(),
						creditCompany.getObjectType(), IS_DEBUG, "");
				@SuppressWarnings("unchecked")
				Result<List<com.crawler.creditchina.domain.jsontwo.CompanyRecord>> result1 = (Result<List<com.crawler.creditchina.domain.jsontwo.CompanyRecord>>) o1;
				List<com.crawler.creditchina.domain.jsontwo.CompanyRecord> CreditchinaList = result1
						.getData();
				if (null != CreditchinaList && !CreditchinaList.isEmpty()) {
					String resultJsonStr = GsonFactory.getGson().toJson(
							CreditchinaList);
					BDResultJson resultJson = new BDResultJson(
							creditCompany.getName(),
							creditCompany.getObjectType(), resultJsonStr,
							executetime);
					resultJson.setCreditCompany(creditCompany);
					// 要防止懒加载异常
					Set<BDResultJson> bDResultJsons = bDResultJsonRepository
							.findByCreditCompany_Id(creditCompany.getId());
					if (bDResultJsons == null) {
						bDResultJsons = new HashSet<BDResultJson>();
					}
					bDResultJsons.add(resultJson);
					creditCompany.setbDResultJsons(bDResultJsons);
					Set<CompanyRecord> creditChinas = companyRecordRepository
							.findByCreditCompany_Id(creditCompany.getId()); // customsCompany.getRegInformations();
					if (creditChinas == null) {
						creditChinas = new HashSet<CompanyRecord>();
					}
					for (com.crawler.creditchina.domain.jsontwo.CompanyRecord wd : CreditchinaList) {
						CompanyRecord voToPo = CreditchinaPoVoTransfer
								.voToPo(wd);
						if (null != voToPo) {
							voToPo.setExecutetime(executetime);
							voToPo.setCreditCompany(creditCompany);
							creditChinas.add(voToPo);
						}
					}
					creditCompany.setCompanyRecords(creditChinas);
					creditCompany.setState(Integer
							.parseInt(StatusCodeDef.GET_DATA_SCCCESS));
					// 返回输入参数
					Map<String, Object> arguments = new HashMap<String, Object>();
					arguments.put("company", creditCompany.getName());
					resultMap.put("arguments", arguments);
					resultMap.put("cName", creditCompany.getName());
					resultMap.put("cId", creditCompany.getId());
					LOGGER.info("================将公司状态由未入库变为入库即将state由-3变为1==="
							+ creditCompany.getName()
							+ "========================================="
							+ creditCompany.getState());
				} else {
					String errorCode = result1.getError().getErrorCode();
					int errorCodeInt = Integer.parseInt(errorCode);
					creditCompany.setState(errorCodeInt);
					// 返回输入参数
					Map<String, Object> arguments = new HashMap<String, Object>();
					arguments.put("company", creditCompany.getName());
					resultMap.put("arguments", arguments);
					resultMap.put("cName", creditCompany.getName());
					resultMap.put("cId", creditCompany.getId());
					LOGGER.info("================将公司状态由未入库变为对应的异常状态==="
							+ creditCompany.getName()
							+ "========================================="
							+ creditCompany.getState());
				}
				Integer num = creditCompany.getNum() == null ? 1
						: (creditCompany.getNum() + 1);
				creditCompany.setNum(num);
				Integer totalNum = creditCompany.getTotalNum() == null ? 1
						: (creditCompany.getTotalNum() + 1);
				creditCompany.setTotalNum(totalNum);
				// 保存
				creditCompanyRepository.save(creditCompany);
				LOGGER.info("==========result1 is " + result1.toString()
						+ "==========");
				resultMap.put("APIresult", result1);
			}
		} catch (Exception e) {
			if (creditCompany != null) {
				creditCompany.setState(Integer
						.parseInt(StatusCodeDef.WEB_SERVICE_EXCEPTION));
				creditCompanyRepository.save(creditCompany);
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("company", creditCompany.getName());
				resultMap.put("arguments", arguments);
				resultMap.put("cName", creditCompany.getName());
				resultMap.put("cId", creditCompany.getId());
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw, true);
				sw.append("-------------------");
				sw.append(creditCompany.getName());
				e.printStackTrace(pw);
				pw.flush();
				sw.flush();
				resultMap.put("Exception", sw.toString());
			} else {
				e.printStackTrace();
			}
		}
		LOGGER.info("=====resultMap is " + resultMap.toString() + "=====");
		return resultMap;
	}

}
