//初回の値を設定する
$(document).ready(function() {
	alert("開始");
	document.question.value="初回の問題";
})

//次の問題押下
function nextQuestion(){
	alert("次");
	document.question.value="次の問題";
}

//前の問題押下
function backQuestion(){
	alert("前");
	document.question.value="前の問題";
}