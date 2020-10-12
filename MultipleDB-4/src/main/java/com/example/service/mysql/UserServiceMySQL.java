package com.example.service.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.mysql.UserMySQL;

@Service
public interface UserServiceMySQL {
	
	public List<UserMySQL> findAllUser();
	
	public Optional<UserMySQL> findUserByID(Long id);

	public void save( UserMySQL userMySQL);
	
}
