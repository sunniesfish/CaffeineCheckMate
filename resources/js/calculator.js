const container = document.querySelector(".calc-container");
const bar = new ProgressBar.SemiCircle(container, {
    strokeWidth: 6,
    color: '#FFEA82',
    trailColor: '#eee',
    trailWidth: 1,
    easing: 'easeInOut',
    duration: 1400,
    svgStyle: null,
    text: {
      value: '',
      alignToBottom: false
    },
    from: {color: '#FFEA82'},
    to: {color: '#ED6A5A'},
    // Set default step function for all animate calls
    step: (state, bar) => {
      bar.path.setAttribute('stroke', state.color);
      var value = Math.round(bar.value() * 100);
      if (value === 0) {
        bar.setText("0%");
      } else {
        bar.setText(value+"%");
      }
  
      bar.text.style.color = state.color;
    }
  });
  bar.text.style.fontFamily = '"Raleway", Helvetica, sans-serif';
  bar.text.style.fontSize = '2rem';
//   bar.animate(1.0);  // Number from 0.0 to 1.0


const dailyC = document.querySelector(".calc-box__dailyc"); //커피 섭취량
bar.animate(calcResult/400);



//계산
function calc(cno) {
    if(isAuth){//로그인
        if (cno !== null && cno !== undefined) {
            const calcurl = (cno) => {
                return("/calc?yn=0&"+cno);
            }
            fetch(calcurl(cno))
            .then(respons => {
                return (respons.json());
            })
            .then(data => {
                const caffeine = data.caffeine;
                const rda = data.rda;
                const ratio = caffeine/rda;
                console.log("cf:",caffeine,"  rda:",rda,"  ratio:",ratio);
                dailyC.innerText = `${caffeine} mg`;
                bar.animate(ratio);
            })
            .catch(error => {
                console.log("error",error);
            })
        } else {
            console.log("cno is null");
        }
    }else{//비로그인
        let caf = localStorage.getItem("caf");
        let caffeine = caf? parseInt(caf.toString()) : 0;
        caffeine += parseInt(cno);
        localStorage.setItem("caf",caffeine);
        dailyC.innerText = `${caffeine} mg`;
        bar.animate(parseInt(caffeine)/400);
    }

    dailyC.classList.add("bold");
    setTimeout(() => {
        dailyC.classList.remove("bold");
    }, 300);
}

//리셋
function resetCalc(event) {
    if(isAuth){//로그인
        const reseturl = () => {
            return ("/calc?yn=1");
        }
        fetch(reseturl())
        .then(response => {
            return (response.json());
        })
        .then(data => {
            const caffeine = data.caffeine;
            const rda = data.rda;
            const ratio = caffeine/rda;
            console.log("cf:",caffeine,"  rda:",rda,"  ratio:",ratio);
            dailyC.innerText = `${caffeine} mg`;
            bar.animate(ratio);
        })
        .catch(error => {
            console.log("error",error);
        })
    }else{//비로그인
        localStorage.setItem("caf",0);
        dailyC.innerText = `0 mg`;
        bar.animate(0);
    }
    dailyC.classList.add("bold");
    setTimeout(() => {
        dailyC.classList.remove("bold");
    }, 300);
}

//리셋버튼
const cResetBtn = document.querySelector("#calc-box__btn-reset");
cResetBtn.addEventListener("click", resetCalc);


// 즐겨찾기 목록에 이벤트 리스너 추가
if(isAuth){
    const favB = document.querySelector(".fav-box");
    const favI = favB.children;
    for (let i=0; i<favI.length; i++) {
        favI[i].querySelector(".fp-item__box").addEventListener("click",doCalc);
    }
}else {
    let caf = localStorage.getItem("caf");
    let caffeine = caf? parseInt(caf.toString()) : 0;
    localStorage.setItem("caf",caffeine);
    bar.animate(parseInt(caffeine)/400);
    dailyC.innerText = `${caffeine} mg`;
    const popB = document.querySelector(".popular-box");
    const popI = popB.children;
    for (let i=0; i<popI.length; i++) {
        popI[i].querySelector(".fp-item__box").addEventListener("click",doCalc);
    }
}


//계산기 기능
function doCalc(event) {
  calc(event.target.closest(".fp-item").getAttribute("value"));
}