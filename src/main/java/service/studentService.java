package service;


import mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Student;
import util.SqlSessionFactoryUtils;

import java.util.List;

public class studentService {
    SqlSessionFactory sqlSessionFactory=SqlSessionFactoryUtils.getSqlSessionFactory();

    public  List<Student> selectAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = mapper.selectAll();

        sqlSession.close();

        return students;
    }


    public   void add(Student student){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.add(student);

        sqlSession.commit();

        sqlSession.close();
    }

    public  void delete(int sid){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.delete(sid);

        sqlSession.commit();

        sqlSession.close();
    }


    public void update(Student student){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.update(student);

        sqlSession.commit();

        sqlSession.close();
    }

    public Student selectBySid(int sid){
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        Student student = mapper.selectBySid(sid);

        return student;
    }



}
