package by.roodxx.district.redirect.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("redirect")
public class RedirectController {

    @GET
    @Produces("application/json")
    @Path("instagram")
    public String doInstagramRedirect(@Context HttpServletRequest request) {
        return "123";
    }
}
