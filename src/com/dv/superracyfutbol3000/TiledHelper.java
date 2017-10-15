package com.dv.superracyfutbol3000;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.io.InputStream;
import java.util.ArrayList;

public class TiledHelper extends TiledMap {
    public TiledHelper(String ref, String tileSetsLocation) throws SlickException {
        super(ref, tileSetsLocation);
    }

    public ArrayList<Object> getObjectGroups(){
        ArrayList<Object> object_list = new ArrayList<>();
        for (int i = 0; i < this.getObjectGroupCount();i++)
            object_list.add(this.objectGroups.get(i));
        return object_list;
    }
}
