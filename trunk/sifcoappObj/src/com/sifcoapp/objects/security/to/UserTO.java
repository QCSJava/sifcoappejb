package com.sifcoapp.objects.security.to;

import java.sql.Date;
import java.util.List;

public class UserTO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8555865315670434475L;
	private int usersign;
	private String nickname;
	private String username;
	private String lastname;
	private String password;
	private int profilecode;
	private String locked;
	private Date userdate;
	private int cusersign;

	public UserTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTO(int usersign, String nickname, String username,
			String lastname, String password, int profilecode, String locked,
			int cusersign) {
		super();
		this.usersign = usersign;
		this.nickname = nickname;
		this.username = username;
		this.lastname = lastname;
		this.password = password;
		this.profilecode = profilecode;
		this.locked = locked;
		this.cusersign = cusersign;
	}

	public int getUsersign() {
		return usersign;
	}

	public void setUsersign(int usersign) {
		this.usersign = usersign;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getProfilecode() {
		return profilecode;
	}

	public void setProfilecode(int profilecode) {
		this.profilecode = profilecode;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public Date getUserdate() {
		return userdate;
	}

	public void setUserdate(Date userdate) {
		this.userdate = userdate;
	}

	public int getCusersign() {
		return cusersign;
	}

	public void setCusersign(int cusersign) {
		this.cusersign = cusersign;
	}

}
