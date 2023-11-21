package com.example.demo.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.CategoryServ;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Category", description = "API danh mục ")
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryCtrl {
	private final CategoryServ categoryServ;

	@PostMapping
	@Operation(summary = "ADMIN - Thêm mới danh mục chung")
	@ApiResponse(responseCode = "201", description = "Thêm danh mục chung thành công")
	public ResponseEntity<?> create(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
			@RequestBody @Valid Category request) {
		SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
		categoryServ.addCategory(request);
		System.out.println(categoryServ.addCategory(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/")
	@Operation(summary = "ADMIN - Danh sách danh mục chung")
	@ApiResponse(responseCode = "200", description = "Lấy thành công")
	public ResponseEntity<?> get(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
		return ResponseEntity.ok(categoryServ.getList());
	}
	

	@PutMapping("/{id}")
	@Operation(summary = "ADMIN - Cập nhật danh mục chung")
	@ApiResponse(responseCode = "202", description = "Cập nhật danh mục chung thành công")
	public ResponseEntity<?> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long id,
			@RequestBody @Valid Category request) {
		SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
		categoryServ.updateCategory(id, request);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@GetMapping("/{id}")
	@Operation(summary = "ADMIN - Chi tiết danh mục chung")
	@ApiResponse(responseCode = "200", description = "Lấy thành công")
	public ResponseEntity<?> get(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long id) {
		SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
		return ResponseEntity.ok(categoryServ.getOne(id));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "ADMIN - Xoá danh mục chung")
	@ApiResponse(responseCode = "204", description = "Xoá thành công")
	public ResponseEntity<?> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long id) {
		SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
		categoryServ.deleteCategory(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping
	@Operation(summary = "ADMIN - Danh sách/ Tìm danh mục chung")
	@ApiResponse(responseCode = "200", description = "Lấy thành công")
	public ResponseEntity<?> find(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
			@Param(value = "name") String name) {
		SecurityUtils.validateToken(token, SecurityUtils.ADMINS);
		return ResponseEntity.ok(categoryServ.findByName(name));
	}
}
