package com.fadili.learn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fadili.learn.models.UserApp;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
	
	UserApp findByEmail(String email);
	UserApp findByUserId(String userID);

}
