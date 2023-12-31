package com.meong.meongtwork.controller;

import com.meong.meongtwork.entity.BoardEntity;
import com.meong.meongtwork.entity.FollowEntity;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.service.BoardService;
import com.meong.meongtwork.service.FollowService;
import com.meong.meongtwork.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BoardService boardService;
    private final UserDetailService userDetailService;
    private final FollowService followService;

    @GetMapping("/home")
    public String home(Pageable pageable, Model model) {
        // Page<BoardEntity> boardEntities = boardService.findAllByUserFollow(1L, pageable);

        // model.addAttribute("boards", boardEntities);
        return "home";
    }

    @GetMapping("/home/data")
    @ResponseBody
    public Page<BoardEntity> homeData(Pageable pageable, Principal principal) {
        UserEntity user = userDetailService.loadUserByUsername(principal.getName());
        List<FollowEntity> followEntities = followService.loadFollowingByUserId(user.getId());

        Set<Long> followedUserIds = followEntities.stream()
                .map(followEntity -> followEntity.getFollowerUser().getId())
                .collect(Collectors.toSet());

        return boardService.findAllByUserFollow(followedUserIds, pageable);
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model){
        List<UserEntity> Users = userDetailService.findUsers();
        List<String> results = new ArrayList<>();
        for (UserEntity user : Users) {
            // 검색어가 데이터에 포함되어 있는지 확인하고 검색 결과를 생성
            if (user.getUsername().contains(query)) {
                results.add(user.getUsername());
            }
        }
        model.addAttribute("results", results);
        return "search-results";
    }
}
