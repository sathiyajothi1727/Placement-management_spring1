package com.tnsif.pm.studentservicee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
	@Autowired
	private StudentService service;
	
	
	
	
	@GetMapping("/student")
	public List<Student> list()
	{
		return service.listAll();
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student>get(@PathVariable Integer id)
	{
		try
		{ 
			Student student =service.get(id);
			return new ResponseEntity<Student>(student,HttpStatus.OK);
			
		}
		catch (Exception e)
		{
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/student")
	public void add(@RequestBody Student student)
	{
		service.save(student);
	}
	@PutMapping("/api/update/{id}")
	 public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Student studentDetails) {
	        return service.getById(id).map(student -> {
	        	student.setFirstName(studentDetails.getFirstName());
	        	student.setLastName(studentDetails.getLastName()); 
	        	student.setDepartment(studentDetails.getDepartment());
	        	student.setEmail(studentDetails.getEmail());
	       
               service.save(student);
               return new ResponseEntity<>("Student detail updated successfully", HttpStatus.OK);
	            }).orElseGet(() -> {
	            String errorMessage = "Invalid StudentId: " + id;
	            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	           });
	               
	    }
	@DeleteMapping("/student/{id}")
	public void delete(@PathVariable Integer id) 
	{
		service.delete(id);
	}
}

