package com.mcxiaoke.fanfouapp.app;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.mcxiaoke.fanfouapp.R;
import com.mcxiaoke.fanfouapp.dao.model.UserModel;
import com.mcxiaoke.fanfouapp.fragments.ProfileFragment;

/**
 * @author mcxiaoke
 * @version 1.2 2011.11.17
 */
public class UIProfile extends UIBaseSupport {
    private String userId;
    private UserModel user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (checkIntent()) {
            finish();
            return;
        }
        setLayout();
    }

    private void setLayout() {
        setContentView(R.layout.ui_profile);
        setProgressBarIndeterminateVisibility(false);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, ProfileFragment.newInstance(userId));
        ft.commit();
    }

    private boolean checkIntent() {
        Intent intent = getIntent();
        String action = intent.getAction();
        if (action == null) {
            user = intent.getParcelableExtra("data");
            if (user != null) {
                userId = user.getId();
            } else {
                userId = intent.getStringExtra("id");
            }
        } else if (action.equals(Intent.ACTION_VIEW)) {
            Uri data = intent.getData();
            if (data != null) {
                userId = data.getLastPathSegment();
            }
        }

        return TextUtils.isEmpty(userId);

    }

    @Override
    protected int getMenuResourceId() {
        return 0;
    }

}
