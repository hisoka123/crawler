package com.module.timetask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.thrift7.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import backtype.storm.generated.DRPCExecutionException;

import com.crawler.weibo.domain.json.TweetJson;
import com.crawlermanage.service.RegionService;
import com.crawlermanage.service.weibo.WeiboTopicService;
import com.module.dao.entity.data.Picture;
import com.module.dao.entity.data.Region;
import com.module.dao.entity.data.Tweet;
import com.module.dao.entity.data.UserFeed;
import com.module.dao.repository.data.PictureDao;
import com.module.dao.repository.data.TweetDao;
import com.module.dao.repository.data.UserFeedDao;

@Component("sinaEmergencyStore")
@Transactional
public class SinaEmergencyStore {
	private static final Logger log = LoggerFactory
			.getLogger(SinaEmergencyStore.class);
	@Autowired
	TweetDao tweetDao;
	@Autowired
	PictureDao pictureDao;
	@Autowired
	UserFeedDao userFeedDao;
	@Autowired
	WeiboTopicService weiboTopicService;
	@Autowired
	RegionService regionService;

	/**	 * @param url
	 *            突发事件入库
	 * @param jobGroup 
	 * @param jobName 
	 * @throws DRPCExecutionException 
	 * @throws TException 
	 */
	public Map<String, Object> saveSinaBurstout(String jobName, String jobGroup) throws TException, DRPCExecutionException{
		log.info("=======*突发事件入库*========SinaBurstoutStore.saveSinaBurstout() Starting================");
		List<String> urlList = new ArrayList<String>();
		//#突发#
		urlList.add("http://s.weibo.com/weibo/%2523%25E7%25AA%2581%25E5%258F%2591%2523&rd=newTips");
		//#突发事件#
		urlList.add("http://s.weibo.com/weibo/%2523%25E7%25AA%2581%25E5%258F%2591%25E4%25BA%258B%25E4%25BB%25B6%2523&rd=newTips");
		//#突发新闻#
		urlList.add("http://s.weibo.com/weibo/%2523%25E7%25AA%2581%25E5%258F%2591%25E6%2596%25B0%25E9%2597%25BB%2523&rd=newTips");
		List<Long> tids = tweetDao.findTidAll();

		Set<Long> tidSet = new HashSet<Long>(tids);
		List<Tweet> tweets = new ArrayList<Tweet>();
		List<Long> ids = new ArrayList<Long>();
		List<TweetJson> tweetJsons = new ArrayList<TweetJson>();
		for (String url : urlList) {
			log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++urlList.size()++++++++++++"+urlList.size()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			List<TweetJson> jsons = weiboTopicService.getTopicTweets(url, false,"").getData();
			tweetJsons.addAll(jsons);
			System.err.println("=====================================================================================================");
			log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++tweetJsons.size()++++++++"+tweetJsons.size()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
tweetJsons:	for (TweetJson tweetJson : tweetJsons) {
				if (!(tidSet.contains(Long.parseLong(tweetJson.getTid().trim()))) 
						&& null != tweetJson.getContent_text()
						&& null != tweetJson.getCreated_at()
						&& !"".equals(tweetJson.getContent_text())
						) {
					List<Region> regionList = regionService.getAllMainlandProvince();
					Map<Integer, Region> regionMap = new HashMap<Integer, Region>();
					Region region = null;
	region:			for (int i = 0; i < regionList.size(); i++) {
						//判断新增爆发事件是否包含地点字段
						if(tweetJson.getContent_text().contains(regionList.get(i).getRegionChiName())){
							//以region在微博内容中的index大小为键，以region为值，将其存进map内寻找index最小的region为所取region
							regionMap.put(Integer.valueOf(tweetJson.getContent_text().indexOf(regionList.get(i).getRegionChiName())), regionList.get(i));
							log.info("+++++++++++++++++++++++++++++++++afdindexSet"+regionMap.toString()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
							log.info("+++++++++++++++++++++++++++++++++afdindexSet"+regionList.get(i).getRegionChiName()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
							log.info("+++++++++++++++++++++++++++++++++regionList.size()"+regionList.size()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						}
						if(i < (regionList.size()-1)){
							continue region;
						}
						Set<Integer> indexSet = regionMap.keySet();
						log.info("+++++++++++++++++++++++++++++++++indexSet.toString()"+indexSet.toString()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						if(!indexSet.isEmpty()){
						Integer index = Collections.min(indexSet);
						log.info("+++++++++++++++++++++++++++++++++index.toString()"+index.toString()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						region = regionMap.get(index); 
						log.info("+++++++++++++++++++++++++++++++++region.toString()"+region.toString()+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						}
						//当regionList循环遍历全部之后
						if(null != region && i == (regionList.size() -1)){
							//如果是转发贴，//前若有地理信息则入库
							if(tweetJson.getContent_text().contains("//")){
								String repostRegion = tweetJson.getContent_text().substring(0, tweetJson.getContent_text().indexOf("//"));
								if(!repostRegion.contains(region.getRegionChiName())){
									continue tweetJsons;
								}
							}
							//用户信息
							//如果用户已存在则不保存用户信息，否则保存
							UserFeed uf = new UserFeed();
							if(null != tweetJson.getUid()){
							if (userFeedDao.findByUid(Long.valueOf(tweetJson.getUid())) == null) {
								uf.setUid(Long.valueOf(tweetJson.getUid()));//微博用户ID
								uf.setScreen_name(tweetJson.getNickname());//用户昵称
								userFeedDao.save(uf);
							}else{
								uf = userFeedDao.findByUid(Long.valueOf(tweetJson.getUid()));
							}
							}
							
							Tweet tweet = new Tweet();
							tweet.setRegion(region);//保存地点信息
							tweet.setTid(Long.parseLong(tweetJson.getTid()));// 微博ID
							tweet.setText(tweetJson.getContent_text()); // 微博信息内容
							tweet.setSource(tweetJson.getSource());// 微博来源
							//如果回帖不为空，则把回帖信息入库
							if(null !=tweetJson.getRetweet() && !"".equals(tweetJson.getRetweet())){
							tweet.setReply_to_tid(Long.parseLong(tweetJson.getRetweet().getTid()));// 回复ID
							tweet.setReply_to_uid(Long.parseLong(tweetJson.getRetweet().getUid()));// 回复用户ID
							}
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
							try {
								if(null != tweetJson.getCreated_at()){
								Date date = formatter.parse(tweetJson.getCreated_at());
								tweet.setCreated_at(date);//发帖时间
								}
							} catch (ParseException e) {
								throw new RuntimeException("突发事件微博创建时间解析异常", e);
							}
							
							//如果转发数与评论数不为空则入库
							if(tweetJson.getReposts_count() != null && !"".equals(tweetJson.getReposts_count())){
							tweet.setReposts_count(new Long(tweetJson.getReposts_count()));
							}
							if(tweetJson.getComments_count() != null && !"".equals(tweetJson.getReposts_count())){
							tweet.setComments_count(new Long(tweetJson.getComments_count()));
							}
							tweet.setUserFeed(uf);
							tweetDao.save(tweet);
							
							if (null != tweetJson.getPic_urls()) {
								String[] pic_urls = tweetJson.getPic_urls();
								List<Picture> picList = new ArrayList<Picture>();
								for (String pic_url : pic_urls) {
									Picture picture = new Picture();
									picture.setTweet(tweet);
									picture.setUrl(pic_url);
									picList.add(picture);
									pictureDao.save(picture);
								}
								tweet.setPic(picList);
							}
							//如果微博内容包含地点保存信息后，则退出地点校验循环
							tweets.add(tweet);
							ids.add(tweet.getId());
						}
					}
				}
			}
			log.info("=============SinaBurstoutStore.saveSinaBurstout() finish !==========");
			Map<String , Object> map = new HashMap<String, Object>();
			map.put("tweets", tweets);
			map.put("ids", ids);
			return map;
		
	}

//	public static void main(String[] args) {
//
//		String access_token = "2.00oTwkUFpHMPpDe31b060d84HBVE1E";
//		HomeTimeLineCrawler homeTimeLineCrawler = new HomeTimeLineCrawler();
//		homeTimeLineCrawler.saveHomeTimeLine(access_token);
//		// saveHomeTimeLine(access_token);
//	}
	
}
