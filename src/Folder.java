import java.util.ArrayList;
import java.util.*;

public class Folder extends StorageItem{
    ArrayList<StorageItem> folderContent;


    //constructor
    public Folder(String name){
        super(name);
        this.folderContent = new ArrayList<StorageItem>();
    }

    @Override
    public int getSize() {
        int folderSize = 0;
        for (int i = 0; i < folderContent.size(); i++){
            folderSize += folderContent.get(i).getSize();
        }

        return folderSize;
    }

    public boolean addItem(StorageItem item){
        for (int i = 0; i < folderContent.size(); i++){
            // returns false if there is already a storage item with that name in the folder
            if(folderContent.get(i).itemName.equals(item.itemName)){
                return false;
            }
        }
        // else, adding it to the folder and returns true
        folderContent.add(item);
        return true;
    }

    public File findFile(String path){
        String [] pathParts = path.split("/");
        File returnFile = null;
        //int pathPartIndex = 0;
        for (int i = 0; i < folderContent.size(); i++){
            if (folderContent.get(i).itemName.equals(pathParts[0])){//[pathPartIndex])){
                //pathPartIndex++;
                if(pathParts.length == 1){
                    returnFile = ((File) folderContent.get(i));
                    return returnFile;
                }
                returnFile = ((Folder) folderContent.get(i)).findFile(path.substring(path.indexOf("/")+1));

            /*if (folderContent.get(i).itemName.equals(pathParts[0]) && folderContent.get(i).getClass().equals("File")){
                ////// putting it in "File" is ok ? or it will treat it as a reguller string and the getClass will return a type that is not treated like a regular string and the comparison wont work ?
                return ((File) folderContent.get(i)); //// find a way to return it in the required type without casting
            }
            if(folderContent.get(i).itemName.equals(pathParts[0]) && folderContent.get(i).getClass().equals("Folder")){
                ////// copy code or find a way to call findFolder for folderContent.get(i) with out casnting us to a Folder
            }*/

            /*
            if(folderContent.get(i).itemName.equals(pathParts[0])){
                if(folderContent.get(i).getClass().equals("File")){
                    return ((File) folderContent.get(i));
                }
                else{
                    /// recursive call
                }*/

            }
        }

        return returnFile;
    }

    public void printTree(SortingField field) {
        ArrayList<StorageItem> folderContentToPrint = new ArrayList<StorageItem>();
        if(field == SortingField.NAME) {
            Comparator<StorageItem> itemNameComparator
                    = Comparator.comparing(StorageItem::getItemName);
            ////////this.folderContent = this.folderContent.sort(itemNameComparator);
            folderContentToPrint.add(this);
            //printTree1(folderContent, 0);
            ///folderContentToPrint.addAll(folderContent);
            ///printTree1(folderContentToPrint, 0);
            printTree1(folderContentToPrint, 0);
        }
        if(field == SortingField.DATE) {
            Comparator<StorageItem> itemDateComparator
                    = Comparator.comparing(StorageItem::getDate).thenComparing(StorageItem::getItemName);
            this.folderContent.sort(itemDateComparator);
            folderContentToPrint.add(this);
            //printTree1(folderContent, 0);
            ///folderContentToPrint.addAll(folderContent);
            ///printTree1(folderContentToPrint, 0);
            printTree1(folderContentToPrint, 0);

        }
        if(field == SortingField.SIZE) {
            Comparator<StorageItem> itemSizeComparator
                    = Comparator.comparing(StorageItem::getSize).thenComparing(StorageItem::getItemName);
            this.folderContent.sort(itemSizeComparator);
            folderContentToPrint.add(this);
            //printTree1(folderContent, 0);
            ///folderContentToPrint.addAll(folderContent);
            ///printTree1(folderContentToPrint, 0);
            printTree1(folderContentToPrint, 0);

        }
        /*if(field == SortingField.NAME) {
            Arrays.sort(folderContent,);
            for(int i = 0; i < this.folderContent.size()-1;i++) {
                if((folderContent.get(i).itemName.compareTo(folderContent.get(i+1).itemName) <0))  {
                    StorageItem temp = folderContent.get(i);
                    folderContent.set(i, folderContent.get(i+1));
                    folderContent.set(i+1, temp);
                }

            }
        }*/

    }
    void printTree1(ArrayList<StorageItem> folderContent,int i) {
        //System.out.println(this.itemName);
        for (int j=0; j<folderContent.size(); j++) { // doesnt start from the current father folder, starts from the first child inseted
            for(int y = 0; y < i; y++) {
                System.out.print("| ");
            }
            if (folderContent.get(j) instanceof File) { // why is the print inside a condition if we do it in both cases ? why not do the print and then do the condition only on the the other thing ?
                System.out.println(folderContent.get(j).itemName);
            }
            else {
                System.out.println(folderContent.get(j).itemName);
                i+=1;
                printTree1(((Folder) (folderContent.get(j))).folderContent, i);
                i-=1;
            }
        }
    }
}
