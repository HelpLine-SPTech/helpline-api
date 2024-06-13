package com.helpline.helplineapi.services.post;

import com.helpline.helplineapi.data.contract.post.getByUserId.GetPostsByUserIdRequest;
import com.helpline.helplineapi.data.contract.post.getByUserId.GetPostsByUserIdResponse;
import com.helpline.helplineapi.mappers.PostMapper;
import com.helpline.helplineapi.repositories.PostRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPostsByUserIdService extends BaseService<GetPostsByUserIdRequest, GetPostsByUserIdResponse> {

    @Autowired
    private PostRepository postRepository;

    @Override
    protected GetPostsByUserIdResponse processService(GetPostsByUserIdRequest request) {
        var response = new GetPostsByUserIdResponse();

        var posts = postRepository.findByUserId(request.getUserId());
        response.setPosts(PostMapper.toDto(posts, request.getRequesterUserId()));

        return response;
    }

    @Override
    protected GetPostsByUserIdResponse validateService(GetPostsByUserIdRequest getPostsByUserIdRequest) {
        return new GetPostsByUserIdResponse();
    }
}
