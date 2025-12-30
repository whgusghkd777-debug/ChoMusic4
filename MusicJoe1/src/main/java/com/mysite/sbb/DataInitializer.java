package com.mysite.sbb;

import com.mysite.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // 관리자 계정이 없으면 생성 (아이디: admin, 비번: 1234)
        if (userService.getUser("admin") == null) {
            userService.create("admin", "admin@musicjoe.com", "1234");
            System.out.println("=== 관리자 계정 생성 완료 ===");
            System.out.println("아이디: admin");
            System.out.println("비밀번호: 1234");
            System.out.println("===============================");
        }
    }
}