//package org.springframework.social.showcase.component;
//
//import java.net.URI;
//
//import org.springframework.social.facebook.api.ImageType;
//import org.springframework.social.facebook.api.impl.FacebookTemplate;
//import org.springframework.social.support.URIBuilder;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.databind.JsonNode;
//
//@Component
//public class CustomFacebookTemplate extends FacebookTemplate {
//
//	public CustomFacebookTemplate(String accessToken) {
//		super(accessToken);
//		// TODO Auto-generated constructor stub
//	}
//
//	public String fetchPictureUrl(String userId, ImageType imageType) {
//		URI uri = URIBuilder.fromUri(
//				GRAPH_API_URL + userId + "/picture" + "?type=" + imageType.toString().toLowerCase() + "&redirect=false")
//				.build();
//
//		JsonNode response = getRestTemplate().getForObject(uri, JsonNode.class);
//		return response.get("data").get("url").asText();
//	}
//
//}
