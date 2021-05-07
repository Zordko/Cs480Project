package com.example.Cs480Project.Service;

import com.example.Cs480Project.domain.*;
import com.example.Cs480Project.repository.LoginRepository;
import com.example.Cs480Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserRepository repo;
    @Autowired
    private LoginRepository repol;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public User login(String username,String password){
        User user = repo.findByUsernameAndPassword(username, password);
        return user;
    }
    public Login userLogin(String username, String password, String type){
        Login user = repol.findByUsernameAndPasswordAndType(username, password, type);
        return user;
    }
    public void InsertIntoPatient(Patient p,String username,String password){
        String sql = "INSERT INTO patient "+ "(name,SSN,Race,Occupation,Gender,MedicalHistory,Address,Vaccine) VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,p.getName(),p.getSsn(),p.getRace(),p.getOccupation(),p.getGender(),p.getMedicalHistory(),p.getAddress(),p.getVaccine());
        String sq = "INSERT INTO logins "+ "(username,password,type) VALUES(?,?,?)";
        jdbcTemplate.update(sq,username,password,"patient");
        System.out.println("Created the login");
    }
    public void InsertIntoNurse(Nurse n){
        String sql = "Insert INTO nurse "+ "(EmployeeId,Gender,name,Age,Address,Phone) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, n.getEmployeeId(),n.getGender(),n.getName(),n.getAge(),n.getAddress(),n.getPhone());
        System.out.println("Got it into the datebase");
    }
    public void createNurseLogin(String username,String password){
        String sql = "Insert INTO logins "+ "(username,password,type) VALUES(?,?,?)";
        jdbcTemplate.update(sql,username,password,"nurse");
        System.out.println("Created the login");
    }

    public void UpdateNurse(Nurse n){
        String sql = "UPDATE nurse SET Gender = ?  ,name = ? ,Age = ?  WHERE EmployeeId = ?";
        jdbcTemplate.update(sql, n.getGender(),n.getName(),n.getAge(),n.getEmployeeId());
        System.out.println("Got it update the datebase");
    }
    public void UpdatePatient(Patient p,String id){
        String sql = "UPDATE patient SET name = ?, Race = ?, Occupation = ?, Gender = ?, MedicalHistory = ?, Address = ?, Vaccine = ?   WHERE SSN = ?";
        jdbcTemplate.update(sql,p.getName(),p.getRace(),p.getOccupation(),p.getGender(),p.getMedicalHistory(),p.getAddress(), p.getVaccine(),id);
        System.out.println("Got it update the datebase");
    }
    public void UpdateNurseMine(String address,String phone,String id){
        String sql = "UPDATE nurse SET Address = ? , Phone = ? WHERE EmployeeId =?";
        jdbcTemplate.update(sql,address,phone,id);
        System.out.println("Got it update the datebase");
    }
    public void DeleteNurse(String id){
        String sql = "DELETE FROM nurse WHERE EmployeeId = ?";
        jdbcTemplate.update(sql,id);
        System.out.println("Deleted from database");
    }
    public void addVaccine(Vaccine v){
        String sql = "Insert INTO vaccine "+ "(Name,NameOfCom,NumofRequested,StorageAmount,ReservedAmount,OptionalTexture) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,v.getName(),v.getNameofCom(),v.getNumRequested(),v.getStorageAmount(),v.getReservedAmount(),v.getTexture());
        System.out.println("Added Vaccine");
    }
    public void UpdateVaccine(Vaccine v){
        String sql = "UPDATE vaccine SET NameOfCom = ?  ,NumofRequested = ? ,StorageAmount = ? ,ReservedAmount = ?,OptionalTexture = ? WHERE Name = ?";
        jdbcTemplate.update(sql, v.getNameofCom(),v.getNumRequested(),v.getStorageAmount(),v.getReservedAmount(),v.getTexture(),v.getName());
        System.out.println("Got it update the datebase");
    }
    public Nurse getNurse(String id){
        String sql = "SELECT * FROM nurse WHERE EmployeeId = " + id;
        return jdbcTemplate.queryForObject(sql,(rs,rowNum)->( new Nurse(
                rs.getInt("EmployeeId"),
                rs.getString("Gender"),
                rs.getString("name"),
                rs.getInt("Age"),
                rs.getString("Address"),
                rs.getString("Phone")
        )

                ));
    }
    public Patient getPatient(String id){
        String sql = "SELECT * FROM patient WHERE SSN = " + id;
        return jdbcTemplate.queryForObject(sql,(rs,rowNum)->( new Patient(
                rs.getString("name"),
                rs.getInt("SSN"),
                rs.getString("Race"),
                rs.getString("Occupation"),
                rs.getString("Gender"),
                rs.getString("MedicalHistory"),
                rs.getString("Address"),
                rs.getString("Vaccine")
        )

        ));
    }
    public List<Nurse> viewNurses(){
        String sql = "SELECT * FROM nurse";
        List<Nurse> nurses = jdbcTemplate.query(sql,(rs,rowNum)-> new Nurse(
                rs.getInt("EmployeeId"),
                rs.getString("Gender"),
                rs.getString("name"),
                rs.getInt("Age"),
                rs.getString("Address"),
                rs.getString("Phone")

        ));
        return nurses;
    }
    public List<Schedule> viewSchedules(){
        String sql = "SELECT * FROM schedule";
        List<Schedule> schedules = jdbcTemplate.query(sql,(rs,rowNum)-> new Schedule(
                rs.getString("Time"),
                rs.getString("Day"),
                rs.getString("Month")
        ));
        return schedules;
    }
    public List<Schedule> viewMySchedulesNurse(String currentId){
        String sql ="SELECT * FROM nurseshifts WHERE EmployeeId = "+ currentId;
        List<Schedule> schedules = jdbcTemplate.query(sql,(rs,rowNum)-> new Schedule(
                rs.getString("Time"),
                rs.getString("Day"),
                rs.getString("Month")
        ));
        return schedules;
    }
    public List<Schedule> viewMySchedulesPatient(String currentId){
        String sql ="SELECT * FROM patienttimes WHERE SSN = "+ currentId;
        List<Schedule> schedules = jdbcTemplate.query(sql,(rs,rowNum)-> new Schedule(
                rs.getString("Time"),
                rs.getString("Day"),
                rs.getString("Month")
        ));
        return schedules;
    }
    public void deleteScheduleNurse(List<Schedule>list,String id,int index){
        String sql = "DELETE FROM nurseshifts WHERE EmployeeId = ? and Time = ? and Day = ? and Month = ?";
        jdbcTemplate.update(sql,id,list.get(index).getTime(),list.get(index).getDay(),list.get(index).getMonth());
    }
    public void deleteSchedulePatient(List<Schedule>list,String id,int index){
        String sql = "DELETE FROM patienttimes WHERE SSN = ? and Time = ? and Day = ? and Month = ?";
        jdbcTemplate.update(sql,id,list.get(index).getTime(),list.get(index).getDay(),list.get(index).getMonth());
    }
    public void selectScheduleNurse(List<Schedule> list,String id,int index){
        String sql = "INSERT INTO nurseshifts VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,id,list.get(index).getTime(),list.get(index).getDay(),list.get(index).getMonth()
        );
    }
    public void selectSchedulePatient(List<Schedule> list,String id,int index){
        String sql = "INSERT INTO patienttimes VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,id,list.get(index).getTime(),list.get(index).getDay(),list.get(index).getMonth()
        );
        String second = "UPDATE vaccine SET NumofRequested = NumofRequested + ? , ReservedAmount = ReservedAmount + ? WHERE Name = ?" ;
        jdbcTemplate.update(second,1,1,getPatient(id).getVaccine());
    }
    public List<Patient> viewPatients(){
        String sql = "SELECT * FROM patient";
        List<Patient> patients = jdbcTemplate.query(sql,(rs, rowNum)-> new Patient(
                rs.getString("name"),
                rs.getInt("SSN"),
                rs.getString("Race"),
                rs.getString("Occupation"),
                rs.getString("Gender"),
                rs.getString("MedicalHistory"),
                rs.getString("Address"),
                rs.getString("Vaccine")

        ));
        return patients;
    }

    public void DropOff( String name,String amount){
        String sql = "UPDATE vaccine SET StorageAmount = ? + StorageAmount WHERE Name = ?" ;
        jdbcTemplate.update(sql,amount,name);
    }






}
