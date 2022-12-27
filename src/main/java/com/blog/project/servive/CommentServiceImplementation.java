package com.blog.project.servive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.project.entity.Comment;
import com.blog.project.entity.Post;
import com.blog.project.repository.CommentRepository;
import com.blog.project.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	PostService postService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public void addComment(Comment theComment, int postId) {
		Post post = postService.getPost(postId);
		System.out.println("before adding comment"+post.toString());
//		List<Comment> comments = post.getComments();  
//		commentRepository.save(theComment);
		post.addComment(theComment);
//		post.setComments(comments);
//		postService.addPost(post, postId, tagService.getAllTags(postId));
		commentRepository.save(theComment);
		postRepository.save(post);
		System.out.println("after adding comment"+post.toString());
		
	}

	@Override
	public void deleteComment(Comment theComment, int postId) {
		Post post = postService.getPost(postId);
		List<Comment> comments = post.getComments();
		comments.remove(theComment);
		post.setComments(comments);
		postService.addPost(post, postId, tagService.getAllTags(postId));

	}

	@Override
	public List<Comment> getComments(int postId) {
		List<Comment> listComments = commentRepository.findAllByPostId(postId);
		return listComments;
	}

}