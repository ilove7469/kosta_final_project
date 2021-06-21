package com.kosta.springbootproject.usercontroller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.LectureHall;
import com.kosta.springbootproject.model.Subject;
import com.kosta.springbootproject.userservice.SearchService;


@Controller
public class SearchController {
	
	@Autowired
	SearchService service;
	
	@GetMapping("/search/searchForm")
	public void searchForm() {
		
	}
	
	@GetMapping("/search/searchResult")
	public void searchResult(Model model, String keyword) {
		List<Classes> result = service.selectOption(keyword);
		
		HashSet<Subject> subset = new HashSet<>();
		HashSet<LectureHall> hallset = new HashSet<>();
		for(Classes c:result) {
			subset.add(c.getLecture().getCourse().getSubject());
			hallset.add(c.getClassRoom().getLectureHall());
		}
		List<Subject> sublist = new ArrayList<>(subset);
		List<LectureHall> hlist = new ArrayList<>(hallset);
		sublist.sort(null);
		hlist.sort(null);
		
		model.addAttribute("word", keyword);
		model.addAttribute("hlist", hlist);
		model.addAttribute("sublist", sublist);
		model.addAttribute("word", keyword);
		model.addAttribute("result", result);
	}

	@GetMapping("/search/searchSubject")
	public String searchSubject(Model model, String keyword, Long subNo) {
		List<Classes> result = service.selectSubClasses(keyword, subNo);
			
		HashSet<LectureHall> hallset = new HashSet<>();
		for(Classes c:result) {
			hallset.add(c.getClassRoom().getLectureHall());
		}
		List<LectureHall> hlist = new ArrayList<>(hallset);
		hlist.sort(null);
		
		model.addAttribute("word", keyword);
		model.addAttribute("subNo", subNo);
		model.addAttribute("hlist", hlist);
		model.addAttribute("result", result);
		return "/search/table_con";
	}
	
	@GetMapping("/search/searchHall")
	public String searchHall(Model model, String keyword, Long subNo, Long lecHallNo) {
		List<Classes> result = service.selectSubHall(keyword, subNo, lecHallNo);
		
		model.addAttribute("word", keyword);
		model.addAttribute("subNo", subNo);
		model.addAttribute("lecHallNo", lecHallNo);
		model.addAttribute("result", result);
		return "/search/table_con2";
	}
}
