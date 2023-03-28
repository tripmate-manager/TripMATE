<%@ page import="com.tripmate.domain.PostVO" %>
<%@ page import="com.tripmate.common.util.DateUtil" %>
<%@ page import="com.tripmate.domain.CommentVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tripmate.domain.MemberDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/include/commonImport.jsp" %>
<%@ include file="/WEB-INF/jsp/common/bottomNavigationMenu.jsp" %>

<html>
<head>
    <jsp:include page="/WEB-INF/jsp/common/include/header.jsp">
        <jsp:param name="title" value="WishList"/>
    </jsp:include>
    <jsp:include page="/WEB-INF/jsp/common/messagePopUp.jsp"/>
    <jsp:include page="/WEB-INF/jsp/common/checkPopUp.jsp"/>
    <link rel="stylesheet" href="<%=Const.STATIC_CSS_PATH%>/wishlist/postMain.css"/>
    <script src="<%=Const.STATIC_JS_PATH%>/wishlist/postMain.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/popUp.js"></script>
    <script src="<%=Const.STATIC_JS_PATH%>/common/checkPopUp.js"></script>
</head>
<body>
<%
    final String COMMENT_DEPTH_1 = "1";
    final String COMMENT_DEPTH_3 = "3";

    PostVO postInfo = (PostVO) request.getAttribute("postInfo");
    List<CommentVO> commentList = (List<CommentVO>) request.getAttribute("commentList");

    MemberDTO memberInfo = null;
    session = request.getSession();
    if (session != null) {
        memberInfo = (MemberDTO) session.getAttribute(Const.MEMBER_INFO_SESSION);
    }
%>

<% if (memberInfo != null) { %>
<input type=hidden id="sessionMemberNo" value=<%=memberInfo.getMemberNo()%>>
<% } %>

