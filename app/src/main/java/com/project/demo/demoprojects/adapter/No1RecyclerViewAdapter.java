package com.project.demo.demoprojects.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.demo.demoprojects.DemoModel;
import com.project.demo.demoprojects.R;
import com.project.demo.demoprojects.library.recyclerview.HeaderRecyclerViewAdapter;

import java.util.List;

/**
 * Created by Kimsj on 2016. 2. 11..
 */
public class No1RecyclerViewAdapter extends HeaderRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private List<DemoModel> items;
    private boolean useHeader = false;
    private boolean useFooter = false;

    public No1RecyclerViewAdapter(List<DemoModel> items, boolean useHeader, boolean useFooter) {
        this.items = items;
        this.useHeader = useHeader;
        this.useFooter = useFooter;
    }

    /*@Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_item, parent, false));
            case 1:
                return new WideViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_item_wide, parent, false));
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_item, parent, false);
        return new NormalViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if (items != null) {
            return items.get(position).cellType;
        }

        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DemoModel model = items.get(position);
        if (getItemViewType(position) == 0) {
            ((NormalViewHolder) holder).label.setText(model.label);
            String dateStr = DateUtils.formatDateTime(
                    ((NormalViewHolder) holder).label.getContext(),
                    model.dateTime.getTime(),
                    DateUtils.FORMAT_ABBREV_ALL);
            ((NormalViewHolder) holder).dateTime.setText(dateStr);
        } else {
            ((WideViewHolder) holder).label.setText(model.label);
            String dateStr = DateUtils.formatDateTime(
                    ((WideViewHolder) holder).label.getContext(),
                    model.dateTime.getTime(),
                    DateUtils.FORMAT_ABBREV_ALL);
            ((WideViewHolder) holder).dateTime.setText(dateStr);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }*/

    public Object getItem(int position) {
        if (items == null || items.size() <= position) {
            return null;
        }
        return items.get(position);
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        TextView dateTime;

        public NormalViewHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.txt_label_item);
            dateTime = (TextView) itemView.findViewById(R.id.txt_date_time);
        }
    }

    class WideViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        TextView dateTime;

        public WideViewHolder(View itemView) {
            super(itemView);

            label = (TextView) itemView.findViewById(R.id.txt_label_item);
            dateTime = (TextView) itemView.findViewById(R.id.txt_date_time);
        }
    }




    @Override
    public boolean useHeader() {
        return useHeader;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindHeaderView(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public boolean useFooter() {
        return useFooter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindFooterView(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new NormalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_item, parent, false));
            case 1:
                return new WideViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_item_wide, parent, false));
        }
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_item, parent, false);
        return new NormalViewHolder(itemView);
    }

    @Override
    public void onBindBasicItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        DemoModel model = items.get(position);
        if (getBasicItemViewType(position) == 0) {
            ((NormalViewHolder) holder).label.setText(model.label);
            String dateStr = DateUtils.formatDateTime(
                    ((NormalViewHolder) holder).label.getContext(),
                    model.dateTime.getTime(),
                    DateUtils.FORMAT_ABBREV_ALL);
            ((NormalViewHolder) holder).dateTime.setText(dateStr);
        } else {
            ((WideViewHolder) holder).label.setText(model.label);
            String dateStr = DateUtils.formatDateTime(
                    ((WideViewHolder) holder).label.getContext(),
                    model.dateTime.getTime(),
                    DateUtils.FORMAT_ABBREV_ALL);
            ((WideViewHolder) holder).dateTime.setText(dateStr);
        }
    }

    @Override
    public int getBasicItemCount() {
        return items.size();
    }

    @Override
    public int getBasicItemViewType(int position) {
        if (items != null) {
            return items.get(position).cellType;
        }

        return super.getItemViewType(position);
    }




}
