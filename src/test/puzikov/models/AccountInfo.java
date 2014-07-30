package test.puzikov.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "AccountInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountInfo {
    @XmlAttribute(name = "login")
    private String login;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Phone")
    private String phone;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
