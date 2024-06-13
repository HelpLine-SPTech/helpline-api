package com.helpline.helplineapi.services.post;

import com.helpline.helplineapi.data.contract.post.like.LikePostRequest;
import com.helpline.helplineapi.data.contract.post.like.LikePostResponse;
import com.helpline.helplineapi.entities.post.LikeEntity;
import com.helpline.helplineapi.entities.post.PostEntity;
import com.helpline.helplineapi.entities.user.BaseUserEntity;
import com.helpline.helplineapi.enums.ErrorCodeEnum;
import com.helpline.helplineapi.mappers.LikeMapper;
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
        var response = new LikePostResponse();

        if(isPostLiked(request)) {
            unlikePost();
        } else {
            likePost(request, response);
        }

        return response;
    }

    private boolean isPostLiked(LikePostRequest request) {
        return post.getLikes().stream().anyMatch(like -> like.getUser().getId() == request.getUserId());
    }

    private void unlikePost() {
        likeRepository.deleteByUserIdAndPostId(user.getId(), post.getId());
    }

    private void likePost(LikePostRequest request, LikePostResponse response) {
        var like = new LikeEntity();
        like.setUser(user);
        like.setPost(post);
        var saved = likeRepository.save(like);
        response.setLike(LikeMapper.toDto(saved));
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
