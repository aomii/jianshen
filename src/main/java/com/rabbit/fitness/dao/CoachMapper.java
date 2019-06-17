package com.rabbit.fitness.dao;

import com.rabbit.fitness.coach.vo.CoachVO;
import com.rabbit.fitness.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CoachMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(Coach record);

    int insertSelective(Coach record);

    Coach selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Coach record);

    int updateByPrimaryKey(Coach record);

    @Select("select * from student where cid=#{param2} limit #{param1}, #{param3}")
    List<Student> queryCoachByPage(Integer page, Integer cid,Integer count);

    @Select("select * from order where cid=#{cid}")
    List<Order> queryOrderByPage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize, @Param("cid") Integer cid);

    @Update("update  cocah set gids=#{param2} where cid=#{param1}")
    void signGym(Integer cid, Integer gid);


    List<Coach> queryCoachAll(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize,@Param("vo") CoachVO vo);

    /*查找教练名下学生*/
    @Select("select * from student where cid=#{cid}")
    List<Student> queryStudentByCid(int cid);

    @Select("select count(*) from coach")
    Integer selMaxIndex();

    @Select("select * from coach limit #{param1},#{param2}")
    List<Coach> roundSelCoach(int startIndex, Integer count);

    @Select("select * from coach where uid=#{uid}")
    Coach queryCoachByUid(Integer uid);

    Integer updateByUidKeySelective(Coach coach);

    @Insert("insert into communication(send_name,receive_name,message,is_read) values(#{sendName},#{receiveName},#{message},#{isRead})")
    void insertComm(Communication communication);

    @Select("SELECT * from communication  where (send_name=#{param1}  and  receive_name=#{param2}) or  (send_name=#{param2} and  receive_name=#{param1}) ORDER BY time desc limit #{param3},#{param4}")
    List<Communication> queryChatRecord(String sendName, String receiveName,Integer startIndex,Integer selCount);


    //查看教师余额
    @Select("select cmoney from coach where uid=#{uid}")
    double queryMoneyByUid(Integer uid);

    @Update("update coach set cmoney = cmoney + #{totalAmount} where uid=#{uid}")
    void updateCmoneyByUid(double totalAmount, Integer uid);


    @Select("select count(*) from student where cid=#{cid} ")
    Integer queryCountByCid(Integer cid);


    @Insert("insert into coach_order(conum,coname,coprice,uid) values(#{conum},#{coname},#{coprice},#{uid})")
    void insertCorder(String conum, String coname, double coprice, Integer uid);

    @Update("update coach_order set costate='已支付' where conum=#{conum}")
    void updCorderByConum(String conum);

    @Select("select * from coach_order where uid=#{uid} order by cotime desc")
    List<CoachOrder> queryCoachOrderByUid(Integer uid);

    @Select("select * from communication where receive_name=#{param1} and is_read=#{param2} GROUP BY receive_name ")
    List<Communication> showTips(Integer uid,String isReadNo);

    @Update("update communication set is_read=#{param3} where send_name=#{param1} and receive_name={param2}")
    void updateCommState(String sendName, String receiveName, String commYes);


    //================后台申请记录，成功后修改场馆===========================================
    @Update("update coach set gid=#{gid} where cid=#{cid}")
    Integer updateBack(Integer cid, Integer gid);
    //=================================================

}