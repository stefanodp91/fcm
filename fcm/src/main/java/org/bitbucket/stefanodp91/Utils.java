package org.bitbucket.stefanodp91;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by stefano on 05/10/2016.
 */

class Utils {
    static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    static final int VERTICAL = LinearLayout.VERTICAL;

    /**
     * returns a drawable with the new color
     *
     * @param context
     * @param colorToId          the id of the new color to change
     * @param drawableToChangeId the id of the drawable to color
     * @return the colored drawable
     */
    static Drawable changeDrawableColor(Context context, @ColorRes int colorToId,
                                        @DrawableRes int drawableToChangeId) {
        int color = context.getResources().getColor(colorToId);
        Drawable drawable = context.getResources().getDrawable(drawableToChangeId);
        drawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY);

        return drawable;
    }

    /**
     * Return the integer value of dimen in /dimens.xml
     *
     * @param context
     * @param dimensId the dimen
     * @return the integer valud
     */
    static int getDimens(Context context, @DimenRes int dimensId) {
        return (int) context.getResources().getDimension(dimensId);
    }

    /**
     * return edited layout params with margin bottom or right
     *
     * @param context
     * @param orientation the orientation:
     * @return
     */
    static LinearLayout.LayoutParams getMargin(Context context, int orientation) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        // setMargins(left, top, right, bottom)
        if (orientation == HORIZONTAL) {
            // add margin right
            layoutParams.setMargins(0, 0, Utils.getDimens(context, R.dimen.floating_contextual_item_horizontal_margin), 0);
        } else if (orientation == VERTICAL) {
            // add margin bottom
            layoutParams.setMargins(0, 0, 0, Utils.getDimens(context, R.dimen.floating_contextual_item_vertical_margin));
        }

        return layoutParams;
    }

    static View addChild(Context context, FloatingContextualItem item, LinearLayout container, Type type, int orientation) {
        View child = Utils.createChildFromFloatingContextualItem(context, item, type);
        addViewWithMargin(context, container, child, orientation);
        return child;
    }

    /**
     * Add a view inside a container adding a margin right
     *
     * @param context
     * @param container the container
     * @param child     the view to add
     */
    static void addViewWithMargin(Context context, ViewGroup container, View child, int orientation) {
        container.addView(child, getMargin(context, orientation));
    }

    // create a view from the FloatingContextualItem
    private static View createChildFromFloatingContextualItem(Context context, FloatingContextualItem item, Type type) {
        // create a view
        View child = LayoutInflater.from(context).inflate(R.layout.floating_contextual_item, null);

        // set title
        TextView mTitleView = (TextView) child.findViewById(R.id.contextual_action_title);
        mTitleView.setText(item.getName());

        // set listener
        child.setOnClickListener(item.getAction());

        // set icon
        ImageView mIconView = (ImageView) child.findViewById(R.id.contextual_action_icon);
        if (item.getIconId() != 0) {
            Drawable drawable = Utils.changeDrawableColor(context,
                    R.color.contextual_menu_icon_color,
                    item.getIconId());
            mIconView.setBackground(drawable);
            mIconView.setVisibility(View.VISIBLE);
        } else {
            mIconView.setVisibility(View.GONE);
        }

        // set visibility
        if (item.isVisible()) {
            child.setVisibility(View.VISIBLE);
        } else {
            child.setVisibility(View.GONE);
        }

        // set view type
        if (type == Type.TEXT) {
            // only text is visible
            mIconView.setVisibility(View.GONE);
            mTitleView.setVisibility(View.VISIBLE);
        } else if (type == Type.ICON) {
            // only icon is visible
            mTitleView.setVisibility(View.GONE);
            mIconView.setVisibility(View.VISIBLE);
        } else if (type == Type.BOTH) {
            // text and icon are visible
            mIconView.setVisibility(View.VISIBLE);
            mTitleView.setVisibility(View.VISIBLE);
        } else {
            // default case - text and icon are visible
            mIconView.setVisibility(View.VISIBLE);
            mTitleView.setVisibility(View.VISIBLE);
        }

        return child;
    }
}