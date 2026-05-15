package uk.gov.ons.census.fwmt.tm.mock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.ons.census.fwmt.tm.mock.comet.api.managers.CaseManager;
import uk.gov.ons.census.fwmt.tm.mock.config.LatencyBean;
import uk.gov.ons.census.fwmt.tm.mock.logging.MockMessage;
import uk.gov.ons.census.fwmt.tm.mock.logging.MockMessageLogger;

@RestController
@RequestMapping("logger")
public class MockLoggerController {
  
  @Autowired
  private MockMessageLogger mockLogger;
  
  @Autowired
  private CaseManager caseManager;
  
  @Autowired
  private LatencyBean latencyBean;

  @GetMapping(value = "allMessages", produces = "application/json")
  public ResponseEntity<List<MockMessage>> getAllMessages() {
    List<MockMessage> messages = mockLogger.getAllMessages();
    if (messages == null) {
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    } else {
      return new ResponseEntity<>(messages, HttpStatus.OK);
    }
  }

  @GetMapping(value = "getCount", produces = "application/json")
  public int getJobCount() {
    return mockLogger.getJobCount();

  }

  @GetMapping(value = "faultCount", produces = "application/json")
  public int getFaultCount() {
    return mockLogger.getFaultCount();
  }

  @GetMapping(value = "reset")
  public void reset() {
    mockLogger.reset();
    caseManager.reset();
  }

  @GetMapping(value = "enableRequestRecorder", produces = "application/json")
  public void enableCaseManager() {
    caseManager.enableCaseManager();
  }

  @GetMapping(value = "disableRequestRecorder", produces = "application/json")
  public void disableCaseManager() {
    caseManager.disableCaseManager();
  }
  
  @PutMapping(value = "latency/{ms}")
  public void setLatency(@PathVariable Integer ms) {
    latencyBean.setLatency(ms);
  }
}
