package com.tmap.tmap.controller;

import com.tmap.tmap.dto.ApUser;
import com.tmap.tmap.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String , String> loginProc(Model model , @RequestBody ApUser form , HttpServletResponse response){
        Map<String , String> res = new HashMap<>();
        try{

            if(form.getUserId().isBlank() || form.getUserPw().isBlank()){
                res.put("res" , "400");
                return res;
            }

            res = loginService.findByIdAndPw(form);

            model.addAttribute("title","login");

            if(res.get("res").equals("anonymous") || res.get("res").equals("wrongPw")){
                return res;
            }else {

                Cookie idCookie = new Cookie("userCode", res.get("res"));
                // 쿠키 설정
                idCookie.setHttpOnly(true);
                idCookie.setSecure(false);
                idCookie.setPath("/");
                idCookie.setMaxAge(30 * 60 * 60);

                response.addCookie(idCookie);

                res.put("res" , "login");

                return res;
            }

        }catch (Exception e){
            e.printStackTrace();

            res.put("res" , "err");
            return res;
        }

    }

    @GetMapping("/map")
    public String map(Model model , @RequestParam Map<String , String> loc){

        try{
            this.setAddr(model , loc);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            model.addAttribute("title","tmap");
            return "navi";
        }

    }


    private void setAddr(Model model , Map<String , String> loc){
        String departure = loc.get("departure");
        String destination = loc.get("destination");

        String[] departureParts = departure.split("_");
        String departureAddr = departureParts[0];
        String departurePostCode = departureParts[1];
        String departureAddrDetail = departureParts[2];

        String[] destinationParts = destination.split("_");
        String destinationAddr = destinationParts[0];
        String destinationPostCode = destinationParts[1];
        String destinationAddrDetail = destinationParts[2];

        model.addAttribute("destinationx" , loc.get("destinationx"));
        model.addAttribute("destinationy" , loc.get("destinationy"));

        model.addAttribute("departurex" , loc.get("departurex"));
        model.addAttribute("departurey" , loc.get("departurey"));

        model.addAttribute("departureAddr" , departureAddr);
        model.addAttribute("departureAddrDetail" , departureAddrDetail);
        model.addAttribute("departurePostCode" , departurePostCode);
        model.addAttribute("destinationAddr" , destinationAddr);
        model.addAttribute("destinationAddrDetail" , destinationAddrDetail);
        model.addAttribute("destinationPostCode" , destinationPostCode);
    }


}
