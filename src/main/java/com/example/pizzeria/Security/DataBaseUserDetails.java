package com.example.pizzeria.Security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.pizzeria.model.Role;
import com.example.pizzeria.model.User;

public class DataBaseUserDetails implements UserDetails{
	
	
	private static final long serialVersionUID = 1L;

	private final User user;
	
	private final Set<GrantedAuthority> authorities;
	
	public DataBaseUserDetails(User user) {
		this.user = user;
		
		authorities = new HashSet<GrantedAuthority>();
		for(Role role : user.getRoles()) {
			authorities.
            	add(new SimpleGrantedAuthority(role.getName()));
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
