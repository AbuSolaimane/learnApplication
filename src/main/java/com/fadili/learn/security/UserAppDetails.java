package com.fadili.learn.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fadili.learn.models.UserApp;

public class UserAppDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2755084553866872650L;
	private UserApp userApp;

	public UserAppDetails(UserApp userApp) {
		super();
		this.userApp = userApp;
	}

	public UserApp getUserApp() {
		return userApp;
	}

	public void setUserApp(UserApp userApp) {
		this.userApp = userApp;
	}

	@Override
	public String getPassword() {

		return this.userApp.getEncryptedPassword();
	}

	@Override
	public String getUsername() {

		return this.userApp.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
