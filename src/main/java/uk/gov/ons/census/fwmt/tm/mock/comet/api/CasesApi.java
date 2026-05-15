package uk.gov.ons.census.fwmt.tm.mock.comet.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.gov.ons.census.fwmt.common.data.tm.*;

import javax.validation.Valid;
import java.util.Optional;

@Api(value = "cases")
public interface CasesApi {

  // GET Case
  @ApiOperation(value = "Get a Case.", nickname = "getCase", response = Case.class, authorizations = {
      @Authorization(value = "Client Credentials", scopes = {@AuthorizationScope(scope = "", description = "")}),
      @Authorization(value = "Implicit", scopes = {@AuthorizationScope(scope = "", description = "")})
  }, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Case returned.", response = Case.class),
      @ApiResponse(code = 404, message = "The Case does not exist.")})
  @RequestMapping(value = "/cases/{id}", produces = {"application/json"}, method = RequestMethod.GET)
  ResponseEntity<Case> getCase(
      @ApiParam(value = "The Case identifier.", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Additional objects to include.",
          allowableValues = "CaseOutcomes, OutputArea, AccessInfo, DistributedToOfficer, AllocatedTo, all")
      @PathVariable("include") Optional <String> include);

  // GET Cases
  @ApiOperation(value = "Get a Case.", nickname = "getCases", response = FetchResponse.class, authorizations = {
      @Authorization(value = "Client Credentials", scopes = {@AuthorizationScope(scope = "", description = "")}),
      @Authorization(value = "Implicit", scopes = {@AuthorizationScope(scope = "", description = "")})
  }, tags = {})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = FetchResponse.class)})
  @RequestMapping(value = "/cases", produces = {"application/json"}, method = RequestMethod.GET)
  ResponseEntity<FetchResponse> getCases(
      @ApiParam(value = "The Case identifier.",
          allowableValues = "CoordCode, GeneralSearch, RequiredOfficer, CaseState")
      @PathVariable("filter") String filter,
      @ApiParam(value = "Additional objects to include.",
          allowableValues = "CaseOutcomes, OutputArea, AccessInfo, DistributedToOfficer, AllocatedTo, all")
      @PathVariable("include") String include,
      @ApiParam(value = "Zero-based page number.") @PathVariable("pageNo") int pageNo,
      @ApiParam(value = "Maximum results per page.") @PathVariable("pageSize") int pageSize,
      @ApiParam(value = "Ordering of returned results.", allowableValues = "Reference, Address, OutputArea, VisitCount")
      @PathVariable("order") String order);

  // GET Case Pause
  @ApiOperation(value = "Get the pause for a Case.", nickname = "getCasePause", notes = "", response = CasePause.class, authorizations = {
      @Authorization(value = "Client Credentials", scopes = {@AuthorizationScope(scope = "", description = "")}),
      @Authorization(value = "Implicit", scopes = {@AuthorizationScope(scope = "", description = "")})
  }, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Pause returned.", response = CasePause.class),
      @ApiResponse(code = 404, message = "The Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/pause", produces = {"application/json"}, method = RequestMethod.GET)
  ResponseEntity<CasePause> getCasePause(
      @ApiParam(value = "The Case identifier.", required = true) @PathVariable("id") String id);

  // PUT Case
  @ApiOperation(value = "Create or update a Case.", nickname = "putCase", response = Case.class, authorizations = {
      @Authorization(value = "Client Credentials", scopes = {@AuthorizationScope(scope = "", description = "")}),
      @Authorization(value = "Implicit", scopes = {@AuthorizationScope(scope = "", description = "")})}, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Case updated.", response = Case.class),
      @ApiResponse(code = 201, message = "Case created.", response = Case.class),
      @ApiResponse(code = 400, message = "Case has missing/invalid values.")})
  @RequestMapping(value = "/cases/{id}",
      produces = {"application/json"},
      consumes = {"application/json-patch+json", "application/json", "text/json", "application/_*+json"},
      method = RequestMethod.PUT)
  ResponseEntity<Case> putCase(
      @ApiParam(value = "The Case identifier.", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Case.") @Valid @RequestBody CaseRequest request);

  // PATCH Case CE Details
  @ApiOperation(value = "Patch CE Case details.", nickname = "patchCeCaseDetails", response = CeCase.class, authorizations = {
      @Authorization(value = "Client Credentials", scopes = {@AuthorizationScope(scope = "", description = "")}),
      @Authorization(value = "Implicit", scopes = {@AuthorizationScope(scope = "", description = "")})}, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success.", response = CeCase.class),
      @ApiResponse(code = 400, message = "Bad Request."),
      @ApiResponse(code = 404, message = "Not Found.")})
  @RequestMapping(value = "/cases/{id}/cedetails",
      produces = {"application/json"},
      consumes = {"application/json-patch+json", "application/json", "text/json", "application/_*+json"},
      method = RequestMethod.PATCH)
  ResponseEntity<CeCase> patchCeCaseDetails(
      @ApiParam(value = "The Case identifier.", required = true) @PathVariable("id") String id,
      @ApiParam(value = "The Patch request.") @Valid @RequestBody CeCasePatchRequest request);

  // Close Case
  @ApiOperation(value = "Close a CE or CCS case.", nickname = "closeCase", authorizations = {
      @Authorization(value = "Client Credentials", scopes = {@AuthorizationScope(scope = "", description = "")}),
      @Authorization(value = "Implicit", scopes = {@AuthorizationScope(scope = "", description = "")})}, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Case closure requested."),
      @ApiResponse(code = 400, message = "Case has missing/invalid values."),
      @ApiResponse(code = 404, message = "Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/close",
      method = RequestMethod.POST)
  ResponseEntity<Void> closeCase(
      @ApiParam(value = "The Case identifier.", required = true) @PathVariable("id") String id);

  // Reopen Case
  @ApiOperation(value = "Reopen a CE or CCS case.", nickname = "reopenCase", authorizations = {
      @Authorization(value = "Client Credentials", scopes = {@AuthorizationScope(scope = "", description = "")}),
      @Authorization(value = "Implicit", scopes = {@AuthorizationScope(scope = "", description = "")})}, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Case re-opened."),
      @ApiResponse(code = 400, message = "Request has missing/invalid values."),
      @ApiResponse(code = 404, message = "Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/reopen",
      consumes = {"application/json-patch+json", "application/json", "text/json", "application/_*+json"},
      method = RequestMethod.POST)
  ResponseEntity<Void> reopenCase(
      @ApiParam(value = "The Case identifier.", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Re-open Case Request.") @Valid @RequestBody ReopenCaseRequest request);

  // PUT to Create or Update Case Pause
  @ApiOperation(value = "Create or update a pause on a Case.", nickname = "putCasePause", notes = "", response = CasePause.class, authorizations = {
      @Authorization(value = "Client Credentials", scopes = {@AuthorizationScope(scope = "", description = "")}),
      @Authorization(value = "Implicit", scopes = {@AuthorizationScope(scope = "", description = "")})
  }, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Pause created or updated.", response = CasePause.class),
      @ApiResponse(code = 404, message = "The Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/pause",
      produces = {"application/json"},
      consumes = {"application/json-patch+json", "application/json", "text/json", "application/_*+json"},
      method = RequestMethod.PUT)
  ResponseEntity<CasePause> putCasePause(
      @ApiParam(value = "The Case identifier.", required = true) @PathVariable("id") String id,
      @ApiParam(value = "Pause to apply to the Case.") @Valid @RequestBody CasePauseRequest pauseRequest);

  // DELETE Case Pause
  @ApiOperation(value = "Remove the pause from a Case.", nickname = "deleteCasePause", notes = "", authorizations = {
      @Authorization(value = "Client Credentials", scopes = {@AuthorizationScope(scope = "", description = "")}),
      @Authorization(value = "Implicit", scopes = {@AuthorizationScope(scope = "", description = "")})
  }, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Pause removed."),
      @ApiResponse(code = 404, message = "The Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/pause", method = RequestMethod.DELETE)
  ResponseEntity<Void> deleteCasePause(
      @ApiParam(value = "The Case identifier.", required = true) @PathVariable("id") String id);
}
