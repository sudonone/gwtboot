package com.github.aceroni75.gwtboot.server;

import com.github.aceroni75.gwtboot.server.resource.ResourcePackage;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages(ResourcePackage.class.getPackage().getName());
    }
}
