package com.chenli.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer empno;
    private String ename;
    private String job;
    private Long mgr;
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;
}
