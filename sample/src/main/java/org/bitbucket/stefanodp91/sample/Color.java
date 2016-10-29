package org.bitbucket.stefanodp91.sample;

import android.support.annotation.ColorRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 29/10/2016.
 */
public class Color {
    private String name;
    @ColorRes
    int res;

    public Color(String name, int res) {
        this.name = name;
        this.res = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "Color{" +
                "name='" + name + '\'' +
                '}';
    }

    public static List<Color> getList() {
        List<Color> mColorList = new ArrayList<>();
        mColorList.add(new Color("Black", R.color.black));
        mColorList.add(new Color("White", R.color.white));
        mColorList.add(new Color("Red", R.color.red_500));
        mColorList.add(new Color("Pink", R.color.pink_500));
        mColorList.add(new Color("Purple", R.color.purple_500));
        mColorList.add(new Color("Deep Purple", R.color.deep_purple_500));
        mColorList.add(new Color("Indigo", R.color.indigo_500));
        mColorList.add(new Color("Blue", R.color.blue_500));
        mColorList.add(new Color("Light Blue", R.color.light_blue_500));
        mColorList.add(new Color("Cyan", R.color.cyan_500));
        mColorList.add(new Color("Teal", R.color.teal_500));
        mColorList.add(new Color("Green", R.color.green_500));
        mColorList.add(new Color("Light Green", R.color.light_green_500));
        mColorList.add(new Color("Lime", R.color.lime_500));
        mColorList.add(new Color("Yellow", R.color.yellow_500));
        mColorList.add(new Color("Amber", R.color.amber_500));
        mColorList.add(new Color("Orange", R.color.orange_500));
        mColorList.add(new Color("Deep Orange", R.color.deep_orange_500));
        mColorList.add(new Color("Brown", R.color.brown_500));
        mColorList.add(new Color("Grey", R.color.grey_500));
        mColorList.add(new Color("Blue Grey", R.color.blue_grey_500));


        return mColorList;
    }
}