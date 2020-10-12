package com.example.service.postgres;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.postgres.UserPostgres;

@Service
public interface UserServicePostgres {
	public List<UserPostgres> findAllUser();
	
	public Optional<UserPostgres> findUserByID(Long id);

	public void save( UserPostgres userMySQL);
}
