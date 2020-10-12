package com.example.service.postgres.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.mysql.UserMySQL;
import com.example.entity.postgres.UserPostgres;
import com.example.repository.postgres.UserPostgresRepository;
import com.example.service.postgres.UserServicePostgres;
@Repository("userServicePostgres")
public class UserServiceImp implements UserServicePostgres{
	@Autowired 
	UserPostgresRepository userPostgresRepository;

	@Override
	public List<UserPostgres> findAllUser() {
		return userPostgresRepository.findAll();
	}

	@Override
	public Optional<UserPostgres> findUserByID(Long id) {
		return userPostgresRepository.findById(id);
	}

	@Override
	public void save(UserPostgres userMySQL) {
		userPostgresRepository.save(userMySQL);
	}


}
