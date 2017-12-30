package com.github.aceroni75.gwtboot.server.resource;

import com.github.aceroni75.gwtboot.shared.resource.HelloResource;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController implements HelloResource {

    @Override
    public String hello() {
        return "Hello world!";
    }
}
