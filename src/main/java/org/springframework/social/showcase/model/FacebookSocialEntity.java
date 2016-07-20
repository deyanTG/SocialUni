package org.springframework.social.showcase.model;

import java.util.Date;

import org.springframework.social.facebook.api.Post;

public class FacebookSocialEntity extends SocialEntity {

	private SocialEntityType socialEntityType = SocialEntityType.FACEBOOK;
	private Post entity;
	// Couldn't find easy way for getting user's profile picture so we do this
	// ugly thing
	private String profilePictureUrl;

	public FacebookSocialEntity(Post p) {
		this.entity = p;
		this.socialEntityType = SocialEntityType.FACEBOOK;
	}

	@Override
	public SocialEntityType getSocialEntityType() {
		return socialEntityType;
	}

	@Override
	public Date getCreationTime() {
		return entity.getCreatedTime();
	}

	@Override
	public String getCreatorName() {
		return entity.getFrom().getName();
	}

	@Override
	public String getLink() {
		return entity.getLink();
	}

	@Override
	public String getText() {
		return entity.getMessage();
	}

	@Override
	public String getType() {
		return entity.getType().toString();
	}

	@Override
	public String getProfilePicture() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	@Override
	public String getProfileUrl() {
		return String.format("https://www.facebook.com/profile.php?id=%s", entity.getFrom().getId());
	}

	@Override
	public String getStory() {
		return entity.getStory();
	}

	@Override
	public String getPicture() {
		// TODO Auto-generated method stub
		return null;
	}

}
