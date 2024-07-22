package com.socialscan.rest.webservices.restfulwebservices.problem;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/problem")
public class ProblemResource {
	
	
	@Autowired
	private ProblemService problemService;
	
	
	
    @DeleteMapping("/{problemId}")
    public void deleteProblem(@PathVariable Long problemId) {
    	
    	problemService.deleteProblem(problemId);
    }
    
    
    @PutMapping("/{problemId}/problem")
    public ResponseEntity<?> updateProblem(@PathVariable Long problemId,@RequestPart("problem") Problem problem,
    		@RequestPart("image") MultipartFile image) {
    	
    	if(image.isEmpty()) {
    		return ResponseEntity.badRequest().body("Image file is Empty");
    		
    	}
    	
    	byte[] imageData;
    	
    	try {
    		imageData = image.getBytes();
    	}
    	catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to read Image data");
		}
    	
    	
    	try {
    		Problem updatedProblem =  problemService.updateProblem(problemId, problem.getDescription(),problem.getAddress(),imageData);
    		return ResponseEntity.ok(updatedProblem);
    	}
    	catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the Image");
		}
   	
    	
    }

}
