package com.kosa.mycompany.home;

import com.kosa.mycompany.common.BaseDto;

public class HomeDto extends BaseDto{
	private String seq="";
	private String title="";
	private String image="";
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
