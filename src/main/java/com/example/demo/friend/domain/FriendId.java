package com.example.demo.friend.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter(AccessLevel.PACKAGE)
@Embeddable
public class FriendId {

    @Column(name = "Friend_id")
    private Long friendId;

    public FriendId(Long friendId) {
        this.friendId = friendId;
    }
}
