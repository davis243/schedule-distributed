package com.davis.scheduledistributed;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.hazelcast.HazelcastLockProvider;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hazelcast.core.HazelcastInstance;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class SchedulerConfiguration {

	 @Bean
	 @Autowired
	 public HazelcastLockProvider lockProvider(HazelcastInstance hazelcastInstance) {
	    return new HazelcastLockProvider(hazelcastInstance);
	}


}