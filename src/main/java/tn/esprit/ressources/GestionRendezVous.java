package tn.esprit.ressources;


import tn.esprit.entites.RendezVous;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.Date;
import java.util.ArrayList;

import java.util.List;


@Path("rendezvous")
public class GestionRendezVous {

    public static List<RendezVous> listRV = new ArrayList<RendezVous>();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ajouterRV(RendezVous rv) {
        if(listRV.add(rv))
            return Response.status(Response.Status.CREATED).entity("Add Successful").build();
        return Response.status(Response.Status.NOT_FOUND).entity("Echec").build();


    }
    @GET
    @Produces("text/plain")
    public  Response  displayRVList() {

        if(listRV.size()!=0)
            return Response.status(Response.Status.FOUND).entity(listRV).build();
        else
            return Response.status(Response.Status.NOT_FOUND).entity("no record found").build();

    }

    @GET
    @Path("{id}")
    @Produces("text/plain")
    public Response getRV(@PathParam(value = "id") int id) {
        for (RendezVous info:listRV) {
            if(info.getId()==id) {
                return  Response.status(Response.Status.FOUND)
                        .entity(info)
                        .build();

            }
        }

        return  Response.status(Response.Status.NOT_FOUND).build();


    }


    @PUT
    @Consumes("application/xml")
    @Produces("text/plain")
    public Response updateRV(RendezVous rv) {
        int index= this.getIndexById(rv.getId());
        if (index!=-1) {
            listRV.set(index, rv);
            return Response.status(Response.Status.OK).entity("update successful").build();

        }
        return Response.status(Response.Status.NOT_FOUND).entity("update unsuccessful").build();

    }

    @DELETE
    @Path("{id}")
    @Produces("text/plain")
    public boolean deleteRV(@PathParam(value = "id") int id){
        int index= getIndexById(id);

        if (index!=-1) {
            listRV.remove(index);
            return true;
        }else
            return false;

    }
    public int getIndexById(int id) {
        for(RendezVous rv: listRV) {
            if (rv.getId()==id)
                return listRV.indexOf(rv);
        }
        return -1;
    }
}
