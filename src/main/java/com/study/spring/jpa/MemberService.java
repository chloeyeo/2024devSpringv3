package com.study.spring.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService { // implements CRUD using MVC pattern
	@Autowired
	private MemberRepository memberRepository;
	
	public Member insert(Member member) {
		return memberRepository.save(member);	
	}

	public List<Member> selectAll() {
		return memberRepository.findAll();
	}
	
	// when getting single object always use Optional (in case for null then returns empty Optional)
	public Optional<Member> select(Long id) {
		return memberRepository.findById(id);
	}
	
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}
	
	public Member update(Member member) {
		Member existingMember = memberRepository.findById(member.getId())
				.orElseThrow(()-> new RuntimeException("Member not found"));
		// update fields
		existingMember.changeUsername(member.getUsername());
		existingMember.changeCreateDate(member.getCreateDate());
		return memberRepository.save(member);
	}
}
