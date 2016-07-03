package by.roodxx.district.redirect.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by roodxx on 6.6.16.
 */
@Path("")
public class Test {

    @GET
    @Produces("application/json")
    public String test1() {
        return "123";
    }
}
