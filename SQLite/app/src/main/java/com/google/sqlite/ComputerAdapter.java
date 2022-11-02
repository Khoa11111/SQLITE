package com.google.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ComputerAdapter extends BaseAdapter {
    private ComputerList context;
    private int Layout;
    private List<Computer> computerList;

    public ComputerAdapter(ComputerList context, int layout, List<Computer> computerList) {
        this.context = context;
        Layout = layout;
        this.computerList = computerList;
    }

    @Override
    public int getCount() {
        return computerList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtName;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(Layout, null);
            holder.txtName = (TextView) view.findViewById(R.id.Name);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imgEdit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Computer computer = computerList.get(i);

        holder.txtName.setText(computer.getComputerName());

        // bat su kien xoa & sua
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogSua(computer.getIdComputer(),computer.getComputerName());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoa(computer.getIdComputer());
            }
        });

        return view;
    }
}
