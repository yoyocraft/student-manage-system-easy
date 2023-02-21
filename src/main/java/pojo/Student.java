package pojo;

public class Student {
    private int sid;
    private String name;
    private int age;
    private String major;
    private String grade;
    private String loveSubj;

    public Student() {
    }

    public Student(int sid, String name, int age, String major, String grade, String loveSubj) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.major = major;
        this.grade = grade;
        this.loveSubj = loveSubj;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getloveSubj() {
        return loveSubj;
    }

    public void setloveSubj(String loveSubj) {
        this.loveSubj = loveSubj;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", loveSubj='" + loveSubj + '\'' +
                '}';
    }
}
