package com.media.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.media.models.Post;
import com.media.models.User;
import com.media.repository.PostRepository;
import com.media.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Override
	public Post createNewPost(Post postDto, Integer userId) throws Exception {

		Post newPost = new Post(); // newPost.setId(post.getId());
		newPost.setCaption(postDto.getCaption());
		newPost.setImage(postDto.getImage());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(postDto.getVideo());
		newPost.setUser(userService.findUserById(userId));

		Post post = postRepository.save(newPost);
		return post;
	}

	@Override
	public List<Post> findAllPosts() {

		List<Post> posts = postRepository.findAll();

		return posts;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {

		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (post.getUser().getId() != user.getId()) {
			throw new Exception("You cannot delete another user post");
		}

		postRepository.delete(post);
		return "Post deleted successfully";
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {

		Optional<Post> opt = postRepository.findById(postId);

		if (opt.isEmpty())
			throw new Exception("Post not found with id " + postId);

		return opt.get();
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {

		List<Post> userPosts = postRepository.findPostByUserId(userId);

		return userPosts;
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {

		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		} else {
			user.getSavedPost().add(post);
		}
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {

		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		} else {
			post.getLiked().add(user);
		}
		return postRepository.save(post);
	}

}
