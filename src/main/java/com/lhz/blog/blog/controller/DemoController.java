package com.lhz.blog.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@Controller
public class DemoController {
    @RequestMapping("hello")
    public String hello(@RequestParam(name="name") String name, Model model) {
        model.addAttribute("name",name);
        return "hello";
    }
}
