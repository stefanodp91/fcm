package org.bitbucket.stefanodp91;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;


/**
 * Created by stefano on 20/10/2016.
 */
public class FloatingContextualItem {
    private String name; // item name
    private View.OnClickListener action; // item action on click listener
    @DrawableRes
    private int iconId = R.drawable.ic_error; // item icon
    private
    @ColorRes
    int iconColorId = R.color.grey_700; // item icon color
    @ColorRes
    private int textColorId = R.color.grey_700; // item text color
    private boolean visible = false; // item visibility

    public FloatingContextualItem(Builder builder) {
        this.name = builder.name;
        this.action = builder.action;

        // default icon
        if (builder.iconId != 0) {
            this.iconId = builder.iconId;
        }

        // default icon color
        if (builder.iconColorId != 0) {
            this.iconColorId = builder.iconColorId;
        }

        // default icon color
        if (builder.textColorId != 0) {
            this.textColorId = builder.textColorId;
        }

        this.visible = builder.visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public View.OnClickListener getAction() {
        return action;
    }

    public void setAction(View.OnClickListener action) {
        this.action = action;
    }

    @DrawableRes
    public int getIconId() {
        return iconId;
    }

    public void setIconId(@DrawableRes int iconId) {
        this.iconId = iconId;
    }

    @ColorRes
    public int getIconColorId() {
        return iconColorId;
    }

    public void setIconColorId(@ColorRes int iconColorId) {
        this.iconColorId = iconColorId;
    }

    @ColorRes
    public int getTextColorId() {
        return textColorId;
    }

    public void setTextColorId(@ColorRes int textColorId) {
        this.textColorId = textColorId;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public static class Builder {
        private String name;
        private View.OnClickListener action;
        @DrawableRes
        private int iconId = R.drawable.ic_error;

        @ColorRes
        private int iconColorId = R.color.grey_700;

        @ColorRes
        private int textColorId = R.color.grey_700;

        private boolean visible = false;

        public Builder(String name, View.OnClickListener action) {
            this.name = name;
            this.action = action;
        }

        public Builder icon(@DrawableRes int iconId) {
            this.iconId = iconId;
            return this;
        }

        public Builder iconColor(@ColorRes int iconColorId) {
            this.iconColorId = iconColorId;
            return this;
        }

        public Builder textColor(@ColorRes int textColorId) {
            this.textColorId = textColorId;
            return this;
        }

        public Builder visible(boolean visible) {
            this.visible = visible;
            return this;
        }

        public FloatingContextualItem build() {
            return new FloatingContextualItem(this);
        }
    }
}