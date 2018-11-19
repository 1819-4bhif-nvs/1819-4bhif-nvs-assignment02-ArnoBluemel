package at.htl.vehicle.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VehicleEndpointIT
{
    private Client client;
    private WebTarget target;

    @Before
    public void initClient()
    {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8085/vehicle/rs/vehicle/");
    }

    @Test
    public void fetchVehicle()
    {
        Response response = this.target.request(MediaType.TEXT_PLAIN).get();
        assertThat(response.getStatus(), is(200));
        String payload = response.readEntity(String.class);
        System.out.println("Payload = " + payload);
    }

    @Test
    public void fetchVehicleJsonObject()
    {
        Response response = this.target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println("Payload = " + payload);
    }

    @Test
    public void crud()
    {

    }
}