<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dto.*"%>
<%
	List<Product> list = (List<Product>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>架空ECサイト</title>
<link rel="stylesheet" href="css/stylesheet.css">
</head>
<body>
    <header>
        <!-- ロゴ表示 -->
        <p>架空ECサイト</p>
    </header>
    
    <main>
        <p>検索</p>
        
        <div>
            <label>商品名：</label>　<input type="text">
            <label>カテゴリ：</label>　<input type="text">
        </div>

        <div>
            <label>価格：</label>　<input type="text"><label>円 〜　</label><input type="text"><label>円</label> 
        </div>

        <div class="search">
        <input class="btn" type="submit" value="検索">
        </div>

        <!-- 水平線をひく -->
        <hr>

        <!-- ここからは商品情報を表示する箇所 -->

        <div　class="seni">
            <label>並び順：</label><input type="text" value="おすすめ順">
            <a href="index.html">最後へ</a>
            <a href="index.html">次へ</a>
            <a href="index.html">5</a>
            <a href="index.html">4</a>
            <a href="index.html">3</a>
            <a href="index.html">2</a>
            <a href="index.html">1</a>
            <a href="index.html">前へ</a>
            <a href="index.html">最初へ</a>
        </div>
        
        
        <div class="parent">
        	<%for(Product p:list){ %>
            <div class="child">
            	<img src="/EC_site/upload/<%=p.getProduct_img()%>">
                <p><%=p.getProductName() %></p>
                <p><%=p.getProductPrice() %></p>
            </div>
            <%} %>
        </div>
    
    </main>
    <footer>
        <p>copyright@Web開発3.05</p>
    </footer>
</body>
</html>
