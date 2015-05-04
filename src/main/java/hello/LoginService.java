package hello;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.User;
import model.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("loginService")
public class LoginService implements UserDetailsService {
	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		try {
			User user = userService.get(username);
			if(user != null) {
				userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPw(), true, true, true, true, getAuthorities());
			} else {
				System.out.println("검색된 사용자 없습니다.");
			}
		} catch (UsernameNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetails;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return authList;
	}
	
}
