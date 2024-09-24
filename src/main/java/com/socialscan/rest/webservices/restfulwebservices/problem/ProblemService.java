package com.socialscan.rest.webservices.restfulwebservices.problem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.socialscan.rest.webservices.restfulwebservices.user.User;
import com.socialscan.rest.webservices.restfulwebservices.user.UserRepository;

@Service
public class ProblemService {
	
	@Autowired
	private ProblemRepository problemRepository;
	
	 @Autowired
	private UserRepository userRepository;


	    public Problem addProblem(Long id, String description, String address,byte[] image) {
	        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	        Problem problem = new Problem();
	        problem.setDescription(description);
	        problem.setAddress(address);
	        problem.setImage(image);
	        problem.setUser(user);

	        return problemRepository.save(problem);
	    }


		public void deleteProblem(Long problemId) {
	
			problemRepository.deleteById(problemId);
			 
		}

		public Problem updateProblem(Long problemId, String description, String address,byte[] image) {
			
			Problem problem = problemRepository.findById(problemId).orElseThrow(() -> new RuntimeException("Problem not  found"));
			problem.setDescription(description);
			problem.setAddress(address);
			problem.setImage(image);
			return problemRepository.save(problem);
		}


		public Problem getProblem(Long problemId) {
			// TODO Auto-generated method stub
			
		
			
			return problemRepository.getProblemById(problemId);
		
		}

}
 