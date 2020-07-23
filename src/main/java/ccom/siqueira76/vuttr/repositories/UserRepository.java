package ccom.siqueira76.vuttr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ccom.siqueira76.vuttr.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Transactional(readOnly=true)
	User findByEmail(String email);

}
