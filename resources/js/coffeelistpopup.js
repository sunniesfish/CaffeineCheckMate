function viewDetail(element) {
	const item = element.target.closest(".coffeelist-item");
	const cno = item.getAttribute("value");
	const cname = item.getAttribute("coffeeName");
    console.log(cno);
	window.open(
		"/coffeedetailpop.do?"+cno,
		cname,
		"width=600, height=400"
	);
}


const cB = document.querySelector(".coffee-box");
const cI = cB.children;
console.log(cI);
for (let i=0; i<cI.length; i++){
	let citem = cI[i].querySelector(".coffeelist-item__clicks");
	if(citem != null && citem != undefined) {
		citem.addEventListener("click",viewDetail);
	}
}