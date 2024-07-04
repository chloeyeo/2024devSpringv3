package com.study.spring.jpa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MyController {
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() {
		return "menu"; // goes to menu.jsp when Uri / is visited
	}
	
	@RequestMapping("/insert") // insert?username=han
	public String insert(@RequestParam("username") String username, Model model) {
		
		// Method 1 - faster speed
//		Member user = new Member();
//		user.changeUsername(username);
//		user.changeCreateDate(LocalDate.now());
		
		// Method 2 - more convenient as it automatically creates constructors
		Member user = Member.builder()
				.username(username)
				.createDate(LocalDate.now())
				.build(); // SAME as Method 1
		
		//Member user = Member.builder().username(username).build(); // this ALSO works with builder()
		// even if there's no single parameter constructor in Member class - builder() automatically creates this constructor
		
		Member result = memberService.insert(user);
		
		model.addAttribute("member", result); // access member.username, member.createDate etc in insert.jsp file
		
		return "insert"; // returns insert.jsp
	}
	
	@RequestMapping("/selectAll")
	public String selectAll(Model model) {
		List<Member> allMembers = memberService.selectAll();
		model.addAttribute("allMembers", allMembers);
		return "selectAll"; // selectAll.jsp
	}
	
	@RequestMapping("/select") // select?id=1
	public String select(@RequestParam("id") Long id, Model model) {
		Optional<Member> result =  memberService.select(id);
		if (result.isPresent()) {
			model.addAttribute("member", result.get());
		} else {
			model.addAttribute("member", null);
		}
		return "select"; // select.jsp
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") Long id) {
		memberService.delete(id);
		return "delete";
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam("id") Long id,
			@RequestParam("username") String username,
//			Member member, 
			Model model) {
		Optional<Member> result =  memberService.select(id);
		if (result.isPresent()) {
			Member member = result.get();
			member.changeUsername(username);
			Member updatedMember = memberService.update(member);
			model.addAttribute("member", updatedMember);
		} else {
			model.addAttribute("member", null);
		}
		return "update";
	}
	
}
