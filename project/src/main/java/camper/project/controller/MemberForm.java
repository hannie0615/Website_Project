package camper.project.controller;

public class MemberForm {
    private String name;
    private String id;
    private int birthDate;
    private String pw;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birth_date) {
        this.birthDate = birth_date;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
