package com.project.demo.demoprojects.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.project.demo.demoprojects.R;
import com.project.demo.demoprojects.library.BaseBackPressedListener;

import java.lang.reflect.Constructor;

public class ContentsActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent() != null) {
            String fragmentName = getIntent().getStringExtra("fragment_name");
            /*if (fragmentName.equals(No1RecyclerViewFragment.class.getName())) {
                setTitle("RecyclerView");
                switchContent(new No1RecyclerViewFragment(), false, fragmentName);
            } else if (fragmentName.equals(No2PagerWithRecyclerViewFragment.class.getName())) {
                setTitle("PagerRecyclerView");
                switchContent(new No2PagerWithRecyclerViewFragment(), false, fragmentName);
            }*/

            Class<?> cls = null;
            try {
                cls = Class.forName(fragmentName);
                Constructor<?> constructor = cls.getConstructor();
                Object obj = constructor.newInstance(new Object[] {});

//                if (getIntent().getBooleanExtra("show_toolbar", true) == false) {
//                    toolbar.setVisibility(View.GONE);
//                }
                setTitle(cls.getSimpleName());
                switchContent((Fragment)obj, false, fragmentName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    protected void switchContent(Fragment contentFragment, boolean isAnimation, String fragmentName) {
        if (contentFragment == null) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.layout_content, contentFragment, fragmentName);
        transaction.commit();
    }

    private BaseBackPressedListener.OnBackPressedListener listener;
    public void setOnBackPressedListener(BaseBackPressedListener.OnBackPressedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBackPressed() {
        if (listener != null && listener.doBack() == false) {
            return;
        } else {
            super.onBackPressed();
        }
    }
}
