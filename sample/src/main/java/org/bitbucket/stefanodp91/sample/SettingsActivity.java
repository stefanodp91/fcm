package org.bitbucket.stefanodp91.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import org.bitbucket.stefanodp91.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefano on 21/10/2016.
 */
public class SettingsActivity extends AppCompatActivity implements CheckBox.OnCheckedChangeListener {
    protected static final String TAG = SettingsActivity.class.getName();

    protected static final String EXTRA_REPLY = "EXTRA_REPLY";
    protected static final String EXTRA_COPY = "EXTRA_COPY";
    protected static final String EXTRA_FORWARD = "EXTRA_FORWARD";
    protected static final String EXTRA_SELECT_ALL = "EXTRA_SELECT_ALL";
    protected static final String EXTRA_TRANSLATE = "EXTRA_TRANSLATE";
    protected static final String EXTRA_TYPE = "EXTRA_TYPE";
    protected static final String EXTRA_BACKGROUND_COLOR = "EXTRA_BACKGROUND_COLOR";
    protected static final String EXTRA_MORE_LESS_COLOR = "EXTRA_MORE_LESS_COLOR";
    protected static final String EXTRA_ICONS_COLOR = "EXTRA_ICONS_COLOR";
    protected static final String EXTRA_TITLES_COLOR = "EXTRA_TITLES_COLOR";

    private CheckBox mReply, mCopy, mForward, mSelectAll, mTranslate;
    private boolean isReplyVisible, isCopyVisible, isForwardVisible, isSelectAllVisible, isTranslateVisible;
    private Spinner mExtraType, mExtraBackgroundColor, mExtraMoreLessColor, mExtraIconsColor, mExtraTitlesColor;
    private Type type;
    @ColorRes
    private int backgroundColor, moreLessColor, iconsColor, titlesColor;
    private ArrayAdapter<String> dataAdapter;

    private FloatingActionButton mSave;

    private List<Color> mColorList;
    private List<String> mColorNameList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initResources();

