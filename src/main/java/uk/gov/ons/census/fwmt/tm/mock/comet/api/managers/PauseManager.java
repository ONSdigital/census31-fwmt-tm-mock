package uk.gov.ons.census.fwmt.tm.mock.comet.api.managers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.ons.census.fwmt.common.data.tm.CasePause;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PauseManager {
  private Map<String, CasePause> pauseDirectory = new ConcurrentHashMap<>();

  @Value("${customisation.logging.logFlagType.logAllMessages}")
  private boolean logAllMessages;

  public void enablePauseManager() {
    logAllMessages = true;
  }

  public void disablePauseManager() {
    logAllMessages = false;
  }

  public void addPause(String id, CasePause casePause) {
      pauseDirectory.put((id), casePause);
  }

  public CasePause getPause(String id) {
    return pauseDirectory.get(id);
  }

  public void reset() {
    pauseDirectory.clear();
  }
}
