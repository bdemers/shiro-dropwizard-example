package org.apache.shiro.example.dropwizard.resources;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.example.dropwizard.dao.StormtrooperDao;
import org.apache.shiro.example.dropwizard.models.Stormtrooper;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;

/**
 * Stormtrooper Resource.
 */
@Path("/troopers")
@Produces("application/json")
public class StormtrooperResource {

    private final StormtrooperDao trooperDao;

    @Inject
    public StormtrooperResource(StormtrooperDao trooperDao) {
        this.trooperDao = trooperDao;
    }

    /**
     * Returns a Collection of all Stormtroopers.
     * @return Returns a Collection of all Stormtroopers.
     */
    @GET
    @RequiresPermissions("trooper:read")
    public Collection<Stormtrooper> listTroopers() {
        return trooperDao.listStormtroopers();
    }

    @Path("/{id}")
    @GET
    @RequiresPermissions("trooper:read")
    public Stormtrooper getTrooper(@PathParam("id") String id) {

        Stormtrooper stormtrooper = trooperDao.getStormtrooper(id);
        if (stormtrooper == null) {
            throw new NotFoundException();
        }
        return stormtrooper;
    }

    @POST
    @RequiresPermissions("trooper:create")
    public Stormtrooper createTrooper(Stormtrooper trooper) {

        return trooperDao.addStormtrooper(trooper);
    }

    @Path("/{id}")
    @POST
    @RequiresPermissions("trooper:update")
    public Stormtrooper updateTrooper(@PathParam("id") String id, Stormtrooper updatedTrooper) {

        return trooperDao.updateStormtrooper(id, updatedTrooper);
    }

    @Path("/{id}")
    @DELETE
    @RequiresPermissions("trooper:delete")
    public void deleteTrooper(@PathParam("id") String id) {
        trooperDao.deleteStormtrooper(id);
    }

}
