/*
계산기 기능이 필요없는 보여주기만 할 즐겨찾기 리스트
*/

// 즐겨찾기 목록 렌더링 함수 ReactDOM.render(<App data={data}/>,root);
if (isAuth){
    const root = document.querySelector(".fav-box");
    function App(props) {
        const fav = props.data;
        return(
            <>
                {Object.keys(fav).map(key => (
                <div key={key} className="fp-item" id="fi_1" value={"C_NO="+fav[key]["C_NO"]}>
                    <div className="fp-item__box clickable" onClick={hasCalc? doCalc:null}>
                        <img className="fp-item__img clickable" src={fav[key]["C_IMAGE"]}/>
                        <div className="fp-item__info clickable textlenth">{fav[key]["C_NAME"]}</div>
                    </div>
                    <button className="fp-item__delete-btn clickable hidden" onClick={deleteFavItem}>
                        <i className="fa-solid fa-minus"></i>  
                    </button>
                </div>
                ))}
            </>
        )
    }
    function render(data){
        ReactDOM.render(<App data={data}/>,root);
        addDelBtn();
    }

    //즐겨찾기 박스 선택
    const favItems = root.children;

    // 즐겨찾기 목록에 이벤트 리스너 추가 메서드
    function loadLisetener() {
        for (let i=0; i<favItems.length; i++) {
            favItems[i].querySelector(".fp-item__delete-btn").addEventListener("click",deleteFavItem);
        }
    }
    if(isAuth){
        loadLisetener();
    }

    //즐겨찾기 추가
    // **즐겨찾기에 커피 추가 버튼의 클래스리스트에 addFav-btn 필요
    // 커피목록 페이지에서는 script에 const hascoffees = true; 필요 커피목록 펭지가 아니면 false
    if (hasCoffees){
        const coffeeBox = document.querySelector(".coffee-box");
        const coffeeitem = coffeeBox.children;
        for(let i=0; i<coffeeitem.length; i++){
            let citem = coffeeitem[i].querySelector(".addFav-btn");
            if(citem != null && citem != undefined){
                citem.addEventListener("click",addFavItem);
            }
        }
        // **커피 요소 박스 value에 C_NO 필요
        function addFavItem(element) {
            const item = element.target.closest(".coffeelist-item");//커피리스트아이템
            const cno = item.getAttribute("value");
            fetch("/addfav?"+cno)
            .then(response => {
                return(response.json());
            })
            .then(data => {
                render(data);
            })
            .catch(error => {
                console.log("error",error);
            })
        }
    }


    //즐겨찾기 삭제
    function deleteFavItem(element) {
        element.stopPropagation();
        const item = element.target.closest(".fp-item");
        const cno = item.getAttribute("value");
        fetch("/delfav?"+cno)
        .then(response => {
            return(response.json());
        })
        .then(data => {
            render(data);
        })
        .catch(error => {
      })
    }
} //로그인상태에서만 동작함


function addDelBtn (){
    const favtogle =document.querySelector(".fav-box");
    const deleteBtns = document.querySelectorAll(".fp-item__delete-btn");
    if (deleteBtns.length>0){        
        if(favtogle.classList.contains("showing-fav")){
            deleteBtns.forEach((btn) => {
                if(btn.classList.contains("hidden")) {
                    btn.classList.remove("hidden");
                }
            });
        }else{
            deleteBtns.forEach((btn) => {
                if (!btn.classList.contains("hidden")) {
                    btn.classList.add("hidden");
                }
            });
        }
    }
}
/* 리액트 및 babel cdn
<script src="https://unpkg.com/react@17.0.2/umd/react.production.min.js"></script>
<script src="https://unpkg.com/react-dom@17.0.2/umd/react-dom.production.min.js"></script> 
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script> 
*/