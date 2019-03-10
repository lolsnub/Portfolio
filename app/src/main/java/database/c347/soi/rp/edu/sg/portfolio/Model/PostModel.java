package database.c347.soi.rp.edu.sg.portfolio.Model;

public class PostModel {
    private int imgName;
    String name, timestamp, post;

    public PostModel(int imgName, String name, String timestamp, String post) {
        this.imgName = imgName;
        this.name = name;
        this.timestamp = timestamp;
        this.post = post;
    }

    public int getImgName() {
        return imgName;
    }

    public void setImgName(int imgName) {
        this.imgName = imgName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
