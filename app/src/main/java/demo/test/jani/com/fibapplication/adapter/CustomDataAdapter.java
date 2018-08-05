package demo.test.jani.com.fibapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import demo.test.jani.com.fibapplication.R;
import demo.test.jani.com.fibapplication.bean.ListItem;

public class CustomDataAdapter extends RecyclerView.Adapter<CustomDataAdapter.MyViewHolder> {

    private  ArrayList<ListItem> listItems;
    private Context context;
    private LayoutInflater inflater;

    public CustomDataAdapter(Context context, ArrayList<ListItem> objects) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listItems=objects;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListItem item = listItems.get(position);
        holder.address.setText(item.toString());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView address;

        public MyViewHolder(View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.textView);
        }
    }
}
