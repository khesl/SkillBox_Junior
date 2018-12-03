package module_10.Lessons_.src;

import java.io.File;

public class ExplorerObject {
    private int deep;
    private ExplorerObject parent;
    private File file;

    public ExplorerObject(){
    }
    public ExplorerObject(File file, int deep, ExplorerObject parent){
        this.deep = deep;
        this.parent = parent;
        this.file = file;
    }

    public int getDeep() { return deep; }
    public void setDeep(int deep) { this.deep = deep; }

    public File getFile() { return file; }

    public ExplorerObject getParent() {
        if (parent == null)
            return this;
        return parent;
    }
    public void setParent(ExplorerObject parent) { this.parent = parent; }

    @Override
    public String toString() {
        String tab = "";
        for (int i = 0; i < deep; i++) tab += "    ";
        String str = "|" + (deep > 0 ? tab : "") + (file.isDirectory()?("" + (char)9112 + " "):"") + file.getName() + ", (" + getDeep() + ")";
        return str;
    }
}
