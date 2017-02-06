package com.module.timetask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import weibo4j.model.Status;
import weibo4j.model.StatusWapper;

import com.module.dao.entity.data.Picture;
import com.module.dao.entity.data.Tweet;
import com.module.dao.entity.data.UserFeed;
import com.module.dao.repository.data.PictureDao;
import com.module.dao.repository.data.TweetDao;
import com.module.dao.repository.data.UserFeedDao;
import com.sns.weibo.service.HomeTimelineService;

@Component("homeTimeLineCrawler")
public class HomeTimeLineCrawler {

	private static final Logger log = LoggerFactory
			.getLogger(HomeTimeLineCrawler.class);
	@Autowired
	TweetDao tweetDao;
	@Autowired
	PictureDao pictureDao;
	@Autowired
	UserFeedDao userFeedDao;

	/**	 * @param access_token
	 *            保存发帖
	 */
	public void saveHomeTimeLine() {
		log.info("===============HomeTimeLineCrawler.saveHomeTimeLine() Starting================");
		String access_token = "2.00oTwkUFpHMPpDe31b060d84HBVE1E";
		List<Long> tids = tweetDao.findTidAll();

		Set<Long> tidSet = new HashSet<Long>(tids);
		//for (Long long1 : tidSet) {
		//	System.out.println(long1.toString().length());
		//}

		
		try {
			StatusWapper statusWapper = HomeTimelineService.getHomeTimeline(access_token);
			List<Status> status = statusWapper.getStatuses();
			for (Status s : status) {
				if (!(tidSet.contains(Long.parseLong(s.getId().trim())))) {
					//用户信息
					//如果用户已存在则不保存用户信息，否则保存
					UserFeed uf = new UserFeed();
					if (userFeedDao.findByUid(Long.valueOf(s.getUser().getId())) == null) {
						uf.setUid(Long.valueOf(s.getUser().getId()));//微博用户ID
						uf.setScreen_name(s.getUser().getScreenName());//用户昵称
						uf.setName(s.getUser().getName());//友好显示名称
						uf.setLocation(s.getUser().getLocation());//用户所在地
						uf.setDescription(s.getUser().getDescription());//用户个人描述
						uf.setUrl(s.getUser().getUrl());//用户博客地址
						uf.setProfile_image_url(s.getUser().getProfileImageUrl());//用户头像地址（中图），50×50像素
						uf.setGender(s.getUser().getGender());//性别，m：男、f：女、n：未知
						uf.setFollowers_count(new Long(s.getUser().getFollowersCount()));//粉丝数
						uf.setFriends_count(new Long(s.getUser().getFriendsCount()));//关注数
						uf.setStatuses_count(new Long(s.getUser().getStatusesCount()));//微博数
						uf.setFavourites_count(new Long(s.getUser().getFavouritesCount()));//收藏数
						uf.setCreated_at(s.getUser().getCreatedAt());//用户创建（注册）时间
						
						userFeedDao.save(uf);
					}else{
						uf = userFeedDao.findByUid(Long.valueOf(s.getUser().getId()));
					}
					
					
					Tweet tweet = new Tweet();
					tweet.setTid(Long.parseLong(s.getId().trim()));// 微博ID
					tweet.setText(s.getText()); // 微博信息内容
					tweet.setSource(s.getSource().getName());// 微博来源
					tweet.setReply_to_tid(s.getInReplyToStatusId());// 回复ID
					tweet.setReply_to_uid(s.getInReplyToUserId());// 回复用户ID
					tweet.setCreated_at(s.getCreatedAt());//发帖时间
					tweet.setReposts_count(new Long(s.getRepostsCount()));
					tweet.setComments_count(new Long(s.getCommentsCount()));
					tweet.setUserFeed(uf);
					tweetDao.save(tweet);
					
					if (!(s.getThumbnailPic().equals(null)
							&& s.getBmiddlePic().equals(null) && s.getOriginalPic()
							.equals(null))) {
						List<Picture> picList = new ArrayList<Picture>();
						if (!s.getThumbnailPic().equals(null)) {
							Picture picture = new Picture();
							picture.setTweet(tweet);
							picture.setUrl(s.getThumbnailPic());
							picList.add(picture);
							pictureDao.save(picture);
						}
						if (!s.getBmiddlePic().equals(null)) {
							Picture picture = new Picture();
							picture.setTweet(tweet);
							picture.setUrl(s.getBmiddlePic());
							picList.add(picture);
							pictureDao.save(picture);
						}
						if (!s.getOriginalPic().equals(null)) {
							Picture picture = new Picture();
							picture.setTweet(tweet);
							picture.setUrl(s.getBmiddlePic());
							picList.add(picture);
							pictureDao.save(picture);
						}
						
					}
					
					
				}
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException("HomeTimeLineCrawler.saveHomeTimeLine() 异常", e);
		}
		
		log.info("=============HomeTimeLineCrawler.saveHomeTimeLine() finish !==========");
	}

//	public static void main(String[] args) {
//
//		String access_token = "2.00oTwkUFpHMPpDe31b060d84HBVE1E";
//		HomeTimeLineCrawler homeTimeLineCrawler = new HomeTimeLineCrawler();
//		homeTimeLineCrawler.saveHomeTimeLine(access_token);
//		// saveHomeTimeLine(access_token);
//	}

}
