package com.meong.meongtwork.controller;

import com.meong.meongtwork.dto.BoardDto;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.service.BoardService;
import com.meong.meongtwork.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserDetailService userDetailService;

    @GetMapping("/board/post")
    public String board(Principal principal, Model model) {
        UserEntity user = userDetailService.loadUserByUsername(principal.getName());

        model.addAttribute("user", user);
        return "board/post";
    }

    @PostMapping("/board/post")
    public String postBoard(@ModelAttribute BoardDto boardDto, Principal principal) {
        UserEntity user = userDetailService.loadUserByUsername(principal.getName());

        boardService.save(boardDto, user);
        return "home";
    }

    @GetMapping("/board/edit/{id}")
    public String editBoard(@PathVariable("id") Long id, Model model, Principal principal) {
        UserEntity user = userDetailService.loadUserByUsername(principal.getName());

        model.addAttribute("board", boardService.findByIdWithOwner(id, user.getId()));
        return "board/edit";
    }

    @PostMapping("/board/edit")
    public String editBoard(@ModelAttribute BoardDto boardDto, Principal principal) {
        UserEntity user = userDetailService.loadUserByUsername(principal.getName());

        boardService.update(boardDto, user.getId());
        return "redirect:/board/view/" + boardDto.getId();
    }

    @GetMapping("/board/view/{id}")
    public String viewBoard(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));

        return "board/view";
    }

    @PostMapping("/board/delete")
    public String deleteBoard(@ModelAttribute BoardDto boardDto, Principal principal) {
        UserEntity user = userDetailService.loadUserByUsername(principal.getName());

        boardService.delete(boardDto, user.getId());
        return "redirect:/home";
    }
}
