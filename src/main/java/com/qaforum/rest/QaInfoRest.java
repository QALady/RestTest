/**
 * 
 */
package com.qaforum.rest;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.qaforum.dataprovider.QaInfoDataProvider;
import com.qaforum.dataprovider.impl.QaInfoDataProviderImpl;
import com.qaforum.dto.QaInfoDTO;
import com.qaforum.dto.QaInfoSearchDTO;
import com.qaforum.rest.response.Message;
import com.qaforum.util.ExportUtility;
import com.qaforum.util.ExportUtilityImpl;

/**
 * @author cdacr
 * 
 */
@Path("qaInfo")
public final class QaInfoRest {

	/** */
	private final Logger logger = Logger.getLogger(QaInfoRest.class.getName());
	/** */
	private static final QaInfoDataProvider QA_DP = new QaInfoDataProviderImpl();

	/**
	 * 
	 * @param qaInfo 
	 * @return {@link Message}
	 */
	@POST
	@Path("saveQaInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Message save(final QaInfoDTO qaInfo) {
		logger.entering("QaInfoRest", "save");
		final Message message = new Message();

		try {
			final QaInfoDTO retQaInfo = QA_DP.save(qaInfo);
			message.setType("success");
			message.setDescription("QA Info has been saved successfully");
			message.addQaInfo(retQaInfo);
		} catch (final Exception ex) {
			message.setType("error");
			message.setDescription("There is an error while saving QA Info, if issue still persist then please contact support team.");
		}

		return message;
	}

	/**
	 * 
	 * @param qaInfo 
	 * @return {@link Message}
	 */
	@PUT
	@Path("updateQaInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Message update(final QaInfoDTO qaInfo) {
		final Message message = new Message();

		try {
			message.addQaInfo(QA_DP.save(qaInfo));
			message.setType("success");
			message.setDescription("QA Info has been saved successfully");

		} catch (final Exception ex) {
			message.setType("error");
			message.setDescription("There is an error while saving QA Info, if issue still persist then please contact support team.");
		}

		return message;
	}

	/**
	 * 
	 * @param searchDTO 
	 * @return {@link Message}
	 */
	@POST
	@Path("searchInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message findSelected(final QaInfoSearchDTO searchDTO) {
		logger.info("Start findSelected : :" + searchDTO);
		final Message message = new Message();
		List<QaInfoDTO> qaInfos = null;
		try {
			qaInfos = QA_DP.findSelected(searchDTO);
			message.setType("success");
			if (qaInfos == null) {
				message.setDescription("Record does not exists!");
			} else {
				message.setDescription("QA Info has been fetched successfully");
				message.addQaInfos(qaInfos);
			}

		} catch (final Exception ex) {
			ex.printStackTrace();
			message.setType("error");
			message.setDescription("There is an error while fetching QA Info, if issue still persist then please contact support team.");
		}
		logger.info("Before return ::: " + message);
		return message;
	}

	/**
	 * 
	 * @param qaId 
	 * @return {@link Message}
	 */
	@DELETE
	@Path("{qaId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message delete(@PathParam("qaId") final Long qaId) {
		logger.info("Start delete : :" + qaId);
		final Message message = new Message();
		try {
			QA_DP.delete(qaId);
			message.setType("success");
			message.setDescription("QA Info has been deleted successfully");
		} catch (final Exception ex) {
			ex.printStackTrace();
			message.setType("error");
			message.setDescription("There is an error while fetching QA Info, if issue still persist then please contact support team.");
		}
		logger.info("Before return ::: " + message);
		return message;
	}

	/**
	 * 
	 * @return {@link Message}
	 */
	@GET
	@Path("qaList")
	@Produces(MediaType.APPLICATION_JSON)
	public Message findAll() {
		List<QaInfoDTO> qaInfos = null;
		final Message message = new Message();
		try {
			qaInfos = QA_DP.findAll();
			message.setType("success");
			message.setDescription("QA Info has been fetched successfully");
			message.addQaInfos(qaInfos);
		} catch (final Exception ex) {
			ex.printStackTrace();
			message.setType("error");
			message.setDescription("There is an error while fetching QA Info, if issue still persist then please contact support team.");
		}
		System.out.println(message);
		return message;
	}

	/**
	 * 
	 * @return {@link Response}
	 */
	@GET
	@Path("exportQaInfo/excel")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response exportQaInfo() {
		final ExportUtility utility = new ExportUtilityImpl();
		final File file = utility.generateExcel();

		final ResponseBuilder response = Response.ok(file);
		response.header("Content-Disposition", "attachment; filename=test.xlsx");
		return response.build();
	}

	/**
	 * 
	 * @param fileInputStream 
	 * @param fileMetaData 
	 * @return {@link Response}
	 * @throws Exception 
	 */
	@POST
	@Path("/import")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response importData(
			@FormDataParam("file") final InputStream fileInputStream,
			@FormDataParam("file") final FormDataContentDisposition fileMetaData)
			throws Exception {
		logger.info("Start Import");
		final XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		final XSSFSheet sheet = workbook.getSheetAt(0);
		final Iterator<Row> rowIterator = sheet.iterator();
		final List<QaInfoDTO> qaInfos = new ArrayList<>();
		while (rowIterator.hasNext()) {
			final Row row = rowIterator.next();

			final QaInfoDTO qaInfo = new QaInfoDTO();
			qaInfo.setQuestion(row.getCell(0).getStringCellValue());
			qaInfo.setAnswer(row.getCell(1).getStringCellValue());
			logger.info(qaInfo.toString());
			qaInfos.add(qaInfo);
		}
		final List<QaInfoDTO> qaInfosRet = QA_DP.saveBatch(qaInfos);
		logger.info(qaInfosRet.toString());
		return Response.ok("Data uploaded successfully !!").build();
	}
}