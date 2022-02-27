package top.kaluna.modbusTcp.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import top.kaluna.modbusTcp.req.UserLoginReq;
import top.kaluna.modbusTcp.req.UserQueryReq;
import top.kaluna.modbusTcp.req.UserResetPasswordReq;
import top.kaluna.modbusTcp.req.UserSaveReq;
import top.kaluna.modbusTcp.resp.CommonResp;
import top.kaluna.modbusTcp.resp.PageResp;
import top.kaluna.modbusTcp.resp.UserLoginResp;
import top.kaluna.modbusTcp.resp.UserQueryResp;
import top.kaluna.modbusTcp.service.UserService;
import top.kaluna.modbusTcp.util.SnowFlake;

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
    private RedisTemplate redisTemplate;

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
        userService.save(userSaveReq);
        return resp;
    }
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }
    @GetMapping("/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp resp = new CommonResp();
        userService.resetPassword(req);
        return resp;
    }
    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp<UserLoginResp> resp = new CommonResp();
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
