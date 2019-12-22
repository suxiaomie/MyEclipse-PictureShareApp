package com.mcl.beans;

/**
 * Androiduser entity. @author MyEclipse Persistence Tools
 */

public class Androiduser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String user;
	private String pswd;

	// Constructors

	/** default constructor */
	public Androiduser() {
	}

	/** full constructor */
	public Androiduser(String user, String pswd) {
		this.user = user;
		this.pswd = pswd;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPswd() {
		return this.pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

}