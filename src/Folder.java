import java.util.ArrayList;

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
        //int pathPartIndex = 0;
        for (int i = 0; i < folderContent.size(); i++){
            if (folderContent.get(i).itemName.equals(pathParts[0])){//[pathPartIndex])){
                //pathPartIndex++;
                if(pathParts.length == 1){
                    return ((File) folderContent.get(i));
                }
                ((Folder) folderContent.get(i)).findFile(path.substring(path.indexOf("/")));

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

        return null;
    }
}
