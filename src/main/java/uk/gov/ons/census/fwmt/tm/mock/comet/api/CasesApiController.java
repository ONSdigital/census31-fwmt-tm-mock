package uk.gov.ons.census.fwmt.tm.mock.comet.api;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import ma.glasnost.orika.MapperFacade;
import uk.gov.ons.census.fwmt.common.data.tm.*;
import uk.gov.ons.census.fwmt.tm.mock.comet.api.managers.CaseManager;
import uk.gov.ons.census.fwmt.tm.mock.comet.api.managers.PauseManager;
import uk.gov.ons.census.fwmt.tm.mock.logging.MockMessageLogger;

@Controller
public class CasesApiController implements CasesApi {

  private static final Logger log = LoggerFactory.getLogger(CasesApiController.class);

  @Autowired
  private MockMessageLogger mockLogger;

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private CaseManager caseManager;

  @Autowired
  private PauseManager pauseManager;

  @Autowired
  private MapperFacade mapperFacade;

  @Override
  public ResponseEntity<Case> getCase(String id, Optional<String> include) {
    mockLogger.logEndpoint("CasesApiController", "casesByIdGet");
    Case caseRequest = caseManager.getCase(id);
    if (caseRequest != null) {
      log.info("GET  CaseId: {} : FOUND", id);
      return new ResponseEntity<>(caseRequest, HttpStatus.ACCEPTED);
    } else {
      log.info("GET  CaseId: {} : NOT FOUND", id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<FetchResponse> getCases(String filter, String include, int pageNo, int pageSize,
      String order) {
    return null;
  }

  @Override
  public ResponseEntity<CasePause> getCasePause(String id) {
    mockLogger.logEndpoint("CasesApiController", "casesByIdPauseGet");
    CasePause casePause = pauseManager.getPause(id);
    if (casePause != null) {
      return new ResponseEntity<>(casePause, HttpStatus.ACCEPTED);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<Case> putCase(String id, @Valid CaseRequest body) {
    mockLogger.logEndpoint("CasesApiController", "casesByIdPut");
    String accept = request.getHeader("Accept");
    log.info("Job Received: " + body.getReference(), " with accept: " + accept);
    Case modelCase = mapperFacade.map(body, Case.class);
    modelCase.setId(UUID.fromString(id));
    caseManager.addCase(modelCase);
    log.info("POST CaseId: {} : ADDED", id);
    log.info("POST CaseId: {} : 'Ghost FWMT' Investigation", id);
    log.info("============================================");
    getCase(id, null);
    log.info("============================================");
    log.info("============================================");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CeCase> patchCeCaseDetails(String id, @Valid CeCasePatchRequest request) {
    mockLogger.logEndpoint("CasesApiController", "patchCeCaseDetails");
    log.info("Update Received: " + id);
    log.info("PATCH CaseId: {} : Updated", id);
    log.info("POST CaseId: {} : 'Ghost FWMT' Investigation", id);
    log.info("============================================");
    getCase(id, null);
    log.info("============================================");
    log.info("============================================");
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> closeCase(String id) {
    return null;
  }

  @Override
  public ResponseEntity<Void> reopenCase(String id, @Valid ReopenCaseRequest request) {
    return null;
  }

  @Override
  public ResponseEntity<CasePause> putCasePause(String id, @Valid CasePauseRequest body) {
    mockLogger.logEndpoint("CasesApiController", "casesByIdPausePut");
    String accept = request.getHeader("Accept");
    log.info("Job paused: " + id, " with accept: " + accept);
    CasePause casePause = mapperFacade.map(body, CasePause.class);
    pauseManager.addPause(id, casePause);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> deleteCasePause(String id) {
    String accept = request.getHeader("Accept");
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
