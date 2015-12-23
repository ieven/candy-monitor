package org.matcha.server.net;

/**
 * socket封装类
 * @author lichong
 *
 * @param <E>
 */
public class SocketWrapper<E> {

	/**
	 * 是否需要即时处理
	 */
	protected boolean immediateProcessing;
	
    protected volatile E socket;
    
    //日后添加更多需要封装的数据
    
    public SocketWrapper(E socket) {
        this(socket, false);
    }
    
    public SocketWrapper(E socket,boolean immediateProcessing)
    {
    	this.socket = socket;
    	this.immediateProcessing = immediateProcessing;
    }
    
    public E getSocket() {
        return socket;
    }

	public boolean isImmediateProcessing() {
		return immediateProcessing;
	}
    
}