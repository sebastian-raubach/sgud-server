package raubach.sgud.server.util;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.fileupload.RestletFileUpload;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.io.File;
import java.io.IOException;

public class FileUploadUtils
{
	public static String handle(Representation entity, String formIdentifier, File targetFile)
	{
		if (entity != null)
		{
			if (MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(), true))
			{
				// 1. Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1000240);

				// 2. Create a new file upload handler based on the Restlet FileUpload extension that will parse Restlet requests and generates FileItems.
				RestletFileUpload upload = new RestletFileUpload(factory);

				try
				{
					// 3. Request is parsed by the handler which generates a list of FileItems
					FileItemIterator fileIterator = upload.getItemIterator(entity);

					// Process only the uploaded item with the given name and return back
					while (fileIterator.hasNext())
					{
						FileItemStream fi = fileIterator.next();
						if (fi.getFieldName().equals(formIdentifier))
						{
							// consume the stream immediately, otherwise the stream
							// will be closed.
							FileUtils.copyInputStreamToFile(fi.openStream(), targetFile);
							return fi.getName();
						}
					}

					// If we get here, the file wasn't found
					throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
				}
				catch (IOException | FileUploadException e)
				{
					e.printStackTrace();
					throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
				}
			}
			else
			{
				// POST request with no entity.
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
			}
		}
		else
		{
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST);
		}
	}
}
