package org.bitholic.resources;

import org.bitholic.dao.CarAccess;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by bitholic on 16/7/24.
 */

@Path("/cars")
public class Cars {
    @GET
    @Produces("application/json")
    public String getCars() {
        return CarAccess.getCars();
    }

    @GET
    @Path("pagination")
    @Produces("application/json")
    public Response getCars(@QueryParam("offset") int offset, @QueryParam("limit") int limit) {
        String cars = CarAccess.getCars(1, offset, limit);
        if (!cars.equals("null")) {
            return Response.ok().entity(cars).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{licensePlate}")
    @Produces("application/json")
    public Response getCar(@PathParam("licensePlate") String licensePlate) {
        String car = CarAccess.getCar(licensePlate);
        if (!car.equals("null")) {
            return Response.ok().entity(car).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
