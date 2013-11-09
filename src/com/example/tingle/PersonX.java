package com.example.tingle;

import java.util.List;

public class PersonX
{
	public static String LOCALPHOTO_KEY = "localphoto_key";
	public static String PASSWORD_KEY = "password_key";
	public static String USERNAME_KEY = "username_key";
	public static String NAME_KEY = "name_key";
	public static String AGE_KEY = "age_key";
	public static String GENDER_KEY = "gender_key";
	public static String VIBRATE_KEY = "vibrate_key";
	
	public static String WEB_SERVER_ = "https://evening-dawn-4592.herokuapp.com/";
	
	public String id;
	public String srcImgUrl;
	public String username;
	public String gender;
	public String age;
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String name;
	public List<String> hot;
	public List<String> not;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSrcImgUrl() {
		return srcImgUrl;
	}
	public void setSrcImgUrl(String srcImgUrl) {
		this.srcImgUrl = srcImgUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<String> getHot() {
		return hot;
	}
	public void setHot(List<String> hot) {
		this.hot = hot;
	}
	public List<String> getNot() {
		return not;
	}
	public void setNot(List<String> not) {
		this.not = not;
	}
	
	
	

}