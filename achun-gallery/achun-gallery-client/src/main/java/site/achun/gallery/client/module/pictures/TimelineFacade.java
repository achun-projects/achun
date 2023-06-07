package site.achun.gallery.client.module.pictures;

import site.achun.gallery.client.module.pictures.request.QueryTimelinePage;
import site.achun.gallery.client.module.pictures.response.TimelineResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

public interface TimelineFacade {
    Rsp<RspPage<TimelineResponse>> queryPage(QueryTimelinePage query);
}
