package com.zipbom.zipbom.InterestedRoom.controller;

import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.InterestedRoom.dto.AddInterestedRoomRequestDto;
import com.zipbom.zipbom.InterestedRoom.service.InterestedRoomService;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interestedRoom")
public class InterestedRoomController {

    @Autowired
    private InterestedRoomService interestedRoomService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public CMRespDto<?> addInterestedRood(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody AddInterestedRoomRequestDto addInterestedRoomRequestDto) {
        return interestedRoomService.addInterestedRoom(principalDetails, addInterestedRoomRequestDto);
    }
}