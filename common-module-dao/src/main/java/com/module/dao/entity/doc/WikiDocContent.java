package com.module.dao.entity.doc;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "WikiDocContent")
@SequenceGenerator(name="wikiDocContentSeq",sequenceName="wikiDocContent_sequence",allocationSize=60)
public class WikiDocContent implements Serializable {
	private static final long serialVersionUID = -3798869561964572212L;

	@Id
	@GeneratedValue(generator="wikiDocContentSeq",strategy = GenerationType.SEQUENCE)
	private Long id;


	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition="TEXT")
	private String content;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JoinColumn(name = "wdb_id", referencedColumnName = "id", nullable = false)
	private WikiDocBase wikiDocBase;
	
	public WikiDocContent(){}
	
	public WikiDocContent(Long id,String content){
		   this.id=id;
		   this.content=content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WikiDocBase getWikiDocBase() {
		return wikiDocBase;
	}

	public void setWikiDocBase(WikiDocBase wikiDocBase) {
		this.wikiDocBase = wikiDocBase;
	}

	@Override
	public String toString() {
		return "WikiDocContent [id=" + id + ", content=" + content
				+ ", wikiDocBase=" + wikiDocBase + "]";
	}
}
