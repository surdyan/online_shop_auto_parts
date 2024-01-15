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

@Controller
public class UserController {

    @Autowired
    UserRepo repo;

    @Autowired
    PartsRepo brepo;

    @Autowired
    OrderRepo orepo;

    static public String user_session1 = "User";

    @RequestMapping("/User_Home")
    public ModelAndView User_Home(String User_Session, String print) {
        ModelAndView mv = new ModelAndView("User_View");

        if (User_Session != null) {
            user_session1 = User_Session;
        }

        mv.addObject("User", user_session1);

        mv.addObject("PrintSwal", print);

        return mv;
    }

    @RequestMapping("/User_Partss")
    public ModelAndView User_Partss(String User_Session) {
        ModelAndView mv = new ModelAndView("Search_Parts");

        mv.addObject("User", user_session1);

        return mv;
    }

    @RequestMapping("/user_select_operation")
    public ModelAndView user_select_operation(String Parts_operation) {
        ModelAndView mv = new ModelAndView("Search_Parts");

        mv.addObject("User", user_session1);

        if (Parts_operation.equals("None")) {
            return mv;
        } else if (Parts_operation.equals("Search")) {
            mv.addObject("selectSearch", "Search");
        } else if (Parts_operation.equals("Display")) {
            mv.addObject("selectDisplay", "Display");
            return User_Parts_Details(mv);
        } else if (Parts_operation.equals("R&R")) {
            mv.addObject("selectRR", "RR");
        }

        return mv;
    }

    @RequestMapping("/user_search_Parts")
    public ModelAndView user_search_Parts(String Parts_title) {
        ModelAndView mv = new ModelAndView("Search_Parts");

        mv.addObject("User", user_session1);

        Optional<PartsRegistration> breg1 = brepo.findById(Parts_title);

        if (breg1.isPresent()) {
            mv.addObject("PrintSwal", "Parts_Found");
        } else {
            mv.addObject("PrintSwal", "Parts_Not_Found");
        }
        return mv;
    }

    @RequestMapping("/User_Parts_Details")
    public ModelAndView User_Parts_Details(ModelAndView mv) {

        List<PartsRegistration> breg1 = brepo.findAll();

        if (breg1.isEmpty()) {
            mv.addObject("PrintSwal", "Parts_Details_Empty");

            mv.setViewName("Search_Parts");
        } else {
            PartsRegistration Parts = null;
            mv.addObject("PartsArray", Parts);
            mv.addObject("PartsObject", breg1);

        }

        return mv;

    }

    @RequestMapping("/user_Rate_Parts")
    public ModelAndView user_Rate_Parts(String Parts_title, String Manufacturing_country, String rate) {
        ModelAndView mv = new ModelAndView("Search_Parts");

        mv.addObject("User", user_session1);

        Optional<PartsRegistration> breg1 = brepo.findById(Parts_title);

        if (breg1.isPresent()) {
            mv.addObject("PrintSwal", "RParts_Found");
            PartsRegistration breg = breg1.get();
            breg.setRate(rate);
            breg.setManufacturing_country(Manufacturing_country);
            brepo.save(breg);
        } else {
            mv.addObject("PrintSwal", "RParts_Not_Found");
        }
        return mv;

    }

    @RequestMapping("/User_Buy_Parts")
    public ModelAndView User_Buy_Parts() {
        ModelAndView mv = new ModelAndView("User_Buy_Parts");

        mv.addObject("User", user_session1);

        return mv;
    }

    @RequestMapping("/user_Search_Buy_Parts")
    public ModelAndView user_Search_Buy_Parts(String Parts_title) {
        ModelAndView mv = new ModelAndView("User_Buy_Parts");

        mv.addObject("User", user_session1);


        Optional<PartsRegistration> breg1 = brepo.findById(Parts_title);

        if (breg1.isPresent()) {
            PartsRegistration breg = breg1.get();
            mv.addObject("Bname", breg.getParts_title());
            mv.addObject("Bprice", breg.getPrice());
            mv.addObject("PrintSwal", "Parts_Found");

        } else {
            mv.addObject("PrintSwal", "Parts_Not_Found");
        }

        return mv;
    }

    @RequestMapping("/user_Save_Buy_Parts")
    public ModelAndView user_Save_Buy_Parts(OrderRegistration oreg) {
        ModelAndView mv = new ModelAndView("User_Buy_Parts");

        mv.addObject("User", user_session1);

        oreg.setBusername(user_session1);

        orepo.save(oreg);

        mv.addObject("PrintSwal", "Buy_Sucess");

        return mv;
    }

    @RequestMapping("/Order_Details")
    public ModelAndView Order_Details() {
        ModelAndView mv = new ModelAndView("Order_Details");

        mv.addObject("User", user_session1);

        List<OrderRegistration> oreg1 = orepo.findAll();

        List<OrderRegistration> oreg2 = orepo.findByBusername(user_session1);

        if (oreg1.isEmpty()) {
            mv.addObject("PrintSwal", "Order_Details_Empty");

            mv.setViewName("User_View");
        } else if (oreg2.isEmpty()) {
            mv.addObject("PrintSwal", "Order_Details_Empty");

            mv.setViewName("User_View");
        } else {
            OrderRegistration order = null;
            mv.addObject("OrderArray", order);
            mv.addObject("OrderObject", oreg2);

        }

        return mv;

    }

}