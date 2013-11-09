package com.example.tingle;

import java.util.List;

public class Data
{
	public List<User> users;
	public User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> candidates;

	public List<User> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<User> candidates) {
		this.candidates = candidates;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}