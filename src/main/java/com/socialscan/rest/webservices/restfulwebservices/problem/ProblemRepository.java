 package com.socialscan.rest.webservices.restfulwebservices.problem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem,Long> {

	List<Problem> findByUserId(Long id);

	

}
