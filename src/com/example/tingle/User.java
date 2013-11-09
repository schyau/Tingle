package com.example.tingle;

import java.util.List;

public class User {
	public String username;
	//public List<String> hot;
	//public List<String> not;
	public String _id;
	public int age;
	public String image_url;
	public String gender;
	public String first_name;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*public List<String> getHot() {
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
	}*/

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
}