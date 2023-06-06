/**
 * 
 */
package back.task;

import java.io.File;
import java.util.Collection;
import java.util.List;

import java.util.ArrayList;

import javafx.concurrent.Task;
import voyages.Platform;

/** 
 * This class defined a task that import teenagers from a csv file.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 06/06/23
 * @see Task
 */
public class ImportCsvTask extends Task<Void> {
	
	/**
	 * The platform used to perform the import.
	 */
	private Platform currentPlatform;
	
	/**
	 * List of csv files used to import teenagers.
	 */
	private List<File> filesToImport;
	
	/**
	 * A {@code boolean} to know if the controller has to generate log files.
	 */
	private boolean generateLogFile;
	
	
	/**
	 * @param p, a {@code Platform} to use.
	 * @param files, a {@code Collection<? extends File>} with all files used to import teenagers.
	 * @param generateLogFile a {@code boolean} to know if the controller has to generate log files.
	 */
	public ImportCsvTask(Platform p, Collection<? extends File> files, boolean generateLogFile) {
		this.currentPlatform = p;
		this.filesToImport = new ArrayList<File>();
		this.filesToImport.addAll(files);
		this.generateLogFile = generateLogFile;
	}

	/**
	 * That method is performed on a separated thread, to add teenagers into the platform.
	 */
	@Override
	protected Void call() throws Exception {
		Thread.sleep(300);
		for (File file : filesToImport) {
			updateMessage(this.currentPlatform.importTeenagerFromCsv(file, this.generateLogFile));
			Thread.sleep(100);
		}
		return null;
	}
}
