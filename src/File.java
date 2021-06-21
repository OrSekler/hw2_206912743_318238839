public class File extends StorageItem {
    StringBuilder fileContent;

    //constructor
    public File(String name, String ext){
        super(name + "." + ext);
        this.fileContent = new StringBuilder();
    }

    public void setName(String name, String ext){
        this.itemName = name + "." + ext;
    }
    public String getName() {
        return this.itemName;
    }
    @Override
    public int getSize() {
        return fileContent.length();
    }
    public void printContent(){
        System.out.println(this.getName() + " Size: " + this.getSize() + "MB Created: " + super.getDate());
        System.out.println(fileContent);
    }

    public void addContent(String contentToAdd){
        this.fileContent.append(contentToAdd);
    }
}

