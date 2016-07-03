package by.roodxx.district.redirect;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by roodxx on 8.6.16.
 */
public class Application extends ResourceConfig {

    public Application() {
        packages(true, "by.roodxx.district.redirect.controller;");
    }
}
