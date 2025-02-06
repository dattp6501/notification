package com.dattp.notifitationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class Service {
  @Autowired
  @Lazy
  protected RestTemplate restTemplate;
}
