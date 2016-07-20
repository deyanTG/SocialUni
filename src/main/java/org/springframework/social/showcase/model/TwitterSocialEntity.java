package org.springframework.social.showcase.model;

import java.util.Date;

import org.springframework.social.twitter.api.Tweet;

public class TwitterSocialEntity extends SocialEntity {

	private Tweet entity;
	private SocialEntityType socialEntityType = SocialEntityType.TWITTER;

	public TwitterSocialEntity(Tweet t) {
		this.entity = t;
		socialEntityType = SocialEntityType.TWITTER;
	}

	@Override
	public SocialEntityType getSocialEntityType() {
		return socialEntityType;
	}

	@Override
	public Date getCreationTime() {
		return entity.getCreatedAt();
	}

	@Override
	public String getCreatorName() {
		return entity.getFromUser();
	}

	@Override
	public String getLink() {
		return entity.getSource();
	}

	@Override
	public String getText() {
		return entity.getText();
	}

	@Override
	public String getType() {
		return "Tweet";
	}

	@Override
	public String getProfilePicture() {
		return entity.getProfileImageUrl();
	}

}
