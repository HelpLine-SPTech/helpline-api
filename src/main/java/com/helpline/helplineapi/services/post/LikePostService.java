package com.helpline.helplineapi.services.post;

import com.helpline.helplineapi.data.contract.post.like.LikePostRequest;
import com.helpline.helplineapi.data.contract.post.like.LikePostResponse;
import com.helpline.helplineapi.entities.post.LikeEntity;
import com.helpline.helplineapi.entities.post.PostEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.repositories.BaseUserRepository;
import com.helpline.helplineapi.repositories.LikeRepository;
import com.helpline.helplineapi.repositories.PostRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikePostService extends BaseService<LikePostRequest, LikePostResponse> {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BaseUserRepository userRepository;

    private BaseUserEntity user;

    private PostEntity post;

    @Override
    protected LikePostResponse processService(LikePostRequest request) {

        var like = new LikeEntity();
        like.setPost(post);
        like.setUser(user);
        likeRepository.save(like);

        return new LikePostResponse();
    }

    @Override
    protected LikePostResponse validateService(LikePostRequest request) {
        var response = new LikePostResponse();

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
