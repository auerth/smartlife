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
     */
    public void onButtonClick(int pos, boolean isCheckec) {

    }

    /*
    *Method to override
    * Call when item in recyclerView clicked
     */
    public void onItemClick(int position) {

    }
}
