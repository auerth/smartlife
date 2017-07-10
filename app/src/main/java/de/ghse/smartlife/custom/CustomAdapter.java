package de.ghse.smartlife.custom;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import de.ghse.smartlife.Control;
import de.ghse.smartlife.MainActivity;
import de.ghse.smartlife.R;
import de.ghse.smartlife.objects.Element;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataSwitch> dataSet;
    private Control c;
    private MainActivity mainActivity;
    private int index = 0;

    /*
    *Call when CustomAdapter was created
    *
    * @param dataList List of entry's
    * @param mainActivity Object of MainActivity
    * @param c Object of Control
    */
    public CustomAdapter(ArrayList<DataSwitch> dataList, MainActivity mainActivity, Control c) {
        this.dataSet = dataList;
        this.c = c;
        this.mainActivity = mainActivity;
    }

    /*
    *Call on bind element to RecyclerView
    *
    *@param MyViewHolder holder
    *@param position postiion of entry
    */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final DataSwitch c = dataSet.get(position);
        holder.name.setText(c.getName());
        if (position == 0 || this.c.getHouse(mainActivity.getSelectedHouseIndex() - 1).getRoom(mainActivity.getSelectedRoomIndex() - 1).getElementByPosition(position - 1).getType() == Element.Type.temperature) {
            holder.tbOn.setVisibility(View.GONE);
            if (position == 0) {
                holder.name.setGravity(Gravity.CENTER_HORIZONTAL);
                holder.name.setTextSize(15);
                holder.name.setTypeface(null, Typeface.BOLD);
                RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams) holder.name.getLayoutParams();
                p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                p.addRule(RelativeLayout.ALIGN_PARENT_END);
                holder.name.setLayoutParams(p);
            }
        }
        if (index % 2 == 0) {
            holder.v.setBackgroundColor(mainActivity.getResources().getColor(R.color.colorPrimary));
            holder.name.setTextColor(Color.WHITE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.onItemClick(position);
            }
        });
        holder.tbOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                c.onCheckChanged(position, isChecked);
            }
        });
        index++;

    }

    /*
    *Get size of dataSet
    */
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    /*
    *Call on create
    *
    * @param parent ViewGroup
    * @param viewType int
    */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_switch, parent, false);

        return new MyViewHolder(v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        Switch tbOn;
        View v;

        /*
        *Call when MyViewHolder was created
        */
        MyViewHolder(View view) {
            super(view);
            v = view;
            name = (TextView) view.findViewById(R.id.name);
            tbOn = (Switch) view.findViewById(R.id.tbOn);

        }
    }
}