package lk.ijse.view.tdm;


public class StudentTm implements Comparable<StudentTm>{
    private String id;
    private String name;
    private String address;
    private String tel;
    private String email;

    public StudentTm() {
    }

    @Override
    public String toString() {
        return "StudentTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentTm(String id, String name, String address, String tel, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
    }

    @Override
    public int compareTo(StudentTm c) {
        if (id == null) {
            return (c.getId() == null) ? 0 : -1;
        }
        return id.compareTo(c.getId());
    }



}
