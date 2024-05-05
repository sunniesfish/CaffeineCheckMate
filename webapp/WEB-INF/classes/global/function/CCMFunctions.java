package global.function;

public class CCMFunctions {

	public static String ColorFn(int caffeine, double weight) {
		String color;
		double ratio = weight>0? caffeine/(weight*6.4) : caffeine/400;
		if(ratio>1) {
			color = "#180607"; //검정
		} else if (ratio>0.8) {
			color = "#FF0000"; //빨강
		} else if (ratio>0.6) {
			color = "#FE4001"; //약간 연한 빨강
		} else if (ratio>0.5) {
			color = "#FF8000"; //주황
		} else if (ratio>0.4) {
			color = "#FF8000"; //노랑
		} else if (ratio>0.3) {
			color = "#BFFE01"; //밝은 연두
		} else if (ratio>0.2) {
			color = "#BFFE01"; //연두
		} else if (ratio>0.1) {
			color = "#BFFE01"; //밝은 초록
		} else {
			color = "#BFFE01"; //파랑
		}
		return color;
	}
}
