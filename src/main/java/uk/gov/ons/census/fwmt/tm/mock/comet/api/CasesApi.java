package uk.gov.ons.census.fwmt.tm.mock.comet.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.gov.ons.census.fwmt.common.data.tm.*;

import jakarta.validation.Valid;
import java.util.Optional;

@Tag(name = "cases")
public interface CasesApi {

  // GET Case
  @Operation(summary = "Get a Case.", operationId = "getCase")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Case returned.",
          content = @Content(schema = @Schema(implementation = Case.class))),
      @ApiResponse(responseCode = "404", description = "The Case does not exist.")})
  @RequestMapping(value = "/cases/{id}", produces = {"application/json"}, method = RequestMethod.GET)
  ResponseEntity<Case> getCase(
      @Parameter(description = "The Case identifier.", required = true) @PathVariable("id") String id,
      @Parameter(description = "Additional objects to include: CaseOutcomes, OutputArea, AccessInfo, DistributedToOfficer, AllocatedTo, all")
      @PathVariable("include") Optional<String> include);

  // GET Cases
  @Operation(summary = "Get Cases.", operationId = "getCases")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Success.",
          content = @Content(schema = @Schema(implementation = FetchResponse.class)))})
  @RequestMapping(value = "/cases", produces = {"application/json"}, method = RequestMethod.GET)
  ResponseEntity<FetchResponse> getCases(
      @Parameter(description = "Filter criteria: CoordCode, GeneralSearch, RequiredOfficer, CaseState")
      @PathVariable("filter") String filter,
      @Parameter(description = "Additional objects to include: CaseOutcomes, OutputArea, AccessInfo, DistributedToOfficer, AllocatedTo, all")
      @PathVariable("include") String include,
      @Parameter(description = "Zero-based page number.") @PathVariable("pageNo") int pageNo,
      @Parameter(description = "Maximum results per page.") @PathVariable("pageSize") int pageSize,
      @Parameter(description = "Ordering: Reference, Address, OutputArea, VisitCount")
      @PathVariable("order") String order);

  // GET Case Pause
  @Operation(summary = "Get the pause for a Case.", operationId = "getCasePause")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Pause returned.",
          content = @Content(schema = @Schema(implementation = CasePause.class))),
      @ApiResponse(responseCode = "404", description = "The Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/pause", produces = {"application/json"}, method = RequestMethod.GET)
  ResponseEntity<CasePause> getCasePause(
      @Parameter(description = "The Case identifier.", required = true) @PathVariable("id") String id);

  // PUT Case
  @Operation(summary = "Create or update a Case.", operationId = "putCase")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Case updated.",
          content = @Content(schema = @Schema(implementation = Case.class))),
      @ApiResponse(responseCode = "201", description = "Case created.",
          content = @Content(schema = @Schema(implementation = Case.class))),
      @ApiResponse(responseCode = "400", description = "Case has missing/invalid values.")})
  @RequestMapping(value = "/cases/{id}",
      produces = {"application/json"},
      consumes = {"application/json-patch+json", "application/json", "text/json", "application/_*+json"},
      method = RequestMethod.PUT)
  ResponseEntity<Case> putCase(
      @Parameter(description = "The Case identifier.", required = true) @PathVariable("id") String id,
      @Parameter(description = "Case.") @Valid @RequestBody CaseRequest request);

  // PATCH Case CE Details
  @Operation(summary = "Patch CE Case details.", operationId = "patchCeCaseDetails")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Success.",
          content = @Content(schema = @Schema(implementation = CeCase.class))),
      @ApiResponse(responseCode = "400", description = "Bad Request."),
      @ApiResponse(responseCode = "404", description = "Not Found.")})
  @RequestMapping(value = "/cases/{id}/cedetails",
      produces = {"application/json"},
      consumes = {"application/json-patch+json", "application/json", "text/json", "application/_*+json"},
      method = RequestMethod.PATCH)
  ResponseEntity<CeCase> patchCeCaseDetails(
      @Parameter(description = "The Case identifier.", required = true) @PathVariable("id") String id,
      @Parameter(description = "The Patch request.") @Valid @RequestBody CeCasePatchRequest request);

  // Close Case
  @Operation(summary = "Close a CE or CCS case.", operationId = "closeCase")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Case closure requested."),
      @ApiResponse(responseCode = "400", description = "Case has missing/invalid values."),
      @ApiResponse(responseCode = "404", description = "Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/close",
      method = RequestMethod.POST)
  ResponseEntity<Void> closeCase(
      @Parameter(description = "The Case identifier.", required = true) @PathVariable("id") String id);

  // Reopen Case
  @Operation(summary = "Reopen a CE or CCS case.", operationId = "reopenCase")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Case re-opened."),
      @ApiResponse(responseCode = "400", description = "Request has missing/invalid values."),
      @ApiResponse(responseCode = "404", description = "Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/reopen",
      consumes = {"application/json-patch+json", "application/json", "text/json", "application/_*+json"},
      method = RequestMethod.POST)
  ResponseEntity<Void> reopenCase(
      @Parameter(description = "The Case identifier.", required = true) @PathVariable("id") String id,
      @Parameter(description = "Re-open Case Request.") @Valid @RequestBody ReopenCaseRequest request);

  // PUT to Create or Update Case Pause
  @Operation(summary = "Create or update a pause on a Case.", operationId = "putCasePause")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Pause created or updated.",
          content = @Content(schema = @Schema(implementation = CasePause.class))),
      @ApiResponse(responseCode = "404", description = "The Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/pause",
      produces = {"application/json"},
      consumes = {"application/json-patch+json", "application/json", "text/json", "application/_*+json"},
      method = RequestMethod.PUT)
  ResponseEntity<CasePause> putCasePause(
      @Parameter(description = "The Case identifier.", required = true) @PathVariable("id") String id,
      @Parameter(description = "Pause to apply to the Case.") @Valid @RequestBody CasePauseRequest pauseRequest);

  // DELETE Case Pause
  @Operation(summary = "Remove the pause from a Case.", operationId = "deleteCasePause")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Pause removed."),
      @ApiResponse(responseCode = "404", description = "The Case does not exist.")})
  @RequestMapping(value = "/cases/{id}/pause", method = RequestMethod.DELETE)
  ResponseEntity<Void> deleteCasePause(
      @Parameter(description = "The Case identifier.", required = true) @PathVariable("id") String id);
}
