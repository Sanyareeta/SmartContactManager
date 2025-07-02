package com.scm.scm20.controllers;

import com.scm.scm20.helper.Message;
import com.scm.scm20.helper.MessageType;
import com.scm.scm20.entities.User;
import com.scm.scm20.forms.UserForm;
import com.scm.scm20.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageControllers {
    @Autowired
    UserService userService;
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("home page handler");
            model.addAttribute("name","Cherry Technologies");
        model.addAttribute("objective","Practising springboot here");
        model.addAttribute("done_by","Sanya");


        return "home";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    //aboutUs
    @RequestMapping("/about")
    public String aboutPage(){
    System.out.println("About page loading");
    return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page");
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage(){
    return new String("contact");
    }

    @GetMapping("/login")
    public String loginPage(){
        return new String("login");
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        UserForm userForm=new UserForm();
        model.addAttribute("userForm",userForm);
        return "register"; // NOT new String("register")
    }

//processing registration form
@RequestMapping(value="/do-register",method = RequestMethod.POST)
public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rbindingResult, HttpSession session){
    System.out.println("Processing registration");
    System.out.println(userForm);
    if(rbindingResult.hasErrors()) return "register";
    User user=new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
//    user.setEnabled(false);
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setProfilePic("src/main/resources/static/Images/defaultProfilePic.png");
    User savedUser=userService.saveUser(user);
    System.out.println("User is saved");
    Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();
    session.setAttribute("message",message);
        return "redirect:/register";

}
}
