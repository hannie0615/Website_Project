package camper.project.controller;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class MemberForm {

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @NotBlank(message = "아이디를 입력해 주세요")
    private String id;

    @NotBlank(message = "생년월일을 입력해주세요.")
    @Pattern(regexp = "^[0-9]*$", message = "생년월일은 6자리 숫자로 입력하세요.")
    @Size(min = 6, max = 6, message = "6자리로 입력해 주세요.")
    private String birthDate;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 15, message = "비밀번호는 8~15자리로 입력해주세요.")
    private String pw;

    @NotBlank(message = "판매자/고객 중 선택 해주세요.")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}



