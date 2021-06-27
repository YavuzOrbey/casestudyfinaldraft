package com.casestudydraft.webcontroller;

import org.springframework.web.bind.annotation.ModelAttribute;

abstract class GenericController {
    @ModelAttribute("message")
    public String theMessage(){
        return "";
    }
}
