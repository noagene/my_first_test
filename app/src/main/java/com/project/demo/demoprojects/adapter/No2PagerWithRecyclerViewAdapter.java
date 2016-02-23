package com.project.demo.demoprojects.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.demo.demoprojects.DemoModel;
import com.project.demo.demoprojects.R;

import java.util.List;

/**
 * Created by Kimsj on 2016. 2. 11..
 */
public class No2PagerWithRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FragmentManager fragmentManager;
    private List<DemoModel> items;

    public No2PagerWithRecyclerViewAdapter(FragmentManager fragmentManager, List<DemoModel> items) {
        this.fragmentManager = fragmentManager;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_view_pager, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final DemoModel model = items.get(position);
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return ((model == null || model.bannerList == null) ? 0 : model.bannerList.size());
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return (view == object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                String text = model.bannerList.get(position);

                View view = LayoutInflater.from(container.getContext()).inflate(R.layout.listrow_item, container, false);
                ((TextView) view.findViewById(R.id.txt_label_item)).setText(text);

                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager) container).removeView((View) object);
            }
        };

        ((NormalViewHolder) holder).viewPager.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Object getItem(int position) {
        if (items == null || items.size() <= position) {
            return null;
        }
        return items.get(position);
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;

        public NormalViewHolder(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.view_pager);
        }
    }

}
