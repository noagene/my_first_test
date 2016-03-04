package com.project.demo.demoprojects.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.demo.demoprojects.DemoModel;
import com.project.demo.demoprojects.DemoProject;
import com.project.demo.demoprojects.R;
import com.project.demo.demoprojects.activity.DetailActivity;
import com.project.demo.demoprojects.adapter.No1RecyclerViewAdapter;
import com.project.demo.demoprojects.library.recyclerview.RecyclerViewItemClickManager;

import java.util.List;

public class No1RecyclerViewFragment extends Fragment implements RecyclerViewItemClickManager.OnItemClickListener {

    private RecyclerView recyclerView;
    private No1RecyclerViewAdapter adapter;

    private RecyclerViewItemClickManager itemClickManager;

    public No1RecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        itemClickManager = RecyclerViewItemClickManager.addTo(recyclerView);
        itemClickManager.setOnItemClickListener(this);

        /*LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);*/

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<DemoModel> items = DemoProject.getDemoData();
        adapter = new No1RecyclerViewAdapter(items, false, false);
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(RecyclerView parent, View view, int position, long id) {
        if (adapter == null || adapter.getItem(position) == null) {
            return;
        }

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                Pair.create(view.findViewById(R.id.image_small), getActivity().getString(R.string.transition_image))
        );
        getActivity().startActivity(intent, options.toBundle());

//        DemoModel model = (DemoModel) adapter.getItem(position);
//        String text = "onClick model\r\nlabel : " + model.label + "\r\ntime : " + model.dateTime;
//        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
