package com.tnsif.pm.studentservicee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class StudentService {
	
	@Autowired
	private StudentRepository repo; 
	
	public List<Student> listAll()
	{
		return repo.findAll();
	}
	public Student get(Integer id)
	{
		return repo.findById(id).get();
	}
	public void save(Student student)
	{
		 repo.save(student);
		 
		 
	}
	
	public Optional<Student> getById(int id) {

		return repo.findById(id);
	}
	public void delete(Integer id)
	{
		repo.deleteById(id);
	
	}
}
