package com.chenli;

import com.chenli.pojo.Dept;
import com.chenli.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    private SqlSession sqlSession;
    @Before
    public void init(){
        SqlSessionFactoryBuilder ssfb =new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory=ssfb.build(resourceAsStream);
        Configuration configuration = factory.getConfiguration();
        sqlSession=factory.openSession();
    }
    @Test
    public void testFindAll(){
        // 调用SQL语句
        List<Dept> list = sqlSession.selectList("findDept");
        list.forEach(System.out::println);
    }

    @Test
    public void selectList(){
        List<Emp> empList  = sqlSession.selectList("findAll");
        empList.forEach(System.out::println);
    }
    @Test
    public void selectOne(){
        Emp emp = sqlSession.selectOne("findAll");
        System.out.println(emp);

    }
    @Test
    public void selectMap(){
        Map<Integer, Emp> empMap  = sqlSession.selectMap("findMap", "EMPNO");
        for(Integer ename: empMap.keySet()){
            System.out.println(ename);
            System.out.println(empMap.get(ename));
        }
    }

    @Test
    public void selectByEmpnoAndSal(){
        Map<String, Object> map = new HashMap<>();
        map.put("deptno", 20);
        map.put("sal", 2000.0);
        List<Emp> empList = sqlSession.selectList("findByDeptnoAndSal", map);
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }
    @Test
    public void selectByEmpnoAndSal2(){
        Emp emp = new Emp();
        emp.setDeptno(20);
        emp.setSal(2000.0);
        List<Emp> empList = sqlSession.selectList("findByDeptnoAndSal", emp);
        for (Emp emp2 : empList) {
            System.out.println(emp2);
        }
    }

    @After
    public void release(){
        // 关闭SQLSession
        sqlSession.close();
    }
}
