package raubach.sgud.server;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/")
public class Sgud extends ResourceConfig
{
	public Sgud()
	{
		packages("raubach.sgud.server");
		register(MultiPartFeature.class);
	}
}
