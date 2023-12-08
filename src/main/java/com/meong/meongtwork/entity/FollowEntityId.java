package com.meong.meongtwork.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowEntityId implements Serializable {
	private Long following;
	private Long follower;
}
