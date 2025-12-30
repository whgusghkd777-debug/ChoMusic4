package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 루트(/) 접속 시 음악 리스트로 바로 리다이렉트
    @GetMapping("/")
    public String home() {
        return "redirect:/music/list";
    }

    // 혹시 www.도메인.com/music 없이 들어올 사람 대비
    @GetMapping("/index")
    public String index() {
        return "redirect:/music/list";
    }
}