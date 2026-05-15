package uk.gov.ons.census.fwmt.tm.mock.comet.api.managers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.ons.census.fwmt.common.data.tm.Case;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CaseManager {
  private Map<String, Case> caseDirectory = new ConcurrentHashMap<>();

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

  public Case getCase(String id) {
    return caseDirectory.get(id);
  }

  public void reset() {
    caseDirectory.clear();
  }
}
