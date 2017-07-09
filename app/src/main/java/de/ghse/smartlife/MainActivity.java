package de.ghse.smartlife;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.ghse.smartlife.R;
import de.ghse.smartlife.custom.CustomAdapter;
import de.ghse.smartlife.custom.DataSwitch;
import de.ghse.smartlife.objects.Element;
import de.ghse.smartlife.objects.House;


public class MainActivity extends AppCompatActivity {

    // Attribute
    private Spinner spinnerHouse;               //Spinner-House
    private Spinner spinnerRoom;                //Spinner-Room
    private RecyclerView recyclerView;          //RecyclerView
    private ArrayAdapter<String> adapterHouse;  //Adapter for House
    private ArrayAdapter<String> adapterRoom;   //Adapter for Room
    private ArrayAdapter<String> adapterType;   //Adapter for Type of Element
    private CustomAdapter adapter;              //Adapter for Element
    private Control c;                          //Control
    private ArrayList<DataSwitch> dataSwitch;   //Array of all dataSwitch for Elements
    // Attribute


    //Methoden
    /*
    *Call on Application-Start
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = new Control(this);
        requestPermission();
        recyclerView = (RecyclerView) findViewById(R.id.listView2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        spinnerHouse = (Spinner) findViewById(R.id.spinnerHouse);   //House Spinner to work with
        spinnerRoom = (Spinner) findViewById(R.id.spinnerRoom);    //Room Spinner to work with
        dataSwitch = new ArrayList<>();


        c.updateSpinner(Control.Spinners.house);
        adapterHouse = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, c.getArrayListSpinnerHouse()); //adapter Initialisierung (House)
        adapterHouse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //Spinner dropdown layout!
        spinnerHouse.setAdapter(adapterHouse); //Set Adapter to Spinner
        spinnerHouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() //Select Listener
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                switch (position) {
                    case 0:
                        Log.d("Spinner_R", "Placeholder");//Place holder NOTHING to DO
                        break;
                    default:
                        if (position == c.getArrayListSpinnerHouse().size() - 1) {
                            //Add House to List
                            final Dialog dialog = new Dialog(MainActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(true);
                            dialog.setContentView(R.layout.dialog_house);
                            TextView tv = (TextView) dialog.findViewById(R.id.tvTitel);
                            tv.setText(R.string.dialogHouse);
                            final EditText et = (EditText) dialog.findViewById(R.id.etNameHouse);
                            Button dialogButton = (Button) dialog.findViewById(R.id.btnOkayHouse);

                            final EditText etPort = (EditText) dialog.findViewById(R.id.etPortHouse);
                            final EditText etIp = (EditText) dialog.findViewById(R.id.etIpHouse);
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();//Beim Okay klicken!
                                    String houseName = et.getText().toString();
                                    String ip = etIp.getText().toString();
                                    int port = Integer.parseInt(etPort.getText().toString());
                                    if (houseName.length() > 0 && ip.length() > 0 && port > 0) {
                                        House h = new House();
                                        h.setName(houseName);
                                        h.setIp(ip);
                                        h.setPort(port);
                                        c.addHouse(h);
                                        Toast.makeText(MainActivity.this, "House \"" + houseName + "\" added", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Please set a name", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            dialog.show();
                        } else {

                            c.updateSpinner(Control.Spinners.room);
                        }
                        //Load Rooms of House

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        spinnerHouse.setOnLongClickListener(new AdapterView.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                c.removeHouse();
                return false;
            }
        });


        adapterRoom = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, c.getArrayListSpinnerRoom()); //new String ersetzen mit List<String> der Häusernamen
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoom.setAdapter(adapterRoom);
        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                switch (position) {
                    case 0:
                        Log.d("Spinner_R", "Placeholder");//Place holder NOTHING to DO
                        break;
                    default:
                        if (position == c.getArrayListSpinnerRoom().size() - 1) {
                            //Add House to List
                            final Dialog dialog = new Dialog(MainActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(true);
                            dialog.setContentView(R.layout.dialog_house);
                            TextView tv = (TextView) dialog.findViewById(R.id.tvTitel);
                            tv.setText(R.string.dialogRoom);
                            final EditText etPort = (EditText) dialog.findViewById(R.id.etPortHouse);
                            final EditText etIp = (EditText) dialog.findViewById(R.id.etIpHouse);
                            etIp.setVisibility(View.GONE);
                            etPort.setVisibility(View.VISIBLE);
                            etPort.setHint("ID");
                            final EditText et = (EditText) dialog.findViewById(R.id.etNameHouse);
                            Button dialogButton = (Button) dialog.findViewById(R.id.btnOkayHouse);
                            dialog.setCancelable(true);
                            dialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();//Beim Okay klicken!
                                    String roomName = et.getText().toString();
                                    String roomId = etPort.getText().toString();
                                    if (roomName.length() > 0) {
                                        c.addRoom(roomName, Integer.parseInt(roomId));
                                        Toast.makeText(MainActivity.this, "Room \"" + roomName + "\" added", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Please set a name", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            dialog.show();
                        } else {

                            updateElements();
                        }

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        spinnerRoom.setOnLongClickListener(new AdapterView.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                c.removeRoom();
                return false;
            }
        });


        adapter = new CustomAdapter(dataSwitch, this, c);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int pos = viewHolder.getAdapterPosition();
                if (pos > 0) {
                    c.removeElement(pos - 1);
                }
                updateElements();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(recyclerView);
        c.loadHouses();
    }

    /*
    *Request Permission for File writing
     */
    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to save your entrys. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    /*
    *Call when Permission was requested
    * Handle Result
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    /*
    *Update content RecyclerView
     */
    public void updateElements() {
        dataSwitch.clear();
        if ((this.getSelectedHouseIndex() - 1) > -1 && getSelectedRoomIndex() - 1 > -1 && c.getHouse(getSelectedHouseIndex() - 1).getArrayListRoomSize() > 0) {
            dataSwitch.add(new DataSwitch("~Hinzufügen~") {
                @Override
                public void onItemClick(int position) {
                    super.onItemClick(position);
                    if (position == 0) {
                        addElement();
                    }
                }
            });

            for (int i = 0; i < c.getHouse(this.getSelectedHouseIndex() - 1).getRoom(getSelectedRoomIndex() - 1).getSizeOfElementList(); i++) {
                if (c.getHouse(this.getSelectedHouseIndex() - 1).getRoom(this.getSelectedRoomIndex() - 1).getElementByPosition(i).getType() == Element.Type.light) {
                    dataSwitch.add(new DataSwitch(c.getHouse(this.getSelectedHouseIndex() - 1).getRoom(getSelectedRoomIndex() - 1).getElementByPosition(i).getName()) {
                        @Override
                        public void onButtonClick(int pos, final boolean isChecked) {
                            c.preferData(Element.Type.light, pos, isChecked);
                        }
                    });
                } else if (c.getHouse(this.getSelectedHouseIndex() - 1).getRoom(getSelectedRoomIndex() - 1).getElementByPosition(i).getType() == Element.Type.temperature) {
                    dataSwitch.add(new DataSwitch(c.getHouse(this.getSelectedHouseIndex() - 1).getRoom(getSelectedRoomIndex() - 1).getElementByPosition(i).getName()) {
                        @Override
                        public void onItemClick(int position) {
                            super.onItemClick(position);
                            if (position > 0) {
                                c.preferData(Element.Type.temperature, position, false);
                            }
                        }
                    });

                }
            }
        }
        adapter = new CustomAdapter(dataSwitch, this, c);
        recyclerView.setAdapter(adapter);
    }

