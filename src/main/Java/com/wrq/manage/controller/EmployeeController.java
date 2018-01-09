package com.wrq.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wrq.manage.bean.Employee;
import com.wrq.manage.common.Msg;
import com.wrq.manage.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 王乾 on 2018/1/9.
 */

/**
 * 处理增删改查
 */
@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    /**
     * ajax请求方式
     * 导入jackson包
     * @param pn
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn){

        //1.引入pageHelper

        //2.mybatis-config注册

        //3.查询之前只需要调用,传入页码，每页的大小

        PageHelper.startPage(pn,5);

        //4.startPage后面紧跟的查询是一个分页查询

        List<Employee> employeeList = iEmployeeService.getAll();

        //5.使用pageInfo包装查询后的结果，传入连续显示的页数

        PageInfo pageInfo = new PageInfo(employeeList,5);

        //6.把pageInfo交给页面

        //7.分装了详细的分页信息，包括查询出来的信息

        return Msg.success().add("pageInfo",pageInfo);
    }


    /**
     * 传统方式
     * 查询员工数据，分页
     * @return
     */
    //@RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //1.引入pageHelper

        //2.mybatis-config注册

        //3.查询之前只需要调用,传入页码，每页的大小

        PageHelper.startPage(pn,5);

        //4.startPage后面紧跟的查询是一个分页查询

        List<Employee> employeeList = iEmployeeService.getAll();

        //5.使用pageInfo包装查询后的结果，传入连续显示的页数

        PageInfo pageInfo = new PageInfo(employeeList,5);

        //6.把pageInfo交给页面

        //7.分装了详细的分页信息，包括查询出来的信息

        model.addAttribute("pageInfo",pageInfo);

        return "list";
    }
}