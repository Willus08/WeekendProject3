package com.example.android.weekendproject3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 7/8/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    List<String> strings = new ArrayList<>();

    public ListAdapter(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        String text = strings.get(position);
        holder.textView.setText(text);

    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tvText);
        }
    }
}
