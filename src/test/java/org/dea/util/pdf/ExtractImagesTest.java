package org.dea.util.pdf;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractImagesTest {
	private static final Logger logger = LoggerFactory.getLogger(ExtractImagesTest.class);
	
	public void extractImagesTest(String pdfPath, String tmpDirPath) throws SecurityException, IOException{

		final File largePdf = new File(pdfPath);
		File tmpDir = new File(tmpDirPath);
		if(!largePdf.isFile()) {
			logger.info("Skipping test as test file is not available.");
		}
		
		PageImageWriter imgWriter = new PageImageWriter();
		try {
			imgWriter.extractImages(largePdf.getAbsolutePath(), tmpDir.getAbsolutePath());
		} finally {
			String finalOutPath = imgWriter.getExtractDirectory();
			FileUtils.deleteDirectory(new File(finalOutPath));
		}
		
	}
	
//	@Test
	public void extractImagesTest() throws SecurityException, IOException {
		
		extractImagesTest("/mnt/dea_scratch/TRP/bugs/large_pdf_oom/Beinecke_DL_Voynich Manuscript.pdf","/tmp/pdf/");
	}
	
	public static void main(String [] args) {
		ExtractImagesTest test = new ExtractImagesTest();
		try {
			if (args.length > 0) {
				test.extractImagesTest(args[0], System.getProperty("java.io.tmpdir"));
			} else test.extractImagesTest();

		} catch (SecurityException | IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
