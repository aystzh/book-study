package code;

public class JsonFormat {

    /**
     * id : 13
     * name : 贝吉的塔
     * age : 22
     * sex : 1
     * email : beijita@longzhu.com
     * nickName : 贝贝
     * birthday : 2000-12-12
     * creater : admin
     */

    private int id;
    private String name;
    private int age;
    private int sex;
    private String email;
    private String nickName;
    private String birthday;
    private String creater;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}
