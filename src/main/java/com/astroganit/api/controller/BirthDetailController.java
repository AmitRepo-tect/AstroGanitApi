package com.astroganit.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.astroganit.api.entities.BirthDetailInfo;
import com.astroganit.api.exception.AppException;
import com.astroganit.api.payload.ResponseNew;
import com.astroganit.api.service.BirthDetailService;
import com.astroganit.api.util.ResultCode;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/birth-details")
public class BirthDetailController {

	private final BirthDetailService service;

	// ✅ Constructor Injection (Best Practice)
	public BirthDetailController(BirthDetailService service) {
		this.service = service;
	}

	// ✅ CREATE
	@PostMapping
	public ResponseEntity<ResponseNew<Long>> create(@Valid @RequestBody BirthDetailInfo data) {
		// System.out.println(data.get);
		BirthDetailInfo saved = service.saveBirthDetail(data);

		ResponseNew<Long> response = new ResponseNew<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Kundli has been saved successfully.");
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setData(saved.getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// ✅ GET ALL
	@GetMapping
	public ResponseEntity<ResponseNew<List<BirthDetailInfo>>> getAll() {

		List<BirthDetailInfo> list = service.getAll();

		ResponseNew<List<BirthDetailInfo>> response = new ResponseNew<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("All records fetched successfully.");
		response.setData(list);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/paginated")
	public ResponseEntity<ResponseNew<Page<BirthDetailInfo>>> getAllPaginated(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "viewTime") String sortBy, @RequestParam(defaultValue = "desc") String sortDir,
			@RequestParam(required = false) String search) {

		Set<String> allowedSortFields = Set.of("id", "name", "createTime", "updateTime", "viewTime");

		if (!allowedSortFields.contains(sortBy)) {
			sortBy = "viewTime";
		}

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(page, size, sort);
		Page<BirthDetailInfo> result = service.getAllPaginated(search, pageable);
		ResponseNew<Page<BirthDetailInfo>> response = new ResponseNew<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("Paginated data fetched successfully");
		response.setData(result);
		return ResponseEntity.ok(response);
	}

	// ✅ GET BY ID
	@GetMapping("/{id}")
	public ResponseEntity<ResponseNew<BirthDetailInfo>> getById(@PathVariable Long id) {

		BirthDetailInfo birthDetailInfo = service.getById(id)
				.orElseThrow(() -> new AppException(ResultCode.DATA_NOT_FOUND));

		ResponseNew<BirthDetailInfo> response = new ResponseNew<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("Data retrieved successfully.");
		response.setData(birthDetailInfo);

		return ResponseEntity.ok(response);
	}

	// ✅ UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<ResponseNew<BirthDetailInfo>> update(@PathVariable Long id,
			@Valid @RequestBody BirthDetailInfo data) {

		// Prevent ID override from request body
		data.setId(id);
		BirthDetailInfo updated = service.update(id, data);
		ResponseNew<BirthDetailInfo> response = new ResponseNew<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("Kundli updated successfully.");
		response.setData(updated);

		return ResponseEntity.ok(response);
	}

	// ✅ DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseNew<String>> delete(@PathVariable Long id) {

		service.delete(id);

		ResponseNew<String> response = new ResponseNew<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("Deleted successfully.");
		response.setData("Record deleted successfully.");

		return ResponseEntity.ok(response);
	}

	@PostMapping("/{id}/view")
	public ResponseEntity<?> updateViewTime(@PathVariable Long id) {
		service.updateViewTime(id);
		ResponseNew<String> response = new ResponseNew<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("Deleted successfully.");
		response.setData("Record deleted successfully.");

		return ResponseEntity.ok(response);
	}

	@GetMapping("/search")
	public ResponseEntity<ResponseNew<List<BirthDetailInfo>>> searchKundli(@RequestParam String search) {

		List<BirthDetailInfo> result = service.searchKundli(search);
		for(int i=0;i<result.size()-1;i++) {
			System.out.println(result.get(i).getName()+"--"+result.get(i).getClientUuid());
		}

		ResponseNew<List<BirthDetailInfo>> response = new ResponseNew<>();

		response.setStatusCode(HttpStatus.OK.value());
		response.setResultCode(ResultCode.SUCCESS.getCode());
		response.setMessage("Search completed");
		response.setData(result);

		return ResponseEntity.ok(response);
	}
}