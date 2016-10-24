package org.bitbucket.stefanodp91.sample;

import android.content.Intent;
import android.os.Bundle;
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
    protected static final String EXTRA_REPLY = "EXTRA_REPLY";
    protected static final String EXTRA_COPY = "EXTRA_COPY";
    protected static final String EXTRA_FORWARD = "EXTRA_FORWARD";
    protected static final String EXTRA_SELECT_ALL = "EXTRA_SELECT_ALL";
    protected static final String EXTRA_TRANSLATE = "EXTRA_TRANSLATE";
    protected static final String EXTRA_TYPE = "EXTRA_TYPE";

    private CheckBox mReply, mCopy, mForward, mSelectAll, mTranslate;
    private boolean isReplyVisible, isCopyVisible, isForwardVisible, isSelectAllVisible, isTranslateVisible;
    private Spinner mExtraType;
    private Type type;
    private ArrayAdapter<String> dataAdapter;

    private FloatingActionButton mSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        registerViews();
        initViewsFromExtras();
        registerListeners();
    }

    private void registerViews() {
        mReply = (CheckBox) findViewById(R.id.reply);
        mCopy = (CheckBox) findViewById(R.id.copy);
        mForward = (CheckBox) findViewById(R.id.forward);
        mSelectAll = (CheckBox) findViewById(R.id.select_all);
        mTranslate = (CheckBox) findViewById(R.id.translate);
        mExtraType = (Spinner) findViewById(R.id.type);

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
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    };
}
