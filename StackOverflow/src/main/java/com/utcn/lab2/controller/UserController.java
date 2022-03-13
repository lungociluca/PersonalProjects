package com.utcn.lab2.controller;

import com.utcn.lab2.model.User;
import com.utcn.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/getUser")
    @ResponseBody
    private User findUserById(@RequestParam(name = "id") Integer id) {
        return userService.findByID(id);
    }
}
