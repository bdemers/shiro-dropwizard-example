package org.apache.shiro.example.dropwizard.dao;

import org.apache.shiro.example.dropwizard.models.TieCraft;

import java.util.Collection;

public interface TieCraftDao {

    Collection<TieCraft> listTieCrafts();

    TieCraft getTieCraft(String serial);
}
