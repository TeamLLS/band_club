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

    public boolean existsByClubIdAndUsername(Long clubId, String username);

    @Query("SELECT m FROM Member m JOIN FETCH m.club c WHERE m.username = :username AND m.status <> 'TERMINATED'")
    public Page<Member> findAllWithClubByUsername(@Param("username") String username, Pageable pageable);

    @Query("SELECT m FROM Member m WHERE m.club.id = :clubId AND m.status <> 'TERMINATED'")
    public Page<Member> findAllByClubId(@Param("clubId") Long clubId, Pageable pageable);

    @Query("SELECT m FROM Member m WHERE m.id IN :list AND m.status <> 'TERMINATED'")
    public List<Member> findSpecifics(@Param("list") List<Integer> list);
}
