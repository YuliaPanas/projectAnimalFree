package com.example.projectAnimalFree.service;

import com.example.projectAnimalFree.dto.Mail;
import com.example.projectAnimalFree.dto.PostDto;

import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.entity.Post;
import com.example.projectAnimalFree.entity.PostFiles;
import com.example.projectAnimalFree.entity.PostLike;
import com.example.projectAnimalFree.entity.User;
import com.example.projectAnimalFree.enums.Roles;

import com.example.projectAnimalFree.mapper.PostMapper;

import com.example.projectAnimalFree.mapper.UserMapper;
import com.example.projectAnimalFree.repository.*;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends AbstractService implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;
    private final FilesRepository filesRepository;
    private final PostFilesRepository postFilesRepository;
    private final EmailSenderService emailSenderService;


    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository,
                           PostLikeRepository postLikeRepository, FilesRepository filesRepository, PostFilesRepository postFilesRepository, EmailSenderService emailSenderService) {
        super(userRepository);
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postLikeRepository = postLikeRepository;
        this.filesRepository = filesRepository;
        this.postFilesRepository = postFilesRepository;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public PostDto createPost(PostDto postDto) throws MessagingException {

        Post post = PostMapper.toEntity(postDto);
        UserDto authUser = getAuthUser();
        post.setUserId(authUser.getId());
        Post save = postRepository.save(post);
        if (Objects.nonNull(postDto.getFileId())) {
            PostFiles files = new PostFiles();
            files.setFile(filesRepository.getById(postDto.getFileId()));
            files.setPostId(save.getId());
            postFilesRepository.save(files);
        }
//        Mail mail = new Mail();
//        mail.setSubject("subject");
//        mail.setMailTo("y.panas.3@gmail.com");
//        mail.setFrom("y.panas.3@gmail.com");
//        emailSenderService.sendEmail(mail);
        return PostMapper.toDto(save);

//        Post post = PostMapper.toEntity(postDto);
//        UserDto authUser = getAuthUser();
//        post.setUserId(authUser.getId());
//        Post save = postRepository.save(post);
//        return PostMapper.toDto(save);

    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post -> {
                    Long userId = post.getUserId();
                    User user = userRepository.getById(userId);

                    PostDto postDto = PostMapper.toDto(post, UserMapper.toDto(user));
                    postDto.setLikeCount(postLikeRepository.countByPost_IdAndLikedIsTrue(post.getId()));
                    postDto.setCanRemove(canRemovePost(post));

                    List<PostFiles> allByPostId = postFilesRepository.findAllByPostId(postDto.getId());
                    if (!allByPostId.isEmpty()){
                        PostFiles files = allByPostId.get(0);
                        Long id = files.getFile().getId();
                        postDto.setFileUrl("/file/url/" + id);
                    }
                    postDto.setLikeCount(postLikeRepository.countByPost_IdAndLikedIsTrue(post.getId()));

                    return postDto;
                })
                .collect(Collectors.toList());

    }

    @Override
    public PostDto getByPostId(Long postId) {
        Post post = postRepository.getById(postId);

        Long userId = post.getUserId();
        User user = userRepository.getById(userId);
        UserDto userDto = UserMapper.toDto(user);
        PostDto postDto = PostMapper.toDto(post, userDto);
        postDto.setLikeCount(postLikeRepository.countByPost_IdAndLikedIsTrue(postId));
//        if ()
//        postDto.setFileUrl("/file/url/" + postId);
        return postDto;
    }

    @Override
    public PostDto getById(Long postId) {

        Post post = postRepository.getById(postId);

        Long userId = post.getUserId();
        User user = userRepository.getById(userId);
        UserDto userDto = UserMapper.toDto(user);
        PostDto postDto = PostMapper.toDto(post, userDto);

        List<PostFiles> allByPostId = postFilesRepository.findAllByPostId(postId);
        PostFiles files = allByPostId.get(0);
        Long id = files.getFile().getId();
        postDto.setFileUrl("/file/url/" + id);
        postDto.setLikeCount(postLikeRepository.countByPost_IdAndLikedIsTrue(post.getId()));
        return postDto;

//        Post post = postRepository.getById(postId);
//
//        Long userId = post.getUserId();
//        User user = userRepository.getById(userId);
//        UserDto userDto = UserMapper.toDto(user);
//        PostDto postDto = PostMapper.toDto(post, userDto);
//        postDto.setLikeCount(postLikeRepository.countByPost_IdAndLikedIsTrue(post.getId()));
//        return postDto;
    }

    @Override
    public void deletePost(Long id) throws AccessDeniedException {
        Post post = postRepository.getById(id);
        UserDto userDto = getAuthUser();
        Set<Roles> roles = userDto.getRoles();
        if (roles.contains(Roles.ROLE_ADMIN)) {
            post.setRemoved(true);
            postRepository.save(post);
        } else throw new AccessDeniedException("You can`t delete this person");

    }

    private boolean canRemovePost(Post post) {
        boolean canRemove;
        try {
            UserDto currentUser = getAuthUser();
            canRemove = post.getUserId().equals(currentUser.getId()) || currentUser.getRoles().contains(Roles.ROLE_ADMIN);
        } catch (Exception e) {
            canRemove = false;
        }

        return canRemove;
    }


    @Override
    public void doLikeOperation(long postId) {
        UserDto userDto = getAuthUser();

        Optional<PostLike> postLikeOptional = postLikeRepository.findByUser_IdAndPost_Id(userDto.getId(), postId);

        postLikeOptional.ifPresent(postLike -> {
            postLike.setLiked(!postLike.isLiked());
            postLikeRepository.save(postLike);
        });

        if (postLikeOptional.isEmpty()) {
            PostLike postLike = new PostLike();
            postLike.setPost(postRepository.getById(postId));
            postLike.setUser(userRepository.getById(userDto.getId()));
            postLikeRepository.save(postLike);
        }

    }


}
