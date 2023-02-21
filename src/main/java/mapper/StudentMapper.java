package mapper;

import pojo.Student;

import java.util.List;

public interface StudentMapper {

    //查询所有学生
    List<Student> selectAll();

    //添加学生
    void add(Student student);

    //验证学号和姓名删除学生
    void delete(int id);

    //修改学生信息
    void update(Student student);

    //查询单个学生
    Student selectBySid(int sid);

}
