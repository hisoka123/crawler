package com.crawlermanage.service.customs;

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

import com.crawler.customs.domain.json.Customs;
import com.crawler.domain.json.Result;
import com.crawler.domain.json.StatusCodeDef;
import com.crawler.htmlparser.GsonFactory;
import com.crawlermanage.service.transfer.CustomsPoVoTransfer;
import com.module.dao.entity.customs.CUResultJson;
import com.module.dao.entity.customs.CustomsCompany;
import com.module.dao.entity.customs.RegInformation;
import com.module.dao.repository.customs.CustomsCompanyRepository;
import com.module.dao.repository.customs.CustomsResultJsonRepository;
import com.module.dao.repository.customs.RegInformationRepository;

@Service
public class CustomsTaskService {

	@Autowired
	private CustomsCompanyRepository customsCompanyRepository;
	@Autowired
	private RegInformationRepository regInformationRepository;
	@Autowired
	private CustomsResultJsonRepository customsResultJsonRepository;
	@Autowired
	private CustomsService customsService;

	private static final boolean IS_DEBUG = true;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomsTaskService.class);

	@Transactional
	public Map<String, Object> customsTask() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CustomsCompany customsCompany = null;
		try {
			customsCompany = customsCompanyRepository
					.findTopByStateInAndNumLessThanOrderByPriorityDesc(
							StatusCodeDef.NEED_QUERY_STATES,
							StatusCodeDef.UPPER_LIMIT_NUM);
			if (customsCompany != null) {
				customsCompany.setState(Integer
						.parseInt(StatusCodeDef.DOING_STATE));
				customsCompany = customsCompanyRepository.save(customsCompany);
				Date executetime = new Date();
				Method m1 = ReflectionUtils.findMethod(CustomsService.class,
						"searchWithOCR", String.class, boolean.class,
						String.class);
				Object o1 = ReflectionUtils.invokeMethod(m1, customsService,
						customsCompany.getName(), IS_DEBUG, "");
				@SuppressWarnings("unchecked")
				Result<List<Customs>> result1 = (Result<List<Customs>>) o1;
				// String json = FileUtils.readFileToString(new
				// File("C:/Users/罗俊平/Desktop/a_201606151059.sql"));
				// Result<List<Customs>> result1 = new
				// GsonBuilder().create().fromJson(json, new
				// TypeToken<Result<List<Customs>>>(){}.getType());
				List<Customs> customsList = result1.getData();
				if (null != customsList && !customsList.isEmpty()) {
					String resultJsonStr = GsonFactory.getGson().toJson(
							customsList);
					CUResultJson resultJson = new CUResultJson(
							customsCompany.getName(), resultJsonStr,
							executetime);
					resultJson.setCustomsCompany(customsCompany);
					// 要防止懒加载异常
					Set<CUResultJson> cUResultJsons = customsResultJsonRepository
							.findByCustomsCompany_Id(customsCompany.getId());
					if (cUResultJsons == null) {
						cUResultJsons = new HashSet<CUResultJson>();
					}
					cUResultJsons.add(resultJson);
					customsCompany.setcUResultJsons(cUResultJsons);
					Set<RegInformation> regInformations = regInformationRepository
							.findByCustomsCompany_Id(customsCompany.getId()); // customsCompany.getRegInformations();
					if (regInformations == null) {
						regInformations = new HashSet<RegInformation>();
					}
					for (Customs customs : customsList) {
						RegInformation regInformation = CustomsPoVoTransfer
								.voToPo(customs);
						if (null != regInformation) {
							regInformation.setExecutetime(executetime);
							regInformation.setCustomsCompany(customsCompany);
							regInformations.add(regInformation);
						}
					}
					customsCompany.setRegInformations(regInformations);
					customsCompany.setState(Integer
							.parseInt(StatusCodeDef.GET_DATA_SCCCESS));
					// 返回输入参数
					Map<String, Object> arguments = new HashMap<String, Object>();
					arguments.put("company", customsCompany.getName());
					resultMap.put("arguments", arguments);
					resultMap.put("cName", customsCompany.getName());
					resultMap.put("cId", customsCompany.getId());
					LOGGER.info("================将公司状态由未入库变为入库即将state由-3变为1==="
							+ customsCompany.getName()
							+ "========================================="
							+ customsCompany.getState());
				} else {
					String errorCode = result1.getError().getErrorCode();
					int errorCodeInt = Integer.parseInt(errorCode);
					customsCompany.setState(errorCodeInt);
					// 返回输入参数
					Map<String, Object> arguments = new HashMap<String, Object>();
					arguments.put("company", customsCompany.getName());
					resultMap.put("arguments", arguments);
					resultMap.put("cName", customsCompany.getName());
					resultMap.put("cId", customsCompany.getId());
					LOGGER.info("================将公司状态由未入库变为对应的异常状态==="
							+ customsCompany.getName()
							+ "========================================="
							+ customsCompany.getState());
				}
				Integer num = customsCompany.getNum() == null ? 1
						: (customsCompany.getNum() + 1);
				customsCompany.setNum(num);
				Integer totalNum = customsCompany.getTotalNum() == null ? 1
						: (customsCompany.getTotalNum() + 1);
				customsCompany.setTotalNum(totalNum);
				// 保存
				customsCompanyRepository.save(customsCompany);
				LOGGER.info("==========result1 is " + result1.toString()
						+ "==========");
				resultMap.put("APIresult", result1);
			}
		} catch (Exception e) {
			if (customsCompany != null) {
				customsCompany.setState(Integer
						.parseInt(StatusCodeDef.WEB_SERVICE_EXCEPTION));
				customsCompanyRepository.save(customsCompany);
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("company", customsCompany.getName());
				resultMap.put("arguments", arguments);
				resultMap.put("cName", customsCompany.getName());
				resultMap.put("cId", customsCompany.getId());
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw, true);
				sw.append("-------------------");
				sw.append(customsCompany.getName());
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
