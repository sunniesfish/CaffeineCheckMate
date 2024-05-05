// 아이디 중복체크
  var idChecked = false;
  var nickChecked = false;
  var emailChecked = false;
				  
	  function checkId(){
		  if(document.joinForm.joinId.value==""){
			  alert("아이디를 입력해주세요");
			  document.joinForm.joinId.focus();
			  return false;
		  }
	  var url = "CheckHandler.do?joinId=" + document.joinForm.joinId.value;		
	  window.open(url,"_blank_1","width=500,height=300, toolbar=no,menubar=no,resizble=no,scrollbars=yes")

  }
	  function setIdValue(id, isUnique) {
		    if (isUnique) {
		        document.joinForm.joinId.value = id;
		        // 중복 확인 완료 플래그 설정
		        window.idChecked = true;
		    }
		}
// 생년월일 셀렉터
	// 출생 연도 셀렉트 박스 option 목록 동적 생성
	const birthYearEl = document.querySelector('#birth-year');
	const birthMonthEl = document.querySelector('#birth-month');
	const birthDayEl = document.querySelector('#birth-day');

	// option 목록 생성 여부 확인
	let isYearOptionExisted = false;
	let isMonthOptionExisted = false;
	let isDayOptionExisted = false;

		birthYearEl.addEventListener('focus', function () {
		  // year 목록 생성되지 않았을 때 (최초 클릭 시)
		  if (!isYearOptionExisted) {
		    isYearOptionExisted = true;
		    for (let i = 1940; i <= 2022; i++) {
		      // option element 생성
		      const yearOption = document.createElement('option');
		      yearOption.setAttribute('value', i);
		      yearOption.innerText = i;
		      // birthYearEl의 자식 요소로 추가
		      this.appendChild(yearOption);
		    }
		  }
		});

		birthMonthEl.addEventListener('focus', function () {
		  // month 목록 생성되지 않았을 때 (최초 클릭 시)
		  if (!isMonthOptionExisted) {
		    isMonthOptionExisted = true;
		    for (let i = 1; i <= 12; i++) {
		      // option element 생성
		      const monthOption = document.createElement('option');
		      monthOption.setAttribute('value', i);
		      monthOption.innerText = i;
		      // birthMonthEl의 자식 요소로 추가
		      this.appendChild(monthOption);
		    }
		  }
		});
		birthDayEl.addEventListener('focus', function () {
		  // day 목록 생성되지 않았을 때 (최초 클릭 시)
		  if (!isDayOptionExisted) {
		    isDayOptionExisted = true;
		    for (let i = 1; i <= 31; i++) {
		      // option element 생성
		      const dayOption = document.createElement('option');
		      dayOption.setAttribute('value', i);
		      dayOption.innerText = i;
		      // birthDayEl의 자식 요소로 추가
		      this.appendChild(dayOption);
		    }
		  }
		});
//메일인증
        function joinEmail() {
            var url = "/views/screens/joinEmail.jsp";
            window.open(url, "_blank_1", "width=500,height=300,toolbar=no,menubar=no,resizable=no,scrollbars=yes");
            window.emailChecked = true;
        }
//닉네임중복체크
	  function checkNick(){
		  if(document.joinForm.joinNick.value==""){
			  alert("닉네임을 입력해주세요");
			  document.joinForm.joinNick.focus();
			  return false;
		  }
		  	    var url = "CheckHandler.do?joinNick=" + encodeURIComponent(document.joinForm.joinNick.value);
		  	    window.open(url,"_blank","width=500,height=300,toolbar=no,menubar=no,resizable=no,scrollbars=yes");
	  }
	  function setNickValue(nickValue, isUnique) {
		    if (isUnique) {
		        document.joinForm.joinNick.value = nickValue;
		        // 중복 확인 완료 플래그 설정
		        window.nickChecked = true;
		    }
		}		
//모든 중복체크 및 이메일인증 완료 플래그
 		function validateForm() {
		    if (!idChecked || !nickChecked || !emailChecked) {
		        alert("중복체크와 이메일인증을 모두 완료해주세요.");
		        return false;
		    }
		    return true;
		}		