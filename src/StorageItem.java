import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;

abstract class StorageItem {
    int itemSize;
    String itemName;
    private long itemCreationDate;

    // constructor
    public StorageItem(String name){
        this.itemName = name;
        setDate();
    }

    public void setDate(){
       // this.itemCreationDate = ;
        long startDate = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long endDate = Timestamp.valueOf("2021-12-31 23:59:59").getTime();
        long diff = endDate - startDate + 1;
        Timestamp rand = new Timestamp(startDate + (long)(Main.rnd.nextInt((int)(diff))));
    }

    abstract int getSize();

    public long getDate(){
        return this.itemCreationDate;
    }

    public void printTree(SortingField field) {

        if (field == SortingField.NAME){
            // decideing who is the starting player
            if (this.itemName.compareTo(secondPlayer.getName()) > 0) {
                tempPlayer = startingPlayer;
                setStartingPlayer(secondPlayer);
                setSecondPlayer(tempPlayer);
                Comparator.comparing
            }
        }
    }
}
