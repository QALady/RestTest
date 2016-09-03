/**
 * 
 */
package com.qaforum.rest;

import static com.qaforum.util.MessageConstants.ERROR;
import static com.qaforum.util.MessageConstants.QA_DEL_E_MSG;
import static com.qaforum.util.MessageConstants.QA_DEL_IMG_E_MSG;
import static com.qaforum.util.MessageConstants.QA_DEL_IMG_S_MSG;
import static com.qaforum.util.MessageConstants.QA_DEL_S_MSG;
import static com.qaforum.util.MessageConstants.QA_GET_E_MSG;
import static com.qaforum.util.MessageConstants.QA_GET_IMG_E_MSG;
import static com.qaforum.util.MessageConstants.QA_GET_IMG_S_MSG;
import static com.qaforum.util.MessageConstants.QA_GET_NE_MSG;
import static com.qaforum.util.MessageConstants.QA_GET_S_MSG;
import static com.qaforum.util.MessageConstants.QA_SAVE_E_MSG;
import static com.qaforum.util.MessageConstants.QA_SAVE_S_MSG;
import static com.qaforum.util.MessageConstants.QA_UPD_E_MSG;
import static com.qaforum.util.MessageConstants.QA_UPD_S_MSG;
import static com.qaforum.util.MessageConstants.QA_UPL_IMG_E_MSG;
import static com.qaforum.util.MessageConstants.QA_UPL_IMG_S_MSG;
import static com.qaforum.util.MessageConstants.SUCCESS;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.qaforum.dataprovider.QaImageDataProvider;
import com.qaforum.dataprovider.QaInfoDataProvider;
import com.qaforum.dataprovider.impl.QaImageDataProviderImpl;
import com.qaforum.dataprovider.impl.QaInfoDataProviderImpl;
import com.qaforum.dto.QaImageInfoDTO;
import com.qaforum.dto.QaInfoDTO;
import com.qaforum.dto.QaInfoSearchDTO;
import com.qaforum.rest.response.Message;
import com.qaforum.rest.response.QaImageResponse;
import com.qaforum.util.ExportUtility;
import com.qaforum.util.ExportUtilityImpl;
import com.qaforum.util.FileUtility;

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

	/** */
	private static final QaImageDataProvider QA_IMG_DP = new QaImageDataProviderImpl();

	@Context
	private ServletContext context;

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
			message.setType(SUCCESS);
			message.setDescription(QA_SAVE_S_MSG);
			message.addQaInfo(retQaInfo);
		} catch (final Exception ex) {
			message.setType(ERROR);
			message.setDescription(QA_SAVE_E_MSG);
			logger.log(Level.SEVERE, ex.getMessage(), ex);
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
			message.setType(SUCCESS);
			message.setDescription(QA_UPD_S_MSG);

		} catch (final Exception ex) {
			message.setType(ERROR);
			message.setDescription(QA_UPD_E_MSG);
			logger.log(Level.SEVERE, ex.getMessage(), ex);
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
		final Message message = new Message();
		List<QaInfoDTO> qaInfos = null;
		try {
			qaInfos = QA_DP.findSelected(searchDTO);
			message.setType(SUCCESS);
			if (qaInfos == null) {
				message.setDescription(QA_GET_NE_MSG);
			} else {
				message.setDescription(QA_GET_S_MSG);
				message.addQaInfos(qaInfos);
			}

		} catch (final Exception ex) {
			ex.printStackTrace();
			message.setType(ERROR);
			message.setDescription(QA_GET_E_MSG);
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
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
		final Message message = new Message();
		try {
			QA_DP.delete(qaId);
			message.setType(SUCCESS);
			message.setDescription(QA_DEL_S_MSG);
		} catch (final Exception ex) {
			ex.printStackTrace();
			message.setType(ERROR);
			message.setDescription(QA_DEL_E_MSG);
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return message;
	}

	/**
	 * 
	 * @return {@link Message}
	 */
	@GET
	@Path("qaList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<QaInfoDTO> qaInfos = null;
		final Message message = new Message();
		Response response;
		try {
			qaInfos = QA_DP.findAll();
			message.setType(SUCCESS);
			message.setDescription(QA_GET_S_MSG);
			message.addQaInfos(qaInfos);
			response = Response.ok().entity(message).build();
		} catch (final Exception ex) {
			ex.printStackTrace();
			message.setType(ERROR);
			message.setDescription(QA_GET_E_MSG);
			logger.log(Level.SEVERE, ex.getMessage(), ex);
			response = Response.serverError().entity(message).build();
		}
		return response;
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
		final XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		final XSSFSheet sheet = workbook.getSheetAt(0);
		final Iterator<Row> rowIterator = sheet.iterator();
		final List<QaInfoDTO> qaInfos = new ArrayList<>();
		while (rowIterator.hasNext()) {
			final Row row = rowIterator.next();

			final QaInfoDTO qaInfo = new QaInfoDTO();
			qaInfo.setQuestion(row.getCell(0).getStringCellValue());
			qaInfo.setAnswer(row.getCell(1).getStringCellValue());
			qaInfos.add(qaInfo);
		}
		final List<QaInfoDTO> qaInfosRet = QA_DP.saveBatch(qaInfos);
		return Response.ok("Data uploaded successfully !!").build();
	}

	@POST
	@Path("uploadQaImage")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public QaImageResponse uploadQaImage(
			@FormDataParam("file") final InputStream inStream,
			@FormDataParam("file") final FormDataContentDisposition fileMetaData,
			@FormDataParam("qaId") final Long qaId) {

		final QaImageResponse response = new QaImageResponse();

		try {
			QaImageInfoDTO infoDto = new QaImageInfoDTO();
			infoDto.setQaId(qaId);
			infoDto = QA_IMG_DP.save(infoDto, fileMetaData);

			final String targetFileName = context.getInitParameter("temp.dir")
					+ infoDto.getImageId() + "_" + infoDto.getQaId() + "."
					+ infoDto.getImageType();

			FileUtility.copyFile(inStream, targetFileName);

			response.setType(SUCCESS);
			response.setDescription(QA_UPL_IMG_S_MSG);
			response.addInfo(infoDto);
		} catch (final Exception ex) {
			response.setType(ERROR);
			response.setDescription(QA_UPL_IMG_E_MSG);
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return response;
	}

	/**
	 * 
	 * @param qaId 
	 * @return {@link QaImageResponse}
	 */
	@GET
	@Path("findQaImage/{qaId}")
	@Produces(MediaType.APPLICATION_JSON)
	public QaImageResponse getQaImages(@PathParam("qaId") final Long qaId) {

		final QaImageResponse response = new QaImageResponse();

		try {
			final QaImageInfoDTO infoDto = new QaImageInfoDTO();
			infoDto.setQaId(qaId);

			final List<QaImageInfoDTO> infoDtos = QA_IMG_DP
					.findSelected(infoDto);
			response.setType(SUCCESS);
			response.setDescription(QA_GET_IMG_S_MSG);
			response.addInfos(infoDtos);
		} catch (final Exception ex) {
			response.setType(ERROR);
			response.setDescription(QA_GET_IMG_E_MSG);
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return response;
	}

	/**
	 * 
	 * @param imageDto 
	 * @return {@link QaImageResponse}
	 */
	@POST
	@Path("deleteQaImage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public QaImageResponse deleteQaImage(final QaImageInfoDTO imageDto) {
		final QaImageResponse response = new QaImageResponse();
		try {
			QA_IMG_DP.delete(imageDto.getImageId());
			FileUtility.deleteFile(context.getInitParameter("temp.dir")
					+ imageDto.getImagePath());
			response.setType(SUCCESS);
			response.setDescription(QA_DEL_IMG_S_MSG);
		} catch (final Exception ex) {
			response.setType(ERROR);
			response.setDescription(QA_DEL_IMG_E_MSG);
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return response;
	}
}