<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dto.*"%>
<%
	PurchaseDTO purchaseDTO = (PurchaseDTO)request.getAttribute("purchaseDTO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品購入画面</title>
<link rel="stylesheet" href="css/purchaseStyle.css">
</head>
<body>
    <header>
        <!-- ロゴ表示 -->
        <p>架空ECサイト</p>
    </header>
    <main>
    	<!-- 戻る時に必要な検索条件 -->
    	<form action='/EC_site/ProductListController' method='post' name='myform'>
        <input type="hidden" value="<%=purchaseDTO.getProduct().getProductNumber() %>" name="productNumber">
        <input type="hidden" value="<%=purchaseDTO.getApplicationNumber() %>" name="applicationNumber">
        <input type="hidden" value="<%=purchaseDTO.getProductName() %>" name="product_name">
        <input type="hidden" value="<%=purchaseDTO.getSearchCategoryCode() %>" name="category_code">
        <input type="hidden" value="<%=purchaseDTO.getSearchLowPrice() %>" name="lowPrice">
        <input type="hidden" value="<%=purchaseDTO.getSearchUpPrice() %>" name="upPrice">
        <input type="hidden" value="<%=purchaseDTO.getOrderCode() %>" name="recommend">
        <input type="hidden" value="<%=purchaseDTO.getNowPage() %>" name="now_page">
        </form>
        <table border='1'>
            <tr>
                <th>商品名（申込番号）</th>
                <th>価格（税込）</th>
                <th>数量</th>
                <th>小計</th>
            </tr>
            <!-- 商品情報がある場合 -->
            <%if(purchaseDTO.getProduct() != null){ %>
            <tr>
                <td>
                    <div class="product">
                        <img src="/EC_site/upload/<%=purchaseDTO.getProduct().getProduct_img() %>">
                        <label><%=purchaseDTO.getProduct().getProductName() %><br>
                        	（<%=purchaseDTO.getApplicationNumber() %>)</label>
                    </div>
                </td>
                <td><%=purchaseDTO.getProduct().getProductPrice() %>円</td>
                <td>
                <input type="text" value="<%=purchaseDTO.getQuantity() %>" maxlength="2" name='quantity' method='post' id='quantity' size='4' 
                	onblur="checkChar('quantity',<%=purchaseDTO.getProduct().getProductPrice() %>)"><span>個<br></span>
                <label style="color: red;"><%=purchaseDTO.getQuantityErrorMessage() %></label>
                </td>
                <td><label id="total_price"><%=purchaseDTO.getTotalPrice() %></label></label>円</td>
            </tr>
	  	</table>
        <div class="button">
            <button type="button" id="purchaseButton" onclick="goToComplete()">購入</button>
			<button type="button" id="returnButton" onclick="goToProductList()">商品一覧へ<br>戻る</button>
        </div>        
        <!-- 商品情報が無い場合 -->
        <%}else{ %>
       		<tr>
       			<td colspan="4">
       				<label style="color: red;"><%=purchaseDTO.getErrorMessage() %></label>
       			</td>
       		</tr>
       	</table>
       	<div class="button">
            <button id="returnButton" >商品一覧へ<br>戻る</button>
        </div>
        <%} %>
    </main>
    <footer>
        <p>copyright@Web開発3.05</p>
    </footer>
    <!-- ポップアップウィンド -->
    <div class="popup" id="popup">
        <div class="popup-header">
          <h4>購入確認</h4>
        </div>
        <div class="popup-content">
          <p>購入しますか？</p>
        </div>
        <div class="button-container">  
          <button id="yesButton">はい</button>
          <button id="noButton">いいえ</button>
        </div>
    </div>
	<script src="/EC_site/js/PurchaseProduct.js"></script>	
</body>
</html>