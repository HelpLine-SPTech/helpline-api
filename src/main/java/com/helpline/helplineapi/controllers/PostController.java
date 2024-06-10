package com.helpline.helplineapi.controllers;

import com.helpline.helplineapi.data.contract.job.JobContract;
import com.helpline.helplineapi.data.contract.post.PostContract;
import com.helpline.helplineapi.data.contract.post.comment.CommentPostRequest;
import com.helpline.helplineapi.data.contract.post.comment.CommentPostResponse;
import com.helpline.helplineapi.data.contract.post.create.CreatePostRequest;
import com.helpline.helplineapi.data.contract.post.create.CreatePostResponse;
import com.helpline.helplineapi.data.contract.post.like.LikePostRequest;
import com.helpline.helplineapi.data.contract.post.like.LikePostResponse;
import com.helpline.helplineapi.data.contract.post.list.ListPostRequest;
import com.helpline.helplineapi.data.contract.post.list.ListPostResponse;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.services.post.CommentPostService;
import com.helpline.helplineapi.services.post.CreatePostService;
import com.helpline.helplineapi.services.post.LikePostService;
import com.helpline.helplineapi.services.post.ListPostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
@SecurityRequirement(name = "helpline-api")
public class PostController {
    @Autowired
    private CreatePostService createPostService;

    @Autowired
    private ListPostService listPostService;

    @Autowired
    private LikePostService likePostService;

    @Autowired
    private CommentPostService commentPostService;

    @PostMapping
    private ResponseEntity<CreatePostResponse> create(
            @RequestAttribute("RequesterUser") BaseUserEntity requester,
            @RequestParam("content") String content,
            @RequestParam("images") List<MultipartFile> images) {
        var request = new CreatePostRequest();
        request.setContent(content);
        request.setImages(images);
        request.setUserId(requester.getId());

        return createPostService.process(request);
    }

    @GetMapping
    private ResponseEntity<ListPostResponse> list() {
        return listPostService.process(new ListPostRequest());
    }

    @PostMapping("/{id}/like")
    private ResponseEntity<LikePostResponse> like(@PathVariable(name = "id") UUID postId, @RequestAttribute("RequesterUser") BaseUserEntity requester) {
        var request = new LikePostRequest();
        request.setPostId(postId);
        request.setUserId(requester.getId());

        return likePostService.process(request);
    }

    @PostMapping("/{id}/comment")
    private ResponseEntity<CommentPostResponse> comment(
            @PathVariable(name = "id") UUID postId,
            @RequestAttribute("RequesterUser") BaseUserEntity requester,
            @RequestParam("content") String content) {
        var request = new CommentPostRequest();
        request.setContent(content);
        request.setPostId(postId);
        request.setUserId(requester.getId());

        return commentPostService.process(request);
    }
}
