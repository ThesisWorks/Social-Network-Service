package com.meong.meongtwork.controller;

import com.meong.meongtwork.constant.LocalFilePaths;
import com.meong.meongtwork.dto.BoardDto;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.service.BoardService;
import com.meong.meongtwork.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String postBoard(@ModelAttribute BoardDto boardDto, @RequestParam("file") MultipartFile file, Principal principal) {
        UserEntity user = userDetailService.loadUserByUsername(principal.getName());
        String imagePath = null;

        if (!file.isEmpty()) {
            try {
                // 파일 이름 가져오기
                String imageName = file.getOriginalFilename();
                // 저장할 파일 경로 설정
                imagePath = LocalFilePaths.BOARD_IMAGE_DIR_PATH.getPath() + imageName;
                Path path = Paths.get(imagePath);
                // 파일 저장
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                e.printStackTrace();
                return "board/post";
            }
        }

        boardDto.setImagePath("/" + imagePath);
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
