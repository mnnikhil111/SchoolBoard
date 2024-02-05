package com.school.sba.util;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.school.sba.repository.AcademicProgramRepository;
import com.school.sba.serviceImpl.*;

@Component
public class ScheduledJobs {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private AcademicProgramServiceImpl academicProgramServiceImpl;

	@Autowired
	private AcademicProgramRepository academicProgramRepository;

	@Autowired
	private ClassHourServiceImpl classHourServiceImpl;

	@Autowired
	private SchoolServiceImpl schoolServiceImpl;

	@Scheduled(fixedDelay = 2000L)
	public void hardDelete() {
		userServiceImpl.hardDeleteUser();
		academicProgramServiceImpl.hardDeleteAcademicProgram();
		schoolServiceImpl.hardDeleteSchool();
	}

	@Scheduled(cron = "0 0 0 * * MON")
	public void updateClassHour() {
		academicProgramRepository.findAll().forEach(academicProgram -> {
			if (academicProgram.isAutoGeneratedClassHour())
				classHourServiceImpl.classHourGen(academicProgram.getProgramId(), LocalDateTime.now());
		});
	}

}
