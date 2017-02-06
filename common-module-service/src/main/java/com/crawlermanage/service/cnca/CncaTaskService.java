package com.crawlermanage.service.cnca;

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
import com.crawlermanage.service.transfer.CncaPoVoTransfer;
import com.module.dao.entity.cnca.CFResultJson;
import com.module.dao.entity.cnca.Certificate;
import com.module.dao.entity.cnca.CertificateCompany;
import com.module.dao.repository.cnca.CertificateCompanyRepository;
import com.module.dao.repository.cnca.CertificateRepository;
import com.module.dao.repository.cnca.CertificateResultJsonRepository;

@Service
public class CncaTaskService {

	@Autowired
	private CertificateCompanyRepository certificateCompanyRepository;
	@Autowired
	private CertificateRepository certificateRepository;
	@Autowired
	private CertificateResultJsonRepository certificateResultJsonRepository;
	@Autowired
	private CncaService cncaService;

	private static final boolean IS_DEBUG = true;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CncaTaskService.class);

	@Transactional
	public Map<String, Object> cncaTask() {
		LOGGER.info("=====CncaTaskService.cncaTask is start!=====");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CertificateCompany certificateCompany = null;
		try {
			certificateCompany = certificateCompanyRepository
					.findTopByStateInAndNumLessThanOrderByPriorityDesc(
							StatusCodeDef.NEED_QUERY_STATES,
							StatusCodeDef.UPPER_LIMIT_NUM);
			if (certificateCompany != null) {
				certificateCompany.setState(Integer
						.parseInt(StatusCodeDef.DOING_STATE));
				certificateCompany = certificateCompanyRepository
						.save(certificateCompany);
				Date executetime = new Date();
				Method m1 = ReflectionUtils.findMethod(CncaService.class,
						"searchWithOCR", String.class, boolean.class,
						String.class);
				Object o1 = ReflectionUtils.invokeMethod(m1, cncaService,
						certificateCompany.getName(), IS_DEBUG, "");
				@SuppressWarnings("unchecked")
				Result<List<com.crawler.cnca.domain.json.Certificate>> result1 = (Result<List<com.crawler.cnca.domain.json.Certificate>>) o1;
				List<com.crawler.cnca.domain.json.Certificate> certificateList = result1
						.getData();
				if (null != certificateList && !certificateList.isEmpty()) {
					String resultJsonStr = GsonFactory.getGson().toJson(
							certificateList);
					CFResultJson resultJson = new CFResultJson(
							certificateCompany.getName(), resultJsonStr,
							executetime);
					resultJson.setCertificateCompany(certificateCompany);
					// 要防止懒加载异常
					Set<CFResultJson> cFResultJsons = certificateResultJsonRepository
							.findByCertificateCompany_Id(certificateCompany
									.getId());
					if (cFResultJsons == null) {
						cFResultJsons = new HashSet<CFResultJson>();
					}
					cFResultJsons.add(resultJson);
					certificateCompany.setcFResultJsons(cFResultJsons);
					Set<Certificate> certificates = certificateRepository
							.findByCertificateCompany_Id(certificateCompany
									.getId());
					if (certificates == null) {
						certificates = new HashSet<Certificate>();
					}
					for (com.crawler.cnca.domain.json.Certificate certificate1 : certificateList) {
						Certificate certificate2 = CncaPoVoTransfer
								.voToPo(certificate1);
						if (null != certificate2) {
							certificate2.setExecutetime(executetime);
							certificate2
									.setCertificateCompany(certificateCompany);
							certificates.add(certificate2);
						}
					}
					certificateCompany.setCertificates(certificates);
					certificateCompany.setState(Integer
							.parseInt(StatusCodeDef.GET_DATA_SCCCESS));
					// 返回输入参数
					Map<String, Object> arguments = new HashMap<String, Object>();
					arguments.put("company", certificateCompany.getName());
					resultMap.put("arguments", arguments);
					resultMap.put("cName", certificateCompany.getName());
					resultMap.put("cId", certificateCompany.getId());
					LOGGER.info("================将公司状态由未入库变为入库即将state由-3变为1==="
							+ certificateCompany.getName()
							+ "========================================="
							+ certificateCompany.getState());
				} else {
					String errorCode = result1.getError().getErrorCode();
					int errorCodeInt = Integer.parseInt(errorCode);
					certificateCompany.setState(errorCodeInt);
					// 返回输入参数
					Map<String, Object> arguments = new HashMap<String, Object>();
					arguments.put("company", certificateCompany.getName());
					resultMap.put("arguments", arguments);
					resultMap.put("cName", certificateCompany.getName());
					resultMap.put("cId", certificateCompany.getId());
					LOGGER.info("================将公司状态由未入库变为入库即将state由-3变为1==="
							+ certificateCompany.getName()
							+ "========================================="
							+ certificateCompany.getState());
				}
				Integer num = certificateCompany.getNum() == null ? 1
						: (certificateCompany.getNum() + 1);
				certificateCompany.setNum(num);
				Integer totalNum = certificateCompany.getTotalNum() == null ? 1
						: (certificateCompany.getTotalNum() + 1);
				certificateCompany.setTotalNum(totalNum);
				// 保存
				certificateCompanyRepository.save(certificateCompany);
				LOGGER.info("==========result1 is " + result1.toString()
						+ "==========");
				resultMap.put("APIresult", result1);
			}
		} catch (Exception e) {
			if (certificateCompany != null) {
				certificateCompany.setState(Integer
						.parseInt(StatusCodeDef.WEB_SERVICE_EXCEPTION));
				certificateCompanyRepository.save(certificateCompany);
				Map<String, Object> arguments = new HashMap<String, Object>();
				arguments.put("company", certificateCompany.getName());
				resultMap.put("arguments", arguments);
				resultMap.put("cName", certificateCompany.getName());
				resultMap.put("cId", certificateCompany.getId());
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw, true);
				sw.append("-------------------");
				sw.append(certificateCompany.getName());
				e.printStackTrace(pw);
				pw.flush();
				sw.flush();
				resultMap.put("Exception", sw.toString());
			} else {
				e.printStackTrace();
			}
		}
		LOGGER.info("=====resultMap is " + resultMap.toString() + "=====");
		LOGGER.info("=====CncaTaskService.cncaTask is end!=====");
		return resultMap;
	}

}
