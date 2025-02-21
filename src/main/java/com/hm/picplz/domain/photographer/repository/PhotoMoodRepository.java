package com.hm.picplz.domain.photographer.repository;

import com.hm.picplz.domain.photographer.domain.PhotoMood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoMoodRepository extends JpaRepository<PhotoMood, Long> {
}
