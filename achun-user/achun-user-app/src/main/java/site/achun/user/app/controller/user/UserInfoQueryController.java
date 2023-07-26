package site.achun.user.app.controller.user;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.support.api.response.Rsp;
import site.achun.user.app.generator.domain.UserAccount;
import site.achun.user.app.generator.service.UserAccountService;
import site.achun.user.app.service.UserCacheService;
import site.achun.user.app.service.dto.UserCacheInfo;
import site.achun.user.client.module.user.UserInfoQueryClient;
import site.achun.user.client.module.user.response.UserInfoResponse;

@Slf4j
@Tag(name = "用户信息查询")
@RequiredArgsConstructor
@RestController
public class UserInfoQueryController implements UserInfoQueryClient {

    private final UserAccountService userAccountService;
    private final MediaFileQueryClient mediaFileQueryClient;
    private final UserCacheService userCacheService;

    @Override
    public Rsp<UserInfoResponse> queryUser(String satoken) {
        UserCacheInfo userCacheInfo = userCacheService.getByToken(satoken);
        if(userCacheInfo == null){
            return Rsp.error("无效Token");
        }
        UserAccount userAccount = userAccountService.lambdaQuery()
                .eq(UserAccount::getUserCode, userCacheInfo.getUserCode())
                .last("limit 1")
                .one();
        UserInfoResponse response = UserInfoResponse.builder()
                .account(userAccount.getAccount())
                .name(userAccount.getName())
                .userCode(userAccount.getUserCode())
                .build();
        if(StrUtil.isNotBlank(userAccount.getCoverFileCode())){
            try{
                Rsp<MediaFileResponse> fileResponse = mediaFileQueryClient.queryFile(QueryByFileCode.builder().fileCode(userAccount.getCoverFileCode()).build());
                if(fileResponse.hasData()){
                    response.setCover(fileResponse.tryGetData().getCover());
                }else{
                    log.info("fileCode:{}查询不到数据",userAccount.getCoverFileCode());
                }
            }catch (Exception ex){
                log.info("获取头像地址时报错",ex);
                ex.printStackTrace();
            }
        }
        return Rsp.success(response);
    }
}
