package com.module.dao.entity.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;

@Entity
@Table(name = "UserFeed")
public class UserFeed extends IdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8600211067446233885L;

	// 微博用户ID
	private Long uid;

	// 用户昵称
	private String screen_name;

	// 友好显示名称
	private String name;

	// 用户所在地
	private String location;

	// 用户个人描述
	private String description;

	// 用户博客地址
	private String url;

	// 用户头像地址（中图），50×50像素
	private String profile_image_url;

	// 性别，m：男、f：女、n：未知
	private String gender;

	// 粉丝数
	private Long followers_count;

	// 粉丝页面
	private String followers_url;

	// 关注数
	private Long friends_count;

	// 关注页面
	private String friends_url;

	// 微博数
	private Long statuses_count;

	// 微博页面
	private String statuses_url;

	// 收藏数
	private Long favourites_count;

	// 用户创建（注册）时间
	private Date created_at;
 
	private Collection<UserFeed> followers;

	//此字段决定是否在页面上显示该人物，0，不显；1，显
	private int enable;
	
	
	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(Long followers_count) {
		this.followers_count = followers_count;
	}

	public Long getFriends_count() {
		return friends_count;
	}

	public void setFriends_count(Long friends_count) {
		this.friends_count = friends_count;
	}

	public Long getStatuses_count() {
		return statuses_count;
	}

	public void setStatuses_count(Long statuses_count) {
		this.statuses_count = statuses_count;
	}

	public Long getFavourites_count() {
		return favourites_count;
	}

	public void setFavourites_count(Long favourites_count) {
		this.favourites_count = favourites_count;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getFollowers_url() {
		return followers_url;
	}

	public void setFollowers_url(String followers_url) {
		this.followers_url = followers_url;
	}

	public String getFriends_url() {
		return friends_url;
	}

	public void setFriends_url(String friends_url) {
		this.friends_url = friends_url;
	}

	public String getStatuses_url() {
		return statuses_url;
	}

	public void setStatuses_url(String statuses_url) {
		this.statuses_url = statuses_url;
	}

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "UserFollower", joinColumns = { @JoinColumn(name = "user", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "follower", referencedColumnName = "id") })
	@OrderColumn
	public Collection<UserFeed> getFollowers() {
		return followers;
	}

	public void setFollowers(Collection<UserFeed> followers) {
		this.followers = followers;
	}

	@Override
	public String toString() {
		return "UserFeed [uid=" + uid + ", screen_name=" + screen_name
				+ ", name=" + name + ", location=" + location
				+ ", description=" + description + ", url=" + url
				+ ", profile_image_url=" + profile_image_url + ", gender="
				+ gender + ", followers_count=" + followers_count
				+ ", followers_url=" + followers_url + ", friends_count="
				+ friends_count + ", friends_url=" + friends_url
				+ ", statuses_count=" + statuses_count + ", statuses_url="
				+ statuses_url + ", favourites_count=" + favourites_count
				+ ", created_at=" + created_at + ", followers=" + followers
				+ "]";
	}

	public UserFeed(long uid,String screen_name,String name,String location,String description,
	        String url,String profile_image_url,String gender,Long followers_count,
	        String followers_url,Long friends_count,String friends_url,Long statuses_count,
	        String statuses_url,Long favourites_count,Date created_at,int enable){
super();
this.uid=uid;
this.screen_name=screen_name;
this.name=name;
this.location=location;
this.description=description;
this.url=url;
this.profile_image_url=profile_image_url;
this.gender=gender;
this.followers_count=followers_count;
this.followers_url=followers_url;
this.friends_count=friends_count;
this.friends_url=friends_url;
this.statuses_count=statuses_count;
this.statuses_url=statuses_url;
this.favourites_count=favourites_count;
this.created_at=created_at;
this.enable=enable;

} 

public UserFeed(){

}
    
	
}
