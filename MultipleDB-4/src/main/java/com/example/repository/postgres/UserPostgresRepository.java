package com.example.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.postgres.UserPostgres;

@Repository
public interface UserPostgresRepository extends JpaRepository<UserPostgres, Long>{
}
