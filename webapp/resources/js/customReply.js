/**
 * 
 */
//jsp에서 받은 게시판 번호 추출 정수로 변환
var cus_no = parseInt(document.getElementById("cus_no_hidden").value);
var m_id = document.getElementById("m_id_hidden").value;

var autoRefresh;

//시작시 목록 조회 
$(document).ready(function() {
    cusReplyList(cus_no); //페이지가 로드될 때 케시글 목록을 불러옴
});


/*댓글 조회 */
function cusReplyList(cus_no) {
    $.ajax({
        url: "/CusReplyListHandler",
        type: "POST",
        dataType: "json",
        data: {cus_no: cus_no},
        success: function(data) {
	
            $("#replyArea").empty();
           var cus_re_list = data.cus_re_list;
            
            cus_re_list.forEach(function(item) {
                let m_id_db = item.m_id;
                let m_nickname = item.m_nickname;
                let cus_no = item.cus_no;
                let cus_re_no = item.cus_re_no;
                let cus_re_regdate = item.cus_re_regdate;
                let cus_re_content = item.cus_re_content;
                let replyItem =
                    '<br>' +   
                    '<br>' +
                    '<tr class="reply-deatil-content">' +
                    '<td><b>' + m_nickname + '&nbsp;&nbsp;&nbsp;</b></td>' +  
                    '<td colspan="3">' + cus_re_content  + '</td>' +
                    '</tr>' +
                    '<br>' +
                    '<tr>' +
                    '<td>' + cus_re_regdate + '&nbsp; &nbsp; &nbsp;</td>' +
                    '<td><button class="reply_bnt" onclick="updateReply(' + cus_re_no + ')" style="display: ' + (m_id === m_id_db ? 'inline-block' : 'none') + '">수정</button></td>' +
                    '<td><button class="reply_bnt" onclick="deleteReply(' + cus_re_no + ')" style="display: ' + (m_id === m_id_db ? 'inline-block' : 'none') + '">삭제</button></td>' +
                    '</tr>' +
                    '<br>';
                    
                $("#replyArea").append(replyItem);
            });
        },
        error: function() {
            alert("에러");
        }
    });
}

/*댓글 작성 */
function insertReply(){
    var replyContent = $("#reply").val();
    var data = {
		m_id: m_id,
    	cus_no: cus_no,
        cus_re_content: replyContent
    };
    $.ajax({
        url : "/CusReplyAddHandler",
        data : JSON.stringify(data),
        type : "POST",
        dataType: "json",
   }), $(function(){
    autoRefresh = setInterval(function() {
        cusReplyList(cus_no);
    }, 1000); //목록 갱신 시작
    });
    clearInterval(autoRefresh); //갱신 함수 중지
}

/*댓글 수정 버튼 작동*/
function updateReply(cus_re_no) {
    var editedContent = $("#replyArea").find("tr.reply-deatil-content[data-cus-re-no='" + cus_re_no + "']").find("td:nth-child(2)").text().trim();
    $("#editedReply").val(editedContent);
    $("#editModal").css("display", "block");
    $("#saveBtn").attr("data-cus-re-no", cus_re_no);
    $(function(){
    autoRefresh = setInterval(function() {
        cusReplyList(cus_no); //목록 갱신 시작
    }, 1000);
    });
    clearInterval(autoRefresh); //갱신 함수 중지
}


/*댓글 수정*/
function saveEditedReply() {
    var cus_re_no = $("#saveBtn").attr("data-cus-re-no");
    var editedContent = $("#editModal input#editedReply").val(); 
    var data = { cus_re_content: editedContent, cus_re_no: cus_re_no };
    
    $.ajax({
        url: "/CusReplyUpdateHandler",
        data: JSON.stringify(data),
        type: "POST",
        dataType: "json",
    });
    $("#editModal").css("display", "none");
}

/* 댓글 삭제 */
function deleteReply(cus_re_no) {
    $.ajax({
        url: "/CusReplyDeleteHandler",
        data: {cus_re_no: cus_re_no},
        type: "POST",
        dataType: "json",
    }), $(function(){
        autoRefresh = setInterval(function() {
            cusReplyList(cus_no);
        }, 1000); //목록 갱신 시작
    });
    clearInterval(autoRefresh); //갱신 함수 중지
}

