package com.example.bing.yiji.Model;

import android.location.Location;
import android.media.Image;

import java.util.Date;

public class Payment {
    private enum Type {
        food("food"), clothing("clothing"), home("home"), transport("transport"), entertainment("entertainment"),
        education("education"), heath("heath"), investment("investment"), other("other");
        private final String type;

        private Type(String type) {
            this.type = type;
        }
    }
    private Type type;
    private Date date;
    private Location location;
    private Image image;
    private String description;
}
