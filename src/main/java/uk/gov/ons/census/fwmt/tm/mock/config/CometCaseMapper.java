package uk.gov.ons.census.fwmt.tm.mock.config;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.gov.ons.census.fwmt.common.data.tm.Case;
import uk.gov.ons.census.fwmt.common.data.tm.CasePause;
import uk.gov.ons.census.fwmt.common.data.tm.CasePauseRequest;
import uk.gov.ons.census.fwmt.common.data.tm.CaseRequest;

@Mapper(componentModel = "spring")
public interface CometCaseMapper {

  @Mapping(target = "id", ignore = true)
  Case toCase(CaseRequest request);

  @Mapping(target = "until", ignore = true)
  @Mapping(target = "_links", ignore = true)
  CasePause toCasePause(CasePauseRequest request);
}
