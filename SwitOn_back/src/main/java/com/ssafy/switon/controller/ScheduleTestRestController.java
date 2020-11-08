package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.dto.UserSchedule;
import com.ssafy.switon.service.ScheduleService;
import com.ssafy.switon.service.UserScheduleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/scheduleTest")
@Api(value="ScheduleRestController", description="(테스트용) 스케쥴 테스트 테스트트트트트트트트")
public class ScheduleTestRestController {

	@Autowired
	ScheduleService scheduleService;
	
	@ApiOperation(value = "모든 스케쥴 조회한다", response = List.class)
	@GetMapping("/list")
	public List<Schedule> searchAll() {
		return scheduleService.selectAll();
	}
	
	@ApiOperation(value = "아이디로 스케쥴 조회한다", response = Schedule.class)
	@GetMapping("/{id}")
	public Schedule searchById(@PathVariable("id") int id) {
		return scheduleService.selectScheduleById(id);
	}
	
	@ApiOperation(value = "스케쥴 삭제한다", response = String.class)
	@DeleteMapping("/deleteSchedule/{id}")
	public String deleteSchedule(@PathVariable("id") int id) {
		if(scheduleService.deleteSchedule(id)) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@ApiOperation(value = "스케쥴 수정한다", response = String.class)
	@PostMapping("/update")
	public String updateSchedule(Schedule schedule) {
		if(scheduleService.updateSchedule(schedule)) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@Autowired
	UserScheduleService userscheduleService;
	
	@ApiOperation(value = "모든 유저스케쥴 조회한다", response = List.class)
	@GetMapping("/list/2")
	public List<UserSchedule> searchAll2() {
		return userscheduleService.selectAll();
	}
	
	@ApiOperation(value = "유저 스케쥴 삭제한다", response = String.class)
	@DeleteMapping("/delete/{id}")
	public String deleteUserSchedule(@PathVariable("id") int id) {
		if(userscheduleService.deleteUserSchedule(id)) {
			return "success";
		} else {
			return "fail";
		}
	}
}
