package org.springframework.social.showcase.dto;

import java.util.Date;

import org.springframework.social.facebook.api.Post;
import org.springframework.social.showcase.model.SocialEntityType;
import org.springframework.social.twitter.api.Tweet;

public class TimelineOutput {

	private final Object entity;
	private final SocialEntityType socialEntityType;
	private final Date creationTime;
	private final String creatorName;
	private final String link;
	private final String text;

	public TimelineOutput(Object entity, SocialEntityType type) {
		this.entity = entity;
		this.socialEntityType = type;

		this.creationTime = initializeCreationDate(this.entity);
		this.creatorName = initializeCreatorName(this.entity);
		this.text = initializeText(this.entity);
		this.link = initializeLink(this.entity);
	}

	public SocialEntityType getSocialEntityType() {
		return socialEntityType;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public String getLink() {
		return link;
	}

	public String getText() {
		return text;
	}

	private Date initializeCreationDate(Object o) {
		if (o instanceof Post) {
			return ((Post) o).getCreatedTime();
		}
		if (o instanceof Tweet) {
			return ((Tweet) o).getCreatedAt();
		}
		return null;
	}

	private String initializeCreatorName(Object o) {
		if (o instanceof Post) {
			return ((Post) o).getFrom().getName();
		}
		if (o instanceof Tweet) {
			return ((Tweet) o).getFromUser();
		}
		return null;
	}

	private String initializeText(Object o) {
		if (o instanceof Post) {
			return ((Post) o).getMessage();
		}
		if (o instanceof Tweet) {
			return ((Tweet) o).getText();
		}
		return null;
	}

	private String initializeLink(Object o) {
		if (o instanceof Post) {
			return ((Post) o).getLink();
		}
		if (o instanceof Tweet) {
			return ((Tweet) o).getSource();
		}
		return null;
	}

}
