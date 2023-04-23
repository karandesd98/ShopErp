package TechHub.ShopErp.utilityAndSecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import TechHub.ShopErp.model.User;

public class CustomUserDetail implements UserDetails {

	private User user;
	
	public CustomUserDetail(User u) {
		super();
		this.user = u;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority simpleGrantedAuthority=	new SimpleGrantedAuthority("ROLE_" +user.getROLE());
		return List.of(simpleGrantedAuthority);
	}
	
//	 private Collection<? extends GrantedAuthority> getAuthorities() {
//	        List<GrantedAuthority> authorities = new ArrayList<>();
//	        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getROLE()));
//	        return authorities;
//	    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPASSWORD();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEMAIL();
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
