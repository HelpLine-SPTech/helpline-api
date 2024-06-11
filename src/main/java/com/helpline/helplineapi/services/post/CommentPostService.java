package com.helpline.helplineapi.services.post;

import com.helpline.helplineapi.data.contract.post.comment.CommentPostRequest;
import com.helpline.helplineapi.data.contract.post.comment.CommentPostResponse;
import com.helpline.helplineapi.data.contract.post.like.LikePostResponse;
import com.helpline.helplineapi.entities.post.CommentEntity;
import com.helpline.helplineapi.entities.post.PostEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.CommentMapper;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.CommentRepository;
import com.helpline.helplineapi.repositories.PostRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentPostService extends BaseService<CommentPostRequest, CommentPostResponse> {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BaseUserRepository userRepository;

    private BaseUserEntity user;

    private PostEntity post;

    @Override
    protected CommentPostResponse processService(CommentPostRequest request) {
        var response = new CommentPostResponse();

        var comment = new CommentEntity();
        comment.setContent(request.getContent());
        comment.setPost(post);
        comment.setUser(user);

        var saved = commentRepository.save(comment);
        response.setComment(CommentMapper.toDto(saved));

        return response;
    }

    @Override
    protected CommentPostResponse validateService(CommentPostRequest request) {
        var response = new CommentPostResponse();

        var userOpt = userRepository.findById(request.getUserId());
        if(userOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }

        this.user = userOpt.get();

        var postOpt = postRepository.findById(request.getPostId());
        if(postOpt.isEmpty()) {
            response.addError(ErrorCodeEnum.NOT_FOUND_ERROR);
            return response;
        }

        this.post = postOpt.get();

        return response;
    }
}
