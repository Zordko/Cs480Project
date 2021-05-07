package com.example.Cs480Project.controller;

import com.example.Cs480Project.Service.UserService;
import com.example.Cs480Project.domain.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
        @Autowired
    private UserService userService;
String currentID="";
@GetMapping("/createPatient")
public ModelAndView createPatient(){
    ModelAndView mav = new ModelAndView("createPatient");
    mav.addObject("patient",new Patient());
    return mav;
}
@PostMapping("/createPatient")
public String createPatient(@ModelAttribute("patient")Patient p,@RequestParam("username")String username,@RequestParam("password")String password){
    userService.InsertIntoPatient(p,username,password);
    return "redirect:/login";
}
@GetMapping("/Commands")
    public ModelAndView Commands(){
    ModelAndView mav = new ModelAndView("Commands");
    mav.addObject("list",userService.viewSchedules());
    return mav;
}
@PostMapping("/Commands")
public String Dropoff(@RequestParam("NamevaccineID")String name,@RequestParam("Amount")String amount){
    userService.DropOff(name,amount);
    return "redirect:/Commands";
}
    @PostMapping("/TimeSlot")
    public String TimeSlot(@RequestParam("chose")String index,@ModelAttribute("list")List<Schedule> list){

        return "redirect:/Commands";
    }

        @GetMapping("/login")
        public ModelAndView login(){
            ModelAndView mav = new ModelAndView("Login");
            mav.addObject("user",new User());
            return mav;
        }
        @PostMapping("/login")
        public String login(@ModelAttribute("user") User user ,@RequestParam("chose") String type ) {
            switch (type) {
                case "Admin": {
                    User auto = userService.login(user.getUsername(), user.getPassword());
                    System.out.print(auto);
                    if (Objects.nonNull(auto)) {
                        return "redirect:/AdminPage";
                    } else {
                        return "redirect:/login";
                    }

                }
                case "Nurse": {
                    Login auto = userService.userLogin(user.getUsername(), user.getPassword(),type);
                    System.out.print(auto);
                    if (Objects.nonNull(auto)) {
                        return "redirect:/NursePage";
                    } else {
                        return "redirect:/login";
                    }
                }
                case "Patient":{
                    Login auto = userService.userLogin(user.getUsername(), user.getPassword(),type);
                    System.out.print(auto);
                    if (Objects.nonNull(auto)) {
                        return "redirect:/PatientPage";
                    } else {
                        return "redirect:/login";
                    }
                }
            }
            return "";
        }


    @GetMapping("/AdminPage")
    public ModelAndView AdminPage(){
        ModelAndView mav = new ModelAndView("AdminPage");
        return mav;
    }
    @PostMapping("/adminPage")
    public String adminPage(@RequestParam("chose")String pick){
            switch(pick){
                case "Register":
                    return "redirect:/RegisterNurse";

                case "Update":
                    return "redirect:/UpdateNurse";

                case "Delete":
                    return "redirect:/DeleteNurse";

                case "Add":
                    return "redirect:/AddVaccine";

                case "UpdateV":
                    return "redirect:/UpdateVaccine";

                case "ViewN":
                    return "redirect:/ViewNurses";

                case "ViewP":
                    return "redirect:/ViewPatients";
            }
        return "redirect:/AdminPage";
    }
    @GetMapping("/NursePage")
    public ModelAndView NursePage(){
        ModelAndView mav = new ModelAndView("NursePage");
        return mav;
    }


    @PostMapping("/NursePage")
    public String NursePage(@RequestParam("id")String id,@RequestParam("chose")String type){
            currentID = id;
        switch(type){
            case "Update":
                return "redirect:/UpdateNurseMine";
            case "Schedule":
                return "redirect:/ScheduleNurse";
            case  "Cancel":
                return "redirect:/CancelNurse";
            case "Info":
                return "redirect:/InfoNurse";
            case"Record":
                return "redirect:/RecordNurse";
        }
        return "redirect:/NurseLogin";
    }
    @GetMapping("/SchedulePatient")
    public ModelAndView SchedulePatient(){
    ModelAndView mav = new ModelAndView();
    mav.addObject("list",userService.viewSchedules());
    return mav;
    }
    @PostMapping("/SchedulePatient")
    public String SchedulePatient(@RequestParam("chose")String index){
            userService.selectSchedulePatient(userService.viewSchedules(),currentID,Integer.parseInt(index));

            return "redirect:/PatientPage";
    }

    @GetMapping("/CancelPatient")
    public ModelAndView CancelPatient(){
    ModelAndView mav = new ModelAndView("CancelPatient");
    mav.addObject("list",userService.viewMySchedulesPatient(currentID));
    return mav;
    }
    @PostMapping("/CancelPatient")
    public String CancelPatient(@RequestParam("chose")String index){
    userService.deleteSchedulePatient(userService.viewMySchedulesPatient(currentID),currentID,Integer.parseInt(index));
    return "redirect:/PatientPage";
    }
    @GetMapping("/PatientPage")
    public ModelAndView PatientPage(){
    ModelAndView mav = new ModelAndView("PatientPage");
    return mav;
    }
    @PostMapping("/PatientPage")
    public String PatientPage(@RequestParam("id")String id,@RequestParam("chose")String type){
        currentID = id;
        switch(type){
            case "Update":
                return "redirect:/UpdatePatientMine";
            case "Schedule":
                return "redirect:/SchedulePatient";
            case  "Cancel":
                return "redirect:/CancelPatient";
            case "Info":
                return "redirect:/InfoPatient";

        }
        return "redirect:/login";
    }
    @GetMapping("/UpdatePatientMine")
    public ModelAndView updatePatient(){
    ModelAndView mav = new ModelAndView("UpdatePatientMine");
    mav.addObject("patient",new Patient());
    return mav;
    }
    @PostMapping("/UpdatePatientMine")
    public String updatePatient(@ModelAttribute("patient")Patient p){
        userService.UpdatePatient(p,currentID);
        return "redirect:/PatientPage";
    }
    @GetMapping("/UpdateNurseMine")
    public ModelAndView UpdateNurseMine(){
        ModelAndView mav = new ModelAndView("UpdateNurseMine");
        return mav;
    }
    @PostMapping("/UpdateNurseMine")
    public String UpdateNurseMine(@RequestParam("address")String s,@RequestParam("phone")String p){
          userService.UpdateNurseMine(s,p,currentID);
          return "redirect:/NursePage";
    }
    @GetMapping("/ScheduleNurse")
    public ModelAndView ScheduleNurse(){
        ModelAndView mav = new ModelAndView("ScheduleNurse");
        mav.addObject("list",userService.viewSchedules());
        return mav;
    }
    @PostMapping("/ScheduleNurse")
    public String ScheduleNurse( @RequestParam("chose")String index){
            System.out.println(index);
            userService.selectScheduleNurse(userService.viewSchedules(),currentID,Integer.parseInt(index));
            return "redirect:/NursePage";
    }
    @GetMapping("/CancelNurse")
    public ModelAndView CancelNurse(){
        ModelAndView mav = new ModelAndView("CancelNurse");
        mav.addObject("list",userService.viewMySchedulesNurse(currentID));
        return mav;
    }
    @PostMapping("/CancelNurse")
    public String CancelNurse(@RequestParam("chose")String index){
            userService.deleteScheduleNurse(userService.viewMySchedulesNurse(currentID),currentID,Integer.parseInt(index));
            return "redirect:/NursePage";
    }
    @GetMapping("/InfoPatient")
    public ModelAndView ViewPatientInfo(){
        ModelAndView mav = new ModelAndView("InfoPatient");
        mav.addObject("list",userService.viewMySchedulesPatient(currentID));
        mav.addObject("patient",userService.getPatient(currentID));
        return mav;
    }
    @PostMapping("/InfoPatient")
    public String PatientViewInfo(){
        return "redirect:/PatientPage";
    }
    @GetMapping("/InfoNurse")
    public ModelAndView ViewNurseInfo(){
        ModelAndView mav = new ModelAndView("InfoNurse");
        mav.addObject("list",userService.viewMySchedulesNurse(currentID));
        mav.addObject("nurse",userService.getNurse(currentID));
        return mav;
    }
    @PostMapping("/ViewNurseInfo")
    public String ViewInfo(){

        return "redirect:/PatientPage";
    }

