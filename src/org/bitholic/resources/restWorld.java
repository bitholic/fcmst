package org.bitholic.resources;

/**
 * Created by bitholic on 16/5/23.
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;

@Path("/lines")
public class restWorld {
    @GET
    @Produces("text/plain")
    public String saySomething(){
        return "yeah RESTful world";
    }

    @GET
    @Path("/{param}")
    @Produces("text/plain;charset=UTF-8")
    public String sayHelloToSB(@PathParam("param") String name) {
        return "Let's be restful! " + name;
    }
}
