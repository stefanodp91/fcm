package org.bitbucket.stefanodp91;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by stefano on 20/10/2016.
 */
public class FloatingContextualMenu extends PopupWindow {
    private static final String TAG = FloatingContextualMenu.class.getName();

    private static int DISPLAY_CHILDREN_NUMBER = 2; // number of children to display

    private Context context;
    private View anchor; // view to anchor the popup
    private Type type; // type of view (icon only, text only or both)
    private List<FloatingContextualItem> mItemList; // list of item inserted from user
    private List<FloatingContextualItem> mVisibleItemList; // list of item inserted from user
    private static PopupWindow window; // the basic windows

    private boolean isOrientationVertical = false; // dispaly orientation

    private View rootView; // the window layout
    private LinearLayout mFloatingContextualMenuContainer; // the container of the itemList items
    private View mViewMoreContainer; // the "more" or "less" button

    public FloatingContextualMenu(Builder builder) {
        this.context = builder.context;

        // anchor is not mandatory
        if (builder.anchor != null) {
            this.anchor = builder.anchor;
        }

        // default number of children
        if (builder.children != 0) {
            this.DISPLAY_CHILDREN_NUMBER = builder.children;
        }

        this.mItemList = builder.mItemList;

        // create the list of visible items
        mVisibleItemList = new ArrayList<>();
        for (int i = 0; i < mItemList.size(); i++) {
            FloatingContextualItem item = mItemList.get(i);
            if (item.isVisible()) {
                mVisibleItemList.add(item);
            }
        }


        this.type = builder.type;

        createWindow(context);
        addChildren();
    }

    public void setAnchor(View anchor) {
        this.anchor = anchor;
    }

    public static void setDisplayChildrenNumber(int displayChildrenNumber) {
        DISPLAY_CHILDREN_NUMBER = displayChildrenNumber;
    }

    public void show() {
        window.showAsDropDown(anchor, -5, 0);
    }

    @Override
    public void dismiss() {
        if (window != null && window.isShowing()) {
            window.dismiss();
        }
    }

    // create the popup window
    private void createWindow(Context context) {
        window = new PopupWindow(context);
        inflateWindowLayout(context); // inflate a custom layout
        window.setFocusable(true);
        window.setOutsideTouchable(true);  // allow to dismiss the popup
        // popup size
        window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // set a custom background
        window.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shadow));
    }

    // inflate the popup layout
    private void inflateWindowLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.floating_contextual_menu, null);
        window.setContentView(rootView);
    }

//    // add children inserted from user
//    private void addChildren() {
//        // retrieve the container view
//        mFloatingContextualMenuContainer = (LinearLayout) rootView.findViewById(R.id.contextual_menu);
//
//        // check if there are items
//        if (mItemList.size() > 0) {
//            // if there are more than DISPLAY_CHILDREN_NUMBER children show a "more" button
//            if (mItemList.size() > DISPLAY_CHILDREN_NUMBER) {
//                // init the "more button"
//                mViewMoreContainer = LayoutInflater.from(context).inflate(R.layout.floating_contextual_more_item, null);
//                changeMoreViewIcon();
//                addChildrenWithMoreButton();
//            } else if (mItemList.size() == DISPLAY_CHILDREN_NUMBER) {
//                addCustomRangeChildren(0, mItemList.size(), HORIZONTAL);
//            } else {
//                addCustomRangeChildren(0, mItemList.size(), HORIZONTAL);
//            }
//        } else {
//            Log.e(TAG, "list is empty");
//            throw new IllegalStateException("YOU HAVE TO ADD AT LEAST ONE FloatingActionItem");
//        }
//    }
//
//
//    // a custom range of children
//    private void addCustomRangeChildren(int start, int limit, int orientation) {
//        for (int i = start; i < limit; i++) {
//            FloatingContextualItem item = mItemList.get(i);
//            if (item.isVisible()) {
//                Utils.addChild(context, item, mFloatingContextualMenuContainer, type, orientation);
//            }
//        }
//    }


    // add children inserted from user
    private void addChildren() {
        // retrieve the container view
        mFloatingContextualMenuContainer = (LinearLayout) rootView.findViewById(R.id.contextual_menu);

        // check if there are items
        if (mItemList.size() > 0) {
            // if there are more than DISPLAY_CHILDREN_NUMBER children show a "more" button
            if (mVisibleItemList.size() <= DISPLAY_CHILDREN_NUMBER) {
                addCustomRangeChildren(0, mVisibleItemList.size(), Utils.HORIZONTAL);
            } else {
                // init the "more button"
                mViewMoreContainer = LayoutInflater.from(context).inflate(R.layout.floating_contextual_more_item, null);
                changeMoreViewIcon();
                addChildrenWithMoreButton();
            }
        } else {
            Log.e(TAG, "list is empty");
            throw new IllegalStateException("YOU HAVE TO ADD AT LEAST ONE FloatingActionItem");
        }
    }

    // a custom range of children
    private void addCustomRangeChildren(int start, int limit, int orientation) {
        for (int i = start; i < limit; i++) {
            FloatingContextualItem item = mVisibleItemList.get(i);
            if (item.isVisible()) {
                Utils.addChild(context, item, mFloatingContextualMenuContainer, type, orientation);
            }
        }
    }

    // add DISPLAY_CHILDREN_NUMBER children, than show a "more" button
    private void addChildrenWithMoreButton() {
        // add DISPLAY_CHILDREN_NUMBER children
        setHorizontalLayout();// default

        // set more  listener
        mViewMoreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOrientation(); // change layout orientation
                changeMoreViewIcon(); // update the more/less icon

                // update the layout adding the views
                if (isOrientationVertical) {
                    setVerticalLayout();
                } else {
                    setHorizontalLayout();
                }
            }
        });
    }

    // set the horizontal layout
    private void setHorizontalLayout() {
        mFloatingContextualMenuContainer.removeAllViews(); // remove all views
        // add children
        addCustomRangeChildren(0, DISPLAY_CHILDREN_NUMBER, Utils.HORIZONTAL);

        // add view more if there are at least DISPLAY_CHILDREN_NUMBER children
        if (mItemList.size() > DISPLAY_CHILDREN_NUMBER &&
                mFloatingContextualMenuContainer.getChildCount() >= DISPLAY_CHILDREN_NUMBER) {
            Utils.addViewWithMargin(context, mFloatingContextualMenuContainer, mViewMoreContainer, Utils.HORIZONTAL);
        }
    }

