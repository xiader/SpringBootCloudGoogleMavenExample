package com.sashaspringboot;

import com.sashaspringboot.handler.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.telegram.telegrambots.api.objects.Update;

@Controller
public class HelloWorldController {

    private final UpdateHandler handler;

    @Autowired
    public HelloWorldController(UpdateHandler handler) {
        this.handler = handler;
    }

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        String message = "You just create Spring Boot Example successfully";
        model.addAttribute("name", name);
        model.addAttribute("message", message);

        return "hello";
    }
    @RequestMapping("/mybotwebhook")
    public ResponseEntity<?> webhook(@RequestBody Update update) {
        handler.handleUpdate(update);
        return new ResponseEntity(HttpStatus.OK);
    }
}
