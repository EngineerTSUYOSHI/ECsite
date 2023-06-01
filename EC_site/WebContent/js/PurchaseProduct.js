/* 入力値チェック */
function checkChar(quantity,getPrice){
	var regex = /^[\d\s]*$/
	var quantity = document.getElementById("quantity");
	var total_price = document.getElementById("total_price");
    if(!regex.test(quantity.value)){
        alert("半角数字を入力してください");
        quantity.value="";
        total_price.value="";
        return;
    }else if(parseInt(quantity.value.length) > 2){
    	alert("2桁以内で入力して下さい");
    	quantity.value="";
    	total_price.value="";
        return;
    /* 入力値に問題なければ小計を計算 */
    }else{
	    total_price.innerHTML = (quantity.value * getPrice).toLocaleString();
    }
}
		
/* 購入ぼたんを押した場合 */
const goToComplete = function() {
    const purchaseButton = document.getElementById('purchaseButton');
    const popup = document.getElementById('popup');
    const yesButton = document.getElementById('yesButton');
    const noButton = document.getElementById('noButton');
	/* 購入ボタンを押したらポップアップウィンドを表示 */
    purchaseButton.addEventListener('click', () => {
        popup.style.display = 'block';
    });
    /* はいを押した場合。　※処理内容は完了画面の作成時に記入 */
    yesButton.addEventListener('click', () => {
        popup.style.display = 'none';
        document.complete.submit();
    });
    /* いいえボタンを押した場合はポップアップウィンドを非表示 */
    noButton.addEventListener('click', () => {
        popup.style.display = 'none';
    });
};
const purchaseButton = document.getElementById('purchaseButton');
purchaseButton.addEventListener('click', () => {
    const popup = document.getElementById('popup');
    popup.style.display = 'block';
});
/* 戻るボタンを押した場合 */
const returnButton = document.getElementById('returnButton');
returnButton.addEventListener('click', () => {
    document.myform.submit(); // フォームを送信
});

window.onload = function(){
	var priceElement = document.getElementById("price");
	priceElement.innerText = Number(priceElement.innerText).toLocaleString();
	
	var totalPriceElement = document.getElementById("total_price");
	totalPriceElement.innerText = Number(totalPriceElement.innerText).toLocaleString();
};
