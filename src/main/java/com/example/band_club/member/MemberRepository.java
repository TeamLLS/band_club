package com.example.band_club.member;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByClubIdAndUsername(Long clubId, String username);

    @Query("SELECT m FROM Member m JOIN m.club c WHERE m.username = :username")
    public Page<Member> findAllWithClubByUsername(@Param("username") String username, Pageable pageable);
}
