package com.example.demo.feed.service;

import com.example.demo.feed.domain.FeedRepository;
import com.example.demo.friend.domain.FriendShipRepository;
import com.example.demo.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class FeedCreateTest {

    @Mock
    FeedRepository feedRepository;

    @Mock
    FriendShipRepository friendShipRepository;

    @Mock
    UserService userService;

    @DisplayName("게시글 만들기 1. 잘못된 글인 경우 ")
    @Test
    void test1(){
        FeedService feedService
                = new FeedService(
                        feedRepository,
                friendShipRepository,
                userService
        );




    }

}