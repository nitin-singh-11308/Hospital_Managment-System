package com.myorganization.HMS.controller;

import com.myorganization.HMS.dto.response.ServerStatusResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api")
public class ServerController {
        private final Instant serverStartTime;
        private final String applicationName;

        public ServerController(@Value("${spring.application.name:Default App}") String applicationName) {
            this.serverStartTime = Instant.now();
            this.applicationName = applicationName;
        }

        @GetMapping
        public ResponseEntity<ServerStatusResponseDto> checkServerStatus() {
            ServerStatusResponseDto serverStatusResponseDto = new ServerStatusResponseDto(serverStartTime, applicationName);

            return new ResponseEntity<>(serverStatusResponseDto, HttpStatus.valueOf(200));
        }

}
