package com.example.real_world_app.utils;

import java.util.Date;

public class SlugUtil {
    public  static String getSlug(String title) {
        return title.toLowerCase().replaceAll("\\s+","-") + "-" + new Date().getTime();
    }
}
