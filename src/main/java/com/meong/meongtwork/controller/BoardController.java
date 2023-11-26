package com.meong.meongtwork.controller;

import com.meong.meongtwork.dto.BoardDto;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.service.BoardService;
import com.meong.meongtwork.service.UserDetailService;
import com.meong.meongtwork.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserDetailService userDetailService;

    @GetMapping("/board/post")
    public String board() {
        return "post";
    }

    @PostMapping("/board/post")
    public String postBoard(@ModelAttribute BoardDto boardDto, Principal principal) {
        System.out.println("boardDTO = " + boardDto);

        UserEntity user = userDetailService.loadUserByUsername(principal.getName());

        boardService.save(boardDto, user);
        return "home";
    }
}
