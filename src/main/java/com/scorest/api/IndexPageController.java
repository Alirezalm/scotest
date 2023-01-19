package com.scorest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexPageController {
    @GetMapping("/")
    public IndexPage indexPage() {
        return new IndexPage("online");
    }
}
