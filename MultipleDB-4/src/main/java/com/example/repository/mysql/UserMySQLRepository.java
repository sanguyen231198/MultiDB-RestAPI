package com.example.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.mysql.UserMySQL;

@Repository
public interface UserMySQLRepository extends JpaRepository<UserMySQL, Long>{
	
//	 public void save(Long id, UserMySQL userMySQL);

	    
}
