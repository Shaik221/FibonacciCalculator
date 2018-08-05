package demo.test.jani.com.fibapplication.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import demo.test.jani.com.fibapplication.R;
import demo.test.jani.com.fibapplication.adapter.CustomDataAdapter;
import demo.test.jani.com.fibapplication.bean.ListItem;

import java.util.ArrayList;

public class SummaryFragment extends Fragment {

    public static ArrayList<ListItem> perOpTimeData;
    private CustomDataAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                perOpTimeData = bundle.getParcelableArrayList("my_custom_object");
            }

            if (perOpTimeData != null && perOpTimeData.size() > 0) {

                adapter = new CustomDataAdapter(this.getActivity(), perOpTimeData);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView listView = view.findViewById(R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //set the values to the adapter
        if (listView != null) {
            listView.setAdapter(adapter);
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        perOpTimeData = null;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.back_navigation, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.navigation_back:
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }


    @Override
    public void onResume() {
        super.onResume();
    }

}
