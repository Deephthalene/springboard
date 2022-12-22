package com.ezen.myProject.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
//
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService bsv;
	
	@GetMapping("/register")
	public String boardRegisterGet() {
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String register(BoardVO bvo, RedirectAttributes reAttr) {
		log.info(bvo.toString());
		
		int isOk = bsv.register(bvo);
		reAttr.addFlashAttribute("isOk", isOk>0 ? "1":"0");
		log.info("Board Register >> " +  (isOk>0 ? "OK" : "Fail"));
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<BoardVO> list = bsv.getList();
		model.addAttribute("list", list);
		return "/board/list";
	}
	
	@GetMapping("/detail")
	public String detail(Model model, @RequestParam("bno")int bno) {
		BoardVO bvo = bsv.getDetail(bno);
		model.addAttribute("board", bvo);
		
		return "/board/detail";
	}
}
