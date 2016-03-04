package com.project.demo.demoprojects.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.demo.demoprojects.DemoModel;
import com.project.demo.demoprojects.DemoProject;
import com.project.demo.demoprojects.R;
import com.project.demo.demoprojects.adapter.No2PagerWithRecyclerViewAdapter;
import com.project.demo.demoprojects.library.recyclerview.RecyclerViewItemClickManager;

import java.util.List;

public class No2PagerWithRecyclerViewFragment extends Fragment implements RecyclerViewItemClickManager.OnItemClickListener {

    private RecyclerView recyclerView;
    private No2PagerWithRecyclerViewAdapter adapter;

    private RecyclerViewItemClickManager itemClickManager;

    public No2PagerWithRecyclerViewFragment() {
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<DemoModel> items = DemoProject.getDemoData();
        adapter = new No2PagerWithRecyclerViewAdapter(getActivity().getSupportFragmentManager(), items);
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

        DemoModel model = (DemoModel) adapter.getItem(position);
        String text = "onClick model\r\nlabel : " + model.label + "\r\ntime : " + model.dateTime;
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
