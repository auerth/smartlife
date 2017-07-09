package de.ghse.smartlife.custom;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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

    /*
    *Call when CustomAdapter was created
    */
    public CustomAdapter(ArrayList<DataSwitch> countryList, MainActivity mainActivity, Control c) {
        this.dataSet = countryList;
        this.c = c;
        this.mainActivity = mainActivity;
    }

    /*
    *Call on bind element to RecyclerView
    */
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final DataSwitch c = dataSet.get(position);
        holder.name.setText(c.getName());

        if (position == 0 || this.c.getHouse(mainActivity.getSelectedHouseIndex() - 1).getRoom(mainActivity.getSelectedRoomIndex() - 1).getElementByPosition(position - 1).getType() == Element.Type.temperature) {
            holder.tbOn.setVisibility(View.GONE);
            holder.name.setGravity(Gravity.CENTER_HORIZONTAL);
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
                c.onButtonClick(position, isChecked);
            }
        });
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

        /*
        *Call when MyViewHolder was created
        */
        MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            tbOn = (Switch) view.findViewById(R.id.tbOn);

        }
    }
}