function post(){
    var questionId=$("#question_id").val();
    var commentContent=$("#comment-content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": questionId,
            "content": commentContent,
            "type": 1
        }),
        success:function(response){
            if(response.code==200){
                $("#comment_section").hide();
            }else{
                alert(response.message);
            }
        },
        dataType:"json"
    });

}