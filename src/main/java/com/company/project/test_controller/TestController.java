package com.company.project.test_controller;

//import com.company.project.dao.TbPurchaseMasterRoleActionMapper;
//import com.company.project.model.TbPurchaseMasterRoleAction;

import com.company.project.model.TbUser;
import com.company.project.redis.ReplicationExampleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author dewey.du
 * @create 2020/4/3 0003
 */

@Controller
public class TestController {
    @Value("")
    private List<String> list;
//    @Autowired
//    private TbPurchaseMasterRoleActionMapper roleActionMapper;

    @Autowired
    private ReplicationExampleService replicationExampleService;

    @RequestMapping("/login")
    @ResponseBody
    public String login() throws InterruptedException {
        replicationExampleService.setByCache("Lucas","Lucas");
        // 每个一秒钟，操作一下redis，看看最终效果
        int i = 0;
        while (true) {
            i++;
            replicationExampleService.setByCache("test-value", String.valueOf(i));
            System.out.println("修改test-value值为: " + i);
            Thread.sleep(1000L);
        }
//        String dewey = replicationExampleService.getByCache("Lucas");
//        return "";
    }


    @RequestMapping("/insertBatch")
    public String insertBatch(){

//        List<TbPurchaseMasterRoleAction> records = new ArrayList<>();
//        roleActionMapper.insertList(records);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject == null){
            subject.logout();
        }
        return "login";
    }

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        String dewey = replicationExampleService.getByCache("dewey");
        return dewey;
//        return "index";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){
        return "edit success";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin success";
    }

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username")String username,
                            @RequestParam("password")String password,
                            HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(token);
            TbUser user = (TbUser)subject.getPrincipal();
            session.setAttribute("user",user);
            return "index";
        }catch (Exception e){
            return "login";
        }

    }
}
