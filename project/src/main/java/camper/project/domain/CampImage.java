package camper.project.domain;

public class CampImage {

    private String name;
    private String uuid;
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


    public CampImage() {}
    public CampImage(String name, String uuid, String contentType, int campId) {
        this.name = name;
        this.uuid = uuid;

        this.contentType = contentType;
        this.campId = campId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }



    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
