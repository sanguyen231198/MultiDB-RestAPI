package com.example.service.mysql.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.mysql.UserMySQL;
import com.example.repository.mysql.UserMySQLRepository;
import com.example.service.mysql.UserServiceMySQL;
@Repository("userServiceMySQL")
public class UserServiceImp implements UserServiceMySQL {
	@Autowired
	UserMySQLRepository userMySQLRepository;

	@Override
	public List<UserMySQL> findAllUser() {
		return userMySQLRepository.findAll();
	}


	@Override
	public void save(UserMySQL userMySQL) {
		 userMySQLRepository.save(userMySQL);
	}


	@Override
	public Optional<UserMySQL> findUserByID(Long id) {
		return userMySQLRepository.findById(id);
	}

}
