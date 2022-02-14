package com.ijpay.core.kit.ssl;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * SslSocket
 * @Description
 * @Copyright Copyright (c) 2019
 * @author xyb
 * @Date 2019年12月18日 上午10:10:23
 *
 */
public class SslSocket extends SSLSocketFactory {

	private final SSLSocketFactory factory;

	private final String[] versions = new String[] { "TLSv1.2", "TLSv1.1", "TLSv1" };

	public SslSocket(SSLSocketFactory factory) {
		this.factory = factory;
	}

	@Override
	public String[] getDefaultCipherSuites() {
		return factory.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return factory.getSupportedCipherSuites();
	}

	@Override
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException {
		final Socket socketn = factory.createSocket(socket, host, port, autoClose);
		return protocol(socketn);
	}


	@Override
	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		final Socket socketn = factory.createSocket(host, port);
		return protocol(socketn);
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		final Socket socketn = factory.createSocket(host, port);
		return protocol(socketn);
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localAddress, int localPort) throws IOException, UnknownHostException {
		final Socket socketn = factory.createSocket(host, port, localAddress, localPort);
		return protocol(socketn);
	}

	@Override
	public Socket createSocket(InetAddress host, int port, InetAddress localAddress, int localPort) throws IOException {
		final Socket socketn = factory.createSocket(host, port, localAddress, localPort);
		return protocol(socketn);
	}

	private Socket protocol(final Socket socket) {
		if (!(socket instanceof SSLSocket)) {
			throw new RuntimeException("An instance of SSLSocket is expected");
		}
		((SSLSocket) socket).setEnabledProtocols(versions);
		return socket;
	}

}
