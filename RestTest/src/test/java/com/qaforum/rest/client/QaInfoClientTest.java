/**
 * 
 */
package com.qaforum.rest.client;

import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.qaforum.dto.QaInfoDTO;
import com.qaforum.dto.QaInfoSearchDTO;
import com.qaforum.rest.response.Message;
import com.qaforum.util.Operator;
import com.qaforum.util.SearchOption;

/**
 * @author cdacr
 * 
 */
@Ignore
public class QaInfoClientTest {
	QaInfoClient qaInfoClient;
	private static final Logger LOGGER = Logger
			.getLogger(QaInfoClientTest.class.getName());

	public QaInfoClientTest() {
		qaInfoClient = new QaInfoClient();
	}

	@Test
	public void testFindAll() {
		final Message response = qaInfoClient.findAll();
		System.out.println(response);
		Assert.assertNotNull(response);

	}

	@Test
	public void testExport() {
		final Response response = qaInfoClient.exportQaInfo();
		System.out.println(response);
		Assert.assertNotNull(response);

	}

	@Test
	public void testSave() {
		final QaInfoDTO qaInfo = new QaInfoDTO();
		qaInfo.setQuestion("What is Junit?");
		qaInfo.setAnswer("Junit is Java testing framework and is a part of unit testing.");
		qaInfo.setType("Java");

		final Message response = qaInfoClient.save(qaInfo);
		final String type = response.getType();
		if (type.equals("success")) {
			LOGGER.info(response.getQaInfos().get(0).toString());
			Assert.assertNotNull(response.getQaInfos().get(0).getQaId());
		}

		final QaInfoDTO nullQaInfo = null;

		final Message nullResponse = qaInfoClient.save(nullQaInfo);

	}

	@Test
	public void testUpdate() {
		final QaInfoDTO qaInfo = new QaInfoDTO();
		qaInfo.setQaId(151L);
		qaInfo.setQuestion("What is Junit?");
		qaInfo.setAnswer("Junit is a Java testing framework and part of unit testing.");
		qaInfo.setType("Java");

		final Message response = qaInfoClient.update(qaInfo);
		final String type = response.getType();
		if (type.equals("success")) {
			LOGGER.info(response.getQaInfos().get(0).getAnswer());
			Assert.assertEquals(qaInfo.getAnswer(), response.getQaInfos()
					.get(0).getAnswer());
		}
	}

	@Test
	public void testDelete() {
		// First add the record, afterward delete it.
		final QaInfoDTO qaInfo = new QaInfoDTO();
		qaInfo.setQuestion("What is Junit?");
		qaInfo.setAnswer("Junit is Java testing framework and is a part of unit testing.");
		qaInfo.setType("Java");

		final Message response = qaInfoClient.save(qaInfo);
		final String type = response.getType();
		if (type.equals("success")) {
			final Long qaId = response.getQaInfos().get(0).getQaId();
			LOGGER.info("QaId: " + qaId);

			final Message delResponse = qaInfoClient.delete(qaId);

			LOGGER.info(delResponse.getDescription());

			if (delResponse.getType().equals("success")) {
				final QaInfoSearchDTO searchDTO = new QaInfoSearchDTO();
				searchDTO.setOperator(Operator.EQUALS.getString());
				searchDTO.setSearchOption(SearchOption.QUESTION_ID.getString());
				searchDTO.setSearchValue(String.valueOf(qaId));
				final Message getResponse = qaInfoClient
						.findSelected(searchDTO);
				LOGGER.info(getResponse.getDescription());
				if (getResponse.getType().equals("success")) {
					Assert.assertEquals("Record does not exists!",
							getResponse.getDescription());
				}
			}
		}
	}

	/**
	 * Method will test all possible search criteria for Question Id
	 */
	@Test
	public void testFindQuestionIdQaInfoSearch() {
		final QaInfoSearchDTO searchDTO = new QaInfoSearchDTO();
		searchDTO.setSearchOption("questionId");
		searchDTO.setOperator("equals");
		searchDTO.setSearchValue("559");

		// Equals Test
		final Message getResponse = qaInfoClient.findSelected(searchDTO);
		Assert.assertNotNull(getResponse);
		if (getResponse.getType().equals("success")) {
			Assert.assertTrue(getResponse.getQaInfos().size() == 1);
		}

		// Greater Than Test
		searchDTO.setOperator("greaterThan");
		final Message getResponseGreaterThan = qaInfoClient
				.findSelected(searchDTO);
		Assert.assertNotNull(getResponseGreaterThan);
		if (getResponseGreaterThan.getType().equals("success")) {
			Assert.assertTrue(getResponseGreaterThan.getQaInfos().size() > 1);
		}

		// Less Than Test
		searchDTO.setOperator("lessThan");
		final Message getResponseLessThan = qaInfoClient
				.findSelected(searchDTO);
		Assert.assertNotNull(getResponseLessThan);
		if (getResponseLessThan.getType().equals("success")) {
			Assert.assertTrue(getResponseLessThan.getQaInfos().size() > 1);
		}
	}

