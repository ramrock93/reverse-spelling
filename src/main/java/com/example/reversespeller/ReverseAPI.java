package com.example.reversespeller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReverseAPI {

	@RequestMapping(value = "/api/reverse/{reverse}")
	public String reverseString(@PathVariable String reverse) {
		return ReverseString.reverseString(reverse);
	}
}
