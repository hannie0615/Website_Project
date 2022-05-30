package camper.project.domain;

public class Room {
    private String name;
    private String price;
    private boolean reserveCheck;

    private int campId;
    private int roomId;


    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
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

    public boolean isReserveCheck() {
        return reserveCheck;
    }

    public void setReserveCheck(boolean reserveCheck) {
        this.reserveCheck = reserveCheck;
    }
}
