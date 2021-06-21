import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;

abstract class StorageItem {
    int itemSize;
    String itemName;
    //private long itemCreationDate;
    Timestamp itemCreationDate;

    // constructor
    public StorageItem(String name){
        this.itemName = name;
        setDate();
    }

    public String getItemName() {
        return this.itemName;
    }
    public void setDate(){
       // this.itemCreationDate = ;
        long startDate = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long endDate = Timestamp.valueOf("2021-12-31 23:59:59").getTime();
        long diff = endDate - startDate;
        long randomDate = (long)(startDate + (Main.rnd.nextDouble() * diff));
        this.itemCreationDate = new Timestamp(randomDate);
    }

    abstract int getSize();

    public Timestamp getDate(){
        return this.itemCreationDate;
    }

    public void printTree(SortingField field) {}




}
