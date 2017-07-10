package de.ghse.smartlife.custom;


public class DataSwitch {

    private String name;

    /*
    *Call when DataSwitch was created
     */
    public DataSwitch(String name) {
        this.name = name;
    }

    /*
    *Get name of DataSwitch
     */
    public String getName() {
        return name;
    }

    /*
    *Button method to override
    * @param pos position of element which button is checked
    * @param isChecked State of switch from element
     */
    public void onCheckChanged(int pos, boolean isChecked) {

    }

    /*
    *Method to override
    * Call when item in recyclerView clicked
    * @param position position of clicked element
     */
    public void onItemClick(int position) {

    }
}
