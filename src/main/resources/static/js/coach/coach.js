function randomSelCoach() {
    $.ajax({
        url:"coach/randomSelCoach",
        type:"get",
        success:function(data){
            var content ="";
            content+=
        "<div class='col-md-4 menuItem'>"+
                "<ul class='menu'> <li>"+

                "<div><img src='" + data[0].cpic+ " ' width='100%'/></div>"+
                "<div class='detail'><b>"+data[0].cname+"</b><span class='price'>"+data[0].type+"</span></div>"+
            "</li>"+
            "<li>"+
            "<div><img src='" + data[1].cpic+ "' width='100%'/></div>"+
                "<div class='detail'><b>"+data[1].cname+"</b><span class='price'>"+data[1].type+"</span></div>"+
            "</li>  </ul> </div>"+


            "<div class='col-md-4 menuItem'>"+
                "<ul class='menu'>"+
                "<li>"+
                "<div><img src='" + data[2].cpic+ "' width='100%'/></div>"+
                "<div class='detail'><b>"+data[2].cname+"</b><span class='price'>"+data[2].type+"</span></div>"+
            "</li> <li>"+

            "<div><img src='" + data[3].cpic+ "' width='100%'/></div>"+
                "<div class='detail'><b>"+data[3].cname+"</b><span class='price'>"+data[3].type+"</span></div>"+
            "</li> </ul>  </div>"+


            "<div class='col-md-4 menuItem'>"+
                "<ul class='menu'>"+
                "<li>"+
                "<div><img src='" + data[4].cpic+ "' width='100%'/></div>"+
                "<div class='detail'><b>"+data[4].cname+"</b><span class='price'>"+data[4].type+"</span></div>"+
            "</li>"+
            "<li>"+
            "<div><img src='" + data[5].cpic+ "' width='100%'/></div>"+
                "<div class='detail'><b>"+data[5].cname+"</b><span class='price'>"+data[5].type+"</span></div>"+
            "</li> </ul>  </div>"

            $("#randomSelCoachList").html(content);
        }
    });
}



function mystulist() {
    var myname=$("#myuname").val();
    $.ajax({
        url:"coach/myStuList",
        type:"post",
        data:{
            "uid":myname,
            "page":$("#nowPage").val(),
        },
        success:function(data){
            var stu = data;
            var content="";
            for(var i=0;i<stu.length;i++){
                content+=
                    "<form action='web/websocket/"+myname+"'>"+
                    "<div class='col-md-4 menuItem' >"+
                    "<input type='hidden' name='uid' value="+data[i].uid+" >"+
                    "<ul class='menu'> <li>"+
                    "<div style='height: 33px'>" +
                    "<div id='tips"+data[i].uid+"' style='border-radius:50%;width: 30px;height: 30px;float: right;border: 1px solid red;text-align: center;display: none'>!</div>"+
                    "</div>"+
                    "<button type='submit'>"+
                    "<div><img src='"+ data[i].stupic+"'  width='100%'/></div>"+
                    "<div class='detail'><b>"+data[i].stuname+"</b><span class='price'>"+data[i].stugender+"</span></div>"+
                    "</li>"+
                     "</ul> </div> </button> </form>"
            }
            $("#mystulist").html(content);
            showTips();
        },

    });
}

function showTips() {
    var myname=$("#myuname").val();
    $.ajax({
        url:"coach/showTips",
        type:"post",
        data:{
            "uid":myname,
        },
        success:function(data){
            for (var i=0;i<data.length;i++){
                var id = data[i].sendName;
                $("#tips"+id+"").css("display","block");
            }
        },

    });
}

function  insertComm() {
    $.ajax({
        url:"/coach/insertComm",
        data:{
            "message":$("#sayText").val(),
            "sendName":$("#myname").val(),
            "receiveName":$("#oname").val(),

        }
    })
}
function updateChatRecordStatus(userName) {
        $.ajax({
            url:"/student/updateChatRecordStatus",
            type:"post",
            data:{"userName":userName},
            success:function (data) {}
        })
}
function showChatRecord() {
    var sendName = $("#myname").val();
    var receiveName = $("#oname").val();
    $.ajax({
        url:"/coach/showChatRecord",
        type:"post",
        data:{
            "sendName":sendName,
            "receiveName":receiveName
        },
        success:function (data) {
            var arr =data;
            var content="";
            for(var i=arr.length-1;i>=0;i--){
                if(arr[i].sendName==sendName){
                    content+=
                     "<div class='question'>"+
                    "<div class='heard_img right'><img src='/img/coach/coach_chart_images/dglvyou.jpg'></div>"+
                    "<div class='question_text clear'><p>"+arr[i].message+"</p><i></i>"+
                    "</div></div>"
                }
                if(arr[i].sendName==receiveName){
                    content+=
                    "<div class='answer'>"+
                    "<div class='heard_img left'><img src='/img/coach/coach_chart_images/dglvyou.jpg'></div>"+
                    "<div class='answer_text'><p>"+arr[i].message+"</p><i></i>"+
                    "</div></div>"
                }
            }
            $('.speak_box').append(content);
            //更新查看聊天消息后的状态
            updateChatRecordStatus(sendName);
        }
    })
}


function uodateChatState() {
    var sendName = $("#oname").val();
    var receiveName = $("#myname").val();
    $.ajax({
        url:"/coach/uodateChatState",
        type:"post",
        data:{
            "sendName":sendName,
            "receiveName":receiveName
        },
        success:function (data) {}
    })
}

function firPage() {
    var nowPage=$("#nowPage").val();
    nowPage=Number(1);
    $("#nowPage").val(nowPage);
    mystulist();
}

function previousPage() {
    var nowPage=$("#nowPage").val();
    var maxPage=$("#maxPage").val();
    maxPage=Number(maxPage);
    nowPage=Number(nowPage)-1;
    if(nowPage<=0){
        nowPage=1;
    }
    $("#nowPage").val(nowPage);
    mystulist();
}

function nextPage() {
    var nowPage=$("#nowPage").val();
    var maxPage=$("#maxPage").val();
    maxPage=Number(maxPage);
    nowPage=Number(nowPage)+1;
    if(nowPage>maxPage){
        nowPage=maxPage;
    }
    $("#nowPage").val(nowPage);
    mystulist();
}

function lastPage() {
    var nowPage=$("#nowPage").val();
    var maxPage=$("#maxPage").val();
    $("#nowPage").val(maxPage);
    mystulist();
}



function showPage() {
    var myname=$("#myuname").val();
    $.ajax({
        url:"coach/showPage",
        type:"post",
        data:{
            "uid":myname,
        },
        success:function(data){
            var allPage="/"+data+"页";
            var nowPage=$("#nowPage").val();
            $("#maxPage").val(data);
            $("#PageBoxesNowPage").html(nowPage);
            $("#PageBoxesAllPage").html(allPage);
        },

    });
}