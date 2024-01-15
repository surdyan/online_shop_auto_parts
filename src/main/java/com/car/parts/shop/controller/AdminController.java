package com.car.parts.shop.controller;

import java.util.List;
import java.util.Optional;

import com.car.parts.shop.configuration.PartsRegistration;
import com.car.parts.shop.repository.OrderRepo;
import com.car.parts.shop.repository.PartsRepo;
import com.car.parts.shop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.car.parts.shop.configuration.OrderRegistration;
import com.car.parts.shop.configuration.UserRegistration;

@Controller
public class AdminController {

    @Autowired
    UserRepo repo;

    @Autowired
    PartsRepo brepo;

    @Autowired
    OrderRepo orepo;

    static public String user_session;

    @RequestMapping("/Admin_Home")
    public ModelAndView Admin_Home() {
        ModelAndView mv = new ModelAndView("Admin_View");
        return mv;
    }

    @RequestMapping("/Parts_Management")
    public ModelAndView Parts_Management() {
        ModelAndView mv = new ModelAndView("Parts_Management");
        return mv;
    }

    @RequestMapping("/selectoperation")
    public ModelAndView selectoperation(String Parts_operation) {
        ModelAndView mv = new ModelAndView("Parts_Management");

        if (Parts_operation.equals("None")) {
            return mv;
        } else if (Parts_operation.equals("Add")) {
            mv.addObject("selectAdd", "Add");
        } else if (Parts_operation.equals("Delete")) {
            mv.addObject("selectDelete", "Delete");
        }
        return mv;
    }

    @RequestMapping("/Parts_Add")
    public ModelAndView Parts_Add(PartsRegistration breg, String Parts_title) {
        ModelAndView mv = new ModelAndView("Parts_Management");

        Optional<PartsRegistration> breg1 = brepo.findById(Parts_title);

        if (breg1.isPresent()) {
            mv.addObject("PrintSwal", "Parts_Exist");
        } else {
            mv.addObject("PrintSwal", "Add_Sucess");
            brepo.save(breg);
        }

        return mv;
    }

    @RequestMapping("/Parts_Delete")
    public ModelAndView Parts_Delete(String Parts_title) {
        ModelAndView mv = new ModelAndView("Parts_Management");


        Optional<PartsRegistration> breg1 = brepo.findById(Parts_title);

        if (breg1.isPresent()) {
            brepo.deleteById(Parts_title);

            mv.addObject("PrintSwal", "Delete_Sucess");
        } else {
            mv.addObject("PrintSwal", "Delete_Failed");
        }

        return mv;
    }

    @RequestMapping("/Parts_Details")
    public ModelAndView Parts_Details() {
        ModelAndView mv = new ModelAndView("Parts_Details");

        List<PartsRegistration> breg1 = brepo.findAll();

        if (breg1.isEmpty()) {
            mv.addObject("PrintSwal", "Parts_Details_Empty");

            mv.setViewName("Admin_View");
        } else {
            PartsRegistration Parts = null;
            mv.addObject("PartsArray", Parts);
            mv.addObject("PartsObject", breg1);

        }

        return mv;
    }

    @RequestMapping("/User_Details")
    public ModelAndView User_Details() {
        ModelAndView mv = new ModelAndView("User_Details");

        List<UserRegistration> ureg1 = repo.findAll();

        if (ureg1.isEmpty()) {
            mv.addObject("PrintSwal", "User_Details_Empty");

            mv.setViewName("Admin_View");
        } else {
            UserRegistration user = null;
            mv.addObject("UserArray", user);
            mv.addObject("UserObject", ureg1);


        }

        return mv;
    }

    @RequestMapping("/Admin_Order_Details")
    public ModelAndView Admin_Order_Details() {
        ModelAndView mv = new ModelAndView("Admin_Order_Details");

        List<OrderRegistration> oreg1 = orepo.findAll();

        if (oreg1.isEmpty()) {
            mv.addObject("PrintSwal", "Order_Details_Empty");

            mv.setViewName("Admin_View");
        } else {
            OrderRegistration order = null;
            mv.addObject("OrderArray", order);
            mv.addObject("OrderObject", oreg1);

        }

        return mv;
    }

}