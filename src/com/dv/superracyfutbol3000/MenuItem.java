package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.ResourceManager;
import org.newdawn.slick.Image;

import java.util.HashMap;
import java.util.Map;

public class MenuItem extends Entity{
    private Map<String, String> menu_item_image_paths = new HashMap<String, String>();

    MenuItem(float x, float y, String item_off_path, String item_on_path) {
        super(x,y);
        menu_item_image_paths.put("off", item_off_path);
        menu_item_image_paths.put("on", item_on_path);
        addImageWithBoundingBox(ResourceManager.getImage(menu_item_image_paths.get("off")));
    }

    public boolean SwapImage(boolean on_flag, boolean white_flag) {
        if (on_flag && !white_flag){
            //  then remove grey
            this.removeImage(ResourceManager.getImage( menu_item_image_paths.get("off")));
            //  add white
            this.addImageWithBoundingBox(ResourceManager.getImage(menu_item_image_paths.get("on")));
            return true;
        }else if (white_flag && !on_flag){
            //  remove white
            this.removeImage(ResourceManager.getImage(menu_item_image_paths.get("on")));
            //  add grey
            this.addImageWithBoundingBox(ResourceManager.getImage(menu_item_image_paths.get("off")));
            return false;
        }
        return white_flag;
    }
}
