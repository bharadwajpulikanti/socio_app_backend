package com.media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.media.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	
	@Query("select u from User u where u.firstName like %:query% OR u.lastName like %:query% OR u.email like %:query%")
	public List<User> searchUser(@Param("query") String query);

}
