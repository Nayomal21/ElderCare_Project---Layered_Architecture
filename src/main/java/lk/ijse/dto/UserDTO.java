package lk.ijse.dto;

public class UserDTO {
    private  String userName;
    private  String password;

    private  String name;

    private  String type;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDTO() {
    }

    public UserDTO(String userName, String password, String name) {
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(String userName, String name, String password, String type) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.type = type;
        System.out.println(type);



    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