<div class="wishlist_detail_wrap">
    <div class="wishlist_detail_title_wrap">
        <img class="icon_arrow_left" src="<%=Const.STATIC_IMG_PATH%>/common/icon_arrow_left.png"
             onclick="history.back()"/>
        <div class="wishlist_detail_title">WishList</div>
        <% if (postInfo != null && memberInfo != null) {
            if (postInfo.getRegistrationNo().equals(String.valueOf(memberInfo.getMemberNo()))) { %>
            <details class="flag">
                <summary class="ellipsis"></summary>
                <div class="report">
                    <p class="post_menu_delete" onclick="editPost()">수정하기</p>
                    <p class="post_menu_delete" onclick="deletePost()">삭제하기</p>
                </div>
            </details>
        <% }
        }%>
    </div>
    <div class="wishlist_detail_contents_wrap">
        <% if (postInfo != null) { %>
        <div id="postTypeCode" value="<%=postInfo.getPostTypeCode()%>" hidden></div>
        <div class="contents_wrap">
            <div class="post_title"><%=postInfo.getPostTitle()%>
            </div>
            <div class="post_type">
                <% if (ConstCode.POST_TYPE_CODE_LODGING.equals(postInfo.getPostTypeCode())) { %>숙소
                <% } else if (ConstCode.POST_TYPE_CODE_TOUR.equals(postInfo.getPostTypeCode())) { %>관광지
                <% } else if (ConstCode.POST_TYPE_CODE_RESTAURANT.equals(postInfo.getPostTypeCode())) { %>식당
                <% } else { %>기타<% } %></div>
        </div>
        <div class="post_date"><%=DateUtil.dateTimeFormat(postInfo.getRegistrationDateTime())%>
        </div>
        <div class="post_contents"><%=postInfo.getPostContents()%>
        </div>

        <% if (!ConstCode.POST_TYPE_CODE_ETC.equals(postInfo.getPostTypeCode())) { %>
        <div class="contents_wrap">
            <img class="icon_location" src="<%=Const.STATIC_IMG_PATH%>/common/icon_location.png"/>
            <div class="post_spot_address"><%=postInfo.getSpotAddress()%>
            </div>
        </div>
        <% } %>

        <div class="contents_wrap">
            <img class="icon_chain" src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_chain.png"/>
            <div class="post_info_url"><%=postInfo.getInformationUrl()%>
            </div>
        </div>
        <% } %>
    </div>

    <div class="wishlist_detail_divi_line"></div>

    <form name="commentForm" id="commentForm" method="post">
        <% if (postInfo != null) { %>
        <input type=hidden class="planNo" id="planNo" name="planNo" value=<%=postInfo.getPlanNo()%>>
        <input type=hidden class="postNo" id="postNo" name="postNo" value=<%=postInfo.getPostNo()%>>
        <% } %>

        <div class="wishlist_detail_comment_wrap">
            <% if (commentList != null) {
                for (CommentVO commentVO : commentList) {
                    String commentText = Const.Y.equals(commentVO.getUseYn()) ? commentVO.getCommentText() : "삭제된 댓글입니다."; %>

            <% if (COMMENT_DEPTH_1.equals(commentVO.getCommentDepth())) { %>
            <div class="comment_wrap">
                <input type=hidden class="commentNo" name="commentNo" value=<%=commentVO.getCommentNo()%>>
                <input type=hidden class="commenterNo" name="commenterNo" value=<%=commentVO.getCommenterNo()%>>
                <input type=hidden class="commentGroupNo" name="commentGroupNo" value=<%=commentVO.getCommentGroupNo()%>>
                <input type=hidden class="commentDepth" name="commentDepth" value=<%=commentVO.getCommentDepth()%>>
                <div class="comment_title_wrap">
                    <img class="icon_comment_rectangle"
                         src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_comment_rectangle.png"/>
                    <div class="comment_nickname"><%=commentVO.getNickName()%></div>
                    <details class="flag">
                        <summary class="ellipsis"></summary>
                        <div class="report">
                            <p class="comment_menu_delete" onclick="deleteComment(<%=commentVO.getCommentNo()%>)">댓글 삭제하기</p>
                        </div>
                    </details>
                </div>
                <div class="wishlist_comment_contents"><%=commentText%>
                </div>
            </div>
            <% } else { %>
            <div class="comment_reply_wrap">
                <input type=hidden class="commentNo" name="commentNo" value=<%=commentVO.getCommentNo()%>>
                <input type=hidden class="commenterNo" name="commenterNo" value=<%=commentVO.getCommenterNo()%>>
                <input type=hidden class="commentGroupNo" name="commentGroupNo" value=<%=commentVO.getCommentGroupNo()%>>
                <input type=hidden class="commentGroupNo2" name="commentGroupNo2" value=<%=commentVO.getCommentGroupNo2()%>>
                <input type=hidden class="commentDepth" name="commentDepth" value=<%=commentVO.getCommentDepth()%>>
                <img class="icon_arrow_comment" src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_arrow_comment.png"/>

                <% if (COMMENT_DEPTH_3.equals(commentVO.getCommentDepth())) { %>
                <div class="depth3_comment_reply_contents_wrap" name="comment_reply_contents_wrap" id="comment_reply">
                <% } else { %>
                <div class="comment_reply_contents_wrap" name="comment_reply_contents_wrap">
                <% } %>
                    <div class="comment_reply_title_wrap">
                        <img class="icon_comment_rectangle"
                             src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_comment_rectangle.png"/>
                        <div class="comment_reply_nickname_wrap"><%=commentVO.getNickName()%></div>
                        <details class="flag">
                            <summary class="ellipsis"></summary>
                            <div class="report">
                                <p class="comment_menu_delete" onclick='deleteComment("<%=commentVO.getCommentNo()%>", "<%=commentVO.getCommenterNo()%>")'>댓글 삭제하기</p>
                            </div>
                        </details>
                    </div>
                    <div class="wishlist_comment_contents"><%=commentText%></div>
                </div>
            </div>
            <% }
            }
        } %>
        </div>
        <div class="wishlist_detail_comment_input_wrap">
            <input type="text" class="commentText" id="commentText" name="commentText" placeholder="댓글을 입력하세요">
            <img class="icon_send" src="<%=Const.STATIC_IMG_PATH%>/wishlist/icon_send.png"/>
        </div>
    </form>
</div>
</body>