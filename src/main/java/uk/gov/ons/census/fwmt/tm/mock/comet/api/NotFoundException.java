package uk.gov.ons.census.fwmt.tm.mock.comet.api;

public class NotFoundException extends ApiException {
  private int code;

  public NotFoundException(int code, String msg) {
    super(code, msg);
    this.code = code;
  }
}
