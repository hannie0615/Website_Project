package camper.project.domain;

public class CampImage {

    private String uuid;
    private String imgName;
    private String contentType;
    private String campId;

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public CampImage(String uuid, String imgName, String contentType, String campId) {
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
