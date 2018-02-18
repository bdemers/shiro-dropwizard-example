package org.apache.shiro.example.dropwizard.dao;

import org.apache.shiro.example.dropwizard.models.TieCraft;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Simple in memory DAO that initializes the collection with content found from http://starwars.wikia.com/wiki/TIE_line.
 */
public class DefaultTieCraftDao implements TieCraftDao {

    final private Map<String, TieCraft> tieCraftMap = Collections.synchronizedSortedMap(new TreeMap<String, TieCraft>());

    public DefaultTieCraftDao() {
        addTieCraft(new TieCraft("tie-11", "Starfighter", "Starfighter"));
        addTieCraft(new TieCraft("tie-22", "Shuttle", "Boarding Shuttle"));
        addTieCraft(new TieCraft("tie-33", "Starfighter", "Interceptor"));
        addTieCraft(new TieCraft("tie-44", "Starfighter", "Defender"));
        addTieCraft(new TieCraft("tie-55", "Light bomber", "Bomber"));
    }

    public TieCraft addTieCraft(TieCraft tieCraft) {
        tieCraftMap.put(tieCraft.getSerial(), tieCraft);
        return tieCraft;
    }

    @Override
    public Collection<TieCraft> listTieCrafts() {
        return Collections.unmodifiableCollection(tieCraftMap.values());
    }

    @Override
    public TieCraft getTieCraft(String serial) {
        return tieCraftMap.get(serial);
    }
}
