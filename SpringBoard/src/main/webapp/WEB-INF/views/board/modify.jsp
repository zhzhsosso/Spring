<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@ include file="../include/header.jsp" %>

     <h1>/board/modify.jsp</h1>
   
    <!-- 수정,삭제 bno정보를 전달하는 폼태그 -->
    <form role="form" method="post">
       
    <%-- ${vo } --%>
	<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title"> 게시판 수정페이지 </h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
              <div class="box-body">
                <div class="form-group">
                  <label for="aaa">글 번 호</label>
                  <input type="text" name="bno" class="form-control" id="aaa" value="${vo.bno }" readonly>
                </div>
                
                <div class="form-group">
                  <label for="aaa">제  목</label>
                  <input type="text" name="title" class="form-control" id="aaa" value="${vo.title }" >
                </div>
                
                <div class="form-group">
                  <label for="exampleInputPassword1">작 성 자</label>
                  <input type="text" name="writer" class="form-control" id="exampleInputPassword1" value="${vo.writer }" >
                </div>
                
                <div class="form-group">
                  <label>내   용</label>
                  <textarea name="content" class="form-control" rows="3" placeholder="내용을 입력하세요." >${vo.content }</textarea>
                </div>
      
      
          
              </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-danger">수정</button>
                <button type="reset" class="btn btn-warning">취소</button>
              </div>
          </div>
           </form>
          
          <!-- jQuery 사용한 페이지 이동 -->
          
<!-- <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script> -->
<script>
 $(document).ready(function(){
	
	 $(".btn-warning").click(function(){
		 // 목록으로 이동
		 //location.href="/board/list";
		 location.href="/board/read?bno=${vo.bno}";
	 });
	 
 });

</script>
          
          
          
          
          
          
   <%@ include file="../include/footer.jsp" %>