@GetMapping("/RegisterNurse")
    public ModelAndView registerNurse(){
            ModelAndView mav = new ModelAndView("RegisterNurse");
            mav.addObject("nurse",new Nurse());
            return mav;
}
@PostMapping("/RegisterNurse")
    public String RegisterNurse(@ModelAttribute("nurse") Nurse nurse){
        userService.InsertIntoNurse(nurse);
        return "redirect:/NurseLogin";
}
    @GetMapping("/NurseLogin")
    public ModelAndView NurseLogin(){
        ModelAndView mav = new ModelAndView("NurseLogin");

        return mav;
    }
    @PostMapping("/NurseLogin")
    public String NurseLogin(@RequestParam("username")String username,@RequestParam("password") String password){
        userService.createNurseLogin(username,password);
        return "redirect:/AdminPage";
    }

    @GetMapping("/UpdateNurse")
    public ModelAndView UpdateNurse(){
        ModelAndView mav = new ModelAndView("UpdateNurse");
        mav.addObject("nurse",new Nurse());
        return mav;
    }

    @PostMapping("/UpdateNurse")
    public String UpdateNurse(@ModelAttribute("nurse")Nurse nurse){
            userService.UpdateNurse(nurse);
            return "redirect:/AdminPage";
    }
    @GetMapping("/DeleteNurse")
    public ModelAndView DeleteNurse(){
            ModelAndView mav = new ModelAndView("DeleteNurse");

            return mav;
    }
    @PostMapping("/DeleteNurse")
    public String DeleteNurse(@RequestParam("input") String id ){
            userService.DeleteNurse(id);
            return "redirect:/AdminPage";
    }
    @GetMapping("/AddVaccine")
    public ModelAndView AddVaccine(){
        ModelAndView mav = new ModelAndView("AddVaccine");
        mav.addObject("vaccine",new Vaccine());
        return mav;
    }
    @PostMapping("/AddVaccine")
    public String AddVaccine(@ModelAttribute("vaccine") Vaccine v ){
        userService.addVaccine(v);
        return "redirect:/AdminPage";
    }
    @GetMapping("/UpdateVaccine")
    public ModelAndView UpdateVaccine(){
        ModelAndView mav = new ModelAndView("updateVaccine");
        mav.addObject("vaccine",new Vaccine());
        return mav;
    }

    @PostMapping("/UpdateVaccine")
    public String UpdateVaccine(@ModelAttribute("vaccine")Vaccine v){
        userService.UpdateVaccine(v);
        return "redirect:/AdminPage";
    }
    @GetMapping("/ViewNurses")
    public ModelAndView ViewN(){
        ModelAndView mav = new ModelAndView("ViewNurses");
        mav.addObject("list",userService.viewNurses());
        return mav;
    }
    @GetMapping("/ViewPatients")
    public ModelAndView ViewP(){
            ModelAndView mav = new ModelAndView("ViewPatients");
            mav.addObject("list",userService.viewPatients());
            return mav;
    }

    }
