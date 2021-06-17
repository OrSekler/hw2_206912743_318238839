import java.sql.Date;
import java.sql.Timestamp;

abstract class StorageItem {
    int itemSize;
    String itemName;
    private long itemCreationDate;

    // constructor
    public StorageItem(String name){
        this.itemName = name;
        setDate();
    }

    /*public void setDate(){
        this.itemCreationDate = ;
    }*/

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
            }
        }
    }
}
