package camper.project.controller;

public class Reserveform {
    private String clientname;
    private String clientid;
    private String reserveplace;
    private String staytime;

    public String getStaytime() {
        return staytime;
    }

    public void setStaytime(String staytime) {
        this.staytime = staytime;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getReserveplace() {
        return reserveplace;
    }

    public void setReserveplace(String reserveplace) {
        this.reserveplace = reserveplace;
    }


}
