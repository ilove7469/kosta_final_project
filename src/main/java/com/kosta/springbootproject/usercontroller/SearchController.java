package com.kosta.springbootproject.usercontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.userservice.SearchService;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
public class SearchController {
	
	@Autowired
	SearchService service;
	
	@GetMapping("/search/searchForm")
	public void searchForm() {
		
	}
	
	@GetMapping("/search/search")
	public void searchResult(Model model, String keyword) {
		List<Classes> result = service.selectOption(keyword);
	}

}
