import java.sql.Timestamp;
import java.util.Comparator;
import static java.lang.Math.abs;
import java.util.ArrayList;

abstract class StorageItem {
    int itemSize;
    String itemName;
    Timestamp itemCreationDate;

    // constructor
    public StorageItem(String name){
        this.itemName = name;
        setDate();
    }

    // set & get methods

    public String getItemName() {
        return this.itemName;
    }
    public Timestamp getDate(){
        return this.itemCreationDate;
    }

    abstract int getSize();

    public void setDate(){
       // this.itemCreationDate = ;
        long startDate = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long endDate = Timestamp.valueOf("2021-12-31 23:59:59").getTime();
        long diff = endDate - startDate;
        long randomDate = startDate + ((abs(Main.rnd.nextLong())) % diff);
        this.itemCreationDate = new Timestamp(randomDate);
    }


    public void printTree(SortingField field) {
        // calls the sorting tree method
        sortTreeBy(field);
    }

    public void sortTreeBy (SortingField field){
        ArrayList <StorageItem> folderContentToPrint = new ArrayList<StorageItem>();
        // adding the current storage item (folder) to the Arraylist
        folderContentToPrint.add(this);
        sortTreeRecursive(field, folderContentToPrint);
        printSortedTree(folderContentToPrint, 0);
    }
    public void sortTreeRecursive(SortingField field,
                                  ArrayList<StorageItem> folder) {
        // sort by name
        if(field == SortingField.NAME) {
            // sorting the father folder by name
            Comparator<StorageItem> itemNameComparator
                    = Comparator.comparing(StorageItem::getItemName);
            folder.sort(itemNameComparator);
            // sorting the child folders
            for (int i = 0; i < folder.size(); i++){
                if (folder.get(i) instanceof Folder){
                    sortTreeRecursive(field, ((Folder) folder.get(i)).folderContent);
                }
            }
        }

        // sort by date
        else  if(field == SortingField.DATE) {
            // sorting the father folder by date and than by name
            Comparator<StorageItem> itemDateComparator
                    = Comparator.comparing(StorageItem::getDate).
                    thenComparing(StorageItem::getItemName);
            folder.sort(itemDateComparator);
            // sorting the child folders
            for (int i = 0; i < folder.size(); i++){
                if (folder.get(i) instanceof Folder){
                    sortTreeRecursive(field, ((Folder) folder.get(i)).folderContent);
                }
            }
        }

        // sort by size
        else if(field == SortingField.SIZE) {
            // sorting the father folder by size and than by name
            Comparator<StorageItem> itemSizeComparator
                    = Comparator.comparing(StorageItem::getSize).
                    thenComparing(StorageItem::getItemName);
            folder.sort(itemSizeComparator);
            // sorting the child folders
            for (int i = 0; i < folder.size(); i++){
                if (folder.get(i) instanceof Folder){
                    sortTreeRecursive(field, ((Folder) folder.get(i)).folderContent);
                }
            }
        }
    }

    void printSortedTree(ArrayList<StorageItem> folderContent,int i) {
        for (int j=0; j<folderContent.size(); j++) {
            for(int y = 0; y < i; y++) {
                System.out.print("|    ");
            }
            // prints the current storage item in the folder
            System.out.println(folderContent.get(j).itemName);
            if (folderContent.get(j) instanceof Folder) {
                i+=1;
                // prints the content of the child folders
                printSortedTree(((Folder) (folderContent.get(j))).folderContent, i);
                i-=1;
            }
        }
    }



}