	/**
	 * Method will test all possible search criteria for Question
	 */
	@Test
	public void testFindQuestionQaInfoSearch() {
		final QaInfoSearchDTO searchDTO = new QaInfoSearchDTO();
		searchDTO.setSearchOption("question");
		searchDTO.setOperator("equals");
		searchDTO.setSearchValue("What is J2EE?");

		// Equals Test
		final Message getResponse = qaInfoClient.findSelected(searchDTO);
		Assert.assertNotNull(getResponse);
		if (getResponse.getType().equals("success")) {
			Assert.assertTrue(getResponse.getQaInfos().size() == 1);
		}

		// Starts With Test
		searchDTO.setOperator("startsWith");
		searchDTO.setSearchValue("What do");
		final Message getResponseStartsWith = qaInfoClient
				.findSelected(searchDTO);
		Assert.assertNotNull(getResponseStartsWith);
		if (getResponseStartsWith.getType().equals("success")) {
			Assert.assertTrue(getResponseStartsWith.getQaInfos().size() >= 1);
		}

		// Ends With Test
		searchDTO.setOperator("endsWith");
		searchDTO.setSearchValue("EJB?");
		final Message getResponseEndsWith = qaInfoClient
				.findSelected(searchDTO);
		Assert.assertNotNull(getResponseEndsWith);
		if (getResponseEndsWith.getType().equals("success")) {
			Assert.assertTrue(getResponseEndsWith.getQaInfos().size() >= 1);
		}

		// Contains Test
		searchDTO.setOperator("contains");
		searchDTO.setSearchValue("versions");
		final Message getResponseContains = qaInfoClient
				.findSelected(searchDTO);
		Assert.assertNotNull(getResponseContains);
		if (getResponseContains.getType().equals("success")) {
			Assert.assertTrue(getResponseContains.getQaInfos().size() >= 1);
		}
	}

	/**
	 * Method will test all possible search criteria for Answer
	 */
	@Test
	public void testFindAnswerQaInfoSearch() {
		final QaInfoSearchDTO searchDTO = new QaInfoSearchDTO();
		searchDTO.setSearchOption("answer");
		searchDTO.setOperator("equals");
		searchDTO
				.setSearchValue("The act of redeployment, deployment and un-deployment in Web logic when the server is running in EJB is called Hot Deployment.");

		// Equals Test
		final Message getResponse = qaInfoClient.findSelected(searchDTO);
		Assert.assertNotNull(getResponse);
		if (getResponse.getType().equals("success")) {
			Assert.assertTrue(getResponse.getQaInfos().size() == 1);
		}

		// Starts With Test
		searchDTO.setOperator("startsWith");
		searchDTO.setSearchValue("A collection");
		final Message getResponseStartsWith = qaInfoClient
				.findSelected(searchDTO);
		Assert.assertNotNull(getResponseStartsWith);
		if (getResponseStartsWith.getType().equals("success")) {
			Assert.assertTrue(getResponseStartsWith.getQaInfos().size() >= 1);
		}

		// Ends With Test
		searchDTO.setOperator("endsWith");
		searchDTO.setSearchValue("is called J2EE.");
		final Message getResponseEndsWith = qaInfoClient
				.findSelected(searchDTO);
		Assert.assertNotNull(getResponseEndsWith);
		if (getResponseEndsWith.getType().equals("success")) {
			Assert.assertTrue(getResponseEndsWith.getQaInfos().size() >= 1);
		}

		// Contains Test
		searchDTO.setOperator("contains");
		searchDTO.setSearchValue("synchronized");
		final Message getResponseContains = qaInfoClient
				.findSelected(searchDTO);
		Assert.assertNotNull(getResponseContains);
		if (getResponseContains.getType().equals("success")) {
			Assert.assertTrue(getResponseContains.getQaInfos().size() >= 1);
		}
	}
}
