<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dto.*"%>
<%
	ProductListDTO dto = (ProductListDTO)request.getAttribute("dto");
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
        <form action='/EC_site/ProductListController' method='post' name='myform'>
	        <div>
	            <label>商品名：</label>　<input type="text" name='product_name'>
	            <label>カテゴリ：</label>　
	            <!-- カテゴリのプルダウンリストを表示 -->
	            <select name="category_code">
	            	<!-- ProductListDTOからCategoryの要素を代入 -->
	            	<% ArrayList<Category> categorys = dto.getCategorys(); %>
	                <% for(int i=0; i < categorys.size() ;i++){%>
	    				<option value=<%=i %>><%=categorys.get(i).getCategoryName() %></option>
	    			<% } %>
	            </select>
	        </div>
	        <div>
	            <label>価格：</label> <input type="text" name='lowPrice'  ><label>円 〜 </label><input type="text" name='upPrice' ><label>円</label> 
	        </div>
	        <div class="search">
	        	<input class="btn" type="submit" value="検索">
	        </div>
	        <!-- 水平線をひく -->
	        <hr>
	        <!-- ここからは商品情報を表示する箇所 -->
	        <div class="seni">
		            <label>並び順：</label>
		            <select name="recommend" id='recommend'>
		            <!-- ProductListDTOからRecommendの要素を代入 -->
		            	<% ArrayList<Category> recommends = dto.getRecommends();%>
		                <% for(int i=0; i < recommends.size() ;i++){%>
		    				<option value=<%=i+1 %>><%=recommends.get(i).getCategoryName() %></option>
		    			<%} %>
		            </select>
	            <a id='page_num' value='5' onclick='pageClick(5)'>最後へ</a>
	            <a id='page_num' value=' ' onclick='pageClick("next")'>次へ</a>
	            <a id='page_num' value='5' onclick='pageClick(5)'>5</a>
	            <a id='page_num' value='4' onclick='pageClick(4)'>4</a>
	            <a id='page_num' value='3' onclick='pageClick(3)'>3</a>
	            <a id='page_num' value='2' onclick='pageClick(2)'>2</a>
	            <a id='page_num' value='1' onclick='pageClick(1)'>1</a>
	            <a id='page_num' value=' ' onclick='pageClick("prev")'>前へ</a>
	            <a id='page_num' value='1' onclick='pageClick(1)'>最初へ</a>
	            <input type='hidden' value='1' id='now_page' name='now_page'>
	        </div>
        <!-- 検索のフォーム閉じタグ -->
        </form>
		<!-- 商品一覧の表示テーブル -->
        <div class="my-parts">
			<% ArrayList<Product> products = dto.getProducts(); %>
            <% if(products == null || products.isEmpty()){%>
            	<p>該当する商品はありません</p>
            <% }else{ %>
	            <%for(Product p: products){ %>
	            <div>
	                <img src="/EC_site/upload/<%=p.getProduct_img()%>">
	                <p><%=p.getProductName() %></p>
	                <p><%=p.getProductPrice() %></p>
	            </div>
	           <% } %>
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
	    function pageClick(page_num){
	    	let nowPage = document.getElementById('now_page');
			if(page_num === 'next'){
				nowPage.value = <%=dto.getNow_page()%> + 1;
			}else if(page_num === 'prev'){
				nowPage.value = <%=dto.getNow_page()%> - 1;
			}else{
				nowPage.value = page_num;
			}
			console.log(nowPage.value);
			document.myform.submit();
		}
  	</script>
</body>
</html>
