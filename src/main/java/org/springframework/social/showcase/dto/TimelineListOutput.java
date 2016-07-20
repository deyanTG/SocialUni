package org.springframework.social.showcase.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.social.showcase.model.SocialEntity;

public class TimelineListOutput {

	private Comparator<SocialEntity> comparator;

	public TimelineListOutput(Comparator<SocialEntity> comparator) {
		super();
		this.comparator = comparator;
	}

	private List<SocialEntity> entities = new ArrayList<>();

	public List<SocialEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<SocialEntity> entities) {
		this.entities = entities;
	}

	public void addEntity(SocialEntity entity) {
		entities.add(entity);
	}

	public TimelineListOutput sort() {
		entities.sort(comparator);
		return this;
	}

}
