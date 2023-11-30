package com.meong.meongtwork.controller;

import com.meong.meongtwork.dto.ProfileDto;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.service.BoardService;
import com.meong.meongtwork.service.ProfileService;
import com.meong.meongtwork.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {
	private final UserDetailService userDetailService;
	private final BoardService boardService;
	private final ProfileService profileService;

	@GetMapping("profile")
	public String myprofile(Principal principal) {
		String name = principal.getName();
		return "redirect:/profile/" + name;
	}

	@GetMapping("profile/{username}")
	public String profile(@PathVariable("username") String username, Model model) {
		UserEntity userEntity = userDetailService.loadUserByUsername(username);
		model.addAttribute("profile", profileService.loadProfileByUserId(userEntity.getId()));
		return "profile/profile";
	}

	@GetMapping("/profile/edit")
	public String profileEdit(Model model, Principal principal) {
		Long id = userDetailService.loadUserByUsername(principal.getName()).getId();
		model.addAttribute("profile", profileService.loadProfileByUserId(id));
		return "profile/edit";
	}

	@PostMapping("/profile/saveEdit")
	public String saveIntro(@ModelAttribute ProfileDto profileDto, Principal principal) {
		profileService.profileUpdate(profileDto, userDetailService.loadUserByUsername(principal.getName()));
		return "redirect:/profile";
	}
}
