package de.ghse.smarthome.custom;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import de.ghse.smarthome.Control;
import de.ghse.smarthome.MainActivity;
import de.ghse.smarthome.R;
import de.ghse.smarthome.objects.Element;

/**
 * Created by Thorben Auer on 06.04.2017.
 */



public class CustomAdapter extends
        RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private ArrayList<DataSwitch> dataSet;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public  Switch tbOn;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            tbOn = (Switch) view.findViewById(R.id.tbOn);

        }
    }


    private Control c;
    private MainActivity mainActivity;
    public CustomAdapter(ArrayList<DataSwitch> countryList,MainActivity mainActivity,Control c) {
        this.dataSet = countryList;
        this.c = c;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final int p = position;
        final DataSwitch c = dataSet.get(position);
        holder.name.setText(c.name);

        if(position == 0 || this.c.arrayListHouse.get(mainActivity.getSelectedHouseIndex()-1).getAllRooms().get(mainActivity.getSelectedRoomIndex()-1).getElementByPosition(position-1).getType() == Element.Type.temperature) {
            holder.tbOn.setVisibility(View.GONE);
            holder.name.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL);
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
                c.onButtonClick(p,isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_switch,parent, false);

        return new MyViewHolder(v);
    }
/*
    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final DataSwitch dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_switch, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.tbOn = (Switch) convertView.findViewById(R.id.tbOn);
            if(position == 0) {
                viewHolder.tbOn.setVisibility(View.GONE);
                viewHolder.txtName.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL);
            }
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        viewHolder.tbOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataModel.onButtonClick(position,isChecked);
                Log.d("TEST","HALLOOOOOOO");
            }
        });
        viewHolder.txtName.setText(dataModel.getName());
        // Return the completed view to render on screen
        return convertView;
    }
    */
}