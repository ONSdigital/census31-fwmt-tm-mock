package uk.gov.ons.census.fwmt.tm.mock.comet.api.managers;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.ons.census.fwmt.common.data.tm.Case;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import uk.gov.ons.census.fwmt.common.data.tm.CaseRequest;

@Component
public class CaseManager {
  private Map<String, Case> caseDirectory = new ConcurrentHashMap<>();
  private Map<String, CaseRequest> caseRequestDirectory = new ConcurrentHashMap<>();

  @Value("${customisation.logging.logFlagType.logAllMessages}")
  private boolean logAllMessages;

  public void enableCaseManager() {
    logAllMessages = true;
  }

  public void disableCaseManager() {
    logAllMessages = false;
  }

  public void addCase(Case modelCase) {
    if (logAllMessages) {
      caseDirectory.put(String.valueOf(modelCase.getId()), modelCase);
    }
  }

  public void addCaseRequest(String caseId, CaseRequest modelCaseRequest) {
    if (logAllMessages) {
      caseRequestDirectory.put(caseId, modelCaseRequest);
    }
  }

  public Case getCase(String id) {
    return caseDirectory.get(id);
  }

  public CaseRequest getCaseRequest(String id) {
    return caseRequestDirectory.get(id);
  }

  public void reset() {
    caseDirectory.clear();
  }
}
