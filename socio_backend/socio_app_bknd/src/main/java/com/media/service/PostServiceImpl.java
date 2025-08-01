package com.media.service;

import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
	/*
	 * 
	 * @Autowired PostRepository postRepository;
	 * 
	 * @Autowired UserService userService;
	 * 
	 * @Autowired UserRepository userRepository;
	 * 
	 * @Override public Post createNewPost(Post post, Integer userId) throws
	 * Exception {
	 * 
	 * Post newPost = new Post(); // newPost.setId(post.getId());
	 * newPost.setCaption(post.getCaption()); newPost.setImage(post.getImage());
	 * newPost.setCreatedAt(LocalDateTime.now()); newPost.setVideo(post.getVideo());
	 * newPost.setUser(userService.findUserById(userId));
	 * 
	 * return postRepository.save(newPost); }
	 * 
	 * @Override public String deletePost(Integer postId, Integer userId) throws
	 * Exception {
	 * 
	 * Post post = findPostById(postId); User user =
	 * userService.findUserById(userId);
	 * 
	 * if (post.getUser().getId() != user.getId()) { throw new
	 * Exception("You cannot delete another user post"); }
	 * 
	 * postRepository.delete(post); return "Post deleted successfully"; }
	 * 
	 * @Override public List<Post> findPostByUserId(Integer userId) {
	 * 
	 * return postRepository.findPostByUserId(userId); }
	 * 
	 * @Override public Post findPostById(Integer postId) throws Exception {
	 * 
	 * Optional<Post> opt = postRepository.findById(postId);
	 * 
	 * if (opt.isEmpty()) throw new Exception("Post not found with id " + postId);
	 * 
	 * return opt.get(); }
	 * 
	 * @Override public List<Post> findAllPosts() {
	 * 
	 * return postRepository.findAll(); }
	 * 
	 * @Override public Post savedPost(Integer postId, Integer userId) throws
	 * Exception {
	 * 
	 * Post post = findPostById(postId); User user =
	 * userService.findUserById(userId);
	 * 
	 * if (user.getSavedPost().contains(post)) { user.getSavedPost().remove(post); }
	 * else { user.getSavedPost().add(post); } userRepository.save(user); return
	 * post; }
	 * 
	 * @Override public Post likePost(Integer postId, Integer userId) throws
	 * Exception {
	 * 
	 * Post post = findPostById(postId); User user =
	 * userService.findUserById(userId);
	 * 
	 * if (post.getLiked().contains(user)) { post.getLiked().remove(user); } else {
	 * post.getLiked().add(user); } return postRepository.save(post); }
	 * 
	 */}
