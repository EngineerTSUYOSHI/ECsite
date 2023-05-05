<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dto.*"%>
<%
	PurchaseDTO dto = (PurchaseDTO)request.getAttribute("dto");
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
        <table border='1'>
            <tr>
                <th>商品名（申込番号）</th>
                <th>価格（税込）</th>
                <th>数量</th>
                <th>小計</th>
            </tr>
            <tr>
                <td>
                    <div class="product">
                        <img src="/EC_site/upload/<%=dto.getProduct().getProduct_img() %>">
                        <p><%=dto.getProduct().getProductName() %>（申込番号）</p>
                    </div>
                </td>
                <td><%=dto.getProduct().getProductPrice() %>円</td>
                <td><input type="text" maxlength="2" name='quantity' method='post' id='quantity' size='4'><span>個</span></td>
                <td><input value="" name="total_price" type="text" id="total_price" size='10' readonly>円</td>
                
            </tr>
        </table>

        <div class=button>
            <a>購入</a>
            <a>商品一覧へ<br>戻る</a>
        </div>
    </main>
    <footer>
        <p>copyright@Web開発3.05</p>
    </footer>
    <script>
    	//getElementByIdで数量と合計額の変数を用意
	    var quantity = document.getElementById("quantity");
		var total_price = document.getElementById("total_price");
	    //数量が入力されたら合計額を計算して表示
	    quantity.addEventListener("change",function(){
	      total_price.value = this.value * <%=dto.getProduct().getProductPrice() %>
	    });
    </script>
</body>

</html>