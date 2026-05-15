package uk.gov.ons.census.fwmt.tm.mock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger/v1/")
public class SwaggerMockController {
  @GetMapping("swagger.json")
  public String swagger() {
    return "";
  }
}
