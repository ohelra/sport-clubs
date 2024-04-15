package com.running.web.service;

import com.running.web.dto.ClubDto;
import com.running.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClub();
    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(Long clubId);

    void updateClub(ClubDto club);

    void delete(Long clubId);
    List<ClubDto> searchClubs(String query);
}
