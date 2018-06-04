package com.test.domain;

import java.sql.Timestamp;

public class Comment {
	private Integer comment_num;
	private Integer restaurant_num;
	private String member_id;
	private String content;
	private Integer re_ref;
	private Integer re_lev;
	private Integer re_seq;
	private Timestamp reg_date;
	private String ip;
	private Integer readcount;
	
	
	public Integer getComment_num() {
		return comment_num;
	}
	public void setComment_num(Integer comment_num) {
		this.comment_num = comment_num;
	}
	public Integer getRestaurant_num() {
		return restaurant_num;
	}
	public void setRestaurant_num(Integer restaurant_num) {
		this.restaurant_num = restaurant_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(Integer re_ref) {
		this.re_ref = re_ref;
	}
	public Integer getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(Integer re_lev) {
		this.re_lev = re_lev;
	}
	public Integer getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(Integer re_seq) {
		this.re_seq = re_seq;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getReadcount() {
		return readcount;
	}
	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [comment_num=");
		builder.append(comment_num);
		builder.append(", restaurant_num=");
		builder.append(restaurant_num);
		builder.append(", member_id=");
		builder.append(member_id);
		builder.append(", content=");
		builder.append(content);
		builder.append(", re_ref=");
		builder.append(re_ref);
		builder.append(", re_lev=");
		builder.append(re_lev);
		builder.append(", re_seq=");
		builder.append(re_seq);
		builder.append(", reg_date=");
		builder.append(reg_date);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", readcount=");
		builder.append(readcount);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
}
