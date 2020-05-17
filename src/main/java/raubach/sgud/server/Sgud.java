package raubach.sgud.server;

import org.restlet.*;
import org.restlet.data.Header;
import org.restlet.data.Method;
import org.restlet.engine.application.CorsFilter;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;
import org.restlet.util.Series;
import raubach.sgud.server.resource.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Sgud extends Application
{
	public Sgud()
	{
		setName("SQUD");
		setDescription("Server implementation of SGUD");
		setOwner("Sebastian Raubach");
		setAuthor("Sebastian Raubach <sebastian@raubach.co.uk>");
	}

	@Override
	public Restlet createInboundRoot()
	{
		Context context = getContext();

		// Set the encoder
//		Filter encoder = new Encoder(context, false, true, new EncoderService(true));

		// Create new router
		Router router = new Router(context);


		// Set the Cors filter
		CorsFilter corsFilter = new CorsFilter(context, router)
		{
			@Override
			protected int beforeHandle(Request request, Response response)
			{
				if (getCorsResponseHelper().isCorsRequest(request))
				{
					Series<Header> headers = request.getHeaders();

					for (Header header : headers)
					{
						if (header.getName().equalsIgnoreCase("origin"))
						{
							response.setAccessControlAllowOrigin(header.getValue());
						}
					}
				}
				return super.beforeHandle(request, response);
			}
		};
		corsFilter.setAllowedOrigins(new HashSet<>(Collections.singletonList("*")));
		corsFilter.setSkippingResourceForCorsOptions(true);
		corsFilter.setAllowingAllRequestedHeaders(true);
		corsFilter.setDefaultAllowedMethods(new HashSet<>(Arrays.asList(Method.POST, Method.GET, Method.PUT, Method.PATCH, Method.DELETE, Method.OPTIONS)));
		corsFilter.setAllowedCredentials(true);
		corsFilter.setExposedHeaders(Collections.singleton("Content-Disposition"));

		// Attach the url handlers
		attachToRouter(router, "/category", CategoryServerResource.class);
		attachToRouter(router, "/category/{categoryId}", CategoryServerResource.class);
		attachToRouter(router, "/category/{categoryId}/item", CategoryItemServerResource.class);
		attachToRouter(router, "/category/{categoryId}/rating", CategoryRatingServerResource.class);
		attachToRouter(router, "/category/{categoryId}/heatmap", CategoryHeatmapServerResource.class);
		attachToRouter(router, "/category/{categoryId}/type", CategoryItemTypeServerResource.class);
		attachToRouter(router, "/item", ItemServerResource.class);
		attachToRouter(router, "/item/{itemId}", ItemViewServerResource.class);
		attachToRouter(router, "/item/{itemId}/image", ItemImageServerResource.class);
		attachToRouter(router, "/image", ImageServerResource.class);
		attachToRouter(router, "/image/{imageId}", ImageServerResource.class);
		attachToRouter(router, "/image/{imageId}/src", ImageSrcServerResource.class);
		attachToRouter(router, "/item/{itemId}/rating", ItemRatingServerResource.class);
		attachToRouter(router, "/item/{itemId}/rating/{categoryId}", ItemRatingIndividualServerResource.class);
		attachToRouter(router, "/manufacturer", ManufacturerServerResource.class);
		attachToRouter(router, "/manufacturer/{manufacturerId}", ManufacturerServerResource.class);
		attachToRouter(router, "/source", SourceServerResource.class);
		attachToRouter(router, "/source/{sourceId}", SourceServerResource.class);
		attachToRouter(router, "/type", TypeServerResource.class);
		attachToRouter(router, "/type/{typeId}", TypeServerResource.class);

		// CORS first, then router
		corsFilter.setNext(router);

		return corsFilter;
	}

	private void attachToRouter(Router router, String url, Class<? extends ServerResource> clazz)
	{
		router.attach(url, clazz);
		router.attach(url + "/", clazz);
	}
}
