package com.test.domain;

import java.sql.Timestamp;
import java.util.List;

public class Restaurant {
	private Integer num;
	private String name;
	private String address;
	private Integer tel;
	private String filename;
	private Integer score;
	private Integer likecount;
	private Timestamp reg_date;
	private List<Comment> commentList;
	
	
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getTel() {
		return tel;
	}
	public void setTel(Integer tel) {
		this.tel = tel;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getLikecount() {
		return likecount;
	}
	public void setLikecount(Integer likecount) {
		this.likecount = likecount;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Restaurant [num=");
		builder.append(num);
		builder.append(", name=");
		builder.append(name);
		builder.append(", address=");
		builder.append(address);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", filename=");
		builder.append(filename);
		builder.append(", score=");
		builder.append(score);
		builder.append(", likecount=");
		builder.append(likecount);
		builder.append(", reg_date=");
		builder.append(reg_date);
		builder.append(", commentList=");
		builder.append(commentList);
		builder.append(", getNum()=");
		builder.append(getNum());
		builder.append(", getName()=");
		builder.append(getName());
		builder.append(", getAddress()=");
		builder.append(getAddress());
		builder.append(", getTel()=");
		builder.append(getTel());
		builder.append(", getFilename()=");
		builder.append(getFilename());
		builder.append(", getScore()=");
		builder.append(getScore());
		builder.append(", getLikecount()=");
		builder.append(getLikecount());
		builder.append(", getReg_date()=");
		builder.append(getReg_date());
		builder.append(", getCommentList()=");
		builder.append(getCommentList());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
