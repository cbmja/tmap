package com.tmap.tmap.controller;

import com.tmap.tmap.dto.ApUser;
import com.tmap.tmap.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tmap")
@RequiredArgsConstructor
public class TmapController {

    private final LoginService loginService;



    @GetMapping("/test")
    public String tmapTest(Model model){

        model.addAttribute("title","index");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("title","login");
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String loginProc(Model model , @RequestBody ApUser form , HttpServletResponse response){

        try{

            if(form.getUserId().isBlank() || form.getUserPw().isBlank()){
                return "400";
            }

            String userCode = loginService.findByIdAndPw(form);

            model.addAttribute("title","login");

            if(userCode.equals("anonymous") || userCode.equals("wrongPw")){
                return userCode;
            }else {

                Cookie idCookie = new Cookie("userCode", userCode);
                // 쿠키 설정
                idCookie.setHttpOnly(true);
                idCookie.setSecure(false);
                idCookie.setPath("/");
                idCookie.setMaxAge(30 * 60 * 60);

                response.addCookie(idCookie);

                return "login";
            }

        }catch (Exception e){
            e.printStackTrace();
            return "err";
        }



    }


}
