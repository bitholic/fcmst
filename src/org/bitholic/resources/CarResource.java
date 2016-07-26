package org.bitholic.resources;

import org.bitholic.dao.CarAccess;
import org.bitholic.utils.Authentication;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by bitholic on 16/7/24.
 */

@Path("/cars")
public class CarResource {
    @GET
    @Produces("application/json")
    public String getCars() {
        //if(Authentication.identityVerify())
        return CarAccess.getCars();
    }

    @GET
    @Path("/pagination")
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

    @POST
    @Produces("application/json")
    public Response addCar(String data) {
        return Response.ok().entity(CarAccess.addCar(data)).build();
    }

    @DELETE
    @Path("/{licensePlate}")
    @Produces("application/json")
    public Response deleteCar(@PathParam("licensePlate") String licensePlate) {
        return Response.ok().entity(CarAccess.deleteCar(licensePlate)).build();
    }
}
