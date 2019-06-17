package com.rabbit.fitness.dao;

import com.rabbit.fitness.pojo.*;
import com.rabbit.fitness.student.vo.StudentVo;
import org.apache.ibatis.annotations.*;

import com.rabbit.fitness.pojo.Student;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {

    //自带的方法
    int deleteByPrimaryKey(Integer stuid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer stuid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    //获取学员信息
    @Select("select u.lastaddress as lastlogin,s.* from student s,t_user u where s.uid=#{uid} and s.uid=u.user_id;")
    public Student getStudentInfoById(Integer uid);

    //完善/修改学员信息
    @Update("update student set stuname=#{stuname},stuage=#{stuage}," +
            "stugender=#{stugender},stuphone=#{stuphone},stubirth=#{stubirth} where stuid=#{stuid}")
    public int perfectStudentById(Student student);

    //学员修改头像
    @Update("update student set stupic=#{picDir} where stuid=#{stuid}")
    public int modifyStudentPicById(@Param("picDir") String picDir, @Param("stuid") Integer uid);

    //查看学员所属场馆有哪些学员
    @Select("select * from student where gid=#{gid} and uid!=#{uid}")
    public List<Student> findOtherStudentById(Student student);

    //发送好友请求 在addfriend表中增加一条记录
    @Insert("insert into addfriend values(#{senduid},#{agreeuid},default)")
    public int addFriend(Addfriend addfriend);
    //查看好友请求
    @Select("select stu.stuname sendname,af.* from addfriend af,student stu where agreeuid=#{uid} and isnull(status) and af.senduid=stu.uid; ")
    public List<Addfriend> findFriendRequestsById(Integer uid);

    //同意好友请求  1)先查出原来列表中的好友id 2)更新同意方好友列表 3)更新请求方的好友列表 4)更新addfriend表请求记录状态

    @Update("update student set friendids=#{friendids} where uid=#{uid}")
    public int updateFids(Student student);

    //更新同意好友请求记录状态
    @Update("update addfriend set status='Y' where agreeuid=#{agreeuid}")
    public int updateAgreeStatus(Integer agreeuid);

    //更新拒绝好友请求记录状态
    @Update("update addfriend set status='N' where agreeuid=#{agreeuid}")
    public int updateRejectStatus(Integer agreeuid);
    //好友列表
    @Select("select s.uid,s.stuname from t_user u,student s WHERE FIND_IN_SET(user_id,(select friendids from student WHERE uid=#{uid})) and u.user_id=s.uid;")
    public List<Student> friendList(Integer uid);

    //查看双方是否为好友
    @Select("select COUNT(*) from student where uid = #{senduid} and FIND_IN_SET(#{agreeuid},(select friendids from student where uid=#{senduid}))")
    public  int isFriend(@Param("senduid") Integer senduid,@Param("agreeuid") Integer agreeuid);

    //通过uid查询t_user表中的user_name
    @Select("select u.user_name from t_user u,student s where u.user_id=s.uid and s.uid=#{uid};")
    public User getUserNameByUid(Integer uid);

    //保存聊天记录
    @Insert("insert into communication values(#{sendName},#{receiveName},#{message},default,'N')")
    public int addChatRecord(Communication communication);
    //获取向当前用户发送消息的用户的名字
    @Select("SELECT send_name from communication WHERE receive_name = #{userName} and is_read='N' GROUP BY send_name;")
    public List<String> getAnotherSideName(String userName);
    //获取未读消息数量
    @Select("SELECT COUNT(*) from communication WHERE receive_name = #{userName} and is_read='N';")
    public int getUnreadMsgCountByName(String userName);
    //更新聊天消息状态
    @Update("update communication set is_read='Y' where receive_name=#{userName} ")
    public int updateChatRecordStatus(String userName);
    //获取学员的签约的教练信息
    @Select("SELECT * from coach WHERE cid=#{cid};")
    public Coach getCoachByCid(Integer cid);
    boolean addMessage(Student student);
  /*==========================下面后台使用===================================*/

    List<Student> findStudentByPage(@Param("currentPage")Integer currentPage, @Param("pageSize") Integer pageSize, @Param("vo") StudentVo vo,@Param("gid") Integer gid);



    Integer deleteBack(Integer[] stuIds);

    //根据学生id找到其所有的订单
    @Select("SELECT * FROM student_order where stuid = #{stuid}")
    List<StudentOrder> getService(Integer stuid);

    int setCoachNull(@Param("stuid") Integer stuid);

    int setGymNull(Integer stuid);

/*    //生成订单
    void insertCorder(String outTradeNo, String subject, double cmoney, Integer uid);*/

    //=================================================


}