<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dto.*"%>
<%
	ProductListDTO dto = (ProductListDTO)request.getAttribute("dto");
	String lowPrice = (String)request.getAttribute("lowPrice");
	String upPrice = (String)request.getAttribute("upPrice");
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
	            <label>商品名：</label><input type="text" name='product_name' value=<%=dto.getSearch().getProductName() %>>
	            <label>カテゴリ：</label>
	            <!-- カテゴリのプルダウンリストを表示 -->
	            <select name="category_code" id="category_code">
	            	<!-- ProductListDTOからCategoryの要素を代入 -->
	            	<% ArrayList<Category> categorys = dto.getCategorys(); %>
	                <% for(int i=0; i < categorys.size() ;i++){%>
	    				<option value=<%=i %>><%=categorys.get(i).getCategoryName() %></option>
	    			<% } %>
	            </select>
	        </div>
	        <div>
	            <label>価格：</label> 
	            <input type="text" value="<%=lowPrice %>" name="lowPrice" maxvalue="7" id="lowPrice" onblur="checkChar('lowPrice')" >
	            <label>円 〜 </label>
	            <input type="text" value="<%=upPrice %>" name="upPrice" maxvalue="7" id="upPrice" onblur="checkChar('upPrice')" >
	            <label>円</label>
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
	            <a id='last_page' value='5' onclick='pageClick(5)'>最後へ</a>
	            <a id='next_page' value=' ' onclick='pageClick("next")'>次へ</a>
	            <a id='page5' value='5' onclick='pageClick(5)'>5</a>
	            <a id='page4' value='4' onclick='pageClick(4)'>4</a>
	            <a id='page3' value='3' onclick='pageClick(3)'>3</a>
	            <a id='page2' value='2' onclick='pageClick(2)'>2</a>
	            <a id='page1' value='1' onclick='pageClick(1)'>1</a>
	            <a id='prev_page' value=' ' onclick='pageClick("prev")'>前へ</a>
	            <a id='first_page' value='1' onclick='pageClick(1)'>最初へ</a>
	            <input type='hidden' value="" id='now_page' name='now_page'>
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
	                <a class='purchase_link' id='purchase_id' value="<%=p.getProductNumber()%>" onclick='purchaseClick(<%=p.getProductNumber()%>)'><%=p.getProductName() %></a>
	                <p><%=p.getProductPrice() %></p>
	            </div>
	           <% } %>
	        <% } %> 
	        <form action='/EC_site/PurchaseController' method='post' name='purchase'>
	        	<input type='hidden' value='' id='product_number' name='product_number'>
	        </form>
        </div>
    </main>
    <footer>
        <p>copyright@Web開発3.05</p>
    </footer>
    <!-- おすすめ順のプルダウンリストが変わったら画面遷移を行う -->
    <script>
	    var recommend = document.getElementById('recommend');
		/* 並び順で選ばれた項目にselected属性を追加 */
	    recommend.options[<%=dto.getSearch().getRecommendCode()%> - 1].selected = true;
	    recommend.addEventListener('change', function() {
	      //submit()でフォームの内容を送信
	      document.myform.submit(); 
	    })
	    var category_code = document.getElementById('category_code');
	    category_code.options[<%=dto.getSearch().getCategoryCode()%>].selected = true;
		/* 5ページ目にいる場合、次へと最後へを非表示 */
	    if(<%=dto.getNow_page()%> === 5){
	        document.getElementById('last_page').style.display = 'none';
	        document.getElementById('next_page').style.display = 'none';
	    }
	    /* 1ページ目にいる場合、前へと最初へを非表示 */
	    if(<%=dto.getNow_page()%> === 1){
	        document.getElementById('first_page').style.display = 'none';
	        document.getElementById('prev_page').style.display = 'none';
	    }
	    
	    function pageClick(page_num){
	    	let nowPage = document.getElementById('now_page');
			if(page_num === 'next' && page_num != 5){
					nowPage.value = <%=dto.getNow_page()%> + 1;
			}else if(page_num === 'prev'){
				nowPage.value = <%=dto.getNow_page()%> - 1;
			}else{
				nowPage.value = page_num;
			}
	        document.myform.submit();	        
		}
		
		function purchaseClick(product_num){
			let productNumber = document.getElementById('product_number');
			productNumber.value = product_num; 
			document.purchase.submit();
		}
		
		function checkChar(priceType){
			var regex = /^[\d\s]*$/
			var priceInput = document.getElementById(priceType);
	        if(!regex.test(priceInput.value)){
	            alert("半角数字を入力してください");
	            priceInput.value="";
	            return;
	        }
		}		
  	</script>
</body>
</html>
