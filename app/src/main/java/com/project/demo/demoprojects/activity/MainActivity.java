package com.project.demo.demoprojects.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.demo.demoprojects.R;
import com.project.demo.demoprojects.fragment.No2PagerWithRecyclerViewFragment;
import com.project.demo.demoprojects.fragment.No1RecyclerViewFragment;
import com.project.demo.demoprojects.fragment.No3TabLayoutFragment;
import com.project.demo.demoprojects.fragment.No4MultipleViewPagerFragment;
import com.project.demo.demoprojects.fragment.No5TabWithPagerFragment;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnRecyclerView;
    private Button btnPagerRecyclerView;
    private Button btnTabLayout;
    private Button btnMultipleViewPager;
    private Button btnTabWithPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnRecyclerView = (Button) findViewById(R.id.btn_recycler_view);
        btnRecyclerView.setOnClickListener(this);

        btnPagerRecyclerView = (Button) findViewById(R.id.btn_pager_recycler_view);
        btnPagerRecyclerView.setOnClickListener(this);

        btnTabLayout = (Button) findViewById(R.id.btn_tab_layout);
        btnTabLayout.setOnClickListener(this);

        btnMultipleViewPager = (Button) findViewById(R.id.btn_multiple_viewpager);
        btnMultipleViewPager.setOnClickListener(this);

        btnTabWithPager = (Button) findViewById(R.id.btn_tab_with_pager);
        btnTabWithPager.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ContentsActivity.class);
        switch (v.getId()) {
            case R.id.btn_recycler_view:
                intent.putExtra("fragment_name", No1RecyclerViewFragment.class.getName());
                break;

            case R.id.btn_pager_recycler_view:
                intent.putExtra("fragment_name", No2PagerWithRecyclerViewFragment.class.getName());
                break;

            case R.id.btn_tab_layout:
                intent.putExtra("fragment_name", No3TabLayoutFragment.class.getName());
                break;

            case R.id.btn_multiple_viewpager:
                intent.putExtra("fragment_name", No4MultipleViewPagerFragment.class.getName());
                intent.putExtra("show_toolbar", false);
                break;

            case R.id.btn_tab_with_pager:
                intent.putExtra("fragment_name", No5TabWithPagerFragment.class.getName());
                intent.putExtra("show_toolbar", false);
                break;
        }

        startActivity(intent);
    }
}
