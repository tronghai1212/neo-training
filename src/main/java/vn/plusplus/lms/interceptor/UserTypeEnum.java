package vn.plusplus.lms.interceptor;


public enum UserTypeEnum {
    Customer(1, "customer"),
    SystemUser(3,"system-user");

    private Integer id;
    private String name;


    UserTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public static UserTypeEnum fromName(String name) {
        for (UserTypeEnum f : values()) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
        }
        return null;
    }

}
