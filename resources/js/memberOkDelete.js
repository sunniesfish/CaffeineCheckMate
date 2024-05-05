
		const countDownTimer = function (id, date) {
			var _vDate = new Date(date); // 전달 받은 일자
			// 시간 단위 변환을 위한 상수 정의
			var _second = 1000;
			var _minute = _second * 60;
			var _hour = _minute * 60;
			var _day = _hour * 24;
			var timer;
	
			function showRemaining() {
				var now = new Date(); //현재시간 변수설정
				var distDt = _vDate - now; // 남은시간 계산
	
				// 카운트 0 도달시 타이머 종료
				if (distDt < 0) {
					clearInterval(timer);
					// 종료 메시지 표시
					document.getElementById(id).textContent = '탈퇴가 완료 되었습니다!';
					return;
				}
				// Math 함수 이용 남은시간 계산
				var days = Math.floor(distDt / _day);
				var hours = Math.floor((distDt % _day) / _hour);
				var minutes = Math.floor((distDt % _hour) / _minute);
				var seconds = Math.floor((distDt % _minute) / _second);
	
				// 남은 시간 표시
				document.getElementById(id).textContent = days + '일 ';
				document.getElementById(id).textContent += hours + '시간 ';
				document.getElementById(id).textContent += minutes + '분 ';
				document.getElementById(id).textContent += seconds + '초';
			}
			 // 1초마다 showRemaining 함수 실행
			timer = setInterval(showRemaining, 1000);
		}
		var dday = document.getElementById("dday").value;
		var dateObj = new Date(); 
		dateObj.setDate(dateObj.getDate() + 7);
		countDownTimer('deleteTime', dday); // 월/일/년 시:초 am 형식 포맷으로 바꿔 넘김 