<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>開始</title>
</head>
<body>
	<a href="/EC_site/Main">一覧画面へ</a>
	
	<p>商品登録</p>
	<form action="/EC_site/Main" method="post" enctype="multipart/form-data">
	商品コード：<input type="number" name="product_number"><br>
	商品名：<input type="text" name="product_name"><br>
	カテゴリコード：<input type="number" name="category_code"><br>
	価格：<input type="number" name="product_price"><br>
	おすすめ：<input type="number" name="recommend"><br>
	開始日：<input type="text" name="valid_start_date"><br>
	終了日：<input type="text" name="valid_end_date"><br>
	削除フラグ：<input type="number" name="delete_flg"><br>
	画像：<input type="file" name="product_img">
	<button type="submit">登録</button>
	</form>
</body>
</html>