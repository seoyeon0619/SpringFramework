package com.kosa.mycompany.common;

public class BaseDto {
	protected int pg=0;
	protected int pgSize=10;
	protected int num;
	protected String searchKey="";
	protected String searchKeyword="";
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPg() {
		return pg;
	}
	public void setPg(int pg) {
		this.pg = pg;
	}
	public int getPgSize() {
		return pgSize;
	}
	public void setPgSize(int pgSize) {
		this.pgSize = pgSize;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	
}
