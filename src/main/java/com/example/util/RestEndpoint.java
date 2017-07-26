package com.example.util;

import com.example.data.DataRecorder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/agent")
public class RestEndpoint {

    @GET
    public Response getData(){
        return Response.status(200).entity(DataRecorder.getAllDetails()).build();
    }


}
