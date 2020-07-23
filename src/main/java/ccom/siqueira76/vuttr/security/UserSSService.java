package ccom.siqueira76.vuttr.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserSSService {
	
	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
		
	}

}
