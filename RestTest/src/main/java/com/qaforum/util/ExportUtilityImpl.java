/**
 * 
 */
package com.qaforum.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qaforum.dto.QaInfoDTO;

/**
 * @author cdacr
 * 
 */
public final class ExportUtilityImpl implements ExportUtility {

	/** */
	private static final Logger LOGGER = Logger
			.getLogger(ExportUtilityImpl.class.getName());

	/**
	 * @return {@link File}
	 */
	@Override
	public File generateExcel() {
		final XSSFWorkbook workbook = new XSSFWorkbook();
		final XSSFSheet spreadsheet = workbook.createSheet("QA Information");
		XSSFRow row;
		int rowIndex = 0;

		final List<QaInfoDTO> qaInfos = CONVERTER
				.convertListQaBOToDTO(QA_SERVICE.findAll());

		row = spreadsheet.createRow(rowIndex++);
		row.createCell(0).setCellValue("Question #");
		row.createCell(1).setCellValue("Question");
		row.createCell(2).setCellValue("Answer");

		for (final QaInfoDTO qaInfo : qaInfos) {
			row = spreadsheet.createRow(rowIndex++);
			row.createCell(0).setCellValue(qaInfo.getQaId());
			row.createCell(1).setCellValue(qaInfo.getQuestion());
			row.createCell(2).setCellValue(qaInfo.getAnswer());
		}
		File file = null;
		FileOutputStream writer = null;
		try {
			file = new File("qaInfo.xlsx");
			writer = new FileOutputStream(file);
			workbook.write(writer);
			LOGGER.info("Excel file has been generated successfully!");
		} catch (final Exception ex) {
			LOGGER.log(Level.SEVERE,
					"Error occured while generating excel file!", ex);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (final IOException e) {
					LOGGER.log(Level.SEVERE,
							"Error occured while closing stream!", e);
				}
			}
		}

		return file;
	}

	/**
	 * @return {@link File}
	 */
	@Override
	public File generatePdf() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return {@link File}
	 */
	@Override
	public File generateWord() {
		// TODO Auto-generated method stub
		return null;
	}

}
