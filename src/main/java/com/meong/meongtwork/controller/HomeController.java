package com.meong.meongtwork.controller;

import com.meong.meongtwork.entity.BoardEntity;
import com.meong.meongtwork.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BoardService boardService;

    @GetMapping("/home")
    public String home(Pageable pageable, Model model) {
        Page<BoardEntity> boardEntities = boardService.findAllByUserFollow(1L, pageable);

        model.addAttribute("boards", boardEntities);
        return "home";
    }

    @GetMapping("/home/data")
    @ResponseBody
    public Page<BoardEntity> homeData(Pageable pageable) {
        return boardService.findAllByUserFollow(3L, pageable);
    }
}
