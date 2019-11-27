package domain;
//旅客信息
public class Traveller {
    private String id;//uuid
    private String name;//乘客姓名
    private String sex;//乘客性别
    private String phoneNum;//乘客电话号码
    private Integer credentialsType;//乘客证件类型
    private String credentialsTypeStr;//乘客证件类型字符串
    private String credentialsNum;//乘客证件号码
    private Integer travellerType;//乘客类型
    private String travellerTypeStr;//乘客类型字符串

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsTypeStr() {
        if (credentialsType==0){
            return "身份证";
        }else if (credentialsType==1){
            return "护照";
        }
        return "军官证";
    }

    public void setCredentialsTypeStr(String credentialsTypeStr) {
        this.credentialsTypeStr = credentialsTypeStr;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public Integer getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(Integer travellerType) {
        this.travellerType = travellerType;
    }

    public String getTravellerTypeStr() {
        return travellerType==0?"成人":"儿童";
    }

    public void setTravellerTypeStr(String travellerTypeStr) {
        this.travellerTypeStr = travellerTypeStr;
    }
}
