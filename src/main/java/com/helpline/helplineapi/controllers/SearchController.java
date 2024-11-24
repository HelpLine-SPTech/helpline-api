package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.search.SearchUserRequest;
import com.helpline.helplineapi.data.contract.search.SearchUserResponse;
import com.helpline.helplineapi.services.Search.SearchUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/search")
@SecurityRequirement(name = "helpline-api")
public class SearchController {

    @Autowired
    private SearchUserService searchUserService;

    @GetMapping("/users")
    public ResponseEntity<SearchUserResponse> searchUsers(@RequestParam("name") String name) {
        var request = new SearchUserRequest();
        request.setName(name);

        return searchUserService.process(request);
    }
}
