package com.hm.picplz.domain.member.Repository;

import com.hm.picplz.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
