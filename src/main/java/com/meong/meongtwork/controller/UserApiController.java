package com.meong.meongtwork.controller;

import com.meong.meongtwork.dto.AddUserRequest;
import com.meong.meongtwork.dto.ProfileDto;
import com.meong.meongtwork.service.ProfileService;
import com.meong.meongtwork.service.UserDetailService;
import com.meong.meongtwork.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {
	private final UserService userService;
	private final ProfileService profileService;
	private final UserDetailService userDetailService;

	@PostMapping("/user")
	public String signup(AddUserRequest request) {
		userService.save(request);
		profileService.save(new ProfileDto(), userDetailService.loadUserByUsername(request.getUsername()));
		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response,
				SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/login";
	}
}
