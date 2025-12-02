import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Collectors;
public class UPCUtil {
    
    public static void main(String[] args) 
    {
        //Create the Database
        UPCDatabase upcDatabase = new UPCDatabase();
        //Create Test items
        perishableItem testItem = new perishableItem(Integer.toString(100),LocalDate.now(),Integer.toString(0));
        perishableItem testItem2 = new perishableItem(Integer.toString(150), LocalDate.of(2026,6,24), Integer.toString(0));
        //Add items to database
        upcDatabase.addItem(testItem);
        upcDatabase.addItem(testItem2);
        //Print items currently in the DB
        upcDatabase.printAll();
    }
}
class perishableItem{
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
    //Get UPC
    public String getUpc()
    {
        return upc;
    }
    public LinkedHashSet<LocalDate> getExpiryDates()
    {
        return getExpiryDates();
    }
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
    public void setUpc(String upcN)
    {
        upc = upcN;

    }
    public void setItemName(String itemNameS)
    {
        itemName = itemNameS;
    }
    public void setItemLocation(String itemLoc)
    {
        itemLocation = itemLoc;
    }
    public void setItemType(String itemTypeS)
    {
        itemType = itemTypeS;
    }
    public void addDate(LocalDate date)
    {
        expiryDates.add(date);
    }
    public String getItemName()
    {
        return itemName;
    }
    @Override
    public String toString()
    {
        String dates = expiryDates.stream()
            .map(LocalDate::toString)
            .collect(Collectors.joining((" ,")));
        return "Item Name: " + getItemName() +  " UPC: " + getUpc() + " Expiry Dates: " + dates;
    }
    //Minimal Constructor
    public perishableItem(String upcN, LocalDate expireDate,String addedByUser)
    {
        this.upc = upcN;
        this.expiryDates.add(expireDate);
        this.addedBy = addedByUser;
    }
    }
class UPCDatabase
{
    private HashMap<String, perishableItem> db = new HashMap<>();
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
}

class user
{
    String userId;
    String passWord;
}
