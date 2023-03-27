<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dto.*"%>
<%
	List<Product> list = (List<Product>)request.getAttribute("list");
	List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
	List<Category> recommendList = (List<Category>)request.getAttribute("recommendList");
	/* ArrayList<Object> list = (ArrayList<Object>)request.getAttribute("list"); */ 
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
        <form action='/EC_site/Search' method='post' name='myform'>
	        <div>
	            <label>商品名：</label>　<input type="text" name='product_name'>
	            <label>カテゴリ：</label>　
	            <!-- カテゴリのプルダウンリストを表示 -->
	            <select name="category_code">
	                <% for(int i=0; i < categoryList.size();i++){%>
	    				<option value=<%=i %>><%=categoryList.get(i).getCategory_name() %></option>
	    			<%} %>
	            </select>
	        </div>
	
	        <div>
	            <label>価格：</label> <input type="text" name='minPrice'  value=1><label>円 〜 </label><input type="text" name='maxPrice' value=9999999><label>円</label> 
	        </div>
				
	        <div class="search">
	        	<input class="btn" type="submit" value="検索">
	        </div>
        
	        <!-- 水平線をひく -->
	        <hr>
	
	        <!-- ここからは商品情報を表示する箇所 -->

	        <div　class="seni">
	            
		            <label>並び順：</label>
		            <select name="recommend" id='recommend'>
		                <% for(int i=0; i < recommendList.size();i++){%>
		    				<option value=<%=i %>><%=recommendList.get(i).getCategory_name() %></option>
		    			<%} %>
		            </select>
	            
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
        <!-- 検索のフォーム閉じタグ -->
        </form>
		<!-- 商品一覧の表示テーブル -->
        <div class="my-parts">
            <%for(Product p:list){ %>
            <div>
                <img src="/EC_site/upload/<%=p.getProduct_img()%>">
                <p><%=p.getProductName() %></p>
                <p><%=p.getProductPrice() %></p>
            </div>
           <% } %>
        </div>
        
    </main>
    <footer>
        <p>copyright@Web開発3.05</p>
    </footer>
    <!-- おすすめ順のプルダウンリストが変わったら画面遷移を行う -->
    <script>
	    var recommend = document.getElementById('recommend');
	    recommend.addEventListener('change', function() {
	      //submit()でフォームの内容を送信
	      document.myform.submit(); 
	    })
  	</script>
</body>
</html>
