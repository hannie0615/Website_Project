package camper.project.domain;

public class Reserve {
    private String clientname;
    private String clientid;
    private String reserveplace;
    private String reservedate;
    private String staytime;
    private String reserveid;

    public String getReserveid() {
        return reserveid;
    }

    public void setReserveid(String reserveid) {
        this.reserveid = reserveid;
    }

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

    public String getReservedate() {
        return reservedate;
    }

    public void setReservedate(String reservedate) {
        this.reservedate = reservedate;
    }


}
