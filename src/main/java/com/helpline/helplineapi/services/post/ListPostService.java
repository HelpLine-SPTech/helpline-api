package com.helpline.helplineapi.services.post;

import com.helpline.helplineapi.data.contract.post.list.ListPostRequest;
import com.helpline.helplineapi.data.contract.post.list.ListPostResponse;
import com.helpline.helplineapi.mappers.PostMapper;
import com.helpline.helplineapi.repositories.PostRepository;
import com.helpline.helplineapi.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ListPostService extends BaseService<ListPostRequest, ListPostResponse> {

    @Autowired
    private PostRepository postRepository;

    @Override
    protected ListPostResponse processService(ListPostRequest listPostRequest) {
        var response = new ListPostResponse();

        var posts = postRepository.findAll(Sort.by("addedAt"));

        response.setPosts(PostMapper.toDto(posts));

        return response;
    }

    @Override
    protected ListPostResponse validateService(ListPostRequest listPostRequest) {
        return new ListPostResponse();
    }
}
