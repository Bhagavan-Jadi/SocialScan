package com.socialscan.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends  JpaRepository<User,Long> {

	boolean existsByName(String username);

	User findByName(String name);

}
