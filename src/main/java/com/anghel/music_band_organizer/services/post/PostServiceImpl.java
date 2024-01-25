package com.anghel.music_band_organizer.services.post;

import com.anghel.music_band_organizer.models.dtos.PostDTO;
import com.anghel.music_band_organizer.models.entities.Band;
import com.anghel.music_band_organizer.models.entities.Post;
import com.anghel.music_band_organizer.repository.PostRepository;
import com.anghel.music_band_organizer.services.band.BandService;
import com.anghel.music_band_organizer.services.open_ai.OpenAI;
import com.anghel.music_band_organizer.services.user.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final PostServiceValidation postServiceValidation;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final BandService bandService;
    private final OpenAI openAI;

    public PostServiceImpl(PostRepository postRepository, PostServiceValidation postServiceValidation, ModelMapper modelMapper, UserService userService, BandService bandService, OpenAI openAI) {
        this.postRepository = postRepository;
        this.postServiceValidation = postServiceValidation;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.bandService = bandService;
        this.openAI = openAI;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO, Long userId, Long bandId) {
        Band band = bandService.getValidBandForCreateRehearsal(bandId, "createRehearsal");
        userService.createRehearsal(userId, "createRehearsal", band);

        Post post = modelMapper.map(postDTO, Post.class);
        if (Boolean.TRUE.equals(post.getUseOpenAIForDescription()) || postDTO.getPostDescription() == null) {
            post.setPostDescription(openAI.chatGPT("Please generate a description for my social media music post release. Please don't use any quotation marks in your response. My band is called " + band.getBandName() + ", and the title for the post I decided on is " + post.getPostTitle() + ". Also, my music recording is titled " + post.getPostRecordingTitle() + ". Please answer using up to 100 tokens."));
        }
        post.setPostBand(band);

        Post savedPost = postRepository.save(post);
        log.info("Post with id {} inserted in db. Method: {}.", savedPost.getId(), "createRehearsal");

        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Transactional
    @Override
    public List<PostDTO> getAllPosts() {
       List<Post> postList = postRepository.findAll();
       log.info("Post list retrieved. Method: {}.", "getAllBands");

       return postList.stream()
               .map(post -> modelMapper.map(post, PostDTO.class))
               .toList();
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Post post = postServiceValidation.getValidPost(postId, "getPostById");

        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public String deletePostById(Long postId, Long userId, Long bandId) {
        Band band = bandService.getValidBandForDeletePost(bandId, "deletePostById");
        userService.checkUserInBandForDeletePost(userId, band, "deletePostById");
        postServiceValidation.getValidPost(postId, "deletePostById");

        postRepository.deleteById(postId);
        log.info("Post with id {} deleted. Method: {}", postId, "deletePostById");

        return "Post with id " + postId + " deleted.";
    }
}
