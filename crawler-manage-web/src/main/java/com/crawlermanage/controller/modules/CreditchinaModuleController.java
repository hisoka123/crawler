package com.crawlermanage.controller.modules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.creditchina.domain.jsontwo.CompanyRecord;
import com.crawler.domain.json.ErrorMsg;
import com.crawler.domain.json.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/modules/creditchina")
public class CreditchinaModuleController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CreditchinaModuleController.class);

	@Autowired
	private Env env;

	@RequestMapping("/")
	public String redirectToSearch1(Model model) {
		model.addAttribute("env", env.getEnv());
		return "/modules/creditchina/creditchinaSearch";
	}

	@RequestMapping("")
	public String redirectToSearch2(Model model) {
		model.addAttribute("env", env.getEnv());
		return "/modules/creditchina/creditchinaSearch";
	}

	@RequestMapping("/creditchinaSearch")
	public String toCreditchinaSearch(Model model) {
		model.addAttribute("env", env.getEnv());
		return "/modules/creditchina/creditchinaSearch";
	}

	@RequestMapping("/creditchinaDetail")
	public String toCreditchinaDetail(
			@RequestParam("creditchinaDetail") String creditchinaDetail,
			Model model) {
		LOGGER.info("==============toCreditchinaDetail is start!============================");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<CompanyRecord> result = gson.fromJson(creditchinaDetail,
				new TypeToken<List<CompanyRecord>>() {
				}.getType());
		model.addAttribute("creditchinaResults", result);
		model.addAttribute("env", env.getEnv());
		LOGGER.info("==============toCreditchinaDetail is end!==============================");
		return "/modules/creditchina/creditchinaDetail";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/creditchinaJson")
	public String tocreditchinaJson(@RequestParam("data") String data,
			@RequestParam("error") String error, Model model) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Result<List<CompanyRecord>> result = new Result<List<CompanyRecord>>();
		if (error.equals("eNull")) {
			result.setData((List<CompanyRecord>) gson.fromJson(data,
					new TypeToken<List<CompanyRecord>>() {
					}.getType()));
		} else if (data.equals("dNull")) {
			result.setError((ErrorMsg) gson.fromJson(error,
					new TypeToken<ErrorMsg>() {
					}.getType()));
		}
		model.addAttribute("creditchinaDetail", gson.toJson(result));
		model.addAttribute("env", env.getEnv());
		return "/modules/creditchina/creditchinaJson";
	}

}
