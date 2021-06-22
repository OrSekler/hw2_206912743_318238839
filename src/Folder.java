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
        // checks if path exists
        for (int i = 0; i < folderContent.size(); i++){
            // if the current part of the path is in the current folder
            if (folderContent.get(i).itemName.equals(pathParts[0])){
                // if it is the last part of the path (the file name)
                if(pathParts.length == 1){
                    returnFile = ((File) folderContent.get(i));
                    // the path existed, returns the file
                    return returnFile;
                }
                // calls the function again with the folder found in the path
                returnFile = ((Folder) folderContent.get(i)).
                        findFile(path.substring(path.indexOf("/")+1));
            }
        }
        // the path did not exist, returns null
        return returnFile;
    }
}
