package com.mysite.sbb.answer;

import com.mysite.sbb.music.Music;
import com.mysite.sbb.music.MusicService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final MusicService musicService;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/music/answer/create/{id}")  // ★ 경로를 /music 아래로 변경 (실무 표준)
    public String createAnswer(@PathVariable("id") Integer id, String content, Principal principal) {
        Music music = this.musicService.getMusic(id);
        SiteUser author = this.userService.getUser(principal.getName());
        this.answerService.create(music, content, author);
        return "redirect:/music/detail/" + id;  // 간단히 문자열 연결
    }
}