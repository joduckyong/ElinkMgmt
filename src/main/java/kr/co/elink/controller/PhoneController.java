package kr.co.elink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/phone/")
public class PhoneController {

	@GetMapping("{page}")
	public String phoneGet(@PathVariable String page, ModelMap model) throws Exception{
		
		return "phone/"+page;
	}	
	
	@PostMapping("{page}")
	public String phonePost(@PathVariable String page, ModelMap model) throws Exception{
		
		return "phone/"+page;
	}	
	
	
}