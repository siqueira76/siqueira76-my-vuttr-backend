package ccom.siqueira76.vuttr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ccom.siqueira76.vuttr.domain.User;
import ccom.siqueira76.vuttr.repositories.UserRepository;
import ccom.siqueira76.vuttr.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSpringSecurity(user.getId(), user.getEmail(), user.getSenha(), user.getPerfis());
	}

}
