package com.briup.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="book_reader")
@ApiModel
public class Reader {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ApiModelProperty(value="读者ID")
	private Integer id;
	@ApiModelProperty(value="读者标识名")
	private String username;
	@ApiModelProperty(value="读者密码")
	private String password;
	@ApiModelProperty(value="读者邮箱")
	private String email;
	@ManyToMany
	@JoinTable(name = "reader_chapter",joinColumns = @JoinColumn(name="reader_id"),
	inverseJoinColumns = @JoinColumn(name="chapter_id"))
	private List<Chapter> chapters;
	@ManyToMany
	@JoinTable(name = "reader_article",joinColumns = @JoinColumn(name="reader_id"),
	inverseJoinColumns = @JoinColumn(name="article_id"))
	private List<Article> articles;
	@ManyToMany
	@JoinTable(name = "reader_comment",joinColumns = @JoinColumn(name="reader_id"),
			inverseJoinColumns = @JoinColumn(name="comment_id"))
	private List<Comment> comments;
	public Reader(Integer id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public Reader() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Reader [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
}
