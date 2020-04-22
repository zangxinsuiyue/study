package com.lhz.blog.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@Controller
public class DemoController {
    @RequestMapping("/")
    public String hello() {
        return "index";
    }
}
