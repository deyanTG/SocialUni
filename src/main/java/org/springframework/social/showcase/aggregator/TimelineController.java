package org.springframework.social.showcase.aggregator;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.showcase.dto.TimelineListOutput;
import org.springframework.social.showcase.model.FacebookSocialEntity;
import org.springframework.social.showcase.model.SocialEntity;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class TimelineController {

	@Autowired
	private Facebook facebook;

	@Autowired
	private Twitter twitter;

	private RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/aggregator/feed", method = RequestMethod.GET)
	public String showFeed(Model model) {
		PagedList<Post> feeds = facebook.feedOperations().getFeed();
		List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
		TimelineListOutput listOutput = new TimelineListOutput(new TimelineEntityComparator());

		for (Post p : feeds) {
			SocialEntity entity = SocialEntity.getSocialEntity(p);
			FacebookSocialEntity fEntity = (FacebookSocialEntity) entity;
			String profilePicture = getUserProfilePicture(p.getFrom().getId());
			fEntity.setProfilePictureUrl(profilePicture);
			listOutput.addEntity(fEntity);
		}

		for (Tweet t : tweets) {
			SocialEntity entity = SocialEntity.getSocialEntity(t);
			listOutput.addEntity(entity);
		}

		model.addAttribute("timeline", listOutput.sort());
		return "aggregator";
	}

	private String getUserProfilePicture(String userId) {
		String url = String.format("http://graph.facebook.com/%s/picture?type=square&redirect=false", userId);
		HttpEntity<?> entity = HttpEntity.EMPTY;
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
		return ((Map) response.getBody().get("data")).get("url").toString();
	}

	private static class TimelineEntityComparator implements Comparator<SocialEntity> {

		@Override
		public int compare(SocialEntity o1, SocialEntity o2) {
			Date object1Date = o1.getCreationTime();
			Date object2Date = o2.getCreationTime();
			return object2Date.compareTo(object1Date);
		}

	}

}
