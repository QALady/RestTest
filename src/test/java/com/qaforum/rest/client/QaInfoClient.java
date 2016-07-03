/**
 * 
 */
package com.qaforum.rest.client;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import com.qaforum.dto.QaInfoDTO;
import com.qaforum.dto.QaInfoSearchDTO;
import com.qaforum.rest.response.Message;

/**
 * @author cdacr
 * 
 */
public final class QaInfoClient {

	/** */
	private final Client client;
	/** */
	private static final Logger LOGGER = Logger.getLogger(QaInfoClient.class
			.getName());

	/**
	 * 
	 */
	public QaInfoClient() {
		client = ClientBuilder.newClient();

	}

	/**
	 * 
	 * @param searchDTO 
	 * @return {@link Message}
	 */
	public Message findSelected(final QaInfoSearchDTO searchDTO) {
		final WebTarget target = client
				.target("http://localhost:8080/RestTest/webapi/");
		final Message response = target
				.path("qaInfo/searchInfo")
				.request()
				.post(Entity.entity(searchDTO, MediaType.APPLICATION_JSON),
						Message.class);

		return response;
	}

	/**
	 * 
	 * @return {@link Message}
	 */
	public Message findAll() {
		final WebTarget target = client
				.target("http://localhost:8080/RestTest/webapi/");
		System.out.println(target.getUri());
		final Message response = target.path("qaInfo/qaList").request()
				.get(new GenericType<Message>() {
				});
		LOGGER.info(target.getUri().toString());
		return response;
	}

	/**
	 * 
	 * @param qaInfo 
	 * @return {@link Message}
	 */
	public Message save(final QaInfoDTO qaInfo) {
		final WebTarget target = client
				.target("http://localhost:8080/RestTest/webapi/");
		final Message response = target
				.path("qaInfo/saveQaInfo")
				.request()
				.post(Entity.entity(qaInfo, MediaType.APPLICATION_JSON),
						Message.class);
		LOGGER.info(response.toString());
		return response;
	}

	/**
	 * 
	 * @param qaInfo 
	 * @return {@link Message}
	 */
	public Message update(final QaInfoDTO qaInfo) {
		final WebTarget target = client
				.target("http://localhost:8080/RestTest/webapi/");
		final Message response = target
				.path("qaInfo/updateQaInfo")
				.request()
				.put(Entity.entity(qaInfo, MediaType.APPLICATION_JSON),
						Message.class);
		LOGGER.info(response.toString());
		return response;
	}

	/**
	 * 
	 * @param qaId 
	 * @return {@link Message}
	 */
	public Message delete(final Long qaId) {
		final WebTarget target = client
				.target("http://localhost:8080/RestTest/webapi/");
		final Message response = target.path("qaInfo/" + qaId)
				.request(MediaType.APPLICATION_JSON).delete(Message.class);
		LOGGER.info(response.toString());
		return response;
	}

	/**
	 * 
	 * @return {@link Response}
	 */
	public Response exportQaInfo() {
		final WebTarget target = client
				.target("http://localhost:8080/RestTest/webapi/");
		final Response response = target.path("exportQaInfo")
				.request(MediaType.APPLICATION_JSON).get(Response.class);
		return response;
	}

	/**
	 * 
	 * @param args 
	 * @throws IOException 
	 */
	public static void main(final String[] args) throws IOException {
		final Client client = ClientBuilder.newBuilder()
				.register(MultiPartFeature.class).build();

		final FileDataBodyPart filePart = new FileDataBodyPart("file",
				new File("G:/sample.xlsx"));
		final FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
		final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart
				.field("foo", "bar").bodyPart(filePart);

		final WebTarget target = client
				.target("http://localhost:8080/RestTest/webapi/qaInfo/import");
		final Response response = target.request().post(
				Entity.entity(multipart, multipart.getMediaType()));

		System.out.println(response.getStatus());
		// Use response object to verify upload success

		formDataMultiPart.close();
		multipart.close();
	}
}
