package com.ezen.myProject.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.PagingVO;
import com.ezen.myProject.domain.UserVO;
import com.ezen.myProject.handler.PagingHandler;
import com.ezen.myProject.repository.UserDAO;
import com.ezen.myProject.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
   
   @Inject
   private BoardService bsv;
   @Inject
   private UserDAO userDao;
   
   
   @GetMapping("/register")
   public String boardRegisterGet() {
      return "/board/register";
   }
   
   
   
   @PostMapping("/register")
   public String register(BoardVO bvo, RedirectAttributes reAttr) {
      log.info(bvo.toString());
      
      int isOk = bsv.register(bvo);
      reAttr.addFlashAttribute("isOk", isOk > 0 ? "1" : "0"); // setAttribute 와 같음
      log.info("board register >> " + (isOk > 0 ? "OK" : "Fail"));
      return "redirect:/board/list";
   }
   
   @GetMapping("/list")
   public String list(Model model, PagingVO pvo) {
	  log.info(">>>> pageNo : " + pvo.getPageNo());
      List<BoardVO> list = bsv.getList(pvo);
      int totalCount = bsv.getTotalCount();
      PagingHandler pgh = new PagingHandler(pvo, totalCount);
      model.addAttribute("pgh", pgh);
      model.addAttribute("list", list);
      return "/board/list";
   }
   
   
//   @GetMapping("/detail")
//   public String detail(Model model, @RequestParam("bno") int bno) {
//      BoardVO board = bsv.getDetail(bno);
//      model.addAttribute("board", board);
//      return "/board/detail";
//   }
   
   // void 하고 return 없이 해도 가능
   // mapping인 detail로 다시 돌아가기 때문
   @GetMapping({"/detail", "/modify"})
   public void detail(Model model, @RequestParam("bno") int bno) { 
      BoardVO board = bsv.getDetail(bno);
      model.addAttribute("board", board);
   }
   
   @PostMapping("/modify")
   public String modify(BoardVO bvo, RedirectAttributes reAttr) {
      log.info("modify >>> " + bvo.toString());
      // user 비교 : 안 해도 상관은 없음
      UserVO user = userDao.getUser(bvo.getWriter()); 
      int isUp = bsv.modify(bvo, user);
      log.info(">>> modify : " + (isUp > 0 ? "Ok" : "Fail"));
      reAttr.addFlashAttribute(isUp > 0 ? "1" : "0");
      return "redirect:/board/list";
   }
   
   @GetMapping("/remove")
   public String remove(RedirectAttributes reAttr, @RequestParam("bno") int bno, HttpServletRequest req) { // RedirectAttributes 없어도 됨
      HttpSession ses = req.getSession();
      UserVO user = (UserVO)ses.getAttribute("ses");
      int isOk = bsv.remove(bno, user);
      log.info(">>> remove : " + (isOk > 0 ? "Ok" : "fail"));
      return "redirect:/board/list";
   }
   
   

}