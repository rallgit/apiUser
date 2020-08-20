package apiUser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiUser.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	User findByEmail(String email);
}
