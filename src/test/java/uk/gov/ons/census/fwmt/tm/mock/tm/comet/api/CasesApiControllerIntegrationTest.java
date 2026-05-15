package uk.gov.ons.census.fwmt.tm.mock.tm.comet.api;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.census.fwmt.common.data.tm.Case;
import uk.gov.ons.census.fwmt.common.data.tm.CasePause;
import uk.gov.ons.census.fwmt.common.data.tm.CasePauseRequest;
import uk.gov.ons.census.fwmt.common.data.tm.CaseRequest;
import uk.gov.ons.census.fwmt.tm.mock.comet.api.CasesApi;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class CasesApiControllerIntegrationTest {

  @Autowired
  private CasesApi casesApi;

  @Test
  public void casesByIdGetTest() {
    String id = "id_example";
    ResponseEntity<Case> responseEntity = casesApi.getCase(id, null);
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
  }

  @Test
  public void casesByIdPostTest() {
    String id = "id_example";
    CaseRequest body = new CaseRequest();
    ResponseEntity<Case> responseEntity = casesApi.putCase(id, body);
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
  }

  @Test
  public void casesByIdPauseDeleteTest() {
    String id = "id_example";
    ResponseEntity<Void> responseEntity = casesApi.deleteCasePause(id);
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
  }

  @Test
  public void casesByIdPauseGetTest() {
    String id = "id_example";
    ResponseEntity<CasePause> responseEntity = casesApi.getCasePause(id);
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
  }

  @Test
  public void casesByIdPausePutTest() {
    String id = "id_example";
    CasePauseRequest body = new CasePauseRequest();
    ResponseEntity<CasePause> responseEntity = casesApi.putCasePause(id, body);
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
  }

  @Test
  public void casesByIdPutTest() {
    String id = "id_example";
    CaseRequest body = new CaseRequest();
    ResponseEntity<Case> responseEntity = casesApi.putCase(id, body);
    assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
  }
}
