package org.apache.shiro.example.dropwizard.resources;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.example.dropwizard.dao.TieCraftDao;
import org.apache.shiro.example.dropwizard.models.TieCraft;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;

/**
 * Tie Craft Resource.
 */
@Path("/tie")
@Produces("application/json")
public class TieResource {

    private final TieCraftDao tieCraftDao;

    @Inject
    public TieResource(TieCraftDao tieCraftDao) {
        this.tieCraftDao = tieCraftDao;
    }

    /**
     * Returns a Collection of all TieCraft.
     * @return Returns a Collection of all TieCraft.
     */
    @GET
    @RequiresPermissions("tie:read")
    public Collection<TieCraft> listTieCraft() {
        return tieCraftDao.listTieCrafts();
    }

    @Path("/{serial}")
    @GET
    @RequiresPermissions("tie:read")
    public TieCraft getTieCraft(@PathParam("serial") String serial) {
    TieCraft tieCraft = tieCraftDao.getTieCraft(serial);
        if (tieCraft == null) {
            throw new NotFoundException();
        }
        return tieCraft;
    }
}