//    // set the vertical layout
//    private void setVerticalLayout() {
//        mFloatingContextualMenuContainer.removeAllViews(); // remove all views
//        // add children
//        addCustomRangeChildren(0, mItemList.size(), VERTICAL);
//
//        // add view less if there are at least DISPLAY_CHILDREN_NUMBER children
//        if (mItemList.size() > DISPLAY_CHILDREN_NUMBER &&
//                mFloatingContextualMenuContainer.getChildCount() >= DISPLAY_CHILDREN_NUMBER) {
//            Utils.addViewWithMargin(context, mFloatingContextualMenuContainer, mViewMoreContainer, VERTICAL);
//        }
//    }

    // set the vertical layout
    private void setVerticalLayout() {
        mFloatingContextualMenuContainer.removeAllViews(); // remove all views
        // add children
        addCustomRangeChildren(DISPLAY_CHILDREN_NUMBER, mVisibleItemList.size(), Utils.VERTICAL);

        // add view less if there are at least DISPLAY_CHILDREN_NUMBER children
        Utils.addViewWithMargin(context, mFloatingContextualMenuContainer, mViewMoreContainer, Utils.VERTICAL);
    }

    // set more/less icon
    private void changeMoreViewIcon() {
        int drawableId;
        if (!isOrientationVertical) {
            drawableId = R.drawable.ic_more;
        } else {
            drawableId = R.drawable.ic_back;
        }

        Drawable drawable = Utils.changeDrawableColor(context,
                R.color.contextual_menu_icon_color,
                drawableId);
        ImageView mMore = (ImageView) mViewMoreContainer.findViewById(R.id.more);
        mMore.setBackground(drawable);
    }

    // set layout to vertical or horizontal
    private void changeOrientation() {
        int orientation = mFloatingContextualMenuContainer.getOrientation();

        if (orientation == Utils.VERTICAL) {
            mFloatingContextualMenuContainer.setOrientation(Utils.HORIZONTAL);
            isOrientationVertical = false;
        } else if (orientation == Utils.HORIZONTAL) {
            mFloatingContextualMenuContainer.setOrientation(Utils.VERTICAL);
            isOrientationVertical = true;
        }
    }

    // menu builder
    public static class Builder {
        private Context context;
        private View anchor;
        private Type type = Type.BOTH;
        private int children;
        private List<FloatingContextualItem> mItemList;

        public Builder(Context context) {
            this.context = context;
            this.mItemList = new ArrayList<>();
        }

        public Builder anchor(View anchor) {
            this.anchor = anchor;
            return this;
        }

        public Builder children(int children) {
            this.children = children;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder add(FloatingContextualItem item) {
            mItemList.add(item);
            return this;
        }

        public FloatingContextualMenu build() {
            return new FloatingContextualMenu(this);
        }
    }
}