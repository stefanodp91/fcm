package org.bitbucket.stefanodp91.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.bitbucket.stefanodp91.FloatingContextualItem;
import org.bitbucket.stefanodp91.FloatingContextualMenu;
import org.bitbucket.stefanodp91.Type;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ACTIVITY_SETTINGS = 100;

    private FloatingContextualMenu.Builder builder;
    private FloatingContextualMenu floatingContextualMenu;

    private boolean isReplyVisible = true;
    private boolean isCopyVisible = true;
    private boolean isForwardVisible = true;
    private boolean isSelectAllVisible = true;
    private boolean isTranslateVisible = true;
    private int visibleItems = 2;
    private Type type = Type.TEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBuilder();

        final TextView tvCenter = (TextView) findViewById(R.id.tv_center);
        tvCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingContextualMenu = builder.build();
                floatingContextualMenu.setAnchor(tvCenter);
                floatingContextualMenu.show();
            }
        });

        final TextView tvLeft = (TextView) findViewById(R.id.tv_left);
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingContextualMenu = builder.build();
                floatingContextualMenu.setAnchor(tvLeft);
                floatingContextualMenu.show();
            }
        });

        final TextView tvRigth = (TextView) findViewById(R.id.tv_right);
        tvRigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingContextualMenu = builder.build();
                floatingContextualMenu.setAnchor(tvRigth);
                floatingContextualMenu.show();
            }
        });

        final TextView tvLong = (TextView) findViewById(R.id.tv_long);
        tvLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingContextualMenu = builder.build();
                floatingContextualMenu.setAnchor(tvLong);
                floatingContextualMenu.show();
            }
        });
    }

    private View.OnClickListener onReplyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "reply pressed", Toast.LENGTH_SHORT).show();
            floatingContextualMenu.dismiss();
        }
    };

    private View.OnClickListener onCopyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "copy pressed", Toast.LENGTH_SHORT).show();
            floatingContextualMenu.dismiss();
        }
    };

    private View.OnClickListener onForwardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "forward pressed", Toast.LENGTH_SHORT).show();
            floatingContextualMenu.dismiss();
        }
    };

    private View.OnClickListener onSelectAllClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "select all pressed", Toast.LENGTH_SHORT).show();
            floatingContextualMenu.dismiss();
        }
    };

    private View.OnClickListener onTranslateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "translate pressed", Toast.LENGTH_SHORT).show();
            floatingContextualMenu.dismiss();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent activitySettingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
            activitySettingsIntent.putExtra(SettingsActivity.EXTRA_REPLY, isReplyVisible);
            activitySettingsIntent.putExtra(SettingsActivity.EXTRA_COPY, isCopyVisible);
            activitySettingsIntent.putExtra(SettingsActivity.EXTRA_FORWARD, isForwardVisible);
            activitySettingsIntent.putExtra(SettingsActivity.EXTRA_SELECT_ALL, isSelectAllVisible);
            activitySettingsIntent.putExtra(SettingsActivity.EXTRA_TRANSLATE, isTranslateVisible);
            activitySettingsIntent.putExtra(SettingsActivity.EXTRA_TYPE, type);
            startActivityForResult(activitySettingsIntent, REQUEST_ACTIVITY_SETTINGS);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ACTIVITY_SETTINGS && resultCode == RESULT_OK) {
            isReplyVisible = data.getExtras().getBoolean(SettingsActivity.EXTRA_REPLY);
            isCopyVisible = data.getExtras().getBoolean(SettingsActivity.EXTRA_COPY);
            isForwardVisible = data.getExtras().getBoolean(SettingsActivity.EXTRA_FORWARD);
            isSelectAllVisible = data.getExtras().getBoolean(SettingsActivity.EXTRA_SELECT_ALL);
            isTranslateVisible = data.getExtras().getBoolean(SettingsActivity.EXTRA_TRANSLATE);
            type = (Type) data.getExtras().getSerializable(SettingsActivity.EXTRA_TYPE);
            createBuilder();
        }
    }

    private void createBuilder() {
        // default values
        builder =
                new FloatingContextualMenu.Builder(getApplicationContext())
                        .add(new FloatingContextualItem.Builder("Reply", onReplyClickListener)
                                .icon(R.drawable.ic_reply)
                                .visible(isReplyVisible)
                                .build())
                        .add(new FloatingContextualItem.Builder("Copy", onCopyClickListener)
                                .icon(R.drawable.ic_copy)
                                .visible(isCopyVisible)
                                .build())
                        .add(new FloatingContextualItem.Builder("Forward", onForwardClickListener)
                                .visible(isForwardVisible)
                                .build())
                        .add(new FloatingContextualItem.Builder("Select all", onSelectAllClickListener)
                                .visible(isSelectAllVisible)
                                .build())
                        .add(new FloatingContextualItem.Builder("Translate", onTranslateClickListener)
                                .visible(isTranslateVisible)
                                .build())
                        .children(visibleItems)
                        .type(type);
    }
}