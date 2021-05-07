package com.example.Cs480Project.repository;
import com.example.Cs480Project.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LoginRepository extends JpaRepository<Login,Long>{
    Login findByUsernameAndPasswordAndType(String username,String password,String type);
}
