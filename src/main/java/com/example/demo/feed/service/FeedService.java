package com.example.demo.feed.service;


import com.example.demo.feed.domain.*;
import com.example.demo.friend.domain.*;
import com.example.demo.friend.service.FriendShipService;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserRepository;
import com.example.demo.user.domain.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;
    private final UserRepository userRepository;
    private final FriendShipRepository friendShipRepository;

    @Transactional
    public void deleteFeedByFeedId(Long id) {
        Feed feed = feedRepository.findFeedByIdAndDeleted(id, false);
        feed.delete();
    }

    public Page<Feed> findMyTimeLine(int page, int pageSize) { // 친구 문제
        User user = findUser();
        Identfication id = Identfication.create(user.getId());
        Name name = Name.create(user.getUserBasicInfo().getUsername());
        UserInfo userInfo = UserInfo.create(id, name);

        WriterId[] friendIds
                = friendShipRepository
                .findAllByUserInfo(userInfo)
                .stream()
                .map(Friendship::getFriendId)
                .map(WriterId::create)
                .collect(Collectors.toList())
                .toArray(WriterId[]::new);


        Page<Feed> feeds = feedRepository.findAll(PageRequest.of(page, pageSize));



        return feeds;
    }

    public Feed findFeedDetailByFeedId(Long id) {
        Feed feed = feedRepository.findFeedByIdAndDeleted(id, false);
        return feed;
    }



    public void makeFeedByContents(String InsertedContent) {
//        User user = findUser();
        Username username = null;
        User user = null;

        WriterId id = WriterId.create(user.getId());
        WriterName wrtName = WriterName.create(user.getUserBasicInfo().getUsername());
        Writer writer = Writer.create(id, wrtName);
        Content content = Content.create(InsertedContent);
        Feed feed = Feed.create(writer, content);

        feedRepository.save(feed);
    }

    public User findUser(){
        Authentication authentication = findAuthentication();
        Username username = Username.create(authentication.getName());
        return userRepository.findByUserBasicInfo_Username(username);

    }

    public Authentication findAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @Transactional
    public void likeFeedByFeedID(Long id) {
        Feed feed = feedRepository.findFeedByIdAndDeleted(id, false);
        feed.like();
    }
}
