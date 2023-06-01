<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dto.*"%>
<%
	CompleteDTO completeDTO = (CompleteDTO)request.getAttribute("completeDTO");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品購入画面</title>
<link rel="stylesheet" href="css/completeStyle.css">
</head>
<body>
    <header>
        <!-- ロゴ表示 -->
        <p>架空ECサイト</p>
    </header>
    <main>
    	<%if(completeDTO != null){ %>
        <div class="product">
            <img src="/EC_site/upload/<%=completeDTO.getProductImg() %>">
            <label><%=completeDTO.getProductName() %></label>
            <label>(申込番号：<%=completeDTO.getEntryNumber() %>)</label>
        </div>    
                
        <p>商品の購入が完了しました。</p>
        
        <%}else{ %>
        <p>商品の購入に失敗しました。</p>
        <p>もう一度、最初から商品を選択してください。</p>
        <%} %>
        
        <div class="button">
			<button type="button" id="returnButton" onclick="goToProductList()">商品一覧へ<br>戻る</button>
        </div>
       
        <!-- 戻る時に必要な検索条件 -->
    	<form action='/EC_site/ProductListController' method='post' name='myform'>
        <input type="hidden" value="<%=completeDTO.getSearchProductName() %>" name="productNumber">
        <input type="hidden" value="<%=completeDTO.getEntryNumber() %>" name="applicationNumber">
        <input type="hidden" value="<%=completeDTO.getSearchProductName() %>" name="product_name">
        <input type="hidden" value="<%=completeDTO.getSearchCategoryCode() %>" name="category_code">
        <input type="hidden" value="<%=completeDTO.getSearchLowPrice() %>" name="lowPrice">
        <input type="hidden" value="<%=completeDTO.getSearchUpPrice() %>" name="upPrice">
        <input type="hidden" value="<%=completeDTO.getSearchRecommendCode() %>" name="recommend">
        <input type="hidden" value="<%=completeDTO.getSearchPageNumber() %>" name="now_page">
        </form>
    </main>
    <footer>
        <p>copyright@Web開発3.05</p>
    </footer>

    <script>
	    const returnButton = document.getElementById('returnButton');
	    returnButton.addEventListener('click', () => {
	        document.myform.submit(); // フォームを送信
	    });
    </script>
</body>
</html>