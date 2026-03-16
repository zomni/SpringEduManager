package com.untec.springedumanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControllerCustom {

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "403";
    }
}