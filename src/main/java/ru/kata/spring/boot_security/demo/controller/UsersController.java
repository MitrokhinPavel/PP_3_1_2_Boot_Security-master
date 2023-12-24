package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping({"", "/", "list"})
	public String showAllUsers(Model model, @ModelAttribute("flashMessage") String flashAttribute) {
		model.addAttribute("users", userService.getAllUsers());
		return "list";
	}

	@GetMapping(value = "/new")
	public String addUserForm(@ModelAttribute("user") User user) {
		return "form";
	}

	@PostMapping()
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		if (null == user.getId()) {
			userService.createUser(user);
		} else {
			userService.updateUser(user);
		}
		return "redirect:/users";
	}

	@GetMapping("/edit")
	public String editUserForm(@RequestParam("id") Long id, Model model) {
		User user = userService.readUser(id);
		if (null == user) {
			return "redirect:/users";
		}
		model.addAttribute("user", user);
		return "form";
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam(value = "id", required = true, defaultValue = "") Long id) {
		User user = userService.deleteUser(id);
		return "redirect:/users";
	}
}