package com.socialscan.rest.webservices.restfulwebservices.problem;

import org.springframework.beans.factory.annotation.Autowired;

import com.socialscan.rest.webservices.restfulwebservices.user.User;

import jakarta.persistence.Column;

//import com.socialscan.rest.webservices.restfulwebservices.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Problem {
	
	
	@Autowired
	@ManyToOne
	private User user;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;

	private String address;
	

	@Lob
    @Column(name = "image")
	private byte[] image;
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