    /*
    *Create dialog for adding an Element
     */
    private void addElement() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_element);
        TextView tv = (TextView) dialog.findViewById(R.id.tvTitel);
        tv.setText(R.string.dialogElement);
        final EditText etName = (EditText) dialog.findViewById(R.id.etElementName);
        final EditText etId = (EditText) dialog.findViewById(R.id.etID);
        etId.setHint(R.string.hintId);

        final Spinner spinnerType = (Spinner) dialog.findViewById(R.id.spinnerType);
        Button dialogButton = (Button) dialog.findViewById(R.id.btnOkayElement);
        dialog.setCancelable(true);
        adapterType = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1); //adapter Initialisierung (House)
        adapterType.add("Light");
        adapterType.add("Temperature");
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //Spinner dropdown layout!
        spinnerType.setAdapter(adapterType); //Set Adapter to Spinner
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();                   //Beim Okay klicken!
                String elementName = etName.getText().toString();

                int selected = (int) spinnerType.getSelectedItemId();
                String type = adapterType.getItem(selected);
                Element.Type t = null;
                assert type != null;
                if (type.equals("Light")) {
                    t = Element.Type.light;
                } else if (type.equals("Temperature")) {
                    t = Element.Type.temperature;
                }
                if (elementName.length() > 0) {
                    c.addElement(elementName, Integer.parseInt(etId.getText().toString()), t);
                    Toast.makeText(MainActivity.this, "Element \"" + elementName + "\" added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please set a name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();

    }

    /*
    *Update content of Room-Spinner
     */
    public void updateRooms() {
        spinnerRoom.setAdapter(adapterRoom);
        if (c.getArrayListSpinnerRoom().size() > 2) {
            spinnerRoom.setSelection(c.getArrayListSpinnerRoom().size() - 2);
            dataSwitch.add(new DataSwitch("~Hinzufügen~"));
        }
    }

    /*
    *Update content of House-Spinner
     */
    public void updateHouses() {
        spinnerHouse.setAdapter(adapterHouse); //Set Adapter to Spinner
        if (c.getArrayListSpinnerHouse().size() > 2)
            spinnerHouse.setSelection(c.getArrayListSpinnerHouse().size() - 2);
    }


    /*
    *Return the position of selection in House-Spinner
     */
    public int getSelectedHouseIndex() {
        return spinnerHouse.getSelectedItemPosition();
    }

    /*
    *Return the position of selection in Room-Spinner
     */
    public int getSelectedRoomIndex() {

        return spinnerRoom.getSelectedItemPosition();
    }


    /*
    *Call on StartUp when OptionsMenu(three points in the Top-Right) is creating
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options, menu);//Menu Resource, Menu
        return true;
    }

    /*
    *Call when an item from the OptionsMenu was clicked
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                c.saveHouses();
                Toast.makeText(this, "Entrys saved", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_clear:
                c.clearSaves();
                Toast.makeText(this, "Saves cleared", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // Ende Methoden
}