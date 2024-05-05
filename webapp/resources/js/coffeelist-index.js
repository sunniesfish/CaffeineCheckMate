const showFavBtn = document.querySelector(".coffeelist-column_3");
const classes = showFavBtn.classList;

if(isAuth){
    if(classes.contains("hidden")){
        showFavBtn.classList.remove("hidden");
    }
}else{
    if(!classes.contains("hidden")){
        showFavBtn.classList.add("hidden");
    }
}
showFavBtn.addEventListener("click",showFavs);

function toggleRotation(element) {
    if (element.classList.contains("not-rotated")) {
        // 요소가 회전하지 않은 상태라면 회전 상태로 변경
        element.classList.remove("not-rotated");
        element.classList.add("rotated");
    } else {
        // 요소가 회전 상태라면 회전하지 않은 상태로 변경
        element.classList.remove("rotated");
        element.classList.add("not-rotated");
    }
}

function showFavs() {
    const favsBox = document.querySelector(".coffeelist-column_2");
    let favClassList = favsBox.classList;
    if(favClassList.contains("hidden")){
        toggleRotation(showFavBtn);
        favsBox.classList.remove("hidden");
    }else{
        favsBox.classList.add("hidden");
        toggleRotation(showFavBtn);
    }
}



const clTitle = document.querySelector(".coffeelist-title");
clTitle.addEventListener("click",function(){
    window.location.href = "/coffeeList.do";
});

const delFavTogle = document.querySelector(".coffeelist-column_2__togle-fav-del");
delFavTogle.addEventListener("click",function(){
    const favtogle =document.querySelector(".fav-box");
    const deleteBtns = document.querySelectorAll(".fp-item__delete-btn");
    if (deleteBtns.length>0){        
        if(favtogle.classList.contains("showing-fav")){
            deleteBtns.forEach((btn) => {
                if(!btn.classList.contains("hidden")) {
                    btn.classList.add("hidden");
                }
            });
            favtogle.classList.remove("showing-fav");
        }else{
            deleteBtns.forEach((btn) => {
                if (btn.classList.contains("hidden")) {
                    btn.classList.remove("hidden");
                }
            });
            favtogle.classList.add("showing-fav");
        }
    }
});