        registerViews();
        initViewsFromExtras();
        registerListeners();
    }

    private void initResources() {
        mColorList = Color.getList();
        mColorNameList = new ArrayList<>();

        for (Color color : mColorList) {
            mColorNameList.add(color.getName());
        }
    }

    private void registerViews() {
        mReply = (CheckBox) findViewById(R.id.reply);
        mCopy = (CheckBox) findViewById(R.id.copy);
        mForward = (CheckBox) findViewById(R.id.forward);
        mSelectAll = (CheckBox) findViewById(R.id.select_all);
        mTranslate = (CheckBox) findViewById(R.id.translate);
        mExtraType = (Spinner) findViewById(R.id.type);
        mExtraBackgroundColor = (Spinner) findViewById(R.id.background_color);
        mExtraMoreLessColor = (Spinner) findViewById(R.id.more_less_color);
        mExtraIconsColor = (Spinner) findViewById(R.id.icons_color);
        mExtraTitlesColor = (Spinner) findViewById(R.id.titles_color);

        mSave = (FloatingActionButton) findViewById(R.id.save);
    }

    private void registerListeners() {
        mReply.setOnCheckedChangeListener(this);
        mCopy.setOnCheckedChangeListener(this);
        mForward.setOnCheckedChangeListener(this);
        mSelectAll.setOnCheckedChangeListener(this);
        mTranslate.setOnCheckedChangeListener(this);

        mSave.setOnClickListener(onSaveClickListener);
    }

    private void initViewsFromExtras() {
        isReplyVisible = getIntent().getExtras().getBoolean(EXTRA_REPLY);
        mReply.setChecked(isReplyVisible);

        isCopyVisible = getIntent().getExtras().getBoolean(EXTRA_COPY);
        mCopy.setChecked(isCopyVisible);

        isForwardVisible = getIntent().getExtras().getBoolean(EXTRA_FORWARD);
        mForward.setChecked(isForwardVisible);

        isSelectAllVisible = getIntent().getExtras().getBoolean(EXTRA_SELECT_ALL);
        mSelectAll.setChecked(isSelectAllVisible);

        isTranslateVisible = getIntent().getExtras().getBoolean(EXTRA_TRANSLATE);
        mTranslate.setChecked(isTranslateVisible);

        initTypeSpinner();
        initBackgroundColorSpinner();
        initMoreLessColorSpinner();
        initIconsColorSpinner();
        initTitlesColorSpinner();
    }


    private void initTypeSpinner() {
        List<String> mList = new ArrayList<>();
        mList.add(String.valueOf(Type.ICON));
        mList.add(String.valueOf(Type.TEXT));
        mList.add(String.valueOf(Type.BOTH));

        dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mExtraType.setAdapter(dataAdapter);

        type = (Type) getIntent().getExtras().getSerializable(EXTRA_TYPE);
        String typeStr = null;
        if (type != null) {
            typeStr = String.valueOf(type);
        }

        if (typeStr != null && !typeStr.isEmpty()) {
            int selected = dataAdapter.getPosition(typeStr);
            mExtraType.setSelection(selected);
        }
    }

    private void initBackgroundColorSpinner() {
        dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mColorNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mExtraBackgroundColor.setAdapter(dataAdapter);

        backgroundColor = getIntent().getExtras().getInt(EXTRA_BACKGROUND_COLOR);

        String backgroundColorStr = "";
        for (Color color : mColorList) {
            if (backgroundColor == color.getRes())
                backgroundColorStr = color.getName();
        }

        if (backgroundColorStr != null && !backgroundColorStr.isEmpty()) {
            int selected = dataAdapter.getPosition(backgroundColorStr);
            mExtraBackgroundColor.setSelection(selected);
        }
    }

    private void initMoreLessColorSpinner() {
        dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mColorNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mExtraMoreLessColor.setAdapter(dataAdapter);

        moreLessColor = getIntent().getExtras().getInt(EXTRA_MORE_LESS_COLOR);

        String moreLessColorStr = "";
        for (Color color : mColorList) {
            if (moreLessColor == color.getRes())
                moreLessColorStr = color.getName();
        }

        if (moreLessColorStr != null && !moreLessColorStr.isEmpty()) {
            int selected = dataAdapter.getPosition(moreLessColorStr);
            mExtraMoreLessColor.setSelection(selected);
        }
    }

    private void initIconsColorSpinner() {
        dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mColorNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mExtraIconsColor.setAdapter(dataAdapter);

        iconsColor = getIntent().getExtras().getInt(EXTRA_ICONS_COLOR);

        String iconsColorStr = "";
        for (Color color : mColorList) {
            if (iconsColor == color.getRes())
                iconsColorStr = color.getName();
        }

        if (iconsColorStr != null && !iconsColorStr.isEmpty()) {
            int selected = dataAdapter.getPosition(iconsColorStr);
            mExtraIconsColor.setSelection(selected);
        }
    }

    private void initTitlesColorSpinner() {
        dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mColorNameList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mExtraTitlesColor.setAdapter(dataAdapter);

        titlesColor = getIntent().getExtras().getInt(EXTRA_TITLES_COLOR);

        String titlesColorStr = "";
        for (Color color : mColorList) {
            if (titlesColor == color.getRes())
                titlesColorStr = color.getName();
        }

        if (titlesColorStr != null && !titlesColorStr.isEmpty()) {
            int selected = dataAdapter.getPosition(titlesColorStr);
            mExtraTitlesColor.setSelection(selected);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();

        switch (id) {
            case R.id.reply:
                isReplyVisible = isChecked;
                break;
            case R.id.copy:
                isCopyVisible = isChecked;
                break;
            case R.id.forward:
                isForwardVisible = isChecked;
                break;
            case R.id.select_all:
                isSelectAllVisible = isChecked;
                break;
            case R.id.translate:
                isTranslateVisible = isChecked;
                break;
            default:
                break;
        }
    }

    private View.OnClickListener onSaveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent returnIntent = getIntent();
            returnIntent.putExtra(SettingsActivity.EXTRA_REPLY, isReplyVisible);
            returnIntent.putExtra(SettingsActivity.EXTRA_COPY, isCopyVisible);
            returnIntent.putExtra(SettingsActivity.EXTRA_FORWARD, isForwardVisible);
            returnIntent.putExtra(SettingsActivity.EXTRA_SELECT_ALL, isSelectAllVisible);
            returnIntent.putExtra(SettingsActivity.EXTRA_TRANSLATE, isTranslateVisible);

            String typeStr = mExtraType.getSelectedItem().toString();
            Type selectedType = Type.valueOf(typeStr);
            returnIntent.putExtra(SettingsActivity.EXTRA_TYPE, selectedType);

            // background color
            String backgroundColorStr = mExtraBackgroundColor.getSelectedItem().toString();
            @ColorRes int backgroundColor = 0;
            for (Color color : mColorList) {
                if (backgroundColorStr == color.getName()) {
                    backgroundColor = color.getRes();
                }
            }
            returnIntent.putExtra(SettingsActivity.EXTRA_BACKGROUND_COLOR, backgroundColor);

            // more less  color
            String moreLessColorStr = mExtraMoreLessColor.getSelectedItem().toString();
            @ColorRes int moreLessColor = 0;
            for (Color color : mColorList) {
                if (moreLessColorStr == color.getName()) {
                    moreLessColor = color.getRes();
                }
            }
            returnIntent.putExtra(SettingsActivity.EXTRA_MORE_LESS_COLOR, moreLessColor);

            // icons color
            String iconsColorStr = mExtraIconsColor.getSelectedItem().toString();
            @ColorRes int iconsColor = 0;
            for (Color color : mColorList) {
                if (iconsColorStr == color.getName()) {
                    iconsColor = color.getRes();
                }
            }
            returnIntent.putExtra(SettingsActivity.EXTRA_ICONS_COLOR, iconsColor);

            // titles color
            String titlesColorStr = mExtraTitlesColor.getSelectedItem().toString();
            @ColorRes int titlesColor = 0;
            for (Color color : mColorList) {
                if (titlesColorStr == color.getName()) {
                    titlesColor = color.getRes();
                }
            }
            returnIntent.putExtra(SettingsActivity.EXTRA_TITLES_COLOR, titlesColor);

            setResult(RESULT_OK, returnIntent);
            finish();
        }
    };
}