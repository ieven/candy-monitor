package org.matcha.server.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DefaultServerSocketFactory implements ServerSocketFactory{

	@Override
	public ServerSocket createSocket(int port) throws IOException,
			InstantiationException {
		// TODO Auto-generated method stub
		return  new ServerSocket (port);
	}

	@Override
	public ServerSocket createSocket(int port, int backlog) throws IOException,
			InstantiationException {
		// TODO Auto-generated method stub
		return new ServerSocket (port, backlog);
	}

	@Override
	public ServerSocket createSocket(int port, int backlog,
			InetAddress ifAddress) throws IOException, InstantiationException {
		// TODO Auto-generated method stub
		return new ServerSocket (port, backlog, ifAddress);
	}

	@Override
	public Socket acceptSocket(ServerSocket socket) throws IOException {
		// TODO Auto-generated method stub
		return socket.accept();
	}

	@Override
	public void handshake(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		//默认的不做任何操作
	}

}
