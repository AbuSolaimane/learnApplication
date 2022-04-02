package com.fadili.learn.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7213202266428664695L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String addressId;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String country;
	private String streetName;
	@Column(nullable = false)
	private String postalCode;
	private String type;
	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserApp userApp;

}
