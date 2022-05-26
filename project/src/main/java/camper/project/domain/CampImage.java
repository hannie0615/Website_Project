package camper.project.domain;

public class CampImage {

    private String name;
    private String uuid;
    private String imgName;
    private String contentType;
    private int campId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public CampImage(String name, String uuid, String imgName, String contentType, int campId) {
        this.name = name;
        this.uuid = uuid;
        this.imgName = imgName;
        this.contentType = contentType;
        this.campId = campId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
