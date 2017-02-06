package com.module.timetask.aspect;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crawlermanage.service.AlertService;
import com.module.dao.entity.data.Picture;
import com.module.dao.entity.data.Tweet;
import com.module.mail.MimeMailService;

/**
 * 该切面向所有含有新微博入库提醒邮箱发送邮件
 *
 */
@Component
@Aspect
public class SaveSinaBurstoutAspect {

	@Autowired
	private MimeMailService mailService;
	@Autowired
	private AlertService alertService;
	private static final Logger log = LoggerFactory.getLogger(SaveSinaBurstoutAspect.class);
	
	@Pointcut("execution(* com.module.timetask.SinaEmergencyStore.saveSinaBurstout(..))")
	public void pointCut() {
	}


	@AfterReturning(pointcut = "pointCut()", returning = "returnVal")
	public void afterReturning(JoinPoint joinPoint, Map<String, Object> returnVal) {
		log.info("==========++++++++++++++++++++=================突发事件入库报警邮件======++++++++++++++=========================++++++++++++++++++++++++++++");
		if(null != returnVal && !"".equals(returnVal)){
		List<Tweet> tweets = (List<Tweet>) returnVal.get("tweets");
		Map<String, Set<String>> regionEmailMap = alertService.findEmailByRegion();
		
		if(null != tweets && !"".equals(tweets)){
		for (Tweet tweet : tweets) {
			log.info("==============Region======"+tweet.getRegion().getRegionChiName()+"=====================================");
			String text = "<p>"+tweet.getText()+"</p>";
			String subject = "";
			//判断是否有图
			if(null != tweet.getPic() && !"".equals(tweet.getPic())){
				List<Picture> pictures = tweet.getPic();
				//循环加载图片
				for (Picture picture : pictures) {
					String picHtml = "<span><img src="+picture.getUrl().replace("square", "bmiddle")+" />&nbsp;</span>";
					text += picHtml;
				}
				//如果没有发帖人则不显示来源
				subject = "图.#突发事件#["+tweet.getUserFeed().getScreen_name()+"] — "+tweet.getRegion().getRegionChiName();
			}else{
				subject = "#突发事件#["+tweet.getUserFeed().getScreen_name()+"] — "+tweet.getRegion().getRegionChiName();
			}
			subject = subject.replace("[null]", "");
			Set<String>  emailSet = regionEmailMap.get(tweet.getRegion().getRegionChiName());
			log.info("==============text======"+text+"=====================================");
			log.info("==============text======"+subject+"=====================================");
			for (String email : emailSet) {
				String sendTo = email;
				//发送邮件
				log.info("==============text======"+sendTo+"=====================================");
				mailService.sendMail(text, sendTo, subject);
			}
			
			
			
		}
		}
	}
	}
	
}
