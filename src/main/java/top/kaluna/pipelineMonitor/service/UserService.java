package top.kaluna.pipelineMonitor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import top.kaluna.pipelineMonitor.domain.User;
import top.kaluna.pipelineMonitor.domain.UserExample;
import top.kaluna.pipelineMonitor.exception.BusinessException;
import top.kaluna.pipelineMonitor.exception.BusinessExceptionCode;
import top.kaluna.pipelineMonitor.mapper.UserMapper;
import top.kaluna.pipelineMonitor.req.UserLoginReq;
import top.kaluna.pipelineMonitor.req.UserQueryReq;
import top.kaluna.pipelineMonitor.req.UserResetPasswordReq;
import top.kaluna.pipelineMonitor.req.UserSaveReq;
import top.kaluna.pipelineMonitor.resp.PageResp;
import top.kaluna.pipelineMonitor.resp.UserLoginResp;
import top.kaluna.pipelineMonitor.resp.UserQueryResp;
import top.kaluna.pipelineMonitor.util.CopyUtil;
import top.kaluna.pipelineMonitor.util.SnowFlake;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuery
 * @date 2022/2/25/0025 - 22:05
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public int save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        int result = 0;
        if (ObjectUtils.isEmpty(req.getId())) {
            User userDB = selectByLoginName(req.getLoginName());
            if (ObjectUtils.isEmpty(userDB)) {
                // 新增
                user.setId(snowFlake.nextId());
                result = userMapper.insert(user);
            } else {
                // 用户名已存在
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        } else {
            // 更新
            user.setLoginName(null);
            user.setPassword(null);
            result = userMapper.updateByPrimaryKeySelective(user);
        }
        return result;
    }

    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public PageResp<UserQueryResp> list(UserQueryReq userQueryReq) {
        UserExample userExample = new UserExample();
        final UserExample.Criteria criteria = userExample.createCriteria();
        if(!ObjectUtils.isEmpty(userQueryReq.getLoginName())){
            criteria.andLoginNameEqualTo(userQueryReq.getLoginName());
        }

        //两个请求参数
        PageHelper.startPage(userQueryReq.getPage(), userQueryReq.getSize());
        final List<User> users = userMapper.selectByExample(userExample);

        PageInfo<User> pageInfo = new PageInfo<>(users);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}",pageInfo.getPages());

        final List<UserQueryResp> respsList = CopyUtil.copyList(users, UserQueryResp.class);

        PageResp<UserQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respsList);
        return pageResp;
    }
    public User selectByLoginName(String LoginName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(LoginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }
    /**
     * 修改密码
     * @param req
     */
    public int resetPassword(UserResetPasswordReq req) {
        User copy = CopyUtil.copy(req, User.class);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        return userMapper.updateByExample(copy,userExample);
    }

    public UserLoginResp login(UserLoginReq req) {
        //我们只用用户名去数据库查，查完之后再对比密码
        User userDb = selectByLoginName(req.getLoginName());
        if(ObjectUtils.isEmpty(userDb)){
            LOG.info("用户名不存在，{}",req.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }else{
            if(userDb.getPassword().equals(req.getPassword())){
                //登录成功
                UserLoginResp userLoginResp = CopyUtil.copy(userDb, UserLoginResp.class);
                return userLoginResp;
            }else{
                //密码不对
                LOG.info("密码不对，输入密码：{}，数据库密码：{}", req.getPassword(),userDb.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }
}
