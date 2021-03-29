//初期画面表示時に起動
//資格の重複を削除

$(document).ready(function() {

	let obj = document.getElementById("qualificationList").value;
	parseobj = JSON.parse(obj);
	var array =[];
	for (var item in parseobj) {
		if (array.length > 0 && array.indexOf(parseobj[item]['qualification']) >= 0){
		}else{
			array.push(parseobj[item]['qualification']);
		}
	}
	 for(var i=0;i<array.length;i++){
		let op = document.createElement("option");
		op.text = array[i];
		document.getElementById("qualification").appendChild(op);
	 }

	 //初回分のVersion作成
	 qualificationChange();
})

//資格を選択したときに、資格に合わせたVersion選択を作成
function qualificationChange(){
	let obj = document.getElementById("qualificationList").value;
	parseobj = JSON.parse(obj);
	let qualification = document.getElementById("qualification").value
	var array =[];
	array.push("---");
	for (var item in parseobj) {
		if (parseobj[item]['qualification'] == qualification && parseobj[item]['varsion']!==null){
			array.push(parseobj[item]['varsion']);
		}
	}
	//selectの初期化
	var list = document.getElementById("varsion");
	for( i = list.length -1; i >= 0; i-- ) {
		list.remove(i);
	}
	//selectに再設定
	for(var i=0;i<array.length;i++){
		let op = document.createElement("option");
		op.text = array[i];
		document.getElementById("varsion").appendChild(op);
	}
}