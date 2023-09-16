package com.musicreview.api.spotify.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenTaskScheduler {
    private final TokenTask tokenTask;
    private static final Logger logger = LoggerFactory.getLogger(TokenTaskScheduler.class);

    @Autowired
    public TokenTaskScheduler(TokenTask tokenTask) {
        this.tokenTask = tokenTask;
    }

    @Scheduled(fixedRate = 3400000)
    public void runPeriodically(){
        tokenTask.getTokenFromApi();
        logger.info("Token acquired successfully");
    }
}
