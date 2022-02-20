package com.zipbom.zipbom.CustomerSupport.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeDetailsResponse;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeRequest;
import com.zipbom.zipbom.CustomerSupport.dto.NoticeResponse;
import com.zipbom.zipbom.CustomerSupport.mapstruct.NoticeMapper;
import com.zipbom.zipbom.CustomerSupport.model.Notice;
import com.zipbom.zipbom.CustomerSupport.repository.NoticeRepository;
import com.zipbom.zipbom.CustomerSupport.service.NoticeService;
import com.zipbom.zipbom.Util.response.SuccessResponseDto;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;
	private final NoticeRepository noticeRepository;
	private final ObjectMapper objectMapper;

	public NoticeController(NoticeService noticeService, NoticeRepository noticeRepository
		, ObjectMapper objectMapper) {
		this.noticeService = noticeService;
		this.noticeRepository = noticeRepository;
		this.objectMapper = objectMapper;
	}

	@GetMapping()
	public SuccessResponseDto<?> getNotice() {
		List<Notice> notices = noticeRepository.findAll();
		List<NoticeResponse> noticeResponses = notices.stream()
			.map(notice -> new NoticeResponse(notice))
			.collect(Collectors.toList());
		return new SuccessResponseDto<>(true, noticeResponses);
	}

	@GetMapping("/{id}")
	public SuccessResponseDto<?> getNoticeDetails(@PathVariable("id") Long id) {
		Notice notice = noticeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		NoticeDetailsResponse noticeDetailsResponse = NoticeMapper.noticeMapper.noticeToDetailsDto(notice);
		return new SuccessResponseDto<>(true, noticeDetailsResponse);
	}

	@PostMapping
	public ResponseEntity<NoticeResponse> createNotice(@RequestBody NoticeRequest noticeRequest) {
		NoticeResponse noticeResponse = noticeService.createNotice(noticeRequest);
		return ResponseEntity.created(URI.create("/notice/" + noticeResponse.getId())).body(noticeResponse);
	}

	@DeleteMapping("/{id}")
	public SuccessResponseDto<?> deleteNotice(@PathVariable("id") Long id) {
		noticeRepository.deleteById(id);
		return new SuccessResponseDto<>(true, null);
	}
}
