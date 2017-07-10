package de.ghse.smartlife;

import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import de.ghse.smartlife.objects.Backgroundworker;
import de.ghse.smartlife.objects.Element;
import de.ghse.smartlife.objects.House;
import de.ghse.smartlife.objects.Room;
import de.ghse.smartlife.objects.TCPStream;

import static de.ghse.smartlife.Control.Spinners.house;
import static de.ghse.smartlife.Control.Spinners.room;

public class Control {
    //Array Strings for Spinners
    private final List<String> arrayListSpinnerHouse = new ArrayList<>();
    private final List<String> arrayListSpinnerRoom = new ArrayList<>();
    //House array
    private final List<House> arrayListHouse = new ArrayList<>();
    private MainActivity mainActivity;

    /*
    *Call when control was created
    * @param mainActivity object of MainActivity
     */
    Control(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

    }

    List<String> getArrayListSpinnerHouse(){
        return arrayListSpinnerHouse;
    }
    List<String> getArrayListSpinnerRoom(){
        return arrayListSpinnerRoom;
    }

    /*
    *Delete all saved data
     */
    void clearSaves() {
        writeFile("save.json", "");
    }

    /*
    *Load saved data
     */
    void loadHouses() {
        Log.d("Load-House", "Starting");
        String saves = readFile("save.json");
        Log.d("Load-House", saves);
        if (!saves.equals("")) {
            try {
                JSONArray jsonHouseArray = new JSONArray(saves);
                for (int i = 0; i < jsonHouseArray.length(); i++) {
                    JSONObject jsonHouse = new JSONObject(jsonHouseArray.getString(i));
                    House h = new House();
                    String name = jsonHouse.getString("name");
                    String ip = jsonHouse.getString("ip");
                    int port = jsonHouse.getInt("port");
                    h.setPort(port);
                    h.setIp(ip);
                    h.setName(name);
                    JSONArray jsonRoomArray = new JSONArray(jsonHouse.getString("rooms"));
                    for (int n = 0; n < jsonRoomArray.length(); n++) {
                        JSONObject jsonRoom = new JSONObject(jsonRoomArray.getString(n));
                        Room r = new Room(jsonRoom.getInt("id"));
                        r.setName(jsonRoom.getString("name"));

                        JSONArray jsonElementArray = new JSONArray(jsonRoom.getString("elements"));
                        for (int j = 0; j < jsonElementArray.length(); j++) {
                            JSONObject jsonElement = new JSONObject(jsonElementArray.getString(j));
                            Element.Type type = null;
                            if (jsonElement.getString("type").equals("light")) {
                                type = Element.Type.light;
                            } else if (jsonElement.getString("type").equals("temperature")) {
                                type = Element.Type.temperature;
                            }
                            Element e = new Element(jsonElement.getString("name"), jsonElement.getInt("id"), type);
                            r.addElement(e);
                        }
                        h.addRoom(r);
                    }
                    addHouse(h);
                }
            } catch (JSONException ex) {
                Log.d("Load-House", ex.toString());
                ex.printStackTrace();
            }
        }

    }

    /*
    *Save all data
     */
    void saveHouses() {
        try {
            JSONArray jsonHouseArray = new JSONArray();
            for (int i = 0; i < arrayListHouse.size(); i++) {
                JSONObject jsonHouse = new JSONObject();
                House h = arrayListHouse.get(i);
                jsonHouse.put("name", h.getName());
                jsonHouse.put("port", h.getPort());
                jsonHouse.put("ip", h.getIp());

                JSONArray jsonRoomArray = new JSONArray();
                for (int n = 0; n < h.getArrayListRoomSize(); n++) {
                    JSONObject jsonRoom = new JSONObject();
                    Room r = h.getRoom(n);
                    jsonRoom.put("name", r.getName());
                    jsonRoom.put("id", r.getId());
                    JSONArray jsonElementArray = new JSONArray();

                    for (int j = 0; j < r.getSizeOfElementList(); j++) {
                        JSONObject jsonElement = new JSONObject();
                        Element e = r.getElementByPosition(j);
                        jsonElement.put("name", e.getName());
                        jsonElement.put("id", e.getId());
                        jsonElement.put("type", e.getType().toString());
                        jsonElementArray.put(jsonElement);
                    }
                    jsonRoom.put("elements", jsonElementArray);
                    jsonRoomArray.put(jsonRoom);
                }
                jsonHouse.put("rooms", jsonRoomArray);


                jsonHouseArray.put(jsonHouse);


            }
            writeFile("save.json", "");

            writeFile("save.json", jsonHouseArray.toString());
        } catch (JSONException e) {
            Log.e("Error Saving", e.toString());
            e.printStackTrace();
        }
    }

    /*
    *Write file
    * @param fileName name of file
    * @param data data to write
     */
    private void writeFile(String fileName, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(mainActivity.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

    }

    /*
    *Read file
    * @param fileName name of file to read
     */
    private String readFile(String fileName) {
        String ret = "";

        try {
            InputStream inputStream = mainActivity.openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("File-Error", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Reading-Error", "Can not read file: " + e.toString());
        }

        return ret;
    }

    /*
    *Update Spinner items
    * @param spinners type of spinner
     */
    void updateSpinner(Spinners spinners) {
        switch (spinners) {
            case house:
                arrayListSpinnerHouse.clear();
                arrayListSpinnerHouse.add("Choose a House");
                for (int i = 0; i < arrayListHouse.size(); i++) {
                    String name = arrayListHouse.get(i).getName();
                    if (!name.equals("")) {
                        arrayListSpinnerHouse.add(name);
                    }
                }
                arrayListSpinnerHouse.add("~Hinzufügen~");
                mainActivity.updateHouses();
                break;
            case room:
                arrayListSpinnerRoom.clear();
                if (arrayListSpinnerHouse.size() > 2 && mainActivity.getSelectedHouseIndex() > -1 && mainActivity.getSelectedHouseIndex() < arrayListSpinnerHouse.size() - 1) {

                    arrayListSpinnerRoom.add("Choose a room");
                    for (int i = 0; i < arrayListHouse.get(mainActivity.getSelectedHouseIndex() - 1).getArrayListRoomSize(); i++) {
                        String name = arrayListHouse.get(mainActivity.getSelectedHouseIndex() - 1).getRoom(i).getName();
                        if (!name.equals("")) {
                            arrayListSpinnerRoom.add(name);
                        }
                    }
                    arrayListSpinnerRoom.add("~Hinzufügen~");
                }
                mainActivity.updateRooms();
                break;

        }
        mainActivity.updateElements();


    }

    /*
    *Add a House to arrayList and update Spinners
    * @param h object of house
     */
    void addHouse(House h) {
        arrayListHouse.add(h);
        updateSpinner(house);
        updateSpinner(room);
    }

    /*
    *Returns a House by its position in arrayList
    * @param pos position of house to get
     */
    public House getHouse(int pos) {
        return arrayListHouse.get(pos);
    }

    /*
    *Remove House by its selected position in spinner
     */
    void removeHouse() {
        if (mainActivity.getSelectedHouseIndex() > 0 && mainActivity.getSelectedHouseIndex() < arrayListSpinnerHouse.size() - 1) {
            arrayListHouse.remove(mainActivity.getSelectedHouseIndex() - 1);
            updateSpinner(house);
            updateSpinner(room);
            mainActivity.updateElements();
        }
    }

    /*
    *Add Room to arrayList in House
    * @param name name of room
    * @param id id of room
     */
    void addRoom(String name, int id) {
        Room r = new Room(id);
        r.setName(name);
        arrayListHouse.get(mainActivity.getSelectedHouseIndex() - 1).addRoom(r);
        updateSpinner(room);
    }

    /*
    *Remove room from arrayList by selected position in spinner
     */
    void removeRoom() {
        if (mainActivity.getSelectedHouseIndex() > 0 && mainActivity.getSelectedHouseIndex() < arrayListSpinnerRoom.size() - 1) {
            arrayListHouse.get(mainActivity.getSelectedHouseIndex() - 1).removeRoom(mainActivity.getSelectedHouseIndex() - 1);
            updateSpinner(room);
            mainActivity.updateElements();
        }
    }

    /*
    *Add element to arrayList in Room
    * @param name name of element
    * @param id id of element
    * @param type type of element
     */
    void addElement(String name, int id, Element.Type type) {
        switch (type) {
            case temperature:
                arrayListHouse.get(mainActivity.getSelectedHouseIndex() - 1).getRoom(mainActivity.getSelectedRoomIndex() - 1).addElement(new Element(name, id, type));

                break;
            case light:
                arrayListHouse.get(mainActivity.getSelectedHouseIndex() - 1).getRoom(mainActivity.getSelectedRoomIndex() - 1).addElement(new Element(name, id, type));

                break;
        }
        mainActivity.updateElements();
    }

    /*
    *Remove element by its pos
    * @param pos position of element to remove
     */
    void removeElement(int pos) {

        if (pos >= 0)
            arrayListHouse.get(mainActivity.getSelectedHouseIndex() - 1).getRoom(mainActivity.getSelectedRoomIndex() - 1).removeElement(pos);
        mainActivity.updateElements();
    }

    /*
    *Prefer data for sending to server
    * @param type type of element
    * @param position position of element
    * @param isChecked state of element
     */
    void preferData(final Element.Type type, final int position, final boolean isChecked) {
        final House h = arrayListHouse.get(mainActivity.getSelectedHouseIndex() - 1);
        Room r = h.getRoom(mainActivity.getSelectedRoomIndex() - 1);
        Element e = r.getElementByPosition(position - 1);

        final String roomId = String.valueOf(r.getId());
        String elementId = String.valueOf(e.getId());

        final String[] values = new String[]{roomId, elementId, "true", "null"};
        if (!isChecked) {
            values[2] = "false";
        }
        if (type == Element.Type.temperature) {
            final Dialog dialog = new Dialog(mainActivity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_house);
            TextView tv = (TextView) dialog.findViewById(R.id.tvTitel);
            tv.setText(R.string.dialogTemperature);
            final EditText etPort = (EditText) dialog.findViewById(R.id.etPortHouse);
            final EditText etIp = (EditText) dialog.findViewById(R.id.etIpHouse);
            etIp.setVisibility(View.GONE);
            etPort.setVisibility(View.VISIBLE);
            etPort.setHint("Temperature");
            final EditText et = (EditText) dialog.findViewById(R.id.etNameHouse);
            et.setVisibility(View.GONE);
            Button dialogButton = (Button) dialog.findViewById(R.id.btnOkayHouse);
            dialog.setCancelable(true);
            final Control c = this;
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();//Beim Okay klicken!
                    String temperature = etPort.getText().toString();
                    values[3] = temperature;
                    c.sendData(h.getIp(), h.getPort(), values);
                }
            });
            dialog.show();
        } else {
            this.sendData(h.getIp(), h.getPort(), values);
        }


    }

    /*
    *Send data to Server
    * @param ip ip of server
    * @param port port of server
    * @param values data to send
     */
    private void sendData(final String ip, final int port, final String[] values) {
        Backgroundworker bg = new Backgroundworker() {
            @Override
            public void asyncTask() {
                super.asyncTask();
                try {
                    TCPStream tcp = new TCPStream(ip, port);
                    System.out.println("Server: " + tcp.sendData(values));
                } catch (Exception ex) {
                    Looper.prepare();
                    Toast.makeText(mainActivity, "Network-Error: " + ex.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        bg.execute();


    }

    /*
    *Spinner Types
     */
    enum Spinners {
        room, house
    }

}


