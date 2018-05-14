package com.sashaspringboot;

import com.sashaspringboot.handler.UpdateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.api.objects.Update;


@Controller
public class HelloWorldController {
    private Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);
    private final UpdateHandler handler;

    @Autowired
    public HelloWorldController(UpdateHandler handler) {
        this.handler = handler;
    }
    @CrossOrigin
    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        String message = "You just create Spring Boot Example successfully";
        model.addAttribute("name", name);
        model.addAttribute("message", message);

        return "hello";
    }
    @CrossOrigin
    @RequestMapping("/mybotwebhook")
    public ResponseEntity<?> webhook(@RequestBody Update update) {
        LOG.debug("========================= body from telegram recived ================== {}" , update);
        handler.handleUpdate(update);
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/status", method = RequestMethod.GET)
    public ResponseEntity<?> getStatusOfService() {
        return    new ResponseEntity<>("Привет сервис запущен", HttpStatus.OK);
    }
}
