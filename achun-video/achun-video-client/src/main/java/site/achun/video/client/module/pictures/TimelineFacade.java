package site.achun.video.client.module.pictures;

import site.achun.video.client.module.pictures.request.QueryTimelinePage;
import site.achun.video.client.module.pictures.response.TimelineResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

public interface TimelineFacade {
    Rsp<RspPage<TimelineResponse>> queryPage(QueryTimelinePage query);
}
