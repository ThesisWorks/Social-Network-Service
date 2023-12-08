package com.meong.meongtwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name="follow_table")
@Getter
@Setter
public class FollowEntity {
	@EmbeddedId
	private FollowEntityId id;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("following")
	private UserEntity followingUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("follower")
	private UserEntity followerUser;

	public FollowEntity(UserEntity followingUser, UserEntity followerUser) {
		id = new FollowEntityId();
		this.followingUser = followingUser;
		this.followerUser = followerUser;
		saveId();
	}

	public FollowEntity() {

	}

	public void saveId(){
		this.id.setFollowing(followingUser.getId());
		this.id.setFollower(followerUser.getId());
	}
}
