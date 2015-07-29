package com.stu_id_apply.vo;

import java.sql.Blob;


/**
 * TJwcXjZpb entity. @author MyEclipse Persistence Tools
 */

public class TJwcXjZpb  implements java.io.Serializable{

	// Fields

	private String xh;
	private Integer idx;
	private String blobtype;
	private byte[] blobdata;
	private String sfzh;

	// Constructors

	/** default constructor */
	public TJwcXjZpb() {
	}

	/** minimal constructor */
	public TJwcXjZpb(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public TJwcXjZpb(String xh, Integer idx, String blobtype,byte[] blobdata,
			String sfzh) {
		this.xh = xh;
		this.idx = idx;
		this.blobtype = blobtype;
		this.blobdata = blobdata;
		this.sfzh = sfzh;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public Integer getIdx() {
		return this.idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getBlobtype() {
		return this.blobtype;
	}

	public void setBlobtype(String blobtype) {
		this.blobtype = blobtype;
	}


	public byte[] getBlobdata() {
		return blobdata;
	}

	public void setBlobdata(byte[] blobdata) {
		this.blobdata = blobdata;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

}