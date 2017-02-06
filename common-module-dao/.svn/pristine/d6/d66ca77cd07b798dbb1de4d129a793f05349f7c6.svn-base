package com.module.dao.entity.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Tweet")
public class Tweet extends IdEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4161153799096442982L;


	private Long tid;
	
	//微博信息内容
	private String text;

	//微博来源
	private String source;
	 
	//回复ID
	private Long reply_to_tid;
	
	//回复用户ID
	private Long reply_to_uid;
	
	//微博图片（多张） 
	private List<Picture> pic ;
	
	//入库时间（系统自动赋值）
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date();
	
	//发帖时间
	private Date created_at;
	
	//发帖人信息
	private UserFeed userFeed;
	
	//转发数
	private Long reposts_count;
	
	//回帖数
	private Long comments_count;
	//地名
	private Region region;
	@ManyToOne
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	//微博ID
	@NotNull
	@Column(unique=true)						
	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getReply_to_tid() {
		return reply_to_tid;
	}

	public void setReply_to_tid(Long reply_to_tid) {
		this.reply_to_tid = reply_to_tid;
	}

	public Long getReply_to_uid() {
		return reply_to_uid;
	}

	public void setReply_to_uid(Long reply_to_uid) {
		this.reply_to_uid = reply_to_uid;
	}
 
	@OneToMany(mappedBy = "tweet", cascade = CascadeType.REMOVE)
	public List<Picture> getPic() {
		return pic;
	}

	public void setPic(List<Picture> pic) {
		this.pic = pic;
	}

	public Date getCreateTime() {
		return createTime;
	}

 	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	} 
	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@OneToOne(cascade=CascadeType.PERSIST)
	public UserFeed getUserFeed() {
		return userFeed;
	}

	public void setUserFeed(UserFeed userFeed) {
		this.userFeed = userFeed;
	}

	public Long getReposts_count() {
		return reposts_count;
	}

	public void setReposts_count(Long reposts_count) {
		this.reposts_count = reposts_count;
	}

	public Long getComments_count() {
		return comments_count;
	}

	public void setComments_count(Long comments_count) {
		this.comments_count = comments_count;
	}

	@Override
	public String toString() {
		return "Tweet [tid=" + tid + ", text=" + text + ", source=" + source
				+ ", reply_to_tid=" + reply_to_tid + ", reply_to_uid="
				+ reply_to_uid + ", pic=" + pic + ", createTime=" + createTime
				+ ", created_at=" + created_at + ", userFeed=" + userFeed
				+ ", reposts_count=" + reposts_count + ", comments_count="
				+ comments_count + ", region=" + region + "]";
	}
	
	
	
	

}
