package com.meong.meongtwork.controller;

import com.meong.meongtwork.dto.BoardDto;
import com.meong.meongtwork.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/post")
    public String board() {
        return "post";
    }

    @PostMapping("/board/post")
    public String postBoard(@ModelAttribute BoardDto boardDto) {
        System.out.println("boardDTO = " + boardDto);

        boardService.save(boardDto);
        return "index";
    }
}
