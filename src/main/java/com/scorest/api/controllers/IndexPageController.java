package com.scorest.api.controllers;

import com.scorest.api.entities.IndexPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexPageController {
    @GetMapping("/")
    public IndexPage indexPage() {
        return new IndexPage("online");
    }
}
