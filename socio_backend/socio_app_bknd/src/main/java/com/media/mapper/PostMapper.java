package com.media.mapper;

import com.media.dto.PostDto;
import com.media.models.Post;

public class PostMapper {

	public static PostDto mapToPostDto(Post post) {
		return new PostDto(post.getId(), post.getCaption(), post.getImage(), post.getVideo(), post.getUser(),
				post.getLiked(), post.getCreatedAt());

	}

	public static Post mapToPost(PostDto postDto) {
		return new Post(postDto.getId(), postDto.getCaption(), postDto.getImage(), postDto.getVideo(),
				postDto.getUser(), postDto.getLiked(), postDto.getCreatedAt());

	}

}
