package com.socialscan.rest.webservices.restfulwebservices.user;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.socialscan.rest.webservices.restfulwebservices.problem.Problem;
import com.socialscan.rest.webservices.restfulwebservices.problem.ProblemRepository;
import com.socialscan.rest.webservices.restfulwebservices.problem.ProblemService;



@RestController
@RequestMapping("/users")
public class UserResource {
	
	
	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private ProblemRepository problemRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user) {
    	boolean exists = userService.userExists(user.getName());
    	
    	if(exists) {
    		return ResponseEntity.ok("User Exits");
    	}
    	else {
    		
    		User newUser = userService.saveUser(user);
    		return ResponseEntity.status(201).body("User Created with ID: "+newUser.getId());
    	}
        
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> LoginUser(@RequestBody User user) {
    	boolean exists = userService.userExists(user.getName());
    	
    	User user1 = userService.findUserByName(user.getName());
    	
    	if(user1!=null) {
    		
    		Long userId = user1.getId();
    		return ResponseEntity.status(HttpStatus.OK).body(userId.toString());
    	}
    	else {
    		
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exit");
    	}
        
    }
    
    @PostMapping("/{id}/problem")
	public ResponseEntity<?> uploadProblem(@PathVariable Long id, @RequestPart("problem") Problem problem,
			@RequestPart("image") MultipartFile image) {
    	

        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("Image file is empty");
        }
        
        byte[] imageData;
        try {
            imageData = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to read image file");
        }
    	
    	try {
            Problem savedProblem = problemService.addProblem(id, problem.getDescription(), problem.getAddress(), imageData);
            return ResponseEntity.ok(savedProblem);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save problem");
        }
	}
    
  
    @GetMapping("/{id}")
    public List<Problem> getAllProblems(@PathVariable Long id)
    {
    	 List<Problem> problems =  problemRepository.findByUserId(id); 
    	 return problems;
    }
       
}