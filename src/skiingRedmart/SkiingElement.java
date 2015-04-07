package skiingRedmart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class SkiingElement {
	int value;
	int maxLen;
	int lastElementElevation;
	SkiingElement nextElement;
	List<SkiingElement> adjElements;
	boolean isVisited;
	
	public SkiingElement(int value) {
		this.value = value;
		this.isVisited = false;
		this.maxLen = 0;
		this.lastElementElevation = value;
		adjElements =  new ArrayList<>();
	}
	
}
