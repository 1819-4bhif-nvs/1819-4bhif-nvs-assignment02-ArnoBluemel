package at.htl.vehicle.rest;

import at.htl.vehicle.model.Vehicle;

import javax.ws.rs.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("vehicle")
public class VehicleEndpoint
{
    @GET
    @Path("{id}")
    public Vehicle find(@PathParam("id") long id)
    {
        return new Vehicle("Opel " + id, "Commodore");
    }

    @GET
    public List<Vehicle> getAll()
    {
        List<Vehicle> all = new ArrayList<Vehicle>();
        all.add(find(42));
        all.add(find(43));
        return all;
    }

    @DELETE
    @Path ("{id}")
    public void delete(@PathParam("id") long id)
    {
        System.out.printf("%d deleted", id);
    }

    @POST
    public void save(Vehicle v)
    {
        System.out.println("vehicle = " + v);
    }
}