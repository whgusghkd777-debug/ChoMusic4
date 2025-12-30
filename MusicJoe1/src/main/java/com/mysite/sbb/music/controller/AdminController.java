package com.mysite.sbb.music.controller;  // ★ 네 기존 MusicController랑 같은 패키지

import com.mysite.sbb.music.Music;
import com.mysite.sbb.music.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")  // ★ 관리자만 접근 가능
public class AdminController {

    @Autowired
    private MusicService musicService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Music> musics = musicService.getListAdmin();  // ★ 모든 음악 가져오는 메서드 필요
        model.addAttribute("musics", musics);
        return "admin/admin_dashboard";  // ★ templates/admin/admin_dashboard.html 과 맞춤
    }

    @PostMapping("/delete/{id}")
    public String deleteMusic(@PathVariable Integer id) {  // ★ id 타입 Integer로 맞춤
        musicService.delete(id);  // ★ MusicService에 delete 메서드 있어야 함
        return "redirect:/admin/dashboard";
    }
}