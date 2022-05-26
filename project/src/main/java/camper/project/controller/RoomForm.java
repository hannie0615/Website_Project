package camper.project.controller;

public class RoomForm {

    private String name;
    private String price;
    private int campId;
    private String roomId;


    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
