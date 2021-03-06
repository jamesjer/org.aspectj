// Exploring synchronization

aspect WithinAspect {
	before(Object o ): within(Basic4) && args(o) {
		if (thisJoinPoint.getSignature().toString().indexOf("lock(")!=-1) 
			System.err.println("Advice running at "+thisJoinPoint.getSignature()+
								" with args of type "+o.getClass()+" with value "+o);
	}
}

public class Basic4 {
	public static void main(String[] args) {
		Basic4 b = new Basic4();
		
		b.methodWithSyncBlock1();
		b.staticMethodWithSyncBlock1();
		b.methodWithSyncBlock2();
		b.staticMethodWithSyncBlock2();
	}
	
	public void methodWithSyncBlock1() {
		System.err.println("methodWithSyncBlock1");
		synchronized (this) {
		}
	}

	public void staticMethodWithSyncBlock1() {
		System.err.println("staticMethodWithSyncBlock1");
		synchronized (Basic4.class) {
		}
	}
	
	public void methodWithSyncBlock2() {
		System.err.println("methodWithSyncBlock2");
		synchronized (this) {
			int i = 0;
			while (i<100) {
				i++;
			}
		}
	}

	public void staticMethodWithSyncBlock2() {
		System.err.println("staticMethodWithSyncBlock2");
		synchronized (Basic4.class) {
			int i = 0;
			while (i<100) {
				i++;
			}
		}
	}
}
