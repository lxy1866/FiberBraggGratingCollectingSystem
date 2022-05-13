package top.kaluna.pipelineMonitor.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import top.kaluna.pipelineMonitor.req.UserLoginReq;
import top.kaluna.pipelineMonitor.req.UserQueryReq;
import top.kaluna.pipelineMonitor.req.UserResetPasswordReq;
import top.kaluna.pipelineMonitor.req.UserSaveReq;
import top.kaluna.pipelineMonitor.resp.CommonResp;
import top.kaluna.pipelineMonitor.resp.PageResp;
import top.kaluna.pipelineMonitor.resp.UserLoginResp;
import top.kaluna.pipelineMonitor.resp.UserQueryResp;
import top.kaluna.pipelineMonitor.service.UserService;
import top.kaluna.pipelineMonitor.util.SnowFlake;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuery
 * @date 2022/2/25/0025 - 22:03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private SnowFlake snowFlake;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/list")
    public CommonResp<PageResp<UserQueryResp>> list(@Valid UserQueryReq userQueryReq){
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list = userService.list(userQueryReq);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq userSaveReq){
        userSaveReq.setPassword(DigestUtils.md5DigestAsHex(userSaveReq.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp resp = new CommonResp<>();
        final int save = userService.save(userSaveReq);
        if(save != 0){
            resp.setSuccess(true);
            resp.setMessage("保存成功");
        }else {
            resp.setSuccess(false);
            resp.setMessage("保存失败");
        }
        return resp;
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        final int delete = userService.delete(id);
        if(delete != 0){
            resp.setSuccess(true);
            resp.setMessage("删除成功");
        }else {
            resp.setSuccess(false);
            resp.setMessage("删除失败");
        }
        return resp;
    }
    @PostMapping("/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp resp = new CommonResp();
        final int i = userService.resetPassword(req);
        if(i != 0){
            resp.setSuccess(true);
            resp.setMessage("更新成功");
        }else {
            resp.setSuccess(false);
            resp.setMessage("更新失败");
        }
        return resp;
    }
    @PostMapping("/login")
    public CommonResp<UserLoginResp> login(@Valid @RequestBody UserLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);
        Long token = snowFlake.nextId();
        userLoginResp.setToken(token.toString());
        LOG.info("单点登录token:{}，并放入redis中", token);
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp),3600*24, TimeUnit.SECONDS);
        LOG.info("成功放入redis");
        resp.setContent(userLoginResp);
        return resp;
    }
    @GetMapping("/logout/{token}")
    public CommonResp logout(@PathVariable String token){
        System.out.println(token);
        CommonResp resp = new CommonResp();
        redisTemplate.delete(token);
        LOG.info("从redis中删除token:{}", token);
        return resp;
    }
}
