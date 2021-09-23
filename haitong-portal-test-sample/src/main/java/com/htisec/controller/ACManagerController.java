package com.htisec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htisec.service.AccountService;

@RestController
@RequestMapping("/")
//@Api(tags = { "Account Manager" }, description = "API for balance checking")
public class ACManagerController {

	@Autowired
	//private AccountService accountService;

	@GetMapping("/test")
	public String  getBalance() {
		return "hello";
	}
}
