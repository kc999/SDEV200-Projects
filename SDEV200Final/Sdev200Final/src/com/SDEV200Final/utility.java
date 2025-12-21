package com.SDEV200Final;
import java.util.Iterator;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class utility {
    public static class perishableItem{
    //Upc for Item 
    private String upc;
    //Expiry dates, uses linked hash set to keep order of dates as we
    //generally are going to want to grab the first one
    private LinkedHashSet<LocalDate> expiryDates = new LinkedHashSet<>();
    //Item name
    private String itemName;
    //ILC or aisle location
    private String itemLocation;
    //Candy, bakery item, soda, etc
    private String itemType;
    //Id of user who added item
    private String addedBy;
    //Manufacturer of item
    private String itemManufacturer;
    //Getters
    @JsonProperty("upc")
    public String getUpc()
    {
        return upc;
    }
    @JsonProperty("itemName")
    public String getItemName()
    {
        return itemName;
    }
    @JsonProperty("itemManufacturer")
    public String getItemManufacturer()
    {
        return itemManufacturer;
    }
    @JsonProperty("addedBy")
    public String getAddedBy()
    {
        return addedBy;
    }
    @JsonProperty("itemLocation")
    public String getItemLocation()
    {
        return itemLocation;
    }
    @JsonProperty("itemType")
    public String getItemType()
    {
        return itemType;
    }
    @JsonProperty("expiryDates")
    public LinkedHashSet<LocalDate> getExpiryDates()
    {
        return this.expiryDates;
    }
    //Don't use this property for saving or loading
    @JsonIgnore
    public LocalDate getFirstExpiryDate()
    {
        Iterator<LocalDate> expIterator = expiryDates.iterator();
        if (expIterator.hasNext())
        {
            LocalDate firstExpiryDate = expIterator.next();
            return firstExpiryDate;
        }
        else
        {
            System.out.println("Linked list is empty, returning fake date.");
            return LocalDate.of(1900,1,1);
        }
    }
    @JsonProperty("upc")
    public void setUpc(String upcN)
    {
        upc = upcN;
    }
    @JsonProperty("itemName")
    public void setItemName(String itemNameS)
    {
        itemName = itemNameS;
    }
    @JsonProperty("itemLocation")
    public void setItemLocation(String itemLoc)
    {
        itemLocation = itemLoc;
    }
    @JsonProperty("itemType")
    public void setItemType(String itemTypeS)
    {
        itemType = itemTypeS;
    }
    @JsonProperty("itemManufacturer")
    public void setItemManufacturer(String itemManufac)
    {
        itemManufacturer = itemManufac;
    }
    @JsonProperty("addedBy")
    public void setAddedBy(String naddedBy)
    {
        addedBy = naddedBy;
    }
    //This is for Jackson to properly put the dates back in to the item when loading, dont use this
    @JsonProperty("expiryDates")
    public void setExpiryDates(LinkedHashSet<LocalDate> expiryDates)
    {
        this.expiryDates = expiryDates;
    }
    public void addDate(LocalDate date)
    {
        expiryDates.add(date);
    }
   
    
    @Override
    public String toString()
    {
        String dates = expiryDates.stream()
            .map(LocalDate::toString)
            .collect(Collectors.joining((" ,")));
        return getItemName() + " | " + "UPC: " + getUpc();
    }
    //Needed empty constructor for Jackson
    public perishableItem() {}
    //Minimal Constructor
    public perishableItem(String upcN, LocalDate expireDate,String addedByUser)
    {
        this.upc = upcN;
        this.expiryDates.add(expireDate);
        this.addedBy = addedByUser;
    }
     public perishableItem(String upcN, LocalDate expireDate,String addedByUser, String itemLocation, String itemManufac, String itemType)
    {
        this.upc = upcN;
        this.expiryDates.add(expireDate);
        this.itemLocation = itemLocation;
        this.itemManufacturer = itemManufac; 
        this.itemType = itemType;
        this.addedBy = addedByUser;
    }
    }
public static class UPCDatabase
{
    //Create the hashmap to store the perishable items
    public HashMap<String, perishableItem> db = new HashMap<>();
    private ObjectMapper map = new ObjectMapper().registerModule(new JavaTimeModule());
    //Attempt to save the database
    public void saveDatabase(String filename)
    {
        try {
            map.writerWithDefaultPrettyPrinter().writeValue(new File(filename),db);
            System.out.println("Db saved to: " + filename);
        }
        catch(Exception e){
            System.out.println("There was an error saving the database. " + e);
        }
    }
    public void loadDatabase(String filename)
    {
        //Attempt to load the database.
        try
        {
            File file = new File(filename);
            if (file.exists())
            {
                this.db = map.readValue(file, new TypeReference<HashMap<String, perishableItem>>() {});
                System.out.println("Database successfully loaded.");
            
            }
        }
        catch (Exception e)
        {
            System.out.println("There was an error reading the database." + e);
        }
    }
    public void printAll()
    {
        for (String key: db.keySet())
        {
            System.out.println(db.get(key));
        }
    }
    //If item is already in database, attempt to add dates in set
    public void addItem(perishableItem itemAdd)
    {
        //To do, contatenate sets of expiry dates if a key is a duplicate
        db.put(itemAdd.getUpc(),itemAdd);
    }
    //Create a display list and add items from hashmap to it
    public ObservableList<perishableItem> getListViewData()
    {
        ObservableList<perishableItem> displayList = FXCollections.observableArrayList();
        for (HashMap.Entry<String, perishableItem> entry : db.entrySet())
        {
            displayList.add(entry.getValue());
        }
        return displayList;
    }
}

public static class user
{
    String userId;
    String passWord;
    Boolean loggedIn;

    public static Boolean logIn(String username, String password)
    {
        return false;
    }
}
}
