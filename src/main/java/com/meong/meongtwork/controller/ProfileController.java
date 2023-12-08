package com.meong.meongtwork.controller;

import com.meong.meongtwork.dto.ProfileDto;
import com.meong.meongtwork.entity.FollowEntity;
import com.meong.meongtwork.entity.UserEntity;
import com.meong.meongtwork.service.BoardService;
import com.meong.meongtwork.service.FollowService;
import com.meong.meongtwork.service.ProfileService;
import com.meong.meongtwork.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ProfileController {
	private final UserDetailService userDetailService;
	private final BoardService boardService;
	private final ProfileService profileService;
	private final FollowService followService;

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

	@PostMapping("/profile/edit")
	public String profileEdit(@RequestParam("id") Long profileId, Model model, Principal principal) {
		Long myId = userDetailService.loadUserByUsername(principal.getName()).getId();
		profileService.validateAuthorization(profileId, myId);
		model.addAttribute("profile", profileService.loadProfileByUserId(myId));
		return "profile/edit";
	}

	@PostMapping("/profile/saveEdit")
	public String saveIntro(@ModelAttribute ProfileDto profileDto, Principal principal) {
		profileService.profileUpdate(profileDto, userDetailService.loadUserByUsername(principal.getName()));
		return "redirect:/profile";
	}

	@PostMapping("/profile/follow")
	public String follow(@ModelAttribute("id") Long profileId, Principal principal){
		UserEntity me = userDetailService.loadUserByUsername(principal.getName());
		UserEntity profileUser = profileService.loadProfileByUserId(profileId).getUser();
		if (!Objects.equals(profileId, me.getId()))
			followService.saveFollow(profileUser, me);
		return "redirect:/profile/" + profileUser.getUsername();
	}

	@PostMapping("/profile/followingList")
	public String follower(@ModelAttribute("id") Long profileId, Model model) {
		List<FollowEntity> followers = followService.loadFollowerByUserId(profileId);
		List<String> followerNames = followers.stream()
				.map(FollowEntity::getFollowingUser)
				.map(UserEntity::getUsername)
				.toList();
		model.addAttribute("members", followerNames);
		return "/profile/follow";
	}

	@PostMapping("/profile/followerList")
	public String following(@ModelAttribute("id") Long profileId, Model model){
		List<FollowEntity> followings = followService.loadFollowingByUserId(profileId);
		List<String> followingNames = followings.stream()
				.map(FollowEntity::getFollowerUser)
				.map(UserEntity::getUsername)
				.toList();
		model.addAttribute("members", followingNames);
		return "profile/follow";
	}
}
