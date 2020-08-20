package apiUser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apiUser.entity.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,String>{

	
}
