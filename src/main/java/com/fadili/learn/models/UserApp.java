package com.fadili.learn.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserApp implements Serializable {

	private static final long serialVersionUID = -7316943051786614178L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String userId;
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	@Column(nullable = false, unique = true, length = 120)
	private String email;
	@Column(nullable = false)
	private String encryptedPassword;
	private String emailVerificationToken;
	@Column(nullable = false)
	private boolean emailVerificationStatus = false;
	@OneToMany(mappedBy = "userApp", cascade = CascadeType.ALL)
	private List<Address> addresses;

}
