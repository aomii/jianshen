package com.rabbit.fitness.student.service;

import com.rabbit.fitness.dao.GymMapper;
import com.rabbit.fitness.dao.StudentMapper;
import com.rabbit.fitness.dao.UserMapper;
import com.rabbit.fitness.pojo.*;
import com.rabbit.fitness.student.vo.StudentVo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
@Transactional
public class StudentService {
    @Resource(name = "studentMapper")
    private StudentMapper studentMapper;
    //============后台使用=================
    @Resource(name="userMapper")
    private UserMapper userMapper;
    @Autowired
    private GymMapper gymMapper;
    //=============================

    //获取学员信息
    public Student getStudentInfoById(Integer uid){
        return studentMapper.getStudentInfoById(uid);
    }
    //完善学员信息
    public int perfectStudentById(Student student){
        return studentMapper.perfectStudentById(student);
    }
    //修改头像
    public int modifyStudentPicById( String picDir,Integer uid){
        return studentMapper.modifyStudentPicById(picDir, uid);
    }

    //查看学员所属场馆有哪些学员
    public List<Student> findOtherStudentById(Student student){
        return studentMapper.findOtherStudentById(student);
    }

    //发送好友请求
    public int addFriend(Addfriend addfriend){
       return studentMapper.addFriend(addfriend);
    }
    //查看好友请求
    public List<Addfriend> findFriendRequestsById(Integer uid){
        return studentMapper.findFriendRequestsById(uid);
    }

    //更新双方方好友列表
    public int updateFids(Student student){
        return studentMapper.updateFids(student);
    }

    //更新同意好友请求记录
    public int updateAgreeStatus(Integer agreeuid){
        return  studentMapper.updateAgreeStatus(agreeuid);
    }


    //更新拒绝好友请求记录
    public int updateRejectStatus(Integer agreeuid){
        return studentMapper.updateRejectStatus(agreeuid);
    }
    //好友列表
    public List<Student> friendList(Integer uid){
        return studentMapper.friendList(uid);
    }
    //查看是否为好友
    public  int isFriend( Integer senduid, Integer agreeuid){
        return studentMapper.isFriend(senduid, agreeuid);
    }


    //通过uid查询t_user表中的user_name
    public User getUserNameByUid(Integer uid){
        return studentMapper.getUserNameByUid(uid);
    }
    //保存聊天记录
    public int addChatRecord(Communication communication){
        return studentMapper.addChatRecord(communication);
    }

    //获取向当前用户发送消息的用户的名字
    public List<String> getAnotherSideName(String userName){
            return studentMapper.getAnotherSideName(userName);
    }
    //获取未读消息数量
    public int getUnreadMsgCountByName(String userName){
        return  studentMapper.getUnreadMsgCountByName(userName);
    }
    //更新聊天消息状态
    public int updateChatRecordStatus(String userName){
        return  studentMapper.updateChatRecordStatus(userName);
    }
    //获取学员的签约的教练信息
    public Coach getCoachByCid(Integer cid){
        return studentMapper.getCoachByCid(cid);
    }
    public boolean insertStu(Student student) {
        int index = studentMapper.insertSelective(student);
        if(index!=0){
            return true;
        }
        return false;
    }

    public boolean addMessage(Student student) {

        return studentMapper.addMessage(student);
    }






    //==========以下后台使用=======================

    public List<Student> findStudentByPage(Integer currentPage, Integer pageSize, StudentVo vo,Integer gid) {
        return studentMapper.findStudentByPage(currentPage,pageSize,vo,gid);
    }

    public Integer findGidByUid(Integer uid) {
        return  gymMapper.findGidByUid(uid);
    }


    @Transactional
    public void addBack(Student student){
        Integer result= null;
        Integer result2= null;
        try {
            //1先插入user表
            result = userMapper.insertSelective(student.getUser());

            //2根据userName查询user;
            User _user =userMapper.findUserByName(student.getUser().getUserName());
            student.setUid(_user.getUserId());
            System.out.println("uid="+student.getUid());

            //3插入student表中
            result2 = studentMapper.insertSelective(student);
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }

    public Integer updateBack(Student student) {
        return  studentMapper.updateByPrimaryKeySelective(student);
    }

    public int deleteBack(String stuids, String userids) {
        String [] arr_userids=userids.split(",");
        Integer[] userIds=new Integer[arr_userids.length];
        for (int i = 0; i <arr_userids.length ; i++) {
            userIds[i]=Integer.parseInt(arr_userids[i]);
        }
        userMapper.deleteUsers(userIds);


        String [] arr_stuids=stuids.split(",");
        Integer[] stuIds=new Integer[arr_stuids.length];
        for (int i = 0; i <arr_stuids.length ; i++) {
            stuIds[i]=Integer.parseInt(arr_stuids[i]);
        }
        Integer result=studentMapper.deleteBack(stuIds);
        return  result;
    }

    /*根据主键查询学生*/
    public Student findStudent(Integer stuid) {
        return  studentMapper.selectByPrimaryKey(stuid);
    }

    /*根据学生id找到其所购买的所有服务*/
    public List<StudentOrder> getService(Integer stuid) {
        return studentMapper.getService(stuid);
    }

    //更新学生
    public int updateStuBySel(Student currentStu) {
        return studentMapper.updateByPrimaryKeySelective(currentStu);
    }

    public int breakCoachTrade(Integer stuid) {
        return studentMapper.setCoachNull(stuid);
    }

    public int breakGymTrade(Integer stuid) {
        return studentMapper.setGymNull(stuid);
    }

/*    //添加订单
    public void addOrderByUid(String outTradeNo, String subject, double cmoney, Integer uid) {
        studentMapper.insertCorder(outTradeNo, subject, cmoney, uid);
    }*/


    //============以上================================
}